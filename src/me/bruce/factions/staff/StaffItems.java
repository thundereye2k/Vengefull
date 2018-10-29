package me.bruce.factions.staff;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StaffItems
{
    public static void modItems(final Player p) {
        final Inventory inv = (Inventory)p.getInventory();
        inv.clear();
        final ItemStack compass = new ItemStack(Material.COMPASS);
        final ItemStack book = new ItemStack(Material.BOOK);
        final ItemStack tp = new ItemStack(397, 1, (short)3);
        final ItemStack vanish = new ItemStack(351, 1, (short)8);
        final ItemStack carpet = new ItemStack(171, 1, (short)1);
        final ItemStack wand = new ItemStack(Material.WOOD_AXE);
        final ItemMeta compassMeta = compass.getItemMeta();
        final ItemMeta bookMeta = book.getItemMeta();
        final ItemMeta eggMeta = tp.getItemMeta();
        final ItemMeta vanishMeta = vanish.getItemMeta();
        final ItemMeta carpetMeta = carpet.getItemMeta();
        compassMeta.setDisplayName("§eZoom");
        bookMeta.setDisplayName("§eInventory Inspector");
        eggMeta.setDisplayName("§eFind Player");
        vanishMeta.setDisplayName("§eVanish: §aOn");
        carpetMeta.setDisplayName("§eView");
        compass.setItemMeta(compassMeta);
        book.setItemMeta(bookMeta);
        tp.setItemMeta(eggMeta);
        vanish.setItemMeta(vanishMeta);
        carpet.setItemMeta(carpetMeta);
        inv.setItem(0, compass);
        inv.setItem(1, book);
        inv.setItem(2, wand);
        inv.setItem(3, carpet);
        inv.setItem(7, tp);
        inv.setItem(8, vanish);
    }
    
    public static void saveInventory(final Player p) {
        StaffModeCommand.armorContents.put(p.getName(), p.getInventory().getArmorContents());
        StaffModeCommand.inventoryContents.put(p.getName(), p.getInventory().getContents());
    }
    
    public static void loadInventory(final Player p) {
        p.getInventory().clear();
        p.getInventory().setContents((ItemStack[])StaffModeCommand.inventoryContents.get(p.getName()));
        p.getInventory().setArmorContents((ItemStack[])StaffModeCommand.armorContents.get(p.getName()));
        StaffModeCommand.inventoryContents.remove(p.getName());
        StaffModeCommand.armorContents.remove(p.getName());
    }
}
