package me.bruceboy.framework.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String  label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Cannot use in console.");
			return true;
		}
		if(args.length == 0) {
			Player p = (Player) sender;
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m--------------------------------------------"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7There are currently &b" + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + " &7Players Online."));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m--------------------------------------------"));
				return true;
			}
		return false;
	}

}



