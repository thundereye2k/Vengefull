package me.bruce.factions.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.deathban.Deathban;
import me.bruce.factions.faction.FactionUser;
import me.bruce.factions.listener.Cooldowns;

public class Pro implements CommandExecutor {
	
    private final LorexHCF plugin;
    
    public Pro(final LorexHCF plugin) {
        this.plugin = plugin;
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command is only executable by players.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /" + label + " revive [player]");
            return true;
        }
        if(args.length == 1) {
        	sender.sendMessage("§cPlease enter the name");
        	return true;
        }
        else if(args.length == 2){
        	final Player player = (Player)sender;
            final OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
            if (Cooldowns.isOnCooldown("revive_cooldown", player)) {
                sender.sendMessage("§cYou cannot do this for another §l" + Cooldowns.getCooldownForPlayerInt("revive_cooldown", player) / 60 + " §cminutes.");
                return true;
            }
            final UUID targetUUID = target.getUniqueId();
            final FactionUser factionTarget = this.plugin.getUserManager().getUser(targetUUID);
            final Deathban deathban = factionTarget.getDeathban();
            if (deathban == null || !deathban.isActive()) {
                sender.sendMessage(ChatColor.RED + target.getName() + " is not death-banned.");
                return true;
            }
            factionTarget.removeDeathban();
            sender.sendMessage(ChatColor.GRAY + "You have revived " + ChatColor.AQUA + target.getName() + ChatColor.GRAY + '.');
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&f&lREVIVE&7] &6&l" + sender.getName() + " &ehas used his &9&lPro Rank &eto revive &A" + target.getName()));
            Cooldowns.addCooldown("revive_cooldown", player, 3600);
            return true;
        	
        }
		return false;
        
    }
}
	


