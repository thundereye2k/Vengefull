package me.bruce.factions.listener;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.material.EnderChest;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import LorexMC.us.utils.JavaUtils;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.struct.Role;
import me.bruce.factions.faction.type.Faction;
import me.bruce.factions.ymls.SettingsYML;

/**
 * Listener that handles events for the {@link World} such as explosions.
 */
public class WorldListener implements Listener {

	public static final String DEFAULT_WORLD_NAME = "world";

	private final LorexHCF plugin;

	public WorldListener(LorexHCF plugin) {
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = false, priority = EventPriority.HIGH)
	public void onEntityExplode(EntityExplodeEvent event) {
		event.blockList().clear();
		if (event.getEntity() instanceof EnderDragon) {
			event.setCancelled(true);
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onBlockChange(BlockFromToEvent event) {
		if (event.getBlock().getType() == Material.DRAGON_EGG) {
			event.setCancelled(true);
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onEntityPortalEnter(EntityPortalEvent event) {
		if (event.getEntity() instanceof EnderDragon) {
			event.setCancelled(true);
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onWitherChangeBlock(EntityChangeBlockEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Wither || entity instanceof EnderDragon) {
			event.setCancelled(true);
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onBlockFade(BlockFadeEvent event) {
		switch (event.getBlock().getType()) {
		case SNOW:
		case ICE:
			event.setCancelled(true);
			break;
		default:
			break;
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onPlayerSpawn(PlayerSpawnLocationEvent event) {
		Player player = event.getPlayer();

		if (!player.hasPlayedBefore()) {
			plugin.getEconomyManager().addBalance(player.getUniqueId(), 500);
			FactionListener.balance.add(player.getUniqueId());// give player some starting money
			
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onInventoryOpen(InventoryOpenEvent event) {
		if (event.getInventory() instanceof EnderChest) {
			event.setCancelled(true);
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onBlockIgnite(BlockIgniteEvent event) {
		if (event.getCause() == BlockIgniteEvent.IgniteCause.SPREAD) {
			event.setCancelled(true);
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if (event.getEntity() instanceof Squid) {
			event.setCancelled(true);
		}
	}
}
