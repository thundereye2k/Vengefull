package me.bruce.factions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bruce.factions.LorexHCF;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class SetPrefixCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!sender.hasPermission("zentrix.admin")) {
            sender.sendMessage("§cNo permission.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("§cUsage: /setprefix <player> <prefix>");
            return true;
        }
        final Player pl = Bukkit.getPlayer(args[0]);
        if (pl == null) {
            sender.sendMessage("§cPlayer not found!");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage("§cUsage: /setprefix <player> <prefix>");
            return true;
        }
        if (args[1].equals("remove")) {

            pl.sendMessage(LorexHCF.PREFIX + "Your prefix has been removed by: " + ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(sender.getName()).getPrefix()  + sender.getName()));
            sender.sendMessage(LorexHCF.PREFIX + "You have removed " + ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(pl.getName()).getPrefix()  + pl.getName()) +  "'s prefix!");
            return true;
        }
        pl.sendMessage(LorexHCF.PREFIX + "Your prefix has been set to: " + ChatColor.translateAlternateColorCodes('&', args[1]) +  " by " + ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(sender.getName()).getPrefix()  + sender.getName()));
        sender.sendMessage(LorexHCF.PREFIX + "You have set " + ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(pl.getName()).getSuffix() + pl.getName())  + "'s prefix to: " + ChatColor.translateAlternateColorCodes('&', args[1]));
        return true;
    }
}
