package me.bruce.factions.koth.args;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import LorexMC.us.utils.other.command.CommandArgument;
import me.bruce.factions.koth.KothExecutor;

/**
 * An {@link CommandArgument} used for viewing help about KingOfTheHill games.
 */
public class KothHelpArgument extends CommandArgument {

	private final KothExecutor kothExecutor;

	public KothHelpArgument(KothExecutor kothExecutor) {
		super("help", "View help about how KOTH's work");
		this.kothExecutor = kothExecutor;
		this.permission = "hcf.command.koth.argument." + getName();
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(ChatColor.AQUA + "�m--------------------------------------------");
		sender.sendMessage(ChatColor.AQUA + "KoTH Help");
		for (CommandArgument argument : kothExecutor.getArguments()) {
			if (argument != this) {
				String permission = argument.getPermission();
				if (permission == null || sender.hasPermission(permission)) {
					sender.sendMessage(
							ChatColor.AQUA + argument.getUsage(label) + " � " + argument.getDescription() + '.');
				}
			}
		}

		sender.sendMessage(ChatColor.AQUA + "/f show <kothName> - View information about a KOTH.");
		sender.sendMessage(ChatColor.AQUA + "�m--------------------------------------------");
		return true;
	}
}
