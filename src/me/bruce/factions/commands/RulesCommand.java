package me.bruce.factions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RulesCommand implements CommandExecutor{
	
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&C&LSERVER RULES "));
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4»  &cNo racism / excessive insults"));
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4» &CSpamming is a kickable offense"));
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4» &cAll server members, are treated the same (Staff, Donators, Players)"));
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4»  &CStaff cannot punish higher staff members [Only applied in game]"));
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4»  &CDDoSing / Doxing Threats, are a bannable offense on both the Discord and the Server."));
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4» &cGeneral threats are a bannable offense on both the Discord and the Server."));
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4»  &cSuicidal jokes are a kickable offense on the Discord Server."));
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4» &CNo screamers or IP loggers allowed. (Will result in a blacklist ingame & discord)"));
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4» &cNo advertising other servers."));
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4» &c No voice changers / trolling staff."));
    	return true;
    }

}
