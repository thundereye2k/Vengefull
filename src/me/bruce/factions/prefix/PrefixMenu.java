package me.bruce.factions.prefix;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.prefix.inventory.inv_prefixes;
import me.bruce.factions.prefix.simplechat.Chat;
import net.md_5.bungee.api.ChatColor;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PrefixMenu implements Listener {
	
	 @EventHandler
	    public void onInvClick(final InventoryClickEvent event) {
	        final Player player = (Player)event.getWhoClicked();
	        final PermissionUser pexuser = PermissionsEx.getUser(player);
	        final String prefix = PermissionsEx.getUser(player).getGroups()[0].getPrefix();
	        if (event.getInventory().getName().equals(ChatColor.GRAY + "Prefix Menu")) {
	            event.setCancelled(true);
	            if (event.getCurrentItem() == null) {
	                return;
	            }
	            if (event.getCurrentItem().getType().equals(Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&C<&eo&a/"))) {
	                    pexuser.setPrefix("&C<&eo&a/" + prefix, (String)null);
	                    player.closeInventory();
	                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
	                }
	            if (event.getCurrentItem().getType().equals(Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&2&L$$$&8]"))) {
                    pexuser.setPrefix("&8[&2&L$$$&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }	            
	            if (event.getCurrentItem().getType().equals(Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&5&l#&D&LEDATER"))) {
                    pexuser.setPrefix("&5&l#&D&LEDATER" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals(Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&2&L#&a&LTOXIC"))) {
                    pexuser.setPrefix("&2&L#&a&LTOXIC" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals(Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&5GOD&8]"))) {
                    pexuser.setPrefix("&8[&5GOD&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&6&L#&E&LABUSE&8]"))) {
                    pexuser.setPrefix("&8[&6&L#&E&LABUSE&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&5&L#&D&LRAIDABLE&8]"))) {
                    pexuser.setPrefix("&8[&5&L#&D&LRAIDABLE&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&9&lP2W&8]"))) {
                    pexuser.setPrefix("&8[&9&lP2W&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&7&l#&F&lCAKE&8]"))) {
                    pexuser.setPrefix("&8[&7&l#&F&lCAKE&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&7[&d&l#&9&LBLAME&D&lB&A&LAB&E&lY&C&LC&4&LL&F&lE&D&LW&7]"))) {
                    pexuser.setPrefix("&7[&d&l#&9&LBLAME&D&lB&A&LAB&E&lY&C&LC&4&LL&F&lE&D&LW&7]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&a&l#&2&lOG&8]"))) {
                    pexuser.setPrefix("&8[&a&l#&2&lOG&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&c&l#&4&lSeptember&8]"))) {
                    pexuser.setPrefix("&8[&c&l#&4&lSeptember&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&1&l#&9&lFOOD&8]"))) {
                    pexuser.setPrefix("&8[&1&l#&9&lFOOD&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) {
                    player.sendMessage(ChatColor.RED + "You Cannot Use This Prefix.");
                }
	            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&4&ki&6&l#DOT&5&Ki&E&lREEE&8&4&ki&8]"))) {
                    pexuser.setPrefix("&8[&4&ki&6&l#DOT&5&Ki&E&lREEE&8&4&ki&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&4&l3.0&8]"))) {
                    pexuser.setPrefix("&8[&4&l3.0&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
	            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8[&3&L#EGIRL&8]"))) {
                    pexuser.setPrefix("&8[&3&L#EGIRL&8]" + prefix, (String)null);
                    player.closeInventory();
                    player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
                }
        
	        }
            if (event.getCurrentItem().getType().equals((Object)Material.NAME_TAG) && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&c\u2764"))) {
                pexuser.setPrefix("&c\u2764" + prefix, (String)null);
                player.closeInventory();
                player.sendMessage(LorexHCF.PREFIX + "Your new prefix is " + pexuser.getOwnPrefix().replaceAll("&", "§") + player.getName());
            }
	        
	 }
}
