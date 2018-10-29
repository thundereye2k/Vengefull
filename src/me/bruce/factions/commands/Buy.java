package me.bruce.factions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Buy implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if(!(sender instanceof Player)) {
    sender.sendMessage("Cannot use this command in console");
    return true;
    }
    sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.GRAY + " www.zorex.us");
    return true;
	}
}


