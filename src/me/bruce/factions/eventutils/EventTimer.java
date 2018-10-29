package me.bruce.factions.eventutils;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.event.CaptureZoneEnterEvent;
import me.bruce.factions.faction.event.CaptureZoneLeaveEvent;
import me.bruce.factions.faction.type.ConquestFaction;
import me.bruce.factions.faction.type.EventFaction;
import me.bruce.factions.faction.type.Faction;
import me.bruce.factions.faction.type.KothFaction;
import me.bruce.factions.faction.type.PlayerFaction;
import me.bruce.factions.listener.DateTimeFormats;
import me.bruce.factions.listener.EventSignListener;
import me.bruce.factions.timer.GlobalTimer;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//--------------------------
//Event Timer Coded By 
// InspectMC
//--------------------------

public class EventTimer extends GlobalTimer implements Listener {

	private static final long RESCHEDULE_FREEZE_MILLIS;
	private static final String RESCHEDULE_FREEZE_WORDS;
	private long startStramp;
	private long lastContestedEventMillis;
	private EventFaction eventFaction;
	private final LorexHCF plugin;

	public EventFaction getEventFaction() {
		return this.eventFaction;

	}

	public EventTimer(final LorexHCF plugin) {
		super("Event", 0L);
		this.plugin = plugin;
		new BukkitRunnable() {
			@Override
			public void run() {
				if (EventTimer.this.eventFaction != null) {
					EventTimer.this.eventFaction.getEventType().getEventTracker().tick(EventTimer.this,
							EventTimer.this.eventFaction); // shouldnt be erroring ill look in a second.
					return;
				}
				final LocalDateTime now = LocalDateTime.now(DateTimeFormats.SERVER_ZONE_ID);
				final int day = now.getDayOfYear();
				final int hour = now.getHour();
				final int minute = now.getMinute();
				for (final Map.Entry<LocalDateTime, String> entry : plugin.getEventScheduler().getScheduleMap()
						.entrySet()) {
					final LocalDateTime schduledTime = entry.getKey();
					if (day == schduledTime.getDayOfYear() && hour == schduledTime.getHour()) {
						if (minute != schduledTime.getMinute()) {
							continue;
						}
						final Faction faction = plugin.getFactionManager().getFaction(entry.getValue());
						if (faction instanceof EventFaction
								&& EventTimer.this.tryContesting((EventFaction) faction, Bukkit.getConsoleSender())) {
							break;
						}
						continue;
					}
				}
			}
		}.runTaskTimer(plugin, 20L, 20L);
	}

	@Override
	public String getScoreboardPrefix() {
		return ChatColor.BLUE + ChatColor.BOLD.toString();
	}

	@Override
	public String getName() {
		return (this.eventFaction == null) ? "Event" : this.eventFaction.getName();
	}

	@Override
	public boolean clearCooldown() {
		boolean result = super.clearCooldown();
		if (this.eventFaction != null) {
			for (final CaptureZone captureZone : this.eventFaction.getCaptureZones()) {
				captureZone.setCappingPlayer(null);
			}
			this.eventFaction.setDeathban(true);
			this.eventFaction.getEventType().getEventTracker().stopTiming();
			this.eventFaction = null;
			result = true;
		}
		return result;
	}

	@Override
	public long getRemaining() {
		if (this.eventFaction == null) {
			return 0L;
		}
		if (this.eventFaction instanceof KothFaction) {
			return ((KothFaction) this.eventFaction).getCaptureZone().getRemainingCaptureMillis();
		}
		return super.getRemaining();
	}

	public void handleWinner(final Player winner) {
		if (this.eventFaction == null) {
			return;
		}
		final PlayerFaction playerFaction = this.plugin.getFactionManager().getPlayerFaction(winner);
		Bukkit.broadcastMessage(ChatColor.DARK_RED + winner.getName() + ChatColor.GRAY + " has captured the koth "
				+ ChatColor.RED + this.eventFaction.getName());
		final EventType eventType = this.eventFaction.getEventType();
		final World world = winner.getWorld();
		final Location location = winner.getLocation();
		if (this.eventFaction.getName().contains("Conquest")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate givekey " + winner.getName() + " Conquest 1");
		}
		if (this.eventFaction.getName().contains("Koth")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate givekey " + winner.getName() + " Summer 5");
		} else {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate givekey " + winner.getName() + " Summer 5");
		}
		final Map<Integer, ItemStack> excess = winner.getInventory().addItem(
				new ItemStack[] { EventSignListener.getEventSign(this.eventFaction.getName(), winner.getName()) });
		for (final ItemStack entry : excess.values()) {
			world.dropItemNaturally(location, entry);
		}
		clearCooldown();
	}

