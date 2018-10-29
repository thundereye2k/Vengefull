package me.bruce.factions.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Tablist implements CommandExecutor, Listener {

	public static ArrayList<Player> oof = new ArrayList<Player>();
	public static ArrayList<Player> defaulty = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (args.length == 0) {
			sender.sendMessage("Please say wich tablist you want");
			return true;

		}
		if ((args.length == 1 && (args[0].equalsIgnoreCase("kitmap")))) {
			sender.sendMessage(
					ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7you have enabled Kitmap."));
			defaulty.remove(p);
			oof.add(p);
			return true;
		}
		if ((args.length == 1 && (args[0].equalsIgnoreCase("default")))) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7you have enabled default."));
			oof.remove(p);
			defaulty.add(p);
			return true;
		}
		else if(args.length == 1 ) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7You Must choose either kitmap are default."));
		}

		return false;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		oof.remove(p);
		defaulty.add(p);

	}

}
