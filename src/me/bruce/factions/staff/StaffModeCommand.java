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

public class StaffModeCommand implements Listener, CommandExecutor
{
    public static ArrayList<Player> modMode;
    public static ArrayList<UUID> Staff;
    public static ArrayList<Player> teleportList;
    public static HashMap<String, ItemStack[]> armorContents;
    public static HashMap<String, ItemStack[]> inventoryContents;
    static StaffModeCommand instance;
    
    static {
        StaffModeCommand.modMode = new ArrayList<Player>();
        StaffModeCommand.Staff = new ArrayList<UUID>();
        StaffModeCommand.teleportList = new ArrayList<Player>();
        StaffModeCommand.armorContents = new HashMap<String, ItemStack[]>();
        StaffModeCommand.inventoryContents = new HashMap<String, ItemStack[]>();
        StaffModeCommand.instance = new StaffModeCommand();
    }
    
    public String color(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
    
    public static StaffModeCommand getInstance() {
        return StaffModeCommand.instance;
    }
    
    public static boolean isMod(final Player p) {
        return StaffModeCommand.Staff.contains(p.getUniqueId());
    }
    
    public static boolean enterMod(final Player p) {
        StaffModeCommand.modMode.add(p);
        StaffModeCommand.Staff.add(p.getUniqueId());
        StaffItems.saveInventory(p);
        VanishListener.getInstance().setVanish(p, true);
        p.getInventory().clear();
        p.getInventory().setHelmet((ItemStack)null);
        p.getInventory().setChestplate((ItemStack)null);
        p.getInventory().setLeggings((ItemStack)null);
        p.getInventory().setBoots((ItemStack)null);
        p.setExp(0.0f);
        p.setAllowFlight(true);
        p.setGameMode(GameMode.CREATIVE);
        StaffItems.modItems(p);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &eYou have &aEnabled &e&lSTAFFMODE."));
        return true;
    }
    
    public static boolean leaveMod(final Player p) {
        StaffModeCommand.modMode.remove(p);
        StaffModeCommand.Staff.remove(p.getUniqueId());
        p.getInventory().clear();
        StaffItems.loadInventory(p);
        p.setAllowFlight(false);
        VanishListener.getInstance().setVanish(p, false);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &eYou have &cDisabled &e&lSTAFFMODE."));
        p.setGameMode(GameMode.SURVIVAL);
        return true;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!cmd.getName().equalsIgnoreCase("staffmode")) {
            return false;
        }

        if (args.length < 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "User command only");
                return true;
            }
            if (StaffModeCommand.modMode.contains(sender)) {
                leaveMod((Player)sender);
                return true;
            }
            enterMod((Player)sender);
            return true;
        }
        else {
            if (!sender.hasPermission("command.mod.others")) {
                sender.sendMessage(ChatColor.RED + "No.");
                return true;
            }
            final Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                sender.sendMessage("§cPlayer not found.");
                return true;
            }
            if (StaffModeCommand.modMode.contains(t)) {
                leaveMod(t);
                sender.sendMessage("§7You have §cdisabled §7" + t.getName() + "'s §e§lStaff Mode");
                return true;
            }
            enterMod(t);
            sender.sendMessage("§7You have §aenabled §7" + t.getName() + "'s §e§lStaff Mode");
            return true;
        }
    }
    
    public static void onDisableMod() {
        Player[] arrayOfPlayer;
        for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
            final Player p = arrayOfPlayer[i];
            if (StaffModeCommand.Staff.contains(p.getUniqueId())) {
                leaveMod(p);
                p.sendMessage(String.valueOf(ChatColor.RED.toString()) + "You have been taken out of staff mode because of a reload.");
                StaffModeCommand.teleportList.remove(p);
            }
        }
    }
}
