package me.bruce.factions.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.type.Faction;

public class GlowstoneListener implements Listener {

	private LorexHCF hcf;

	public GlowstoneListener(LorexHCF hcf) {
		this.hcf = hcf;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Faction faction = hcf.getFactionManager().getFactionAt(e.getBlock());
		if (e.getBlock().getType().equals(Material.GLOWSTONE)
				&& e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("world_nether")) {
			if (faction.getName().equalsIgnoreCase("GlowstoneMountain")) {
				e.setCancelled(true);
				e.getBlock().setType(Material.BEDROCK);
				Bukkit.getScheduler().scheduleSyncDelayedTask(hcf, new Runnable() {
					@Override
					public void run() {
						if (e.getBlock().getType().equals(Material.AIR)) {
							e.getBlock().setType(Material.GLOWSTONE);
						}
					}
				}, 20L * 60L * 10L);
				p.getInventory().addItem(new ItemStack(Material.GLOWSTONE));
			}
		}
	}

}
