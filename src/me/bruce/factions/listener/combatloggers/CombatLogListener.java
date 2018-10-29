package me.bruce.factions.listener.combatloggers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.bruce.factions.LorexHCF;

public class CombatLogListener implements Listener {
	private static final int NEARBY_SPAWN_RADIUS = 64;
	private final Set<UUID> safelyDisconnected = new HashSet();
	private final Map<UUID, LoggerEntity> loggers = new HashMap();
	private final LorexHCF plugin;

	public CombatLogListener(LorexHCF plugin) {
		this.plugin = plugin;
	}

	public void safelyDisconnect(Player player, String reason) {
		if (this.safelyDisconnected.add(player.getUniqueId())) {
			player.kickPlayer(reason);
		}
	}

	public boolean removeCombatLogger(UUID uuid) {
		LoggerEntity entity = this.loggers.remove(uuid);
		if (entity != null) {
			entity.getBukkitEntity().setHealth(0.0D);
			entity.destroy();
			return true;
		}
		return false;
	}

	public void removeCombatLoggers() {
		Iterator<LoggerEntity> iterator = this.loggers.values().iterator();
		while (iterator.hasNext()) {
			iterator.next().destroy();
			iterator.remove();
		}
		this.safelyDisconnected.clear();
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onLoggerRemoved(LoggerRemovedEvent event) {
		this.loggers.remove(event.getLoggerEntity().getUniqueID());
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerLogin(PlayerLoginEvent event) {
		LoggerEntity currentLogger = this.loggers.remove(event.getPlayer().getUniqueId());
		if (currentLogger != null) {
			currentLogger.destroy();
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		boolean result = this.safelyDisconnected.remove(uuid);
		if ((!result) && (player.getGameMode() != GameMode.CREATIVE) && (!player.isDead())) {
			if (this.loggers.containsKey(player.getUniqueId())) {
				return;
			}
			Location location = player.getLocation();
			if (this.plugin.getFactionManager().getFactionAt(location).isSafezone()) {
				return;

			}
			final LoggerEntity loggerEntity = new LoggerEntityHuman(player, location.getWorld());

			LoggerSpawnEvent calledEvent = new LoggerSpawnEvent(loggerEntity);
			Bukkit.getPluginManager().callEvent(calledEvent);
			if (!calledEvent.isCancelled()) {
				this.loggers.put(player.getUniqueId(), loggerEntity);

				new BukkitRunnable() {
					@Override
					public void run() {
						if (!player.isOnline()) {
							loggerEntity.postSpawn(CombatLogListener.this.plugin);
						}
					}
				}

						.runTaskLater(this.plugin, 1L);
			}
		}
	}
}
