package me.bruce.factions.prefix;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.prefix.inventory.PrefixInventoryClick;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PrefixInv implements Listener{
	


	@EventHandler
    public void onInvClick(final InventoryClickEvent event) {
        final Player player = (Player)event.getWhoClicked();
        final PermissionUser pexuser = PermissionsEx.getUser(player);
        final String prefix = PermissionsEx.getUser(player).getGroups()[0].getPrefix();
        if (event.getInventory().getName().equals(ChatColor.RED + ChatColor.BOLD.toString() + "Prefixes")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getCurrentItem().getType().equals(Material.REDSTONE) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + ChatColor.BOLD.toString() +  "Disable Prefix")) {
                pexuser.setPrefix("", null);
                player.closeInventory();
                player.sendMessage(LorexHCF.PREFIX + "You successfully reset your prefix");
            }
            if (event.getCurrentItem().getType().equals(Material.MINECART) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + ChatColor.BOLD.toString() +  "Purchase Prefix")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorexMC &8» &7Buy a prefix at store.zorex.us"));
            }
            if (event.getCurrentItem().getType().equals(Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + ChatColor.BOLD.toString() +  "Prefixes")) {
                new PrefixInventoryClick(player).openInventory();
            }
        }
    }
}





