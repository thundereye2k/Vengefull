package me.bruce.factions.KIts;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.KIts.Kitsgive.GiveKits;
import me.bruce.factions.prefix.inventory.PrefixInventoryClick;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class KitsInvClick implements Listener {


	@EventHandler
    public void onInvClick(final InventoryClickEvent event) {
        final Player player = (Player)event.getWhoClicked();
        if (event.getInventory().getName().equals(ChatColor.GOLD + ChatColor.BOLD.toString() + "Kits Selector")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getCurrentItem().getType().equals((Object)Material.DIAMOND_CHESTPLATE) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + ChatColor.BOLD.toString() + "Diamond Kit §7(Right Click)")) {
            	player.getInventory().clear();
            	GiveKits.setDiamond(player);
                player.closeInventory();

            }
            if (event.getCurrentItem().getType().equals((Object)Material.GOLD_CHESTPLATE) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + ChatColor.BOLD.toString() + "Bard Kit §7(Right Click)")) {
            	player.getInventory().clear();
            	GiveKits.setBard(player);
                player.closeInventory();

            }
            if (event.getCurrentItem().getType().equals((Object)Material.LEATHER_CHESTPLATE) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + ChatColor.BOLD.toString() + "Archer Kit §7(Right Click)")) {
            	player.getInventory().clear();
            	GiveKits.setArcher(player);
                player.closeInventory();

            }
            if (event.getCurrentItem().getType().equals((Object)Material.IRON_CHESTPLATE) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + ChatColor.BOLD.toString() + "Builder Kit §7(Right Click)")) {
            	player.getInventory().clear();
            	GiveKits.setBuilder(player);
                player.closeInventory();

            }
        }
    }
}


