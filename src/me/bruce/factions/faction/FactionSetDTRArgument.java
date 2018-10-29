package me.bruce.factions.faction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LorexMC.us.utils.JavaUtils;
import LorexMC.us.utils.other.command.CommandArgument;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.type.Faction;
import me.bruce.factions.faction.type.PlayerFaction;

/**
 * Faction argument used to set the DTR of {@link Faction}s.
 */
public class FactionSetDTRArgument extends CommandArgument {

    private final LorexHCF plugin;

    public FactionSetDTRArgument(LorexHCF plugin) {
        super("setdtr", "Sets the DTR of a faction.", new String[] { "dtr" });
        this.plugin = plugin;
        this.permission = "hcf.command.faction.argument." + getName();
    }

    @Override
    public String getUsage(String label) {
        return '/' + label + ' ' + getName() + " <playerName|factionName> <newDtr>";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Usage: " + getUsage(label));
            return true;
        }

        Double newDTR = JavaUtils.tryParseDouble(args[2]);

        if (newDTR == null) {
            sender.sendMessage(ChatColor.RED + "'" + args[2] + "' is not a valid number.");
            return true;
        }

        if (args[1].equalsIgnoreCase("all")) {
            for (Faction faction : plugin.getFactionManager().getFactions()) {
                if (faction instanceof PlayerFaction) {
                    ((PlayerFaction) faction).setDeathsUntilRaidable(newDTR);
                }
            }

            Command.broadcastCommandMessage(sender, ChatColor.YELLOW + "Set DTR of all factions to " + newDTR + '.');
            return true;
        }

        Faction faction = plugin.getFactionManager().getContainingFaction(args[1]);

        if (faction == null) {
            sender.sendMessage(ChatColor.RED + "Faction named or containing member with IGN or UUID " + args[1] + " not found.");
            return true;
        }

        if (!(faction instanceof PlayerFaction)) {
            sender.sendMessage(ChatColor.RED + "You can only set DTR of player factions.");
            return true;
        }

        PlayerFaction playerFaction = (PlayerFaction) faction;
        double previousDtr = playerFaction.getDeathsUntilRaidable();
        newDTR = playerFaction.setDeathsUntilRaidable(newDTR);

        Command.broadcastCommandMessage(sender, ChatColor.YELLOW + "Set DTR of " + faction.getName() + " from " + previousDtr + " to " + newDTR + '.');
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2 || !(sender instanceof Player)) {
            return Collections.emptyList();
        } else if (args[1].isEmpty()) {
            return null;
        } else {
            Player player = (Player) sender;
            List<String> results = new ArrayList<>(plugin.getFactionManager().getFactionNameMap().keySet());
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (player.canSee(target) && !results.contains(target.getName())) {
                    results.add(target.getName());
                }
            }

            return results;
        }
    }
}
