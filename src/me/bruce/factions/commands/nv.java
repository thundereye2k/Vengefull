package me.bruce.factions.commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class nv implements CommandExecutor{
	
	 ArrayList<UUID> sp = new ArrayList<UUID>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command is only executable by players.");
			return true;
		}
		Player p = (Player) sender;
		if(!sp.contains(p.getUniqueId())) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7You have &aEnabled &7NIGHT VISION."));
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
			sp.add(p.getUniqueId());
			return true;
		}
		else {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7You have &cDisabled &7NIGHT VISION."));
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
			sp.remove(p.getUniqueId());
		}
		return false;
	}

}
