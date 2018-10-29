package me.bruce.factions.prefix.inventory;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.bruce.factions.prefix.builder.ItemBuilder1;
import me.bruce.factions.prefix.simplechat.Chat;
import net.md_5.bungee.api.ChatColor;

public class inv_prefixes
{
    Player player;
    
    public inv_prefixes(final Player player) {
        this.player = player;
    }
    
    public void openInventory() {
    	ArrayList<String> lore = new ArrayList<String>();
    	ArrayList<String> lore1 = new ArrayList<String>();
    	ArrayList<String> lore2 = new ArrayList<String>();
        final Inventory PrefixInv = Bukkit.getServer().createInventory((InventoryHolder)null, 9, Chat.RED + Chat.BOLD + "Prefixes");
        final ItemStack SpacerRED = new ItemBuilder1(Material.STAINED_GLASS_PANE, (short)14).setDisplayName(" ").build();
        final ItemStack SpacerYELLOW = new ItemBuilder1(Material.STAINED_GLASS_PANE, (short)4).setDisplayName(" ").build();
        final ItemStack SpacerORANGE = new ItemBuilder1(Material.STAINED_GLASS_PANE, (short)1).setDisplayName(" ").build();
        final ItemStack Symbols = new ItemBuilder1(Material.MINECART, (short)5).setDisplayName(Chat.GOLD + Chat.BOLD +  "Purchase Prefix").build();
        lore.add(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "--------------------------------------");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6&lRIGHT CLICK &7to open the &6Prefix Menu&7."));
        lore.add(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "--------------------------------------");
        ItemStack prefixes = new ItemStack(Material.NAME_TAG);
        ItemMeta pmeta = prefixes.getItemMeta();
        pmeta.setDisplayName(ChatColor.GOLD + "§lPrefixes");
        pmeta.setLore(Arrays.asList(new String[] {
                ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7to open Prefix Menu.",
                ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        
        prefixes.setItemMeta(pmeta);
        
        final ItemStack Other = new ItemBuilder1(Material.REDSTONE).setDisplayName(Chat.GOLD + Chat.BOLD +  "Disable Prefix").build();
        PrefixInv.setItem(0, SpacerRED);
        PrefixInv.setItem(1, SpacerORANGE);
        PrefixInv.setItem(2, SpacerYELLOW);
        PrefixInv.setItem(6, SpacerYELLOW);
        PrefixInv.setItem(7, SpacerORANGE);
        PrefixInv.setItem(8, SpacerRED);
        PrefixInv.setItem(5, Symbols);
        PrefixInv.setItem(4, prefixes);
        PrefixInv.setItem(3, Other);
        this.player.openInventory(PrefixInv);
    }
}
