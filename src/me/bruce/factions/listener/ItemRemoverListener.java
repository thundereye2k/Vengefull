package me.bruce.factions.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.bruce.factions.LorexHCF;

public class ItemRemoverListener implements Listener {

	private LorexHCF hcf;

	public ItemRemoverListener(LorexHCF hcf) {
		this.hcf = hcf;
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		Item item = e.getItemDrop();

		Bukkit.getScheduler().scheduleSyncDelayedTask(hcf, new Runnable() {
			@Override
			public void run() {
				item.remove();
			}
		}, 20L * 60L * 3L);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(hcf, new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < e.getDrops().size(); i++) {
					e.getDrops().remove(i);
				}
			}
		}, 20L * 60L * 3L);
	}

}
