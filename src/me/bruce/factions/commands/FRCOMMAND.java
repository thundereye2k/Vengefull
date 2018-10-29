package me.bruce.factions.commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FRCOMMAND implements Listener, CommandExecutor{
	public static ArrayList<UUID> fr = new ArrayList<UUID>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command is only executable by players.");
			return true;
		}
		Player p = (Player) sender;
		if(!fr.contains(p.getUniqueId())) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7You have &aEnabled &7fire resistance."));
			p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
			fr.add(p.getUniqueId());
			return true;
		}
		else {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7You have &cDisbaled &7fire resistance."));
			p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
			fr.remove(p.getUniqueId());
		}
		return false;
	}

}
