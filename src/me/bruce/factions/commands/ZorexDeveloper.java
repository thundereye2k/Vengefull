package me.bruce.factions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.bruce.factions.LorexHCF;

public class ZorexDeveloper  implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(LorexHCF.PREFIX + "The developer of Zorex is Bruceboy!");
		return false;
	}

}
