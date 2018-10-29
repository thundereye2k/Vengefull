package me.bruce.factions.listener.fixes;

import org.bukkit.event.player.*;

import me.bruce.factions.LorexHCF;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class EnderpearlRefundListener implements Listener {
	private final LorexHCF hcf;

	public EnderpearlRefundListener(final LorexHCF hcf) {
		this.hcf = hcf;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerPearl(final PlayerTeleportEvent event) {
		if (event.isCancelled() && event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
			final Player player = event.getPlayer();
			if (this.hcf.getTimerManager().enderPearlTimer.getRemaining(player) > 0L) {
				this.hcf.getTimerManager().enderPearlTimer.refund(player);
			}
		}
	}
}
