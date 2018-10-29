package me.bruce.factions.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LorexMC.us.utils.internal.com.bruce.base.BasePlugin;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.struct.Relation;
import me.bruce.factions.faction.type.PlayerFaction;
import me.bruce.factions.listener.Cooldowns;

public class LFFCommand implements CommandExecutor {
	
	private final LorexHCF plugin;

	public LFFCommand(final LorexHCF plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		final PlayerFaction faction = LorexHCF.getInstance().getFactionManager().getPlayerFaction(p);
		  if (!(sender instanceof Player)) {
		    sender.sendMessage(ChatColor.RED + "This is a player only command");
		    return true;
		}
			if (Cooldowns.isOnCooldown("lff_cooldown", p)) {
				p.sendMessage("§cYou Still have a cooldown");
				return true;
			}
			if(faction != null) {
				sender.sendMessage("§cYou are already in a faction");
				return true;
			}
			if (sender instanceof Player) {
				final Player player = (Player) sender;
				  Bukkit.broadcastMessage(ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "--------------------------------");
				  Bukkit.broadcastMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + sender.getName() + ChatColor.GRAY + " is looking for a faction!");
				  Bukkit.broadcastMessage(ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "--------------------------------");
				  Cooldowns.addCooldown("lff_cooldown", p, 900);
			}
			return true;
	}
}