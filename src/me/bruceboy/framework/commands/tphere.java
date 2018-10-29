package me.bruceboy.framework.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tphere implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String  label, String[] args) {
		Player p = (Player) sender;
		if(!(sender instanceof Player)) {
			sender.sendMessage("Cannot use in console.");
			return true;
		}
		if(args.length == 0) {
			p.sendMessage("§cPlease specify a player.");
			return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null) {
				p.sendMessage("§cPlayer is not online.");
				return true;
			}			
			target.teleport(p);
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7You have teleported  " + target.getName()) + " to you.");
		
			return true;
	}
}
