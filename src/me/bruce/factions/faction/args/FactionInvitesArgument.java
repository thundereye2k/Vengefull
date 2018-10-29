package me.bruce.factions.faction.args;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LorexMC.us.utils.other.command.CommandArgument;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.type.Faction;
import me.bruce.factions.faction.type.PlayerFaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * FactionListener argument used to check invites for {@link FactionListener}s.
 */
public class FactionInvitesArgument extends CommandArgument {

	private final LorexHCF plugin;

	public FactionInvitesArgument(LorexHCF plugin) {
		super("invites", "View faction invitations.");
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can have faction invites.");
			return true;
		}

		List<String> receivedInvites = new ArrayList<>();
		for (Faction faction : plugin.getFactionManager().getFactions()) {
			if (faction instanceof PlayerFaction) {
				PlayerFaction targetPlayerFaction = (PlayerFaction) faction;
				if (targetPlayerFaction.getInvitedPlayerNames().contains(sender.getName())) {
					receivedInvites.add(targetPlayerFaction.getDisplayName(sender));
				}
			}
		}

		PlayerFaction playerFaction = plugin.getFactionManager().getPlayerFaction((Player) sender);
		String delimiter = ChatColor.WHITE + ", " + ChatColor.GRAY;

		if (playerFaction != null) {
			Set<String> sentInvites = playerFaction.getInvitedPlayerNames();
			sender.sendMessage(ChatColor.AQUA + "Sent by " + playerFaction.getDisplayName(sender) + ChatColor.AQUA
					+ " (" + sentInvites.size() + ')' + ChatColor.DARK_AQUA + ": " + ChatColor.GRAY
					+ (sentInvites.isEmpty() ? "Your faction has not invited anyone."
							: StringUtils.join(sentInvites, delimiter) + '.'));
		}

		sender.sendMessage(ChatColor.AQUA + "Requested (" + receivedInvites.size() + ')' + ChatColor.DARK_AQUA + ": "
				+ ChatColor.GRAY + (receivedInvites.isEmpty() ? "No factions have invited you."
						: StringUtils.join(receivedInvites, ChatColor.WHITE + delimiter) + '.'));

		return true;
	}
}
