package me.bruce.factions.faction.args;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LorexMC.us.utils.other.chat.ClickAction;
import LorexMC.us.utils.other.chat.Text;
import LorexMC.us.utils.other.command.CommandArgument;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.struct.Relation;
import me.bruce.factions.faction.struct.Role;
import me.bruce.factions.faction.type.Faction;
import me.bruce.factions.faction.type.PlayerFaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * FactionListener argument used to invite players into {@link FactionListener}s.
 */
public class FactionInviteArgument extends CommandArgument {

	private static final Pattern USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9_]{2,16}$");

	private final LorexHCF plugin;

	public FactionInviteArgument(LorexHCF plugin) {
		super("invite", "Invite a player to the faction.");
		this.plugin = plugin;
		this.aliases = new String[] { "inv", "invitemember", "inviteplayer" };
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + " <playerName>";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex �8� " + ChatColor.RED + "Only players can invite to a faction.");
			return true;
		}

		if (args.length < 2) {
			sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex �8� " +ChatColor.RED + "Usage: " + getUsage(label));
			return true;
		}

		if (!USERNAME_REGEX.matcher(args[1]).matches()) {
			sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex �8� " +ChatColor.RED + "'" + args[1] + "' is an invalid username.");
			return true;
		}

		Player player = (Player) sender;
		PlayerFaction playerFaction = plugin.getFactionManager().getPlayerFaction(player);

		if (playerFaction == null) {
			sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex �8� " +ChatColor.RED + "You are not in a faction.");
			return true;
		}

		if (playerFaction.getMember(player.getUniqueId()).getRole() == Role.MEMBER) {
			sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex �8� " +ChatColor.RED + "You must a faction officer to invite members.");
			return true;
		}

		Set<String> invitedPlayerNames = playerFaction.getInvitedPlayerNames();
		String name = args[1];

		if (playerFaction.getMember(name) != null) {
			sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex �8� " +ChatColor.RED + "'" + name + "' is already in your faction.");
			return true;
		}

		if (!plugin.getEotwHandler().isEndOfTheWorld() && playerFaction.isRaidable()) {
			sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex �8� " +ChatColor.RED + "You may not invite players whilst your faction is raidable.");
			return true;
		}

		if (!invitedPlayerNames.add(name)) {
			sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex �8� " +ChatColor.RED + name + " has already been invited.");
			return true;
		}

		Player target = Bukkit.getPlayer(name);
		if (target != null) {
			name = target.getName(); // fix casing.
			Text text = new Text(sender.getName()).setColor(Relation.ENEMY.toChatColour())
					.append(new Text(" has invited you to join ").setColor(ChatColor.YELLOW));
			text.append(new Text(playerFaction.getName()).setColor(Relation.ENEMY.toChatColour()))
					.append(new Text(". ").setColor(ChatColor.YELLOW));
			text.append(new Text("Click here").setColor(ChatColor.GREEN)
					.setClick(ClickAction.RUN_COMMAND, '/' + label + " accept " + playerFaction.getName())
					.setHoverText(ChatColor.AQUA + "Click to join " + playerFaction.getDisplayName(target)
							+ ChatColor.AQUA + '.'))
					.append(new Text(" to accept this invitation.").setColor(ChatColor.YELLOW));
			text.send(target);
		}

		playerFaction.broadcast(Relation.MEMBER.toChatColour() + sender.getName() + ChatColor.YELLOW + " has invited "
				+ Relation.ENEMY.toChatColour() + name + ChatColor.YELLOW + " into the faction.");
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (args.length != 2 || !(sender instanceof Player)) {
			return Collections.emptyList();
		}

		Player player = (Player) sender;
		PlayerFaction playerFaction = plugin.getFactionManager().getPlayerFaction(player);
		if (playerFaction == null || (playerFaction.getMember(player.getUniqueId()).getRole() == Role.MEMBER)) {
			return Collections.emptyList();
		}

		List<String> results = new ArrayList<>();
		for (Player target : Bukkit.getOnlinePlayers()) {
			if (player.canSee(target) && !results.contains(target.getName())) {
				if (playerFaction != plugin.getFactionManager().getPlayerFaction(target.getUniqueId())) {
					results.add(target.getName());
				}
			}
		}

		return results;
	}
}
