package me.bruce.factions.listener;

import net.minecraft.server.v1_7_R4.EntityLightning;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntityWeather;
import net.minecraft.server.v1_7_R4.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import LorexMC.us.utils.JavaUtils;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.FactionUser;
import me.bruce.factions.faction.struct.Role;
import me.bruce.factions.faction.type.Faction;
import me.bruce.factions.faction.type.PlayerFaction;
import me.bruce.factions.ymls.SettingsYML;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DeathListener implements Listener {

	private final LorexHCF plugin;

	public DeathListener(LorexHCF plugin) {
		this.plugin = plugin;
	}

	public static HashMap<UUID, ItemStack[]> PlayerInventoryContents;
	public static HashMap<UUID, ItemStack[]> PlayerArmorContents;
	private String prefix = ChatColor.DARK_GRAY + "(" + ChatColor.RED + "!" + ChatColor.DARK_GRAY + ") ";

	static {
		PlayerInventoryContents = new HashMap<>();
		PlayerArmorContents = new HashMap<>();
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void onPlayerDeathKillIncrement(PlayerDeathEvent event) {
		Player killer = event.getEntity().getKiller();
		if (killer != null) {
			FactionUser user = plugin.getUserManager().getUser(killer.getUniqueId());
			user.setKills(user.getKills() + 1);
		}
	}

	static final long BASE_REGEN_DELAY = TimeUnit.MINUTES.toMillis(40L);
	// *Kitmap* private static final long BASE_REGEN_DELAY =
	// TimeUnit.MINUTES.toMillis(0L);

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		@SuppressWarnings("deprecation")
		PlayerFaction playerFaction = plugin.getFactionManager().getPlayerFaction(player);
		PlayerInventoryContents.put(player.getUniqueId(), player.getInventory().getContents());
		PlayerArmorContents.put(player.getUniqueId(), player.getInventory().getArmorContents());

		if (playerFaction != null) {
			Faction factionAt = plugin.getFactionManager().getFactionAt(player.getLocation());
			double dtrLoss = (1.0D * factionAt.getDtrLossMultiplier());
			double newDtr = playerFaction.setDeathsUntilRaidable(playerFaction.getDeathsUntilRaidable() - dtrLoss);

			Role role = playerFaction.getMember(player.getUniqueId()).getRole();
			playerFaction.setRemainingRegenerationTime(
					BASE_REGEN_DELAY + (playerFaction.getOnlinePlayers().size() * TimeUnit.MINUTES.toMillis(2L)));
			playerFaction.broadcast(prefix + ChatColor.GRAY + "Member Death: " + SettingsYML.TEAMMATE_COLOUR
					+ role.getAstrix() + player.getName() + ChatColor.GRAY + ". " + "DTR: (" + ChatColor.GOLD
					+ JavaUtils.format(newDtr, 2) + '/'
					+ JavaUtils.format(playerFaction.getMaximumDeathsUntilRaidable(), 2) + ChatColor.GRAY + ").");
		}

		if (Bukkit.spigot().getTPS()[0] > 12.0D) { // Prevent unnecessary lag during prime times.
			Location location = player.getLocation();
			WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();

			EntityLightning entityLightning = new EntityLightning(worldServer, location.getX(), location.getY(),
					location.getZ(), false);
			PacketPlayOutSpawnEntityWeather packet = new PacketPlayOutSpawnEntityWeather(entityLightning);
			for (Player target : Bukkit.getOnlinePlayers()) {
				if (plugin.getUserManager().getUser(target.getUniqueId()).isShowLightning()) {
					((CraftPlayer) target).getHandle().playerConnection.sendPacket(packet);
					target.playSound(target.getLocation(), Sound.AMBIENCE_THUNDER, 1.0F, 1.0F);
				}
			}
		}
	}
}
