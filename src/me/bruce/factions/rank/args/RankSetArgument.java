package me.bruce.factions.rank.args;


import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import LorexMC.us.utils.other.command.CommandArgument;
import me.bruce.factions.LorexHCF;

public class RankSetArgument extends CommandArgument {

	private final LorexHCF plugin;

	public RankSetArgument(LorexHCF plugin) {
		super("set", " sets the players rank");
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + " <player> <group>";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 1) {
			sender.sendMessage(ChatColor.RED + "usage /rank set <player> <group>");
			return true;
		}
		Player target = Bukkit.getPlayer(args[1]);
		if(target == null) {
			sender.sendMessage(ChatColor.RED  + "Could not find the player " + args[1]);
			return true;
		}
        StringBuilder x = new StringBuilder();

        for (int i = 2; i < args.length; i++) {
            x.append(args[i]+" ");
        }
        sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.GRAY + "You have set " + args[1] + " to the rank " + x.toString().trim());
        target.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.GRAY + "Your rank was set to " + x.toString().trim());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[1] + " group set " + x.toString().trim());
		return true;

	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return args.length == 2 ? null : Collections.emptyList();
	}
}