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
import me.bruce.factions.economy.EconomyManager;

public class PotsCommand implements CommandExecutor, Listener {

	private final LorexHCF plugin;

	public PotsCommand(LorexHCF plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		Inventory pots = Bukkit.getServer().createInventory(null, 9 * 3,
				ChatColor.RED + ChatColor.BOLD.toString() + "Pots gui");
		ArrayList<String> lore = new ArrayList<String>();
		ArrayList<String> lore1 = new ArrayList<String>();
		ArrayList<String> lore2 = new ArrayList<String>();

		ItemStack health = new ItemStack(Material.POTION, 1, (short) 16421);
		ItemMeta hmeta = health.getItemMeta();
		hmeta.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Health Potion");
		lore.add(ChatColor.RED + "press to buy for 500");
		hmeta.setLore(lore);
		health.setItemMeta(hmeta);

		ItemStack slow = new ItemStack(Material.POTION, 1, (short) 16458);
		ItemMeta smeta = health.getItemMeta();
		smeta.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Slowness Potion");
		lore1.add(ChatColor.RED + "press to buy for 1000");
		smeta.setLore(lore1);
		slow.setItemMeta(smeta);

		ItemStack invis = new ItemStack(Material.POTION, 1, (short) 8270);
		ItemMeta imeta = invis.getItemMeta();
		imeta.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Invisibility Potion");
		lore2.add(ChatColor.RED + "press to buy for 1000");
		smeta.setLore(lore2);
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
		if (open.getName().equals(ChatColor.RED + ChatColor.BOLD.toString() + "Pots gui")) {
			event.setCancelled(true);
			if (item.equals(null) && !item.hasItemMeta()) {
				Bukkit.getConsoleSender().sendMessage("");
				return;
			}

			int senderBalance = p != null ? plugin.getEconomyManager().getBalance(p.getUniqueId()) : 1024;

			if (senderBalance < 99 && item.getItemMeta().getDisplayName()
					.equals(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Health Potion")) {
				p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.RED
						+ "You Do not Have " + EconomyManager.ECONOMY_SYMBOL + "100 in you're bank.");

			} else if (senderBalance > 498 && item.getItemMeta().getDisplayName()
					.equals(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Health Potion")) {
				ItemStack health = new ItemStack(Material.POTION, 1, (short) 16421);
				final UUID uuid = p.getUniqueId();
				plugin.getEconomyManager().subtractBalance(uuid, 500);

				p.getInventory().addItem(health);
				p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» "  + ChatColor.GRAY
						+ "You Have Bought the Health potion for " + EconomyManager.ECONOMY_SYMBOL + "500");
				p.closeInventory();
			}
			if (senderBalance < 999 && item.getItemMeta().getDisplayName()
					.equals(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Slowness Potion")) {
				p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.RED
						+ "You Do not Have " + EconomyManager.ECONOMY_SYMBOL + "1000 in you're bank.");

			} else if (senderBalance > 999 && item.getItemMeta().getDisplayName()
					.equals(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Slowness Potion")) {
				ItemStack slow = new ItemStack(Material.POTION, 1, (short) 16458);
				final UUID uuid = p.getUniqueId();
				plugin.getEconomyManager().subtractBalance(uuid, 1000);

				p.getInventory().addItem(slow);
				p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.GRAY
						+ "You Have Bought the Slowness potion for " + EconomyManager.ECONOMY_SYMBOL + "1000");
				p.closeInventory();
			}
			if (senderBalance < 999 && item.getItemMeta().getDisplayName()
					.equals(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Invisibility Potion")) {
				p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.RED
						+ "You Do not Have " + EconomyManager.ECONOMY_SYMBOL + "1000 in you're bank.");

			} else if (senderBalance > 999 && item.getItemMeta().getDisplayName()
					.equals(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Invisibility Potion")) {
				ItemStack invis = new ItemStack(Material.POTION, 1, (short) 8270);
				final UUID uuid = p.getUniqueId();
				plugin.getEconomyManager().subtractBalance(uuid, 1000);

				p.getInventory().addItem(invis);
				p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.GRAY
						+ "You Have Bought the Invisibility  potion for " + EconomyManager.ECONOMY_SYMBOL + "1000");
				p.closeInventory();
			}
		}
	}
}
