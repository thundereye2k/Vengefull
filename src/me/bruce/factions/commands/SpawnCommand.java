package me.bruce.factions.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.ymls.SettingsYML;
import net.md_5.bungee.api.ChatColor;

public class SpawnCommand implements CommandExecutor, Listener {



	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Cannot use this command in console.");
			return true;
		}
		Player p = (Player) sender;
		if(!p.hasPermission("staff.spawn")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» §cTravel to 0.0"));
			return true;
		}
		else {
			if(SettingsYML.KIT_MAP == true) {
					p.teleport(new Location(Bukkit.getWorld("World"), 0,80,-1));
				}
			}
			if(SettingsYML.KIT_MAP == false) {
					p.teleport(new Location(Bukkit.getWorld("World"), 0,73,-1));
				}
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &eYou Have teleported to spawn."));
		return true;
		}
	}

