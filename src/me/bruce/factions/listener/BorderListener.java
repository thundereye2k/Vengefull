package me.bruce.factions.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.ymls.SettingsYML;

public class BorderListener implements Listener {
	private static final int BORDER_OFFSET_TELEPORTS = 50;

	public static boolean isWithinBorder(final Location location) {
		final int borderSize = SettingsYML.BORDER_SIZES.get(location.getWorld().getEnvironment());
		return Math.abs(location.getBlockX()) <= borderSize && Math.abs(location.getBlockZ()) <= borderSize;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onCreaturePreSpawn(final CreatureSpawnEvent event) {
		if (!isWithinBorder(event.getLocation())) {
			event.setCancelled(true);
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onBucketEmpty(final PlayerBucketFillEvent event) {
		if (!isWithinBorder(event.getBlockClicked().getLocation())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "You cannot fill buckets past the border.");
		}
	}
    @EventHandler(ignoreCancelled = true)
    public void onPlayerPearl(final PlayerTeleportEvent e) {
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            final Player player = e.getPlayer();
            if (!isWithinBorder(e.getTo()) && (SettingsYML.KIT_MAP || !LorexHCF.getInstance().getEotwHandler().isEndOfTheWorld())) {
                LorexHCF.getInstance().getTimerManager().enderPearlTimer.refund(player);
                player.sendMessage(ChatColor.RED + "You cannot pearl outside the border");
                e.setCancelled(true);
            }
        }
    }

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onBucketEmpty(final PlayerBucketEmptyEvent event) {
		if (!isWithinBorder(event.getBlockClicked().getLocation())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "You cannot empty buckets past the border.");
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onBlockPlace(final BlockPlaceEvent event) {
		if (!isWithinBorder(event.getBlock().getLocation())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "You cannot place blocks past the border.");
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onBlockBreak(final BlockBreakEvent event) {
		if (!isWithinBorder(event.getBlock().getLocation())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "You cannot break blocks past the border.");
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onPlayerMove(final PlayerMoveEvent event) {
		final Location from = event.getFrom();
		final Location to = event.getTo();
		if (from.getBlockX() == to.getBlockX() && from.getBlockZ() == to.getBlockZ()) {
			return;
		}
		if (!isWithinBorder(to) && isWithinBorder(from)) {
			final Player player = event.getPlayer();
			player.sendMessage(ChatColor.RED + "You cannot go past the border.");
			event.setTo(from);
			final Entity vehicle = player.getVehicle();
			if (vehicle != null) {
				vehicle.eject();
				vehicle.teleport(from);
				vehicle.setPassenger(player);
			}
		}
	}

}