	public boolean tryContesting(final EventFaction eventFaction, final CommandSender sender) {
		if (this.eventFaction != null) {
			sender.sendMessage(ChatColor.RED + "An event is already happening. Use /event cancel to end it.");
			return false;
		}

		if (eventFaction instanceof KothFaction) {
			final KothFaction kothFaction = (KothFaction) eventFaction;
			if (kothFaction.getCaptureZone() == null) {
				sender.sendMessage(ChatColor.RED + "Cannot schedule " + eventFaction.getName()
						+ " as it's capturezone is not set.");
				return false;
			}
		}

		else if (eventFaction instanceof ConquestFaction) {
			final ConquestFaction conquestFaction = (ConquestFaction) eventFaction;
			final Collection<ConquestFaction.ConquestZone> zones = conquestFaction.getConquestZones();
			for (final ConquestFaction.ConquestZone zone : ConquestFaction.ConquestZone.values()) {
				if (!zones.contains(zone)) {
					sender.sendMessage(ChatColor.RED + "Cannot schedule " + eventFaction.getName() + " as capturezone '"
							+ zone.getDisplayName() + ChatColor.RED + "' is not set.");
					return false;
				}
			} // this part
		}

		final long millis = System.currentTimeMillis();
		if (this.lastContestedEventMillis + EventTimer.RESCHEDULE_FREEZE_MILLIS - millis > 0L) {
			sender.sendMessage(ChatColor.RED + "Cannot reschedule events within " + RESCHEDULE_FREEZE_WORDS + '.');
			return false;
		}
		this.lastContestedEventMillis = millis;
		this.startStramp = millis;
		this.eventFaction = eventFaction;
		eventFaction.getEventType().getEventTracker().onContest(eventFaction, this);
		if (eventFaction instanceof ConquestFaction) {
			this.setRemaining(1000L, true);
			this.setPaused(true);
		}
		final Collection<CaptureZone> captureZones = eventFaction.getCaptureZones();
		for (final CaptureZone captureZone : captureZones) {
			if (captureZone.isActive()) {
				final Player player = Iterables.getFirst(captureZone.getCuboid().getPlayers(), null);
				if (player == null
						|| !eventFaction.getEventType().getEventTracker().onControlTake(player, captureZone)) {
					continue;
				}
				captureZone.setCappingPlayer(player);
			}
		}
		eventFaction.setDeathban(true);
		return true;
	}

	public long getUptime() {
		return System.currentTimeMillis() - this.startStramp;
	}

	public long getStartStamp() {
		return this.startStramp;
	}

	public void handleDisconnect(final Player player) {
		Preconditions.checkNotNull(player);
		if (this.eventFaction == null) {
			return;
		}
		final Collection<CaptureZone> captureZones = this.eventFaction.getCaptureZones();
		for (final CaptureZone captureZone : captureZones) {
			if (Objects.equal(captureZone.getCappingPlayer(), player)) {
				captureZone.setCappingPlayer(null);
				this.eventFaction.getEventType().getEventTracker().onControlLoss(player, captureZone,
						this.eventFaction);
				break;

			}
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerDeath(final PlayerDeathEvent event) {
		this.handleDisconnect(event.getEntity());
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerLogout(final PlayerQuitEvent event) {
		this.handleDisconnect(event.getPlayer());

	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerTeleport(final PlayerTeleportEvent event) {
		this.handleDisconnect(event.getPlayer());
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerPortal(final PlayerPortalEvent event) {
		this.handleDisconnect(event.getPlayer());
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onCaptureZoneEnter(final CaptureZoneEnterEvent event) {
		if (this.eventFaction == null) {
			return;
		}
		final CaptureZone captureZone = event.getCaptureZone();
		if (!this.eventFaction.getCaptureZones().contains(captureZone)) {
			return;
		}
		final Player player = event.getPlayer();
		if (captureZone.getCappingPlayer() == null
				&& this.eventFaction.getEventType().getEventTracker().onControlTake(player, captureZone)) {
			captureZone.setCappingPlayer(player);
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onCaptureZoneLeave(final CaptureZoneLeaveEvent event) {
		if (Objects.equal(event.getFaction(), this.eventFaction)) {
			final Player player = event.getPlayer();
			final CaptureZone captureZone = event.getCaptureZone();
			if (Objects.equal(player, captureZone.getCappingPlayer())) {
				captureZone.setCappingPlayer(null);
				this.eventFaction.getEventType().getEventTracker().onControlLoss(player, captureZone,
						this.eventFaction);
				for (final Player target : captureZone.getCuboid().getPlayers()) {
					if (target != null && !target.equals(player)
							&& this.eventFaction.getEventType().getEventTracker().onControlTake(target, captureZone)) {
						captureZone.setCappingPlayer(target);
						break;
					}
				}
			}
		}
	}

	static {
		RESCHEDULE_FREEZE_MILLIS = TimeUnit.SECONDS.toMillis(15L);
		RESCHEDULE_FREEZE_WORDS = DurationFormatUtils.formatDurationWords(EventTimer.RESCHEDULE_FREEZE_MILLIS, true,
				true);
	}
}
