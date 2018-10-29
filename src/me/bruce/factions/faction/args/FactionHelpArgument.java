package me.bruce.factions.faction.args;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import LorexMC.us.utils.JavaUtils;
import LorexMC.us.utils.other.command.CommandArgument;
import me.bruce.factions.faction.FactionExecutor;
import me.bruce.factions.faction.type.Faction;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * FactionListener argument used to show help on how to use {@link FactionListener}s.
 */
public class FactionHelpArgument extends CommandArgument {

	private static final int HELP_PER_PAGE = 8;

	private ImmutableMultimap<Integer, String> pages;
	private final FactionExecutor executor;

	public FactionHelpArgument(FactionExecutor executor) {
		super("help", "View help on how to use factions.");
		this.executor = executor;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 2) {
			showPage(sender, label, 1);
			return true;
		}

		Integer page = JavaUtils.tryParseInt(args[1]);

		if (page == null) {
			sender.sendMessage(ChatColor.RED + "'" + args[1] + "' is not a valid number.");
			return true;
		}

		showPage(sender, label, page);
		return true;
	}

	private void showPage(CommandSender sender, String label, int pageNumber) {
		// Create the multimap.
		if (pages == null) {
			boolean isPlayer = sender instanceof Player;
			int val = 1;
			int count = 0;
			Multimap<Integer, String> pages = ArrayListMultimap.create();
			for (CommandArgument argument : executor.getArguments()) {
				if (argument == this)
					continue;

				// Check the permission and if the player can access.
				String permission = argument.getPermission();
				if (permission != null && !sender.hasPermission(permission))
					continue;
				if (argument.isPlayerOnly() && !isPlayer)
					continue;

				count++;
				pages.get(val).add(ChatColor.GRAY + "§6/" + label + ' ' + argument.getName() + " §7» " + ChatColor.WHITE
						+ argument.getDescription());
				if (count % HELP_PER_PAGE == 0) {
					val++;
				}
			}

			// Finally assign it.
			this.pages = ImmutableMultimap.copyOf(pages);
		}

		int totalPageCount = (pages.size() / HELP_PER_PAGE) + 1;

		if (pageNumber < 1) {
			sender.sendMessage(ChatColor.RED + "You cannot view a page less than 1.");
			return;
		}

		if (pageNumber > totalPageCount) {
			sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.RED + "there is only "
					+ totalPageCount + " pages.");
			return;
		}

		sender.sendMessage(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "----------------------------------------------------");
		sender.sendMessage(ChatColor.BLUE + ChatColor.BOLD.toString() + "Faction Help §7- " + ChatColor.YELLOW + ChatColor.BOLD.toString() + "Faction Help");
		sender.sendMessage(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString()	+ "----------------------------------------------------");
		sender.sendMessage(ChatColor.BLUE + "General Commands:");
		sender.sendMessage(ChatColor.YELLOW + "/f create <factionName> §7- Create a new faction.");
		sender.sendMessage(ChatColor.YELLOW + "/f accept <factionName> §7- Accept a pending invitation.");
		sender.sendMessage(ChatColor.YELLOW + "/f leave  §7- Leave your current faction.");
		sender.sendMessage(ChatColor.YELLOW + "/f home  §7- Teleport to your faction home.");
		sender.sendMessage(ChatColor.YELLOW + "/f stuck  §7- Teleport out of enemy territory.");
		sender.sendMessage(ChatColor.YELLOW + "/f deposit <amount:all> §7- Deposit money into your faction balance.");
		sender.sendMessage("");
		sender.sendMessage(ChatColor.BLUE + "Information Commands:");
		sender.sendMessage(ChatColor.YELLOW + "/f show <factionName> §7- Display a faction information.");
		sender.sendMessage(ChatColor.YELLOW + "/f map <factionName> §7- Show nearby claims.");
		sender.sendMessage(ChatColor.YELLOW + "/f claims  §7- View all claims for a faction.");
		sender.sendMessage(ChatColor.YELLOW + "/f list  §7- Show list of factions online.");
		sender.sendMessage("");
		sender.sendMessage(ChatColor.BLUE + "Captain Commands:");
		sender.sendMessage(ChatColor.YELLOW + "/f invite <player> §7- Invite a player to your faction.");
		sender.sendMessage(ChatColor.YELLOW + "/f uninvite <player:all> §7- Revoke an invatation.");
		sender.sendMessage(ChatColor.YELLOW + "/f invites  §7- List all open invitations.");
		sender.sendMessage(ChatColor.YELLOW + "/f kick  <player> §7-kick a player from your faction.");
		sender.sendMessage(ChatColor.YELLOW + "/f claim  §7- Start a claim for your faction.");
		sender.sendMessage(ChatColor.YELLOW + "/f unclaim  §7- Unclaim land.");
		sender.sendMessage(ChatColor.YELLOW + "/f sethome  §7- Set your faction's home at your current location");
		sender.sendMessage(ChatColor.YELLOW + "/f withdraw <amount:all> §7- Withdray money from your faction's balance.");
		sender.sendMessage(ChatColor.YELLOW + "/f annoucement <message here> §7- Set your faction's annoucement.");
		sender.sendMessage(ChatColor.YELLOW + "/f rename  §7- Rename your faction.");
		sender.sendMessage("");
		sender.sendMessage(ChatColor.BLUE + "Leader Commands:");
		sender.sendMessage(ChatColor.YELLOW + "/f leader  §7- Sets the new leader for your faction.");
		sender.sendMessage(ChatColor.YELLOW + "/f coleader <player> §7- Promotes a player to a co-leader.");
		sender.sendMessage(ChatColor.YELLOW + "/f promote  <player> §7- Promotes a player to a captain.");
		sender.sendMessage(ChatColor.YELLOW + "/f disband  §7- Disband your faction.");	
		sender.sendMessage(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString()	+ "----------------------------------------------------");
	}
}
