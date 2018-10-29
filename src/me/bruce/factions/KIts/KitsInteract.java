package me.bruce.factions.KIts;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class KitsInteract implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack item = e.getItem();
		Action action = e.getAction();
		if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			if (item == null) return;
			if (item.getType() == Material.AIR) return;
			
			
			if (item.getType() == Material.BOOK) {
				if (!item.hasItemMeta()) return;
				
				if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Select a kit")) {
					KitsBook.BookGui(p);
					e.setCancelled(true);
				}
			}
		}
	}
	

}
