package me.bruce.factions.staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import me.bruce.factions.listener.VanishListener;

public class VanishCommand implements CommandExecutor
{
    public static ArrayList<Player> staff;
    
    static {
        VanishCommand.staff = new ArrayList<Player>();
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!cmd.getName().equalsIgnoreCase("vanish")) {
            return false;
        }
        if (!sender.hasPermission("command.vanish")) {
            sender.sendMessage(ChatColor.RED + "You lack the sufficient permissions to execute this command.");
            return true;
        }
        if (args.length < 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You must be a player to execute this command");
                return true;
            }
            final Player p = (Player)sender;
            VanishListener.getInstance();
            if (VanishListener.isVanished(p.getPlayer())) {
                VanishListener.getInstance().setVanish(p, false);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Vanish: &cDisabled"));
                return true;
            }
            VanishListener.getInstance().setVanish(p, true);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Vanish: &aEnabled"));
            return true;
        }
        else {
            if (!sender.hasPermission("command.vanish.others")) {
                sender.sendMessage(ChatColor.RED + "No.");
                return true;
            }
            final Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                sender.sendMessage("§6Could not find player §f" + args[0].toString() + "§6.");
                return true;
            }
            VanishListener.getInstance();
            if (VanishListener.isVanished(t.getPlayer())) {
                VanishListener.getInstance().setVanish(t, false);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Vanish: &cDisabled"));
                return true;
            }
            VanishListener.getInstance().setVanish(t, true);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Vanish: &aEnabled"));
            return true;
        }
    }
}

