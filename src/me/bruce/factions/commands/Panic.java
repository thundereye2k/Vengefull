package me.bruce.factions.commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Panic implements CommandExecutor{
	ArrayList<UUID> panic = new ArrayList<UUID>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Cannot use in console...");
		}
		Player p = (Player) sender;
			for(Player staff : Bukkit.getOnlinePlayers()) {
				if(!panic.contains(p.getUniqueId())) {
					p.sendMessage(ChatColor.RED + "You Have used your panic!");
					staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&3&lPANIC&8] " + ChatColor.RED + sender.getName() + " &7Has used there panic "));
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ss " + sender.getName());
					panic.add(p.getUniqueId());
				}
				else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&3&lPANIC&8] " +  " &7You have unpaniced. "));
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ss " + p.getName());
					panic.remove(p.getUniqueId());
				}
			}	
		return true;
	}
}
	
	

