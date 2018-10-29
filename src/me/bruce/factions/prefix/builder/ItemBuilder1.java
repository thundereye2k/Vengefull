package me.bruce.factions.prefix.builder;

import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.enchantments.*;

public class ItemBuilder1
{
    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private SkullMeta skullMeta;
    
    public ItemBuilder1(final Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = this.itemStack.getItemMeta();
    }
    
    public ItemBuilder1(final Material material, final short subID) {
        this.itemStack = new ItemStack(material, 1, subID);
        this.itemMeta = this.itemStack.getItemMeta();
    }
    
    public ItemBuilder1 setDisplayName(final String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }
    
    public ItemBuilder1 setLore(final String... lore) {
        this.itemMeta.setLore((List)Arrays.asList(lore));
        return this;
    }
    
    public ItemBuilder1 addEnchantment(final Enchantment enchantment, final int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        return this;
    }
    
    public ItemBuilder1 setAmount(final int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }
    
    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }
}
