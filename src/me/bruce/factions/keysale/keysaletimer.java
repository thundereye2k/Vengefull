package me.bruce.factions.keysale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.startoftheworld.SotwTimer;
import me.bruce.factions.startoftheworld.SotwTimer.SotwRunnable;

public class keysaletimer {
	
	@Getter
	private keysalerunnable keysalerunnable;

	public boolean cancel() {
		if (this.keysalerunnable != null) {
			this.keysalerunnable.cancel();
			this.keysalerunnable = null;
			return true;
		}

		return false;
	}

	public void start(long millis) {
		if (this.keysalerunnable == null) {
			this.keysalerunnable = new keysalerunnable(this, millis);
			this.keysalerunnable.runTaskLater(LorexHCF.getInstance(), millis / 50L);
		}
	}

	public static class keysalerunnable extends BukkitRunnable {

		private keysaletimer keysaletimer;
		private long startMillis;
		private long endMillis;

		public keysalerunnable(keysaletimer keysaletimer, long duration) {
			this.keysaletimer = keysaletimer;
			this.startMillis = System.currentTimeMillis();
			this.endMillis = this.startMillis + duration;
		}

		public long getRemaining() {
			return endMillis - System.currentTimeMillis();
		}

		@Override
		public void run() {
			Bukkit.broadcastMessage(("§7§m--------------------------------"));
			Bukkit.broadcastMessage((ChatColor.translateAlternateColorCodes('&', "§7The &6&lKEYSALE &7HAS ENDED")));
			Bukkit.broadcastMessage(("§7§m--------------------------------"));
			this.cancel();
			this.keysaletimer.keysalerunnable = null;
		}
	}

	public keysalerunnable getkeysalerunnable() {
		return this.keysalerunnable;
	}
}

