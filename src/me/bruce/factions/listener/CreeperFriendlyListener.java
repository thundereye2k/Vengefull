package me.bruce.factions.listener;

import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class CreeperFriendlyListener implements Listener {

	@EventHandler
	public void onAgro(EntityExplodeEvent evt) {
		if (evt.getEntity() instanceof Creeper) {
			evt.setCancelled(true);
		}
	}
}
