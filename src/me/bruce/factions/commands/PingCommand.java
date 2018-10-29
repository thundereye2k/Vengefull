package me.bruce.factions.commands;

import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LorexMC.us.utils.BukkitUtils;
import LorexMC.us.utils.internal.com.bruce.base.BaseConstants;

public class PingCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player target = (Player) sender;
		if(target.getPing() < 50) {
		sender.sendMessage((target.equals(sender) ? (ChatColor.GREEN + "Your current ping is ")
				: (ChatColor.GRAY + "Ping of §c" + (target.getName() + " §7is §c"))) + target.getPing());
		return true;
		}
		if(target.getPing()  > 50){
			target.sendMessage("§cWarning Your ping is to high.");
			return true;
		}
		return true;
	}

}
