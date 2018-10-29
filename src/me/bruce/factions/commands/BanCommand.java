package me.bruce.factions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		if(args.length == 0) {
			sender.sendMessage("usage /ban <player>");
			return true;
		}
		Player target = Bukkit.getPlayer(args[0]);
		Player p = (Player) sender;
		if(target == null) {
			sender.sendMessage("ERROR");
			return true;
		}
		target.kickPlayer(ChatColor.RED + "you have beened banned");
		target.setBanned(true);
		return true;
	}
	
	

}
