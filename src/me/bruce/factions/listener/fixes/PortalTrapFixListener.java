package me.bruce.factions.listener.fixes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.bruce.factions.LorexHCF;

/**
 * Listener that prevents {@link Player}s from being trapped in portals.
 */
public class PortalTrapFixListener implements Listener {
	private final LorexHCF plugin;

	public PortalTrapFixListener(LorexHCF plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getClickedBlock() == null)
			return;
		if (LorexHCF.getInstance().getFactionManager().getFactionAt(e.getClickedBlock().getLocation()).isSafezone())
			return;
		if (e.getClickedBlock().getType() == Material.PORTAL) {
			e.getClickedBlock().setType(Material.AIR);
			e.getPlayer()
					.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYou have &cdisabled &ethis portal&e."));
		}
	}

}
