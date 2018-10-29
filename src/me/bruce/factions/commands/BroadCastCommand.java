package me.bruce.factions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadCastCommand implements CommandExecutor{
	
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("broadcast")) {
            String r = "";
            if (sender.hasPermission("command.broadcast")) {
                if (args.length > 0) {
                    for (int i = 0; i < args.length; ++i) {
                        r = String.valueOf(r) + args[i] + " ";
                    }
                    r = r.replace("&", "§");
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&4Alert&8]&f " + r));
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Usage: /broadcast <message>");
                }
            }
            else {
                sender.sendMessage("§cYou lack the sufficient permissions to execute this command.");
            }
        }
        return true;
    }
}



