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

public class SettingsCommand implements CommandExecutor, Listener {
	
	
	public static ArrayList<UUID> lines = new ArrayList<UUID>();
	public static ArrayList<UUID> scoreboard = new ArrayList<UUID>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		Inventory pots = Bukkit.getServer().createInventory(null, 9 * 3,
				ChatColor.BLUE + ChatColor.BOLD.toString() + "Settings GUI");
		ArrayList<String> lore = new ArrayList<String>();
		ArrayList<String> lore1 = new ArrayList<String>();
		ArrayList<String> lore2 = new ArrayList<String>();

		ItemStack health = new ItemStack(Material.BAKED_POTATO);
		ItemMeta hmeta = health.getItemMeta();
		hmeta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "ClearChat");
		lore.add(ChatColor.YELLOW + "This will only clear you're chat");
		hmeta.setLore(lore);
		health.setItemMeta(hmeta);

		ItemStack slow = new ItemStack(Material.BLAZE_ROD);
		ItemMeta smeta = health.getItemMeta();
		smeta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Scoreboard");
		if(scoreboard.contains(p.getUniqueId())) {
			lore1.add(ChatColor.YELLOW + "Balance on scoreboard §aEnabled§7!");
			smeta.setLore(lore1);
		}
		else {
			lore1.add(ChatColor.YELLOW + "Balance on scoreboard §cDisabled§7!");
			smeta.setLore(lore1);
		}
		slow.setItemMeta(smeta);

		ItemStack invis = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta imeta = invis.getItemMeta();
		imeta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Lines on scoreboard");
		if(lines.contains(p.getUniqueId())) {
			lore2.add(ChatColor.YELLOW + "Lines on scoreboard §cDisabled§7!");
			imeta.setLore(lore2);
		}
		else {
			lore2.add(ChatColor.YELLOW + "Lines on scoreboard §aEnabled§7!");
			imeta.setLore(lore2);
		}
		invis.setItemMeta(imeta);

		pots.setItem(13, health);
		pots.setItem(10, slow);
		pots.setItem(16, invis);
		p.openInventory(pots);
		return false;
	}

	@EventHandler
	public void InvenClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		Inventory open = event.getInventory();
		ItemStack item = event.getCurrentItem();

		if (open == null) {
			return;
		}
		if (open.getName().equals(ChatColor.BLUE + ChatColor.BOLD.toString() + "Settings GUI")) {

			if (item.getItemMeta().getDisplayName()
					.equals(ChatColor.BLUE + ChatColor.BOLD.toString() + "ClearChat")) {
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.RED
						+ "You're Chat Has Been Cleared.");
				p.closeInventory();
			}
			if (item.getItemMeta().getDisplayName()
					.equals(ChatColor.BLUE + ChatColor.BOLD.toString() + "Scoreboard")) {
				if(scoreboard.contains(p.getUniqueId())) {
					scoreboard.remove(p.getUniqueId());
					p.closeInventory();
				}
				else {
					scoreboard.add(p.getUniqueId());
					p.closeInventory();
				}

			}
			if (item.getItemMeta().getDisplayName()
					.equals(ChatColor.BLUE + ChatColor.BOLD.toString() + "Lines on scoreboard")) {
				if(lines.contains(p.getUniqueId())) {
					lines.remove(p.getUniqueId());
					p.sendMessage(LorexHCF.PREFIX + "No lines has been §cDisabled§7." );
					p.closeInventory();
					
				}
				else if(!lines.contains(p.getUniqueId())) {
					lines.add(p.getUniqueId());
					p.sendMessage(LorexHCF.PREFIX + "No lines has been §aEnabled§7." );
					p.closeInventory();
					
				}

			}
		}
	}
}
