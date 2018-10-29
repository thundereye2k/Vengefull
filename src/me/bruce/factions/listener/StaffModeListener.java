package me.bruce.factions.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.persistence.Entity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.bruce.factions.staff.StaffInventory;
import me.bruce.factions.staff.StaffModeCommand;

public class StaffModeListener implements Listener
{
	  private final Map<UUID, Location> fakeChestLocationMap;
	    
	    public StaffModeListener() {
	        this.fakeChestLocationMap = new HashMap<UUID, Location>();
	    }
	    
	    public static void handleFakeChest(final Player player, Chest chest, final boolean open) {
	        final Inventory chestInventory = chest.getInventory();
	        if (chestInventory instanceof DoubleChestInventory) {
	            chest = (Chest)((DoubleChestInventory)chestInventory).getHolder().getLeftSide();
	        }
	      //  ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutBlockAction(chest.getX(), chest.getY(), chest.getZ(), (Block)Blocks.CHEST, 1, (int)(open ? 1 : 0)));
	        player.playSound(chest.getLocation(), open ? Sound.CHEST_OPEN : Sound.CHEST_CLOSE, 1.0f, 1.0f);
	    }
	    
	    @EventHandler
	    public void onQuit(final PlayerQuitEvent event) {
	        if (StaffModeCommand.modMode.contains(event.getPlayer())) {
	            StaffModeCommand.leaveMod(event.getPlayer());
	        }
	    }
	    
	    @EventHandler
	    public void onGamemodeChange(final PlayerMoveEvent event) {
	        if (event.getPlayer().hasPermission("command.mod") && event.getPlayer().getGameMode() != GameMode.CREATIVE && StaffModeCommand.modMode.contains(event.getPlayer())) {
	            event.getPlayer().setGameMode(GameMode.CREATIVE);
	            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou may not switch gamemodes whilst in Staff Mode."));
	        }
	    }
	    
	    @EventHandler
	    public void oJoin(final PlayerJoinEvent event) {
	        if (event.getPlayer().hasPermission("core.mod")) {
	            StaffModeCommand.enterMod(event.getPlayer());
	            if (event.getPlayer().getGameMode().equals((Object)GameMode.SURVIVAL)) {
	                event.getPlayer().setGameMode(GameMode.CREATIVE);
	            }
	        }
	    }
	    
	    @EventHandler(priority = EventPriority.HIGHEST)
	    public void onEntityDamage(final EntityDamageEvent e) {
	        if (e.isCancelled()) {
	            return;
	        }
	        final Entity entity = (Entity) e.getEntity();
	        if (entity instanceof Player) {
	            final Player p = (Player)entity;
	            if (StaffModeCommand.modMode.contains(p)) {
	                e.setCancelled(true);
	            }
	        }
	    }
	    
	    @EventHandler
	    public void onDamage(final EntityDamageEvent e) {
	        if (!(e.getEntity() instanceof Player)) {
	            return;
	        }
	        final Player p = (Player)e.getEntity();
	        if (StaffModeCommand.modMode.contains(p)) {
	            e.setCancelled(true);
	        }
	    }
	    
	    @EventHandler
	    public void onLeave(final PlayerQuitEvent event) {
	        if (StaffModeCommand.modMode.contains(event.getPlayer().getName())) {
	            event.getPlayer().getInventory().clear();
	            event.getPlayer().setGameMode(GameMode.SURVIVAL);
	            StaffModeCommand.modMode.remove(event.getPlayer().getName());
	        }
	    }
	    
	    @EventHandler
	    public void onBreak(final BlockBreakEvent e) {
	        final Player p = e.getPlayer();
	        if (StaffModeCommand.modMode.contains(p)) {
	            e.setCancelled(true);
	        }
	    }
	    
	    @EventHandler
	    public void onBreak(final InventoryClickEvent e) {
	        final Player p = (Player)e.getWhoClicked();
	        if (StaffModeCommand.modMode.contains(p)) {
	            e.setCancelled(true);
	        }
	    }
	    
	    @EventHandler
	    public void onPlace(final BlockPlaceEvent e) {
	        final Player p = e.getPlayer();
	        if (StaffModeCommand.modMode.contains(p) && p.getGameMode().equals((Object)GameMode.CREATIVE)) {
	            e.setCancelled(true);
	        }
	    }
	    
	    @EventHandler
	    public void onPick(final PlayerPickupItemEvent e) {
	        if (StaffModeCommand.modMode.contains(e.getPlayer())) {
	            e.setCancelled(true);
	        }
	    }
	    
