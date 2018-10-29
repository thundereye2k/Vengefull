package me.bruce.factions.startoftheworld;

import lombok.Getter;
import me.bruce.factions.LorexHCF;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SotwTimer {

	@Getter
	private SotwRunnable sotwRunnable;

	public boolean cancel() {
		if (this.sotwRunnable != null) {
			this.sotwRunnable.cancel();
			this.sotwRunnable = null;
			return true;
		}

		return false;
	}

	public void start(long millis) {
		if (this.sotwRunnable == null) {
			this.sotwRunnable = new SotwRunnable(this, millis);
			this.sotwRunnable.runTaskLater(LorexHCF.getInstance(), millis / 50L);
		}
	}

	public static class SotwRunnable extends BukkitRunnable {

		private SotwTimer sotwTimer;
		private long startMillis;
		private long endMillis;

		public SotwRunnable(SotwTimer sotwTimer, long duration) {
			this.sotwTimer = sotwTimer;
			this.startMillis = System.currentTimeMillis();
			this.endMillis = this.startMillis + duration;
		}

		public long getRemaining() {
			return endMillis - System.currentTimeMillis();
		}

		@Override
		public void run() {
			this.cancel();
			this.sotwTimer.sotwRunnable = null;
			Bukkit.broadcastMessage(("§7§m--------------------------------"));
			Bukkit.broadcastMessage(("§7The §6§lSOTW §7has ended. §6§lGOOD LUCK§7."));
			Bukkit.broadcastMessage(("§7§m--------------------------------"));
            for (final UUID p : SotwCommand.enabled) {
                SotwCommand.enabled.remove(p);
            }
		}
	}

	public SotwRunnable getSotwRunnable() {
		return this.sotwRunnable;
	}
}
