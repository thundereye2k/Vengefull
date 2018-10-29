package me.bruce.factions.listener;

import com.google.common.collect.Lists;

import LorexMC.us.utils.JavaUtils;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.economy.EconomyManager;
import me.bruce.factions.faction.struct.Role;
import me.bruce.factions.faction.type.Faction;
import me.bruce.factions.faction.type.PlayerFaction;
import me.bruce.factions.ymls.SettingsYML;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Listener that handles deaths by spawning a sign containing information about
 * the death.
 */
public class DeathSignListener implements Listener {

	private final static String DEATH_SIGN_ITEM_NAME = ChatColor.GOLD + "Death Sign";
	private static final long BASE_REGEN_DELAY = TimeUnit.MINUTES.toMillis(40L);

	private final LorexHCF plugin;

	public DeathSignListener(LorexHCF plugin) {
		this.plugin = plugin;
		if (!plugin.getConfig().getBoolean("death-signs", true)) {
			Bukkit.getScheduler().runTaskLater(plugin, () -> HandlerList.unregisterAll(this), 5L);
		}
	}

	/**
	 * Generates a death-sign for a player and killer.
	 *
	 * @param playerName
	 *            the name of the player killed
	 * @param killerName
	 *            the name of the killer of player
	 * @return the death sign featuring the victim and murderer
	 */
	public static ItemStack getDeathSign(String playerName, String killerName) {
		ItemStack stack = new ItemStack(Material.SIGN, 1);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(DEATH_SIGN_ITEM_NAME);
		meta.setLore(Lists.newArrayList(ChatColor.RED + playerName, ChatColor.DARK_RED + "slain by",
				ChatColor.GREEN + killerName, DateTimeFormats.DAY_MTH_HR_MIN_SECS.format(System.currentTimeMillis())));
		stack.setItemMeta(meta);
		return stack;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onSignChange(SignChangeEvent event) {
		if (isDeathSign(event.getBlock())) {
			event.setCancelled(true);
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		if (isDeathSign(block)) {
			BlockState state = block.getState();
			Sign sign = (Sign) state;
			ItemStack stack = new ItemStack(Material.SIGN, 1);
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(DEATH_SIGN_ITEM_NAME);
			meta.setLore(Arrays.asList(sign.getLines()));
			stack.setItemMeta(meta);

			Player player = event.getPlayer();
			World world = player.getWorld();
			if (player.getGameMode() != GameMode.CREATIVE && world.isGameRule("doTileDrops")) {
				world.dropItemNaturally(block.getLocation(), stack);
			}

			// Manually handle the dropping
			event.setCancelled(true);
			block.setType(Material.AIR);
			state.update();
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		ItemStack stack = event.getItemInHand();
		BlockState state = event.getBlock().getState();
		if (state instanceof Sign && stack.hasItemMeta()) {
			ItemMeta meta = stack.getItemMeta();
			if (meta.hasDisplayName() && meta.getDisplayName().equals(DEATH_SIGN_ITEM_NAME)) {
				Sign sign = (Sign) state;
				List<String> lore = meta.getLore();
				int count = 0;
				for (String loreLine : lore) {
					sign.setLine(count++, loreLine);
					if (count == 4)
						break;
				}

				sign.update();

				// sign.setEditible(false);
			}
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Player killer = player.getKiller();
		PlayerFaction playerFaction = plugin.getFactionManager().getPlayerFaction(player);
	     if (playerFaction != null && !SettingsYML.KIT_MAP) {
	            final Faction factionAt = this.plugin.getFactionManager().getFactionAt(player.getLocation());
	            final Role role = playerFaction.getMember(player.getUniqueId()).getRole();
	            if (playerFaction.getDeathsUntilRaidable() >= -5.0) {
	                playerFaction.setDeathsUntilRaidable(playerFaction.getDeathsUntilRaidable() - factionAt.getDtrLossMultiplier());
	                playerFaction.setRemainingRegenerationTime(DeathListener.BASE_REGEN_DELAY + playerFaction.getOnlinePlayers().size() * TimeUnit.MINUTES.toMillis(2L));
	                playerFaction.broadcast(ChatColor.YELLOW + "A member of your faction died " + ChatColor.GREEN + role.getAstrix() + player.getName() + ChatColor.YELLOW + ". DTR:" + ChatColor.GRAY + " [" + playerFaction.getDtrColour() + JavaUtils.format((Number)playerFaction.getDeathsUntilRaidable()) + ChatColor.WHITE + '/' + ChatColor.WHITE + playerFaction.getMaximumDeathsUntilRaidable() + ChatColor.GRAY + "].");
	            }
	            else {
	                playerFaction.setRemainingRegenerationTime(DeathListener.BASE_REGEN_DELAY + playerFaction.getOnlinePlayers().size() * TimeUnit.MINUTES.toMillis(2L));
	                playerFaction.broadcast(ChatColor.YELLOW + "A member of your faction died " + ChatColor.GREEN + role.getAstrix() + player.getName() + ChatColor.YELLOW + ". DTR:" + ChatColor.GRAY + " [" + playerFaction.getDtrColour() + JavaUtils.format((Number)playerFaction.getDeathsUntilRaidable()) + ChatColor.WHITE + '/' + ChatColor.WHITE + playerFaction.getMaximumDeathsUntilRaidable() + ChatColor.GRAY + "].");
	            }
	        }
		
		if (killer != null && !killer.equals(player)) {
			event.getDrops().add(getDeathSign(player.getName(), killer.getName()));
			plugin.getEconomyManager().addBalance(killer.getUniqueId(), 250);
			killer.sendMessage(
					ChatColor.YELLOW + "You earned " + ChatColor.GREEN + ChatColor.BOLD + EconomyManager.ECONOMY_SYMBOL
							+ "250" + ChatColor.YELLOW + " for killing " + ChatColor.WHITE + player.getName() + "");
			FactionListener.balance.add(player.getUniqueId());
		}
	}

	/**
	 * Checks if a block is a death sign.
	 *
	 * @param block
	 *            the block to check
	 * @return true if the block is a death sign
	 */
	private boolean isDeathSign(Block block) {
		BlockState state = block.getState();
		if (state instanceof Sign) {
			String[] lines = ((Sign) state).getLines();
			return lines.length > 0 && lines[1] != null && lines[1].equals(ChatColor.DARK_RED + "slain by");
		}

		return false;
	}
}
