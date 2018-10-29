package me.bruce.factions.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import LorexMC.us.utils.BukkitUtils;
import LorexMC.us.utils.internal.com.bruce.base.BaseConstants;
import me.bruce.factions.LorexHCF;

public class InveeSeeCommand implements Listener,CommandExecutor {
	  private InventoryType[] types;
	    private Map<InventoryType, Inventory> inventories;
	    
	    public void InvSeeCommand(final LorexHCF plugin) {
	        this.types = new InventoryType[] { InventoryType.BREWING, InventoryType.CHEST, InventoryType.DISPENSER, InventoryType.ENCHANTING, InventoryType.FURNACE, InventoryType.HOPPER, InventoryType.PLAYER, InventoryType.WORKBENCH };
	        this.inventories = new EnumMap<InventoryType, Inventory>(InventoryType.class);
	        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
	    }
	    
	    public boolean isPlayerOnlyCommand() {
	        return true;
	    }
	    
	    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
	        if (!(sender instanceof Player)) {
	            if (args.length < 1) {
	                sender.sendMessage(ChatColor.RED + "Usage: /invsee <player>");
	                return true;
	            }
	            final Player target = BukkitUtils.playerWithNameOrUUID(args[0]);
	            if (target == null) {
	                sender.sendMessage(String.format(BaseConstants.PLAYER_WITH_NAME_OR_UUID_NOT_FOUND, args[0]));
	                return true;
	            }
	            sender.sendMessage(ChatColor.GRAY + "This players inventory contains: ");
	            ItemStack[] arrayOfItemStack;
	            for (int j = (arrayOfItemStack = target.getInventory().getContents()).length, i = 0; i < j; ++i) {
	                final ItemStack items = arrayOfItemStack[i];
	                if (items != null) {
	                    sender.sendMessage(ChatColor.AQUA + items.getType().toString().replace("_", "").toLowerCase() + ": " + items.getAmount());
	                }
	            }
	            return true;
	        }
	        else {
	            if (args.length < 1) {
	                sender.sendMessage(ChatColor.RED + "Usage: /invsee <player>");
	                return true;
	            }
	            final Player player = (Player)sender;
	            Inventory inventory = null;
	            final InventoryType[] types = this.types;
	            final int length = types.length;
	            int k = 0;
	            while (k < length) {
	                final InventoryType type = types[k];
	                if (type.name().equalsIgnoreCase(args[0])) {
	                    final Inventory inventoryRevert;
	                    inventory = inventories.putIfAbsent(type, inventoryRevert = Bukkit.createInventory((InventoryHolder)player, type));
	                    if (inventory != null) {
	                        break;
	                    }
	                    inventory = inventoryRevert;
	                    break;
	                }
	                else {
	                    ++k;
	                }
	            }
	            if (inventory == null) {
	                final Player target2 = BukkitUtils.playerWithNameOrUUID(args[0]);
	                if (sender.equals(target2)) {
	                    sender.sendMessage(ChatColor.RED + "You cannot check the inventory of yourself.");
	                    return true;
	                }
	                if (target2 == null) {
	                    sender.sendMessage(String.format(BaseConstants.PLAYER_WITH_NAME_OR_UUID_NOT_FOUND, args[0]));
	                    return true;
	                }
	                inventory = (Inventory)target2.getInventory();
	            }
	            player.openInventory(inventory);
	            return true;
	        }
	    }
	    
	    public List<String> onTabComplete(final CommandSender sender, final Command command, final String label, final String[] args) {
	        if (args.length != 1) {
	            return Collections.emptyList();
	        }
	        final InventoryType[] values = InventoryType.values();
	        final List<String> results = new ArrayList<String>(values.length);
	        final Player senderPlayer = (Player)sender;
	        Player[] arrayOfPlayer;
	        for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
	            final Player target = arrayOfPlayer[i];
	            if (senderPlayer == null || senderPlayer.canSee(target)) {
	                results.add(target.getName());
	            }
	        }
	        return BukkitUtils.getCompletions(args, results);
	    }
	}



