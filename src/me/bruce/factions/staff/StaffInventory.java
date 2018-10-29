package me.bruce.factions.staff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import com.mysql.jdbc.TimeUtil;

import me.bruce.factions.LorexHCF;

public class StaffInventory
{
    private static final LorexHCF plugin;
    
    static {
        plugin = LorexHCF.getInstance();
    }
    
    public static String translate(final String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    
    public static List<String> translateFromArray(final List<String> text) {
        final List<String> messages = new ArrayList<String>();
        for (final String string : text) {
            messages.add(translate(string));
        }
        return messages;
    }
    
    public static void inspector(final Player player, final Player target) {
        final Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder)null, 45, ChatColor.translateAlternateColorCodes('&', "&eInspecting: " + target.getName()));
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)StaffInventory.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                final PlayerInventory playerInventory = target.getInventory();
                final ItemStack cookedBeef = new ItemStack(Material.COOKED_BEEF, target.getFoodLevel());
                final ItemMeta cookedBeefMeta = cookedBeef.getItemMeta();
                cookedBeefMeta.setDisplayName(StaffInventory.translate("&aHunger"));
                cookedBeef.setItemMeta(cookedBeefMeta);
                final ItemStack brewingStand = new ItemStack(Material.BREWING_STAND_ITEM, target.getPlayer().getActivePotionEffects().size());
                final ItemMeta brewingStandMeta = brewingStand.getItemMeta();
                brewingStandMeta.setDisplayName(StaffInventory.translate("&aActive Effects"));
                final ArrayList<String> brewingStandLore = new ArrayList<String>();
                for (final PotionEffect potionEffect : target.getPlayer().getActivePotionEffects()) {
                    final String effectName = potionEffect.getType().getName();
                    int effectLevel = potionEffect.getAmplifier();
                    ++effectLevel;
                    brewingStandLore.add(StaffInventory.translate("&e" + WordUtils.capitalizeFully(effectName).replace("_", " ") + " " + effectLevel + "&7: &c"));//TimeUtil.IntegerCountdown.setFormat(potionEffect.getDuration() / 20)));
                }
                brewingStandMeta.setLore(brewingStandLore);
                brewingStand.setItemMeta(brewingStandMeta);
                final ItemStack compass = new ItemStack(Material.COMPASS, 1);
                final ItemMeta compassMeta = compass.getItemMeta();
                compassMeta.setDisplayName(StaffInventory.translate("&aPlayer Location"));
                compassMeta.setLore(StaffInventory.translateFromArray(Arrays.asList("&eWorld&7: &a" + player.getWorld().getName(), "&eCoords", "  &eX&7: &c" + target.getLocation().getBlockX(), "  &eY&7: &c" + target.getLocation().getBlockY(), "  &eZ&7: &c" + target.getLocation().getBlockZ())));
                compass.setItemMeta(compassMeta);
                inventory.setContents(playerInventory.getContents());
                inventory.setItem(36, playerInventory.getHelmet());
                inventory.setItem(37, playerInventory.getChestplate());
                inventory.setItem(38, playerInventory.getLeggings());
                inventory.setItem(39, playerInventory.getBoots());
                inventory.setItem(40, playerInventory.getItemInHand());
                inventory.setItem(41, cookedBeef);
                inventory.setItem(42, brewingStand);
                inventory.setItem(44, compass);
            }
        }, 0L, 5L);
        player.openInventory(inventory);
    }
}
