package me.bruce.factions.reboot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import me.bruce.factions.LorexHCF;

public class reboottimer {
	@Getter
	private rebootrunnable rebootrunnable;

	public boolean cancel() {
		if (this.rebootrunnable != null) {
			this.rebootrunnable.cancel();
			this.rebootrunnable = null;
			return true;
		}

		return false;
	}

	public void start(long millis) {
		if (this.rebootrunnable == null) {
			this.rebootrunnable = new rebootrunnable(this, millis);
			this.rebootrunnable.runTaskLater(LorexHCF.getInstance(), millis / 50L);
		}
	}

	public static class rebootrunnable extends BukkitRunnable {

		private reboottimer reboottimer;
		private long startMillis;
		private long endMillis;

		public rebootrunnable(reboottimer reboottimer, long duration) {
			this.reboottimer = reboottimer;
			this.startMillis = System.currentTimeMillis();
			this.endMillis = this.startMillis + duration;
		}

		public long getRemaining() {
			return endMillis - System.currentTimeMillis();
		}

		@Override
		public void run() {
			Bukkit.broadcastMessage(("§7§m--------------------------------"));
			Bukkit.broadcastMessage((ChatColor.translateAlternateColorCodes('&', "§7The Server is &6&lREBOOTING&7.")));
			Bukkit.broadcastMessage(("§7§m--------------------------------"));
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
			this.cancel();
			this.reboottimer.rebootrunnable = null;
		}
	}

	public rebootrunnable getrebootrunnable() {
		return this.rebootrunnable;
	}
}


