package me.bruce.factions.listener;

import org.bukkit.event.entity.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.*;
import org.bukkit.potion.*;
import org.bukkit.scheduler.*;

import me.bruce.factions.*;

import org.bukkit.plugin.*;
import org.bukkit.event.*;

public class PotListener implements Listener {
	private final LorexHCF hcf;

	public PotListener(final LorexHCF hcf) {
		this.hcf = hcf;
	}

	@EventHandler(ignoreCancelled = true)
	private void onPlayerThrowPot(final ProjectileLaunchEvent event) {
		if (Bukkit.getPluginManager().isPluginEnabled("FastPot")) {
			return;
		}
		if (!(event.getEntity() instanceof ThrownPotion) || !(event.getEntity().getShooter() instanceof Player)) {
			return;
		}
		final Player player = (Player) event.getEntity().getShooter();
		final ThrownPotion potion = (ThrownPotion) event.getEntity();
		if (!player.isDead() && player.isSprinting() && ((CraftThrownPotion) potion).getHandle().motY < 0.4) {
			for (final PotionEffect potionEffect : potion.getEffects()) {
				if (potionEffect.getType().equals(PotionEffectType.HEAL)) {
					new BukkitRunnable() {
						@Override
						public void run() {
							if (potion.isValid() && !player.isDead()) {
								potion.eject();
							}
						}
					}.runTaskLater(this.hcf, player.isOnGround() ? 3L : 5L);
					break;
				}
			}
		}
	}
}
