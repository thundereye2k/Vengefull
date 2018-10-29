package me.bruceboy.framework.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String  label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Cannot use in console.");
			return true;
		}
		Player p = (Player) sender;
		if(args.length == 0) {
			if(!p.isFlying()) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7You Have enabled Flight"));
			p.setAllowFlight(true);
			p.setFlying(true);
			return true;
		}
			else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7You Have Disabled Flight"));
				p.setAllowFlight(false);
				p.setFlying(false);
				return true;
				
			}
		}
		return false;
	}
}


