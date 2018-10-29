package me.bruce.factions.prefix;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.prefix.inventory.inv_prefixes;

public class prefix implements CommandExecutor
{
    private String USAGE;
    

    
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (args.length == 0) {
                new inv_prefixes(player).openInventory();
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(LorexHCF.NOPLAYER);
        }
        return false;
    }
}
