package me.bruce.factions.listener.fixes;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.type.ClaimableFaction;
import me.bruce.factions.faction.type.Faction;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.InventoryHolder;

public class PearlGlitchListener implements Listener {
	private final ImmutableSet<Material> blockedPearlTypes;
	private final LorexHCF plugin;

	public PearlGlitchListener(final LorexHCF plugin) {
		this.blockedPearlTypes = Sets.immutableEnumSet((Enum) Material.THIN_GLASS,
				(Enum[]) new Material[] { Material.IRON_FENCE, Material.FENCE, Material.NETHER_FENCE,
						Material.FENCE_GATE, Material.ACACIA_STAIRS, Material.BIRCH_WOOD_STAIRS, Material.BRICK_STAIRS,
						Material.COBBLESTONE_STAIRS, Material.DARK_OAK_STAIRS, Material.JUNGLE_WOOD_STAIRS,
						Material.NETHER_BRICK_STAIRS, Material.QUARTZ_STAIRS, Material.SANDSTONE_STAIRS,
						Material.SMOOTH_STAIRS, Material.SPRUCE_WOOD_STAIRS, Material.WOOD_STAIRS });
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onPlayerInteract(final PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.hasItem()
				&& event.getItem().getType() == Material.ENDER_PEARL) {
			final Block block = event.getClickedBlock();
			if (block.getType().isSolid() && !(block.getState() instanceof InventoryHolder)) {
				final Faction factionAt = LorexHCF.getInstance().getFactionManager().getFactionAt(block.getLocation());
				if (!(factionAt instanceof ClaimableFaction)) {
					return;
				}
				event.setCancelled(true);
				final Player player = event.getPlayer();
				player.setItemInHand(event.getItem());
			}
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPearlClip(final PlayerTeleportEvent event) {
		if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
			final Location to = event.getTo();
			final Block block = to.getBlock();
			final Material type = block.getType();
			if (this.blockedPearlTypes.contains(type)) {
				final Block above = block.getRelative(BlockFace.UP);
				final Material aboveType = above.getType();
				if (this.blockedPearlTypes.contains(aboveType) || aboveType.isSolid()) {
					final Player player = event.getPlayer();
					player.sendMessage(ChatColor.YELLOW + "Pearl glitching detected, your pearl has been refunded");
					event.setCancelled(true);
				} else {
					to.add(0.0, 1.0, 0.0);
					event.setTo(to);
				}
			}
		}
	}
}
