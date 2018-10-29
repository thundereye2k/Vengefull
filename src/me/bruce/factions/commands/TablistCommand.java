package me.bruce.factions.commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.bruce.factions.LorexHCF;
import me.tablist.api.tablist.Xernos;
import me.tablist.api.tablist.adapter.TablistAdapter;
import net.md_5.bungee.api.ChatColor;

public class TablistCommand implements CommandExecutor,Listener {
	
	public static ArrayList<UUID> kits = new ArrayList<UUID>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Tablist Selector");
		ItemStack kits = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta kmeta = kits.getItemMeta();
		kmeta.setDisplayName("§7Kitmap Tablist");
		kits.setItemMeta(kmeta);
		
		ItemStack normal = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta nmeta = normal.getItemMeta();
		nmeta.setDisplayName("§7Default Tablist");
		normal.setItemMeta(nmeta);
		
		inv.setItem(0, kits);
		inv.setItem(1, normal);
		p.openInventory(inv);
		return true;
	}
	
	@EventHandler
	public void onInventoryInteract(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.GOLD + "Tablist Selector")) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta() == null) {
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GRAY + "Kitmap Tablist")) {
				p.sendMessage(LorexHCF.PREFIX + " You have selected the Kitmap tablist.");
				TablistAdapter.tablist.add(p);
				p.closeInventory();
				Xernos.getByPlayer(p).clearTab();			
				
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GRAY + "Default Tablist")) {
				p.sendMessage(LorexHCF.PREFIX + " You have selected the Default tablist.");
				p.closeInventory();
				TablistAdapter.tablist.remove(p);
				Xernos.getByPlayer(p).clearTab();
				
				
			}
		}
	}

}
