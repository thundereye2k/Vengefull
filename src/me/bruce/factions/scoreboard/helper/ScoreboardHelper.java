package me.bruce.factions.scoreboard.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;


import LorexMC.us.utils.BukkitUtils;
import LorexMC.us.utils.DurationFormatter;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.KIts.GiveBook;
import me.bruce.factions.armors.PvpClass;
import me.bruce.factions.armors.archer.ArcherClass;
import me.bruce.factions.armors.bard.BardClass;
import me.bruce.factions.armors.miner.MinerClass;
import me.bruce.factions.commands.FreezeCommand;
import me.bruce.factions.commands.SettingsCommand;
import me.bruce.factions.commands.StaffSettings;
import me.bruce.factions.endoftheworld.EotwHandler;
import me.bruce.factions.eventutils.EventTimer;
import me.bruce.factions.eventutils.tracker.ConquestTracker;
import me.bruce.factions.faction.type.ConquestFaction;
import me.bruce.factions.faction.type.EventFaction;
import me.bruce.factions.faction.type.KothFaction;
import me.bruce.factions.faction.type.PlayerFaction;
import me.bruce.factions.keysale.keysaletimer.keysalerunnable;
import me.bruce.factions.listener.DateTimeFormats;
import me.bruce.factions.listener.FactionListener;
import me.bruce.factions.listener.VanishListener;
import me.bruce.factions.reboot.reboottimer.rebootrunnable;
import me.bruce.factions.scoreboard.SidebarEntry;
import me.bruce.factions.scoreboard.SidebarProvider;
import me.bruce.factions.staff.StaffModeCommand;
import me.bruce.factions.startoftheworld.SotwCommand;
import me.bruce.factions.startoftheworld.SotwTimer;
import me.bruce.factions.timer.PlayerTimer;
import me.bruce.factions.timer.Timer;
import me.bruce.factions.timer.type.CombatTimer;
import me.bruce.factions.ymls.SettingsYML;
import net.md_5.bungee.api.ChatColor;

public class ScoreboardHelper implements SidebarProvider {

	public static final ThreadLocal<DecimalFormat> CONQUEST_FORMATTER = new ThreadLocal<DecimalFormat>() {
		@Override
		protected DecimalFormat initialValue() {
			return new DecimalFormat("00.0");
		}
	};
	protected static final String STRAIGHT_LINE = BukkitUtils.STRAIGHT_LINE_DEFAULT.substring(0, 14);
	private static final SidebarEntry EMPTY_ENTRY_FILLER = new SidebarEntry(" ", " ", " ");
	private static Object TPS_FORMAT;

	private RegisteredServiceProvider<FactionListener> plugin1 = Bukkit.getServer().getServicesManager()
			.getRegistration(FactionListener.class);

	private final LorexHCF plugin;

	public ScoreboardHelper(LorexHCF plugin) {
		this.plugin = plugin;
	}

	public SidebarEntry add(String s) {

		if (s.length() < 10) {
			return new SidebarEntry(s);
		}

		if (s.length() > 10 && s.length() < 20) {
			return new SidebarEntry(s.substring(0, 10), s.substring(10, s.length()), "");
		}

		if (s.length() > 20) {
			return new SidebarEntry(s.substring(0, 10), s.substring(10, 20), s.substring(20, s.length()));
		}

		return null;//OOFING IN MINECRAFT I WILL KEEP OOFING IN MINECRAFT OOF I AM TOTALLY CODING RIGHT HERE CODING RIGHT HERE BOYS I AM TOTALLY THE BEST DEVELOPER IN THE WORLD LMAO ...................
	}

	@Override
	public String getTitle() {
		return ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §7\u258f " + SettingsYML.SCOREBOARD_TITLE;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<SidebarEntry> getLines(Player player) {
		PlayerFaction playerFaction = LorexHCF.getInstance().getFactionManager().getPlayerFaction(player);
		List<SidebarEntry> lines = new ArrayList<>();

		EventTimer eventTimer = plugin.getTimerManager().getEventTimer();
		List<SidebarEntry> conquestLines = null;
		List<SidebarEntry> frozenlines = null;
		
		

		SotwTimer.SotwRunnable sotwRunnable = plugin.getSotwTimer().getSotwRunnable();

		if (sotwRunnable != null) {
			lines.add(new SidebarEntry(ChatColor.GREEN.toString() + ChatColor.BOLD, "SOTW§7: ",
					ChatColor.RED + DurationFormatter.getRemaining(sotwRunnable.getRemaining(), true)));
		}
		if (sotwRunnable != null && !SotwCommand.enabled.contains(player.getUniqueId())) {
			lines.add(new SidebarEntry(ChatColor.GREEN.toString() + ChatColor.BOLD, "SOTW§7: ",
					ChatColor.RED + DurationFormatter.getRemaining(sotwRunnable.getRemaining(), true)));
		}
			else if(sotwRunnable != null && SotwCommand.enabled.contains(player.getUniqueId())) {
				lines.remove(new SidebarEntry(ChatColor.GREEN.toString() + ChatColor.BOLD, "SOTW§7: ",
					ChatColor.RED + DurationFormatter.getRemaining(sotwRunnable.getRemaining(), true)));
				lines.add(new SidebarEntry(ChatColor.GREEN.toString() + ChatColor.STRIKETHROUGH, "SOTW§7§m: ",
						ChatColor.RED  + ChatColor.STRIKETHROUGH.toString() + DurationFormatter.getRemaining(sotwRunnable.getRemaining(), true)));
				
			}
		
		
		rebootrunnable rebootrunnable = plugin.getreboottimer().getrebootrunnable();

		if (rebootrunnable != null) {
			lines.add(new SidebarEntry(ChatColor.GOLD.toString() + ChatColor.BOLD, "Reboot §6» ",
					ChatColor.RED + DurationFormatter.getRemaining(rebootrunnable.getRemaining(), true)));
		}

		keysalerunnable keysalerunnable = plugin.getkeysaletimer().getkeysalerunnable();

		if (keysalerunnable != null) {
			lines.add(new SidebarEntry(ChatColor.GOLD.toString() + ChatColor.BOLD, "KEY-SALE §6» ",
					ChatColor.RED + DurationFormatter.getRemaining(keysalerunnable.getRemaining(), true)));
		}

		EotwHandler.EotwRunnable eotwRunnable = plugin.getEotwHandler().getRunnable();
		


		

		if (eotwRunnable != null) {
			long remaining = eotwRunnable.getMillisUntilStarting();
			if (remaining > 0L) {
				lines.add(
						new SidebarEntry(ChatColor.RED.toString() + ChatColor.BOLD, "EOTW" + ChatColor.RED + " begins",
								" in " + ChatColor.BOLD + DurationFormatter.getRemaining(remaining, true)));
			} else if ((remaining = eotwRunnable.getMillisUntilCappable()) > 0L) {
				lines.add(
						new SidebarEntry(ChatColor.RED.toString() + ChatColor.BOLD, "EOTW" + ChatColor.RED + " capable",
								" in " + ChatColor.BOLD + DurationFormatter.getRemaining(remaining, true)));
			}
		}
		if (net.zorex.base.BasePlugin.getPlugin().getUserManager().getUser(player.getUniqueId()).isStaffUtil()) {
            final List<SidebarEntry> list = lines;
            final ChatColor yellow2 = ChatColor.YELLOW;
            final String name = "Vanish §6» ";
            final String name21 = "Vanish§7: ";
            final String name2 = "Ping §6» ";
            final String name3 =  "§6§lStaff Mode:";
            final String name4 = "Tps §6»§a ";
            final String bold = "§l";
            final String arrow = " §6» ";
            DecimalFormat TPS_FORMAT = new DecimalFormat("0.0");
            

            	list.add(new SidebarEntry(name3));
            	list.add(new SidebarEntry(arrow + yellow2, name21, VanishListener.isVanished(player) ? (ChatColor.GREEN + "True") : (ChatColor.RED + "False")));
            	
		}

		if (SettingsYML.KIT_MAP == true) {
			 if (LorexHCF.getInstance().getFactionManager().getFactionAt(player.getLocation()).isSafezone()) {
				 if(!SettingsCommand.scoreboard.contains(player.getUniqueId())) {
						lines.add(new SidebarEntry(ChatColor.DARK_RED, ChatColor.BOLD.toString(), "Kills§7:§c "  + ChatColor.RED + player.getStatistic(Statistic.PLAYER_KILLS)));
						lines.add(new SidebarEntry(ChatColor.DARK_RED + ChatColor.BOLD.toString()  + "Deaths§7:",
								"§c ", player.getStatistic(Statistic.DEATHS)));
		                //lines.add(new SidebarEntry("§4Balance ",  "§6» §f", "$" + LorexHCF.getInstance().getEconomyManager().getBalance(player.getUniqueId())));
				 }
				 else {
						lines.add(new SidebarEntry(ChatColor.DARK_RED, ChatColor.BOLD.toString(), "Kills§7:§c "  + ChatColor.RED + player.getStatistic(Statistic.PLAYER_KILLS)));
						lines.add(new SidebarEntry(ChatColor.DARK_RED + ChatColor.BOLD.toString()  + "Deaths§7:",
								"§c ", player.getStatistic(Statistic.DEATHS)));
		                lines.add(new SidebarEntry("§4§lBalance",  "§7: §c", "$" + LorexHCF.getInstance().getEconomyManager().getBalance(player.getUniqueId())));
				 }
	            }
		}
		PvpClass pvpClass = plugin.getPvpClassManager().getEquippedClass(player);
		if (pvpClass != null) {
			lines.add(new SidebarEntry(ChatColor.YELLOW + "Class" + ChatColor.GRAY + ": ", ChatColor.WHITE,  pvpClass.getName()));
			if (pvpClass instanceof BardClass) {
				BardClass bardClass = (BardClass) pvpClass;
				lines.add(new SidebarEntry(ChatColor.AQUA + "", ChatColor.BOLD + "Bard Energy", ChatColor.GRAY + ": "
						+ ChatColor.GRAY + handleBardFormat(bardClass.getEnergyMillis(player), true)));
				long remaining2 = bardClass.getRemainingBuffDelay(player);
				if (remaining2 > 0L) {
					lines.add(new SidebarEntry(ChatColor.GREEN + "", ChatColor.BOLD + "Bard Effect", ChatColor.GRAY
							+ ": " + ChatColor.GRAY + DurationFormatter.getRemaining(remaining2, true)));
				}
				long remaining = bardClass.getRemainingBuffDelay(player);
				if (remaining > 0) {
				}
			}
			if (pvpClass instanceof ArcherClass) {
				UUID uuid = player.getUniqueId();
				ArcherClass archerClass = (ArcherClass) pvpClass;
				long timestamp = ArcherClass.archerSpeedCooldowns.get(uuid);
				long millis = System.currentTimeMillis();
				long remaining3 = (timestamp == ArcherClass.archerSpeedCooldowns.getNoEntryValue()) ? -1L
						: (timestamp - millis);
				if (remaining3 > 0L) {
					lines.add(new SidebarEntry(ChatColor.YELLOW, " Delay",
							ChatColor.GRAY + ": §f" + DurationFormatter.getRemaining(remaining3, true)));
				}
			}
			if (pvpClass instanceof MinerClass) {



			}
		}

		Collection<Timer> timers = this.plugin.getTimerManager().getTimers();
		for (Timer timer : timers) {
			if (timer instanceof PlayerTimer) {
				PlayerTimer playerTimer = (PlayerTimer) timer;
				long remaining4 = playerTimer.getRemaining(player);
				if (remaining4 <= 0L) {
					continue;
				}
				String timerName = playerTimer.getName();
				if (timerName.length() > 14) {
					timerName = timerName.substring(0, timerName.length());
				}
				lines.add(new SidebarEntry(playerTimer.getScoreboardPrefix(), timerName,
						"§7: " + ChatColor.RED
								+ ((timer instanceof CombatTimer) ? DurationFormatter.getRemaining(remaining4, false)
										: DurationFormatter.getRemaining(remaining4, true))));
			}
		}

		// Show the current PVP Class statistics of the player.

		EventFaction eventFaction = eventTimer.getEventFaction();
		if (eventFaction instanceof KothFaction) {
			// lines.add(new SidebarEntry(ChatColor.AQUA.toString(), ChatColor.BOLD +
			// "Active Events", null));
			lines.add(new SidebarEntry(eventTimer.getScoreboardPrefix(),
					                                                                                                                                                                                                                                                                                                                                                                                                                           
					eventFaction.getScoreboardName() + ChatColor.GRAY,
					"§7: " + ChatColor.RED + DurationFormatter.getRemaining(eventTimer.getRemaining(), true)));
		} else if (eventFaction instanceof ConquestFaction) {
			ConquestFaction conquestFaction = (ConquestFaction) eventFaction;
			DecimalFormat format = CONQUEST_FORMATTER.get();

			conquestLines = new ArrayList<>();

			conquestLines.add(new SidebarEntry(ChatColor.GOLD + ChatColor.BOLD.toString() + "Conquest§7:"));

			conquestLines.add(
					new SidebarEntry(" " + ChatColor.RED.toString() + conquestFaction.getRed().getScoreboardRemaining(),
							ChatColor.GOLD + " | ",
							ChatColor.YELLOW.toString() + conquestFaction.getYellow().getScoreboardRemaining()));

			conquestLines.add(new SidebarEntry(
					" " + ChatColor.GREEN.toString() + conquestFaction.getGreen().getScoreboardRemaining(),
					ChatColor.GOLD + " | " + ChatColor.RESET,
					ChatColor.AQUA.toString() + conquestFaction.getBlue().getScoreboardRemaining()));

			// Show the top 3 factions next.
			ConquestTracker conquestTracker = (ConquestTracker) conquestFaction.getEventType().getEventTracker();
			int count = 0;
			for (Map.Entry<PlayerFaction, Integer> entry : conquestTracker.getFactionPointsMap().entrySet()) {
				String factionName = entry.getKey().getName();
				if (factionName.length() > 14)
					factionName = factionName.substring(0, 14);
				for (int i = 0; i < 3; i++) {
					conquestLines.add(new SidebarEntry(
							ChatColor.AQUA.toString() + ChatColor.BOLD + " " + count++ + ". ",
							ChatColor.AQUA + factionName, ChatColor.GRAY + ": " + ChatColor.WHITE + entry.getValue()));
				}
				if (++count == 3)
					break;
			}
		}

		if (conquestLines != null && !conquestLines.isEmpty()) {
			if (!lines.isEmpty()) {
				conquestLines.add(new SidebarEntry(ChatColor.DARK_GRAY, ChatColor.GRAY + STRAIGHT_LINE, STRAIGHT_LINE));
			}

			conquestLines.addAll(lines);
			lines = conquestLines;
		}
		if (FreezeCommand.frozen.contains(player.getName())) {
			final UUID uuid = player.getUniqueId();
			final int balance = this.plugin.getEconomyManager().getBalance(uuid);
			lines.removeAll(lines);
			frozenlines = new ArrayList<>();
			lines.add(new SidebarEntry(ChatColor.RED + "You are frozen"));
			lines.add(new SidebarEntry(ChatColor.RED + "please join"));
			lines.add(new SidebarEntry(ChatColor.RED + "our ts."));
			lines.add(new SidebarEntry("§f"));
			lines.add(new SidebarEntry(ChatColor.RED + "ts.zorex.us"));

		}
		if (!lines.isEmpty()) {
			if(!SettingsCommand.lines.contains(player.getUniqueId())) {
				lines.add(0, new SidebarEntry(ChatColor.GRAY, STRAIGHT_LINE, STRAIGHT_LINE));
				lines.add(lines.size(),
						new SidebarEntry(ChatColor.GRAY, ChatColor.STRIKETHROUGH + STRAIGHT_LINE, STRAIGHT_LINE));				
			}
			else {
				lines.remove(new SidebarEntry(ChatColor.GRAY, STRAIGHT_LINE, STRAIGHT_LINE));
				lines.remove(new SidebarEntry(ChatColor.GRAY, ChatColor.STRIKETHROUGH + STRAIGHT_LINE, STRAIGHT_LINE));
			}
		}
		return lines;
	}

	private static String handleBardFormat(long millis, boolean trailingZero) {
		return (trailingZero ? DateTimeFormats.REMAINING_SECONDS_TRAILING : DateTimeFormats.REMAINING_SECONDS).get()
				.format(millis * 0.001);
	}

	public static String translate(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);

	}

}
