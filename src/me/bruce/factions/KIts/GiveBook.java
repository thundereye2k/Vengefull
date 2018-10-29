package me.bruce.factions.KIts;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class GiveBook  {
	
	
	public static void GiveBooktoplayer(Player p) {
		ItemStack book = new ItemStack(Material.BOOK);
		ItemMeta bmeta = book.getItemMeta();
		bmeta.setDisplayName(ChatColor.GOLD + "Select a kit");
		book.setItemMeta(bmeta);
		
		p.getInventory().addItem(book);
	}
	public static void takeBooktoplayer(Player p) {
		ItemStack book = new ItemStack(Material.BOOK);
		ItemMeta bmeta = book.getItemMeta();
		bmeta.setDisplayName(ChatColor.GOLD + "Select a kit");
		book.setItemMeta(bmeta);
		
		p.getInventory().remove(book);;
	}




}
