package me.bruce.factions.commands;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import LorexMC.us.utils.BukkitUtils;
import LorexMC.us.utils.other.chat.ClickAction;
import LorexMC.us.utils.other.chat.Text;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.event.PlayerFreezeEvent;
import net.minecraft.util.gnu.trove.map.TObjectLongMap;
import net.minecraft.util.gnu.trove.map.hash.TObjectLongHashMap;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;
import net.minecraft.util.org.apache.commons.lang3.time.DurationFormatUtils;

public class FreezeCommand implements CommandExecutor, Listener {
	private static final String FREEZE_BYPASS = "command.freeze.bypass";
	public static TObjectLongMap<UUID> frozenPlayers;
	public static HashMap<Player, String> frozenReasons;
	private long defaultFreezeDuration;
	private long serverFrozenMillis;
	public static HashSet<String> frozen;

	static {
		FreezeCommand.frozenPlayers = new TObjectLongHashMap();
		FreezeCommand.frozenReasons = new HashMap<Player, String>();
	}

	public FreezeCommand(final LorexHCF plugin) {
		FreezeCommand.frozen = new HashSet<String>();
		this.defaultFreezeDuration = TimeUnit.MINUTES.toMillis(60L);
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label,
			final String[] args) {
		if (args.length < 1) {
			sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.RED + "Usage: " + "/freeze <player>");
			return true;
		}
		final String reason = null;
		Long freezeTicks = this.defaultFreezeDuration;
		final long millis = System.currentTimeMillis();
		if (args[0].equalsIgnoreCase("all") && sender.hasPermission(String.valueOf(command.getPermission()) + ".all")) {
			final long oldTicks = this.getRemainingServerFrozenMillis();
			if (oldTicks > 0L) {
				freezeTicks = 0L;
			}
			this.serverFrozenMillis = millis + this.defaultFreezeDuration;
			Bukkit.getServer()
					.broadcastMessage(ChatColor.YELLOW + "The server is " + ((freezeTicks > 0L)
							? ("now frozen for " + DurationFormatUtils.formatDurationWords(freezeTicks, true, true))
							: "no longer frozen") + ((reason == null) ? "" : (" with reason " + reason)) + '.');
			return true;
		}
		final Player target = Bukkit.getServer().getPlayer(args[0]);
		if (target == null) {
			sender.sendMessage(
					ChatColor.GOLD + "Player '" + ChatColor.WHITE + args[0] + ChatColor.GOLD + "' not found.");
			return true;
		}
		final UUID targetUUID = target.getUniqueId();
		final boolean shouldFreeze = this.getRemainingPlayerFrozenMillis(targetUUID) > 0L;
		final PlayerFreezeEvent playerFreezeEvent = new PlayerFreezeEvent(target, shouldFreeze);
		Bukkit.getServer().getPluginManager().callEvent(playerFreezeEvent);
		if (playerFreezeEvent.isCancelled()) {
			sender.sendMessage(ChatColor.RED + "Unable to freeze " + target.getName() + '.');
			return false;
		}
		if (shouldFreeze) {
			FreezeCommand.frozen.remove(target.getName());
			FreezeCommand.frozenPlayers.remove(targetUUID);
			sender.sendMessage(ChatColor.GOLD + "⚠ " + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.YELLOW + " has been un-frozen by " + ChatColor.LIGHT_PURPLE + sender.getName());
			target.sendMessage(ChatColor.RED + "You have been un-frozen.");
			target.removePotionEffect(PotionEffectType.BLINDNESS);
			FreezeCommand.frozenReasons.remove(target);
		} else if (args.length == 1) {
			FreezeCommand.frozen.add(target.getName());
			FreezeCommand.frozenPlayers.put(targetUUID, millis + freezeTicks);
			final String timeString = DurationFormatUtils.formatDurationWords(freezeTicks, true, true);
			this.Message(target.getName());
			sender.sendMessage(ChatColor.GOLD + "⚠ " + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.YELLOW + " has been frozen by " + ChatColor.LIGHT_PURPLE + sender.getName());
		} else {
			FreezeCommand.frozen.add(target.getName());
			FreezeCommand.frozenPlayers.put(targetUUID, millis + freezeTicks);
			final String timeString = DurationFormatUtils.formatDurationWords(freezeTicks, true, true);
			this.Message(target.getName());
			sender.sendMessage(ChatColor.GOLD + "⚠ " + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.YELLOW + " has been frozen by " + ChatColor.LIGHT_PURPLE + sender.getName());
			final String reason2 = StringUtils.join(args, ' ', 1, args.length);
			FreezeCommand.frozenReasons.put(target, reason2);
		}
		return true;
	}

	private void Message(final String name) {
		final HashMap timer = new HashMap();
		final Player p = Bukkit.getPlayer(name);
		final BukkitTask task = new BukkitRunnable() {
			@Override
			public void run() {
				if (FreezeCommand.frozen.contains(name)) {
					p.sendMessage(" ");
					p.sendMessage("§f\u2588\u2588\u2588\u2588§c\u2588§f\u2588\u2588\u2588\u2588");
					p.sendMessage("§f\u2588\u2588\u2588§c\u2588§6\u2588§c\u2588§f\u2588\u2588\u2588");
					p.sendMessage(
							"§f\u2588\u2588§c\u2588§6\u2588§0\u2588§6\u2588§c\u2588§f\u2588\u2588 §4§lDo NOT log out!");
					p.sendMessage(
							"§f\u2588\u2588§c\u2588§6\u2588§0\u2588§6\u2588§c\u2588§f\u2588\u2588 §cIf you do, you will be banned!");
					p.sendMessage(
							"§f\u2588§c\u2588§6\u2588\u2588§0\u2588§6\u2588\u2588§c\u2588§f\u2588 §ePlease download §eTeamspeak §eand connect to:");
					p.sendMessage(
							"§f\u2588§c\u2588§6\u2588\u2588\u2588\u2588\u2588§c\u2588§f\u2588 " + "§ets.Zorex.us");
					p.sendMessage("§c\u2588§6\u2588\u2588\u2588§0\u2588§6\u2588\u2588\u2588§c\u2588");
					p.sendMessage("§c\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588");
					p.sendMessage(" ");
				} else {
					this.cancel();
				}
			}
		}.runTaskTimerAsynchronously(LorexHCF.getInstance(), 0L, 200L);
	}

	public List<String> onTabComplete(final CommandSender sender, final Command command, final String label,
			final String[] args) {
		return (args.length == 1) ? null : Collections.emptyList();
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
		final Entity entity = event.getEntity();
		if (entity instanceof Player) {
			final Player attacker = BukkitUtils.getFinalAttacker(event, false);
			if (attacker == null) {
				return;
			}
			final Player player = (Player) entity;
			if ((this.getRemainingServerFrozenMillis() > 0L
					|| this.getRemainingPlayerFrozenMillis(player.getUniqueId()) > 0L)
					&& !player.hasPermission("command.freeze.bypass")) {
				attacker.sendMessage(ChatColor.RED + player.getName() + " is currently frozen, you may not attack.");
				event.setCancelled(true);
				return;
			}
			if ((this.getRemainingServerFrozenMillis() > 0L
					|| this.getRemainingPlayerFrozenMillis(attacker.getUniqueId()) > 0L)
					&& !attacker.hasPermission("command.freeze.bypass")) {
				event.setCancelled(true);
				attacker.sendMessage(ChatColor.RED + "You may not attack players whilst frozen.");
			}
		}
	}



	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onPlayerMove(final PlayerMoveEvent event) {
		final Location from = event.getFrom();
		final Location to = event.getTo();
		if (from.getBlockX() == to.getBlockX() && from.getBlockZ() == to.getBlockZ()) {
			return;
		}
		final Player player = event.getPlayer();
		if ((this.getRemainingServerFrozenMillis() > 0L
				|| this.getRemainingPlayerFrozenMillis(player.getUniqueId()) > 0L)
				&& !player.hasPermission("command.freeze.bypass")) {
			event.setTo(event.getFrom());
		}
	}

	@EventHandler
	public void onPlayerQuit(final PlayerQuitEvent e) {
		if (FreezeCommand.frozen.contains(e.getPlayer().getUniqueId())) {
			for (final Player online : Bukkit.getOnlinePlayers()) {
				if (online.hasPermission("command.freeze")) {
					online.sendMessage("");
					online.sendMessage("");
					online.sendMessage("");
					new Text(ChatColor.WHITE + e.getPlayer().getName() + " has " + ChatColor.DARK_RED + "QUIT"
							+ ChatColor.WHITE + " while frozen. " + ChatColor.GRAY + ChatColor.ITALIC
							+ "(Click here to ban)")
									.setHoverText(ChatColor.WHITE + "Click here to permanently ban" + ChatColor.GRAY
											+ e.getPlayer().getName())
									.setClick(ClickAction.RUN_COMMAND,
											"/ban" + e.getPlayer().getName() + "Refusal to SS | Logout")
									.setColor(ChatColor.GRAY).send(online);
					online.sendMessage("");
					online.sendMessage("");
					online.sendMessage("");
				}
			}
		}
	}

	public long getRemainingServerFrozenMillis() {
		return this.serverFrozenMillis - System.currentTimeMillis();
	}

	public long getRemainingPlayerFrozenMillis(final UUID uuid) {
		final long remaining = FreezeCommand.frozenPlayers.get(uuid);
		if (remaining == FreezeCommand.frozenPlayers.getNoEntryValue()) {
			return 0L;
		}
		return remaining - System.currentTimeMillis();
	}
}