package me.bruce.factions.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import com.google.common.base.Optional;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.KIts.GiveBook;
import me.bruce.factions.KIts.KitsBook;
import me.bruce.factions.commands.SettingsCommand;
import me.bruce.factions.commands.StaffSettings;
import me.bruce.factions.faction.FactionUser;
import me.bruce.factions.faction.event.CaptureZoneEnterEvent;
import me.bruce.factions.faction.event.CaptureZoneLeaveEvent;
import me.bruce.factions.faction.event.FactionCreateEvent;
import me.bruce.factions.faction.event.FactionRemoveEvent;
import me.bruce.factions.faction.event.FactionRenameEvent;
import me.bruce.factions.faction.event.PlayerClaimEnterEvent;
import me.bruce.factions.faction.event.PlayerJoinFactionEvent;
import me.bruce.factions.faction.event.PlayerLeaveFactionEvent;
import me.bruce.factions.faction.event.PlayerLeftFactionEvent;
import me.bruce.factions.faction.struct.RegenStatus;
import me.bruce.factions.faction.struct.Relation;
import me.bruce.factions.faction.type.Faction;
import me.bruce.factions.faction.type.KothFaction;
import me.bruce.factions.faction.type.PlayerFaction;
import me.bruce.factions.scoreboard.SidebarEntry;
import me.bruce.factions.staff.StaffModeCommand;
import me.bruce.factions.ymls.SettingsYML;
import me.tablist.api.tablist.adapter.TablistAdapter;

public class FactionListener implements Listener {

	private static final long FACTION_JOIN_WAIT_MILLIS = TimeUnit.SECONDS.toMillis(30L);
	private static final String FACTION_JOIN_WAIT_WORDS = DurationFormatUtils
			.formatDurationWords(FACTION_JOIN_WAIT_MILLIS, true, true);

	private static final String LAND_CHANGED_META_KEY = "landChangedMessage";
	private static final long LAND_CHANGE_MSG_THRESHOLD = 225L;
	public static ArrayList<UUID> balance = new ArrayList<UUID>();

	private final LorexHCF plugin;

	public FactionListener(LorexHCF plugin) {
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onFactionRenameMonitor(FactionRenameEvent event) {
		Faction faction = event.getFaction();
		if (faction instanceof KothFaction) {
			((KothFaction) faction).getCaptureZone().setName(event.getNewName());
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onFactionCreate(FactionCreateEvent event) {
		Faction faction = event.getFaction();
		Player p = (Player) event.getSender();
		if (faction instanceof PlayerFaction) {
			CommandSender sender = event.getSender();
			for (Player player : Bukkit.getOnlinePlayers()) {
				Relation relation = faction.getRelation(player);
				player.sendMessage(ChatColor.YELLOW + "Faction " + ChatColor.BLUE + faction.getName() + ChatColor.YELLOW + " has been §acreated §eby " + ChatColor.LIGHT_PURPLE + p.getName());
			}
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onFactionRemove(FactionRemoveEvent event) {
		Faction faction = event.getFaction();
		Player p = (Player) event.getSender();
		if (faction instanceof PlayerFaction) {
			CommandSender sender = event.getSender();
			for (Player player : Bukkit.getOnlinePlayers()) {
				Relation relation = faction.getRelation(player);
				player.sendMessage(ChatColor.YELLOW + "Faction " + ChatColor.BLUE + faction.getName() + ChatColor.YELLOW + " has been §cdisbaned §eby " + ChatColor.LIGHT_PURPLE + p.getName());
			}
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onFactionRename(FactionRenameEvent event) {
		Faction faction = event.getFaction();
		if (faction instanceof PlayerFaction) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				Relation relation = faction.getRelation(player);
				String msg = ChatColor.YELLOW + "The faction " + relation.toChatColour() + event.getOriginalName()
						+ ChatColor.YELLOW + " changed their name to " + relation.toChatColour() + event.getNewName()
						+ ChatColor.YELLOW + '.';
				player.sendMessage(msg);
			}
		}
	}

	private long getLastLandChangedMeta(Player player) {
		List<MetadataValue> value = player.getMetadata(LAND_CHANGED_META_KEY);
		long millis = System.currentTimeMillis();
		long remaining = value == null || value.isEmpty() ? 0L : value.get(0).asLong() - millis;
		if (remaining <= 0L) { // update the metadata.
			player.setMetadata(LAND_CHANGED_META_KEY,
					new FixedMetadataValue(plugin, millis + LAND_CHANGE_MSG_THRESHOLD));
		}

		return remaining;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onCaptureZoneEnter(CaptureZoneEnterEvent event) {
		Player player = event.getPlayer();
		if (getLastLandChangedMeta(player) > 0L)
			return; // delay before re-messaging.

		if (plugin.getUserManager().getUser(player.getUniqueId()).isCapzoneEntryAlerts()) {
			player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "LorexMC §8» " + ChatColor.GRAY
					+ "Now entering cap zone: " + event.getCaptureZone().getDisplayName() + ChatColor.GRAY + '('
					+ event.getFaction().getName() + ChatColor.GRAY + ')');
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onCaptureZoneLeave(CaptureZoneLeaveEvent event) {
		Player player = event.getPlayer();
		if (getLastLandChangedMeta(player) > 0L)
			return; // delay before re-messaging.

		if (plugin.getUserManager().getUser(player.getUniqueId()).isCapzoneEntryAlerts()) {
			player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "LorexMC §8» " + ChatColor.GRAY
					+ "Now leaving cap zone: " + event.getCaptureZone().getDisplayName() + ChatColor.GRAY + '('
					+ event.getFaction().getName() + ChatColor.GRAY + ')');
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	private void onPlayerClaimEnter(PlayerClaimEnterEvent event) {
		Player player = event.getPlayer();
		Faction toFaction = event.getToFaction();
		if (toFaction.isSafezone()) {
			player.setFoodLevel(20);
			player.setFireTicks(0);
			player.setSaturation(4.0F);
			GiveBook.GiveBooktoplayer(player);
		}
		else if(!toFaction.isSafezone()) {
			GiveBook.takeBooktoplayer(player);
		}
		if (getLastLandChangedMeta(player) > 0L)
			return; // delay before re-messaging.

		Faction fromFaction = event.getFromFaction();
		//player.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.GRAY + "Leaving "
			//	+ fromFaction.getDisplayName(player) + ChatColor.GRAY + ", Entering "
				//+ toFaction.getDisplayName(player));
        player.sendMessage(ChatColor.YELLOW + "Now leaving: " + fromFaction.getDisplayName(player) + ChatColor.GRAY + " (" + (fromFaction.isDeathban() ? (ChatColor.RED + "Deathban") : (ChatColor.GREEN + "Non-Deathban")) + ChatColor.GRAY + ')');
        player.sendMessage(ChatColor.YELLOW + "Now entering: " + toFaction.getDisplayName(player) + ChatColor.GRAY + " (" + (toFaction.isDeathban() ? (ChatColor.RED + "Deathban") : (ChatColor.GREEN + "Non-Deathban")) + ChatColor.GRAY + ')');
				
	
		}
	

	

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerLeftFaction(PlayerLeftFactionEvent event) {
		Optional<Player> optionalPlayer = event.getPlayer();
		if (optionalPlayer.isPresent()) {
			plugin.getUserManager().getUser(optionalPlayer.get().getUniqueId())
					.setLastFactionLeaveMillis(System.currentTimeMillis());
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onPlayerPreFactionJoin(PlayerJoinFactionEvent event) {
		PlayerFaction faction = event.getFaction();
		Optional<Player> optionalPlayer = event.getPlayer();
		if (faction instanceof PlayerFaction && optionalPlayer.isPresent()) {
			Player player = optionalPlayer.get();
			PlayerFaction playerFaction = (PlayerFaction) faction;

			if (!plugin.getEotwHandler().isEndOfTheWorld() && playerFaction.getRegenStatus() == RegenStatus.PAUSED) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You cannot join factions that are not regenerating DTR.");
				return;
			}

			long difference = (plugin.getUserManager().getUser(player.getUniqueId()).getLastFactionLeaveMillis()
					- System.currentTimeMillis()) + FACTION_JOIN_WAIT_MILLIS;
			if (difference > 0L && !player.hasPermission("hcf.faction.argument.staff.forcejoin")) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You cannot join factions after just leaving within "
						+ FACTION_JOIN_WAIT_WORDS + ". " + "You gotta wait another "
						+ DurationFormatUtils.formatDurationWords(difference, true, true) + '.');
			}
			UUID uuid = player.getUniqueId();
			LorexHCF.getInstance().getUserManager().users.putIfAbsent(uuid, new FactionUser(uuid));
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onFactionLeave(PlayerLeaveFactionEvent event) {
		if (event.isForce() || event.isKick()) {
			return;
		}

		PlayerFaction faction = event.getFaction();
		if (faction instanceof PlayerFaction) {
			Optional<Player> optional = event.getPlayer();
			if (optional.isPresent()) {
				Player player = optional.get();
				if (plugin.getFactionManager().getFactionAt(player.getLocation()) == faction) {
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED + "You may not leave a faction whilist in its territory!");
				}
			}
		}
	}

	private String prefix = ChatColor.DARK_GRAY + "(" + ChatColor.RED + "!" + ChatColor.DARK_GRAY + ") ";

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		TablistAdapter.tablist.add(p);
			if(!p.hasPlayedBefore()) {
				StaffSettings.zorex.add(p.getUniqueId());
				p.teleport(new Location(Bukkit.getWorld("World"), 0,80, 1));
			}

		PlayerFaction playerFaction = plugin.getFactionManager().getPlayerFaction(p);
		if (playerFaction != null) {
			playerFaction.printDetails(p);
			playerFaction.broadcast(ChatColor.GOLD + "Member online:§a " +  p.getName());

		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		PlayerFaction playerFaction = plugin.getFactionManager().getPlayerFaction(player);
		if (playerFaction != null) {
			playerFaction.broadcast(ChatColor.GOLD + "Member offline:§c " +  player.getName());
			}
		}
	}

