package me.bruce.factions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.bruce.factions.ymls.SettingsYML;

import java.util.Collections;
import java.util.List;

/**
 *
 */
public class HelpCommand implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
		if (!(p instanceof Player)) {
			p.sendMessage(ChatColor.RED + "This command is only executable by players.");
			return true;
		}
		if(SettingsYML.KIT_MAP == true) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-----------------------------------------------------"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &C(KitMap)"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-----------------------------------------------------"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&LInformation"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eWorld Border &71500 Blocks"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eWarzone Radius &7150 Blocks"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eKits &7Diamond, Bard, Archer, Builder"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eEnd Exit &c( 150 ) South Road"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&LUseful Commands"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &e/helpop &7Request staff assistance"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &e/coords &7View KOTH, Conquest and Mountain locations"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &e/mapkit &7Get the kit of the map"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&LLinks"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eWebsite &7www.zorex.us"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eTeamspeak &7ts.zorex.us"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eStore &7store.zorex.us"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-----------------------------------------------------"));	
	        return true;
		}
		else {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-----------------------------------------------------"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lTribes &C(Map 1)"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-----------------------------------------------------"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&LInformation"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eWorld Border &73000 Blocks"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eWarzone Radius &7800 Blocks"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eKits &7Diamond, Bard, Archer, Builder"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eEnd Exit &c( 150 ) South Road"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&LUseful Commands"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &e/helpop &7Request staff assistance"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &e/coords &7View KOTH, Conquest and Mountain locations"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &e/mapkit &7Get the kit of the map"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&LLinks"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eWebsite &7www.zorex.us"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eTeamspeak &7ts.zorex.us"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6* &eStore &7store.zorex.us"));
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-----------------------------------------------------"));	
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return Collections.emptyList();
	}
}