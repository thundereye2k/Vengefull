package me.bruce.factions.KIts.Kitsgive;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveKits {
	
	 private static final ItemStack SPLASH_HEATH = new ItemStack(Material.POTION, 1, (short)16421);
	 
	 
	 //Kit stuff
		private static  int maxProtection = 1;
		private static int maxSharpness = 1;
		private static int maxUnbreaking = 3;
		private static  int maxFF = 4;
		private static int maxPower = 3;
		private static int maxFire = 0;
		private static int maxInfinite = 1;
	
	public static void setDiamond(Player player) {
		ItemStack helm = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack pants = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);

        sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, maxSharpness);

        helm.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
        chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
        pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);

        helm.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
        chest.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
        pants.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
        boots.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);

        boots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, maxFF);
        player.getInventory().addItem(sword, new ItemStack(Material.ENDER_PEARL, 16), new ItemStack(Material.COOKED_BEEF, 32));
        player.getInventory().setItem(8, new ItemStack(Material.POTION, 1, (short) 8226));
        player.getInventory().setItem(17, new ItemStack(Material.POTION, 1, (short) 8226));
        player.getInventory().setItem(26, new ItemStack(Material.POTION, 1, (short) 8226));
        player.getInventory().setItem(35, new ItemStack(Material.POTION, 1, (short) 8226));
        player.getInventory().setHelmet(helm);
        player.getInventory().setChestplate(chest);
        player.getInventory().setLeggings(pants);
        player.getInventory().setBoots(boots);
        while (player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(new ItemStack[]{SPLASH_HEATH.clone()});
        }
	}
	
	public static void setBard(Player player) {
		ItemStack helm = new ItemStack(Material.GOLD_HELMET);
       ItemStack chest = new ItemStack(Material.GOLD_CHESTPLATE);
       ItemStack pants = new ItemStack(Material.GOLD_LEGGINGS);
       ItemStack boots = new ItemStack(Material.GOLD_BOOTS);
       ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);

       sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, maxSharpness);

       helm.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
       chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
       pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
       boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);

       helm.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
       chest.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
       pants.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
       boots.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);

       boots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, maxFF);
       player.getInventory().addItem(sword, new ItemStack(Material.ENDER_PEARL, 16), new ItemStack(Material.COOKED_BEEF, 32), new ItemStack(Material.BLAZE_POWDER, 32), new ItemStack(Material.SUGAR, 32), new ItemStack(Material.GHAST_TEAR, 2), new ItemStack(Material.FEATHER, 16), new ItemStack(Material.SPIDER_EYE, 16));
       player.getInventory().setHelmet(helm);
       player.getInventory().setChestplate(chest);
       player.getInventory().setLeggings(pants);
       player.getInventory().setBoots(boots);
       while (player.getInventory().firstEmpty() != -1) {
           player.getInventory().addItem(new ItemStack[]{SPLASH_HEATH.clone()});
       }
	}
	
	public static void setArcher(Player player) {
		 ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack pants = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack bow = new ItemStack(Material.BOW);

        sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, maxSharpness);

        bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, maxPower);
        bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, maxFire);
        bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, maxInfinite);

        helm.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
        chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
        pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);

        helm.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
        chest.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
        pants.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
        boots.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);

        boots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, maxFF);

        player.getInventory().addItem(sword, bow, new ItemStack(Material.ENDER_PEARL, 16), new ItemStack(Material.COOKED_BEEF, 32), new ItemStack(Material.ARROW, 1), new ItemStack(Material.SUGAR, 32));

        player.getInventory().setHelmet(helm);
        player.getInventory().setChestplate(chest);
        player.getInventory().setLeggings(pants);
        player.getInventory().setBoots(boots);
        while (player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(new ItemStack[]{SPLASH_HEATH.clone()});
        }
	}

	public static void setBuilder(Player player) {
		ItemStack helm = new ItemStack(Material.DIAMOND_HELMET);
       ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
       ItemStack pants = new ItemStack(Material.DIAMOND_LEGGINGS);
       ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
       ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);

       sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, maxSharpness);

       helm.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
       chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
       pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);
       boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, maxProtection);

       helm.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
       chest.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
       pants.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);
       boots.addUnsafeEnchantment(Enchantment.DURABILITY, maxUnbreaking);

       boots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, maxFF);

       ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE);
       pick.addEnchantment(Enchantment.DIG_SPEED, 5);

       ItemStack spade = new ItemStack(Material.DIAMOND_SPADE);
       spade.addEnchantment(Enchantment.DIG_SPEED, 4);
       player.getInventory().addItem(sword,
               new ItemStack(Material.ENDER_PEARL, 16),
               new ItemStack(Material.COOKED_BEEF, 32),
               pick,
               spade,
               new ItemStack(Material.REDSTONE_BLOCK, 10),
               new ItemStack(Material.GLASS, 64),
               new ItemStack(Material.REDSTONE_COMPARATOR, 64),
               new ItemStack(Material.WOOD_PLATE, 64),
               new ItemStack(Material.FENCE, 64),
               new ItemStack(Material.COOKED_BEEF, 16),
               new ItemStack(Material.STRING, 64),
               new ItemStack(Material.DIRT, 64),
               new ItemStack(Material.COBBLESTONE, 64),
               new ItemStack(Material.STONE, 64),
               new ItemStack(Material.LOG, 64),
               new ItemStack(Material.WATER_BUCKET, 64),
               new ItemStack(Material.WOOL, 64),
               new ItemStack(Material.STAINED_GLASS, 64, (short) 5),
               new ItemStack(Material.COBBLESTONE_STAIRS, 64),
               new ItemStack(Material.QUARTZ, 64),
               new ItemStack(Material.GLOWSTONE),
               new ItemStack(98, 64),
               new ItemStack(Material.PISTON_BASE, 64),
               new ItemStack(Material.SLIME_BALL, 64),
               new ItemStack(Material.REDSTONE, 128),
               new ItemStack(Material.HOPPER, 64),
               new ItemStack(356, 64));
       player.getInventory().setHelmet(helm);
       player.getInventory().setChestplate(chest);
       player.getInventory().setLeggings(pants);
       player.getInventory().setBoots(boots);
       while (player.getInventory().firstEmpty() != -1) {
           player.getInventory().addItem(new ItemStack[]{SPLASH_HEATH.clone()});
       }
	}
	

}