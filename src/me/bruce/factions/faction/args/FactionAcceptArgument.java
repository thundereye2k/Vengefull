package me.bruce.factions.faction.args;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LorexMC.us.utils.other.command.CommandArgument;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.FactionMember;
import me.bruce.factions.faction.struct.ChatChannel;
import me.bruce.factions.faction.struct.Relation;
import me.bruce.factions.faction.struct.Role;
import me.bruce.factions.faction.type.Faction;
import me.bruce.factions.faction.type.PlayerFaction;
import me.bruce.factions.ymls.SettingsYML;

/**
 * FactionListener argument used to accept invitations from {@link FactionListener}s.
 */
public class FactionAcceptArgument extends CommandArgument {

	private final LorexHCF plugin;

	public FactionAcceptArgument(LorexHCF plugin) {
		super("accept", "Accept a join request from an existing faction.", new String[] { "join", "a" });
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + " <factionName>";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command is only executable by players.");
			return true;
		}

		if (args.length < 2) {
			sender.sendMessage(ChatColor.RED + "Usage: " + getUsage(label));
			return true;
		}

		Player player = (Player) sender;

		if (plugin.getFactionManager().getPlayerFaction(player) != null) {
			sender.sendMessage(ChatColor.RED + "You are already in a faction.");
			return true;
		}

		Faction faction = plugin.getFactionManager().getContainingFaction(args[1]);

		if (faction == null) {
			sender.sendMessage(
					ChatColor.RED + "FactionListener named or containing member with IGN or UUID " + args[1] + " not found.");
			return true;
		}

		if (!(faction instanceof PlayerFaction)) {
			sender.sendMessage(ChatColor.RED + "You can only join player factions.");
			return true;
		}

		PlayerFaction targetFaction = (PlayerFaction) faction;

		if (targetFaction.getMembers().size() >= SettingsYML.MAX_MEMBERS_PER_FACTION) {
			sender.sendMessage(faction.getDisplayName(sender) + ChatColor.RED + " is full. FactionListener limits are at "
					+ SettingsYML.MAX_MEMBERS_PER_FACTION + '.');
			return true;
		}

		if (!targetFaction.isOpen() && !targetFaction.getInvitedPlayerNames().contains(player.getName())) {
			sender.sendMessage(
					ChatColor.RED + faction.getDisplayName(sender) + ChatColor.RED + " has not invited you.");
			return true;
		}

		if (targetFaction.addMember(player, player, player.getUniqueId(),
				new FactionMember(player, ChatChannel.PUBLIC, Role.MEMBER))) {
			targetFaction.broadcast(
					Relation.MEMBER.toChatColour() + sender.getName() + ChatColor.YELLOW + " has joined the faction.");
		}

		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (args.length != 2 || !(sender instanceof Player)) {
			return Collections.emptyList();
		}

		return plugin.getFactionManager().getFactions().stream()
				.filter(faction -> faction instanceof PlayerFaction
						&& ((PlayerFaction) faction).getInvitedPlayerNames().contains(sender.getName()))
				.map(faction -> sender.getName()).collect(Collectors.toList());
	}
}
