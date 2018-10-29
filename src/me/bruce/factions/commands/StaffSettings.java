package me.bruce.factions.commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

public class StaffSettings implements CommandExecutor, Listener {
	
	
	public static ArrayList<UUID> faithful = new ArrayList<UUID>();
	public static ArrayList<UUID> zorex = new ArrayList<UUID>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		Inventory pots1 = Bukkit.getServer().createInventory(null, 9,
				ChatColor.BLUE + ChatColor.BOLD.toString() + "Staff GUI");
		ArrayList<String> lore = new ArrayList<String>();
		ArrayList<String> lore1 = new ArrayList<String>();
		ArrayList<String> lore2 = new ArrayList<String>();

		ItemStack health = new ItemStack(Material.BAKED_POTATO);
		ItemMeta hmeta = health.getItemMeta();
		hmeta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Faithful Remake");
		lore.add(ChatColor.BLUE + "");
		hmeta.setLore(lore);
		health.setItemMeta(hmeta);




		ItemStack invis = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta imeta = invis.getItemMeta();
		imeta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Zorex");
		invis.setItemMeta(imeta);

		pots1.setItem(0, health);
		pots1.setItem(1, invis);
		p.openInventory(pots1);
		return true;
	}

	@EventHandler
	public void InvenClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		Inventory open = event.getInventory();
		ItemStack item = event.getCurrentItem();

		if (open == null) {
			return;
		}
		if (open.getName().equals(ChatColor.BLUE + ChatColor.BOLD.toString() + "Staff GUI")) {

			if (item.getItemMeta().getDisplayName()
					.equals(ChatColor.BLUE + ChatColor.BOLD.toString() + "Faithful Remake")) {
				if(faithful.contains(p.getUniqueId())) {
					faithful.remove(p.getUniqueId());
					p.sendMessage(LorexHCF.PREFIX + "You have §4Disabled §7Faithful.");
				}
				if(!faithful.contains(p.getUniqueId())) {
					faithful.add(p.getUniqueId());
					p.closeInventory();
					p.sendMessage(LorexHCF.PREFIX + "You have §aEnabled §7Faithful.");
				}
				if(zorex.contains(p.getUniqueId())) {
					zorex.remove(p.getUniqueId());
					p.closeInventory();
					p.sendMessage(LorexHCF.PREFIX + "Zorex was still on so we disabled it.");
				}
				p.closeInventory();
			}
			if (item.getItemMeta().getDisplayName()
					.equals(ChatColor.BLUE + ChatColor.BOLD.toString() + "NONE")) {
				p.closeInventory();
			}
			if (item.getItemMeta().getDisplayName()
					.equals(ChatColor.BLUE + ChatColor.BOLD.toString() + "Zorex")) {
				if(zorex.contains(p.getUniqueId())) {
					zorex.remove(p.getUniqueId());
					p.sendMessage(LorexHCF.PREFIX + "You have §4Disabled §7Zorex.");
					p.closeInventory();
				}
				if(!zorex.contains(p.getUniqueId())) {
					zorex.add(p.getUniqueId());
					p.sendMessage(LorexHCF.PREFIX + "You have §aEnabled §7Zorex.");
					p.closeInventory();
				}
				if(faithful.contains(p.getUniqueId())) {
					faithful.remove(p.getUniqueId());
				}
			}
					
				}

			}
		}
	

