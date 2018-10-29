package me.bruce.factions.KIts;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class KitsBook {
	
	public static void BookGui(Player p) {
		ArrayList<String> lore = new ArrayList<String>();
		ArrayList<String> lore1 = new ArrayList<String>();
		ArrayList<String> lore2 = new ArrayList<String>();
		ArrayList<String> lore3 = new ArrayList<String>();
		
		Inventory book = Bukkit.createInventory(null, 9, ChatColor.GOLD + ChatColor.BOLD.toString() + "Kits Selector");
		
		//Diamond Kit
		ItemStack diamond = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta dmeta = diamond.getItemMeta();
		dmeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Diamond Kit §7(Right Click)");
		lore.add(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------------");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&6&LRIGHT CLICK &7to use the &6Diamond Kit&7."));
		lore.add(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------------");
		dmeta.setLore(lore);
		diamond.setItemMeta(dmeta);
		
		//Bard
		ItemStack Bard = new ItemStack(Material.GOLD_CHESTPLATE);
		ItemMeta bmeta = Bard.getItemMeta();
		bmeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Bard Kit §7(Right Click)");
		lore1.add(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------------");
		lore1.add(ChatColor.translateAlternateColorCodes('&', "&6&LRIGHT CLICK &7to use the &6Bard Kit&7."));
		lore1.add(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------------");
		bmeta.setLore(lore1);
		Bard.setItemMeta(bmeta);
		
		//Archer Kit
		ItemStack Archer = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta ameta = Archer.getItemMeta();
		ameta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Archer Kit §7(Right Click)");
		lore2.add(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------------");
		lore2.add(ChatColor.translateAlternateColorCodes('&', "&6&LRIGHT CLICK &7to use the &6Archer Kit&7."));
		lore2.add(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------------");
		ameta.setLore(lore2);
		Archer.setItemMeta(ameta);
		
		//Builder Kit
		ItemStack Builder = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta bumeta = Builder.getItemMeta();
		bumeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Builder Kit §7(Right Click)");
		lore3.add(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------------");
		lore3.add(ChatColor.translateAlternateColorCodes('&', "&6&LRIGHT CLICK &7to use the &6Builder Kit&7."));
		lore3.add(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------------");
		bumeta.setLore(lore3);
		Builder.setItemMeta(bumeta);
		
		book.setItem(0, diamond);
		book.setItem(1, Bard);
		book.setItem(2, Archer);
		book.setItem(3, Builder);
		
		p.openInventory(book);
		
	}

}