	    @EventHandler
	    public void onRecord(final PlayerInteractEvent e) {
	        final Player p = e.getPlayer();
	        if (StaffModeCommand.modMode.contains(p) && p.getItemInHand().getType() == Material.SKULL_ITEM && e.getAction().toString().contains("RIGHT")) {
	        	p.chat("/randomtp");
	            }
	        }
	    
	    
	    @EventHandler
	    public void onVanish(final PlayerInteractEvent e) {
	        final Player p = e.getPlayer();
	        if (StaffModeCommand.modMode.contains(p) && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§eVanish: §aOn") && e.getAction().toString().contains("RIGHT")) {
	            p.chat("/v");
	            final ItemStack carpet = new ItemStack(351, 1, (short)10);
	            final ItemMeta carpetMeta = carpet.getItemMeta();
	            carpetMeta.setDisplayName("§eVanish: §cOff");
	            carpet.setItemMeta(carpetMeta);
	            p.getInventory().setItemInHand(carpet);
	        }
	        else if (StaffModeCommand.modMode.contains(p) && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§eVanish: §cOff") && e.getAction().toString().contains("RIGHT")) {
	        	p.chat("/v");
	            final ItemStack carpet = new ItemStack(351, 1, (short)8);
	            final ItemMeta carpetMeta = carpet.getItemMeta();
	            carpetMeta.setDisplayName("§eVanish: §aOn");
	            carpet.setItemMeta(carpetMeta);
	            p.getInventory().setItemInHand(carpet);
	        }
	    }
	    
	    @EventHandler
	    public void onCLick(final InventoryClickEvent e) {
	        if (e.getInventory().getTitle().startsWith(ChatColor.translateAlternateColorCodes('&', "&eInspecting: "))) {
	            e.setCancelled(true);
	        }
	    }
	    
	    @EventHandler
	    public void rightClick2(final PlayerInteractEntityEvent e) {
	        if (!(e.getRightClicked() instanceof Player)) {
	            return;
	        }
	        final Player staff = e.getPlayer();
	        final Player p = (Player)e.getRightClicked();
	        if (StaffModeCommand.modMode.contains(staff) && staff.getGameMode() == GameMode.CREATIVE && p instanceof Player && staff instanceof Player && staff.getItemInHand().getType() == Material.BOOK) {
	            StaffInventory.inspector(staff, p);
	        }
	    }
	    
	    @EventHandler
	    public void onTag(final EntityDamageByEntityEvent e) {
	        if (!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) {
	            return;
	        }
	        final Player staff = (Player)e.getDamager();
	        if (StaffModeCommand.modMode.contains(staff.getName())) {
	            e.setCancelled(true);
	        }
	    }
	    
	    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	    public void onPlayerInteract(final PlayerInteractEvent event) {
	        final Player player = event.getPlayer();
	        final UUID uuid = player.getUniqueId();
	        switch (event.getAction()) {
	            case LEFT_CLICK_BLOCK: {
	                if (StaffModeCommand.modMode.contains(player)) {
	                    event.setCancelled(true);
	                    break;
	                }
	                break;
	            }
	            case RIGHT_CLICK_BLOCK: {
	                final org.bukkit.block.Block block = event.getClickedBlock();
	                final BlockState state = block.getState();
	                if (!(state instanceof Chest) || !StaffModeCommand.modMode.contains(player)) {
	                    break;
	                }
	                final Chest chest = (Chest)state;
	                final Location chestLocation = chest.getLocation();
	                final InventoryType type = chest.getInventory().getType();
	                if (type == InventoryType.CHEST && this.fakeChestLocationMap.putIfAbsent(uuid, chestLocation) == null) {
	                    final ItemStack[] contents = chest.getInventory().getContents();
	                    final Inventory fakeInventory = Bukkit.createInventory((InventoryHolder)null, contents.length, ChatColor.YELLOW + "[Silent] " + type.getDefaultTitle());
	                    fakeInventory.setContents(contents);
	                    event.setCancelled(true);
	                    player.openInventory(fakeInventory);
	                    handleFakeChest(player, chest, true);
	                    this.fakeChestLocationMap.put(uuid, chestLocation);
	                    break;
	                }
	                break;
	            }
	        }
	    }
	    
	    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	    public void onInventoryClose(final InventoryCloseEvent event) {
	        final Player player = (Player)event.getPlayer();
	        final Location chestLocation = this.fakeChestLocationMap.remove(player.getUniqueId());
	        final BlockState blockState;
	        if (chestLocation != null && (blockState = chestLocation.getBlock().getState()) instanceof Chest) {
	            handleFakeChest(player, (Chest)blockState, false);
	        }
	    }
	    
	    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	    public void onInventoryClick(final InventoryClickEvent event) {
	        final HumanEntity humanEntity = event.getWhoClicked();
	        final Player player;
	        final ItemStack stack;
	        if (humanEntity instanceof Player && this.fakeChestLocationMap.containsKey((player = (Player)humanEntity).getUniqueId()) && (stack = event.getCurrentItem()) != null && stack.getType() != Material.AIR && !player.hasPermission("vanish.chestinteract")) {
	            event.setCancelled(true);
	            player.sendMessage(ChatColor.RED + "You cannot interact with this.");
	        }
	    }
	}

