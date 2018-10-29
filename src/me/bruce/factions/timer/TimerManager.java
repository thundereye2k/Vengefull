package me.bruce.factions.timer;

import lombok.Data;
import lombok.Getter;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.eventutils.EventTimer;
import me.bruce.factions.timer.type.*;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import LorexMC.us.utils.Config;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class TimerManager implements Listener {

	@Getter
	public final CombatTimer combatTimer;

	@Getter
	private final LogoutTimer logoutTimer;

	@Getter
	public final EnderPearlTimer enderPearlTimer;

	@Getter
	private final EventTimer eventTimer;

	@Getter
	public final GappleTimer gappleTimer;

	
    @Getter
    private final InvincibilityTimer pvpTimer;
    
	@Getter
	public final ArcherTimer archerTimer;

	@Getter
	private final ClassLoad pvpClassWarmupTimer;

	@Getter
	private final StuckTimer stuckTimer;

	@Getter
	public final TeleportTimer teleportTimer;
	

	@Getter
	private final Set<Timer> timers = new LinkedHashSet<>();

	private final JavaPlugin plugin;
	private Config config;

	public Object pvpProtectionTimer;

	public TimerManager(LorexHCF plugin) {
		(this.plugin = plugin).getServer().getPluginManager().registerEvents(this, plugin);
		this.registerTimer(this.enderPearlTimer = new EnderPearlTimer(plugin));
		this.registerTimer(this.logoutTimer = new LogoutTimer());
		this.registerTimer(this.pvpTimer = new InvincibilityTimer(plugin));
		this.registerTimer(this.gappleTimer = new GappleTimer(plugin));
		this.registerTimer(this.stuckTimer = new StuckTimer());
		this.registerTimer(this.combatTimer = new CombatTimer(plugin));
		this.registerTimer(this.teleportTimer = new TeleportTimer(plugin));
		this.registerTimer(this.eventTimer = new EventTimer(plugin));
		this.registerTimer(this.pvpClassWarmupTimer = new ClassLoad(plugin));
		this.registerTimer(this.archerTimer = new ArcherTimer(plugin));
		this.reloadTimerData();
	}

	public void registerTimer(Timer timer) {
		this.timers.add(timer);
		if (timer instanceof Listener) {
			this.plugin.getServer().getPluginManager().registerEvents((Listener) timer, this.plugin);
		}
	}

	public void unregisterTimer(Timer timer) {
		this.timers.remove(timer);
	}

	/**
	 * Reloads the {@link Timer} data from storage.
	 */
	public void reloadTimerData() {
		this.config = new Config(plugin, "timers");
		for (Timer timer : this.timers) {
			timer.load(this.config);
		}
	}

	/**
	 * Saves the {@link Timer} data to storage.
	 */
	public void saveTimerData() {
		for (Timer timer : this.timers) {
			timer.onDisable(this.config);
		}

		this.config.save();
	}

	public CombatTimer getCombatTimer() {
		return this.combatTimer;
	}

	public GappleTimer getGappleTimer() {
		return this.gappleTimer;
	}

	public LogoutTimer getLogoutTimer() {
		return this.logoutTimer;
	}

	public EnderPearlTimer getEnderPearlTimer() {
		return this.enderPearlTimer;
	}

	public EventTimer getEventTimer() {
		return this.eventTimer;
	}
	public InvincibilityTimer getInvincibilityTimer() {
		return this.pvpTimer;
	}

	public StuckTimer getStuckTimer() {
		return this.stuckTimer;
	}

	public TeleportTimer getTeleportTimer() {
		return this.teleportTimer;
	}

	public Collection<Timer> getTimers() {
		return this.timers;
	}

}
