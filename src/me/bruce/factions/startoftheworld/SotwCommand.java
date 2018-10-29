package me.bruce.factions.startoftheworld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableList;

import LorexMC.us.utils.BukkitUtils;
import LorexMC.us.utils.JavaUtils;
import me.bruce.factions.LorexHCF;

public class SotwCommand implements CommandExecutor, TabCompleter
{
    private static final List<String> COMPLETIONS;
    private final LorexHCF plugin;
    public static ArrayList<UUID> enabled;
    
    public SotwCommand(final LorexHCF plugin) {
        this.plugin = plugin;
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
    	Player p = (Player) sender;
            if (args.length > 0) {
            	
                if (args[0].equalsIgnoreCase("start")) {
                	if(!sender.hasPermission("sotw.start")) {
                		sender.sendMessage("§cYou do not have permission to use this command.");
                	}
                    if (args.length < 2) {
                        sender.sendMessage(ChatColor.RED + "Usage: /" + label + " " + args[0].toLowerCase() + " <duration>");
                        return true;
                    }
                    final long duration = JavaUtils.parse(args[1]);
                    if (duration == -1L) {
                        sender.sendMessage(ChatColor.RED + "'" + args[0] + "' is an invalid duration.");
                        return true;
                    }
                    if (duration < 1000L) {
                        sender.sendMessage(ChatColor.RED + "SOTW protection time must last for at least 20 ticks.");
                        return true;
                    }
                    final SotwTimer.SotwRunnable sotwRunnable2 = this.plugin.getSotwTimer().getSotwRunnable();
                    if (sotwRunnable2 != null) {
                        sender.sendMessage(ChatColor.RED + "Sotw Protection is already enabled. use /" + label + " cancel to end it.");
                        return true;
                    }
                    this.plugin.getSotwTimer().start(duration);
                    sender.sendMessage(ChatColor.RED + "Started Sotw for  " + DurationFormatUtils.formatDurationWords(duration, true, true) + ".");
                    return true;
                }
                    if (args[0].equalsIgnoreCase("enable")) {
                    	if(!p.hasPermission("sotw.enable")) {
                    		p.sendMessage(LorexHCF.NOPERMS);
                    	}
                        final SotwTimer.SotwRunnable sotwRunnable = this.plugin.getSotwTimer().getSotwRunnable();
                        if (sotwRunnable == null) {
                            sender.sendMessage(LorexHCF.PREFIX + "Cannot use this whilst there is no sotw!");
                            return true;
                        }
                        if (SotwCommand.enabled.contains(p.getUniqueId())) {
                            sender.sendMessage(LorexHCF.PREFIX + "Your Sotw enable has already been activated");
                        }
                        else {
                            SotwCommand.enabled.add(p.getUniqueId());
                            sender.sendMessage(LorexHCF.PREFIX + "You Have Enabled Sotw.");
                        }
                        return true;
                    }
            	
                else if (args[0].equalsIgnoreCase("end") || args[0].equalsIgnoreCase("cancel")) {
                	if(!sender.hasPermission("sotw.end")) {
                		sender.sendMessage("§cYou do not have permission to use this command.");
                	}
                    if (this.plugin.getSotwTimer().cancel()) {
            			Bukkit.broadcastMessage(("§7§m--------------------------------"));
            			Bukkit.broadcastMessage(("§7The §6§lSOTW §7has ended. §6§lGOOD LUCK§7."));
            			Bukkit.broadcastMessage(("§7§m--------------------------------"));
                        SotwCommand.enabled.remove(p.getUniqueId());
                        return true;
                    }
                    sender.sendMessage(ChatColor.RED + "SOTW protection is not active.");
                    return true;
                }            
            sender.sendMessage(ChatColor.RED + "Usage: /" + label + " Start,enable,end");
            return true;
        }
        if (args.length <= 0) {
            sender.sendMessage("§8§m" + BukkitUtils.STRAIGHT_LINE_DEFAULT);
            sender.sendMessage("§7/sotw enable");
            sender.sendMessage("§8§m" + BukkitUtils.STRAIGHT_LINE_DEFAULT);
            return true;
        }
		return true;
       }


    public List<String> onTabComplete(final CommandSender sender, final Command command, final String label, final String[] args) {
        return (args.length == 1) ? BukkitUtils.getCompletions(args, SotwCommand.COMPLETIONS) : Collections.emptyList();
    }
    
    static {
        COMPLETIONS = (List)ImmutableList.of((Object)"start", (Object)"end");
        SotwCommand.enabled = new ArrayList<UUID>();
    }
}
