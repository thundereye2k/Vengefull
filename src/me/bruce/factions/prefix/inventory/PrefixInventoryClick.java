package me.bruce.factions.prefix.inventory;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.bruce.factions.prefix.builder.ItemBuilder1;
import me.bruce.factions.prefix.simplechat.Chat;
import net.md_5.bungee.api.ChatColor;

public class PrefixInventoryClick
{
    Player player;
    
    public PrefixInventoryClick(final Player player) {
        this.player = player;
    }
    
    public void openInventory() {
    	ArrayList<String> lore = new ArrayList<String>();
        final Inventory SymbolsInv = Bukkit.getServer().createInventory(null, 36, ChatColor.GRAY + "Prefix Menu");
        if(player.hasPermission("prefix.1")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&C<&eo&a/"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(0, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&C<&eo&a/"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(0, Symbol1);
        	
        }
        if(player.hasPermission("prefix.2")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&2&L$$$&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(1, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&2&L$$$&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(1, Symbol1);
        	
        }
        if(player.hasPermission("prefix.3")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5&l#&D&LEDATER"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(2, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5&l#&D&LEDATER"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(2, Symbol1);
        	
        }
        if(player.hasPermission("prefix.4")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2&L#&a&LTOXIC"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(3, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&C&2&L#&a&LTOXIC"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(3, Symbol1);
        	
        }
        if(player.hasPermission("prefix.5")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&5GOD&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(4, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&5GOD&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(4, Symbol1);
        	
        }
        if(player.hasPermission("prefix.6")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&6&L#&E&LABUSE&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(5, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&6&L#&E&LABUSE&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(5, Symbol1);
        	
        }
        if(player.hasPermission("prefix.7")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&5&L#&D&LRAIDABLE&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(6, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&5&L#&D&LRAIDABLE&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(6, Symbol1);
        	
        }
        if(player.hasPermission("prefix.8")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&9&lP2W&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(7, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&9&lP2W&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(7, Symbol1);
        	
        }
        if(player.hasPermission("prefix.9")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&7&l#&F&lCAKE&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(9, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&7&l#&F&lCAKE&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(9, Symbol1);
        	
        }
        if(player.hasPermission("prefix.10")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7[&d&l#&9&LBLAME&D&lB&A&LAB&E&lY&C&LC&4&LL&F&lE&D&LW&7]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(10, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7[&d&l#&9&LBLAME&D&lB&A&LAB&E&lY&C&LC&4&LL&F&lE&D&LW&7]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(10, Symbol1);
        	
        }
        if(player.hasPermission("prefix.11")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&a&l#&2&lOG&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(11, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&a&l#&2&lOG&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(11, Symbol1);
        	
        }
        if(player.hasPermission("prefix.12")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&c&l#&4&lSeptember&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(12, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&c&l#&4&lSeptember&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(12, Symbol1);
        	
        }
        if(player.hasPermission("prefix.13")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&1&l#&9&lFOOD&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(12, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&1&l#&9&lFOOD&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(12, Symbol1);
        	
        }
        if(player.hasPermission("prefix.14")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&4&ki&6&l#DOT&5&Ki&E&lREEE&8&4&ki&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(13, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&4&ki&6&l#DOT&5&Ki&E&lREEE&8&4&ki&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(13, Symbol1);
        	
        }
        if(player.hasPermission("prefix.15")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&4&l3.0&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(14, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&4&l3.0&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(14, Symbol1);
        	
        }
        if(player.hasPermission("prefix.16")) {
        	ItemStack Symbol1 = new ItemStack(Material.NAME_TAG);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&3&L#EGIRL&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "RIGHT CLICK §7To enable the prefix.",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(14, Symbol1);
        }
        else {
        	ItemStack Symbol1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)14);
        	ItemMeta smeta = Symbol1.getItemMeta();
        	smeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&3&L#EGIRL&8]"));
            smeta.setLore(Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------",
                    ChatColor.GOLD + ChatColor.BOLD.toString() + "§cYou Do not have permission for this prefix",
                    ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------"}));
        	Symbol1.setItemMeta(smeta);
            SymbolsInv.setItem(16, Symbol1);
        	
        }

        
        final ItemStack Reset = new ItemBuilder1(Material.STAINED_GLASS_PANE, (short)14).setDisplayName(Chat.RED + Chat.BOLD + "Reset").build();
        final ItemStack Back = new ItemBuilder1(Material.STAINED_GLASS_PANE, (short)0).setDisplayName(Chat.WHITE + Chat.BOLD + "<<Back").build();
        final ItemStack SpacerDGRAY = new ItemBuilder1(Material.STAINED_GLASS_PANE, (short)15).setDisplayName(" ").build();

        SymbolsInv.setItem(27, SpacerDGRAY);
        SymbolsInv.setItem(28, SpacerDGRAY);
        SymbolsInv.setItem(29, SpacerDGRAY);
        SymbolsInv.setItem(30, SpacerDGRAY);
        SymbolsInv.setItem(31, SpacerDGRAY);
        SymbolsInv.setItem(32, SpacerDGRAY);
        SymbolsInv.setItem(33, SpacerDGRAY);
        this.player.openInventory(SymbolsInv);
    }
}