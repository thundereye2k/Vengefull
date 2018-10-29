package me.bruceboy.framework.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can do this.");
            return true;
        }
		if(cmd.getName().equalsIgnoreCase("gamemode")) {
				if(args.length == 0) {
					p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GRAY + "/" + cmd.getName() + " [gamemode]");
				} else {
					if((args[0].equalsIgnoreCase("survival"))
							|| (args[0].equalsIgnoreCase("s")) 
							|| (args[0].equalsIgnoreCase("0")) 
							|| (args[0].equalsIgnoreCase("one"))) {
						if(args.length == 1) {
							p.setGameMode(GameMode.SURVIVAL);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &cYour gamemode was set to survival"));
						}
					}
					if((args[0].equalsIgnoreCase("creative"))
							|| (args[0].equalsIgnoreCase("c")) 
							|| (args[0].equalsIgnoreCase("1")) 
							|| (args[0].equalsIgnoreCase("one"))) {
						if(args.length == 1) {
							p.setGameMode(GameMode.CREATIVE);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &cYour gamemode was set to Creative"));
						}
					}
				}
									
			}
		
		return false;
	}
}