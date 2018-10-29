package me.bruce.factions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffInformation implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("staffguide")) {
			if (p.hasPermission("zorex.staffguide")) {
				if (args.length == 0) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&8&m------------------------------------------- "));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» Staff Guide"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&8&m------------------------------------------- "));
				} else {
					p.sendMessage(ChatColor.RED + "You Do Not Have Permission To Use this command");
				}
			}
		}
		return false;
	}
}