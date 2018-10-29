package me.bruce.factions.commands;


import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import java.util.*;

public class randomtp implements CommandExecutor{
	
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
    if (!(sender instanceof Player)) {
        sender.sendMessage(ChatColor.RED + "This command is only executable by players.");
        return true;
    }
    final Player player = (Player)sender;
    final List<Player> players = new ArrayList<Player>();
    for (final Player players2 : Bukkit.getOnlinePlayers()) {
        players.add(players2);
    }
    Collections.shuffle(players);
    final Random random = new Random();
    final int randoms = random.nextInt(Bukkit.getOnlinePlayers().length);
    final Player p = players.get(randoms);
    if (player.canSee(p) && player.hasPermission("zorex.teleport")) {
        player.teleport((Entity)p);
        player.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.GRAY+ "You have teleported to " + p.getName());
    }
    else if (player.canSee(p)) {
        player.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.GRAY + "You have found " + p.getName());
    }
    else {
        player.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.RED + "Player could not be found.");
    }
    return true;
}
}



