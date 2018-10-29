package me.bruce.factions.commands;

import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class StatsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(!(arg0 instanceof Player)) {
		return true;
		}	
		Player p = (Player) arg0;
		arg0.sendMessage("§7§m----------------------------------------------");
		arg0.sendMessage(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Player Stats:");
		arg0.sendMessage("");
		arg0.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Kills§7:§f " + p.getStatistic(Statistic.PLAYER_KILLS));
		arg0.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Deaths§7:§f " + p.getStatistic(Statistic.DEATHS));
		arg0.sendMessage("§7§m----------------------------------------------");
		return true;
	}
}
	
	


