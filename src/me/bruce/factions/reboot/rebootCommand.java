package me.bruce.factions.reboot;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.google.common.collect.ImmutableList;

import LorexMC.us.utils.BukkitUtils;
import LorexMC.us.utils.JavaUtils;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.reboot.reboottimer.rebootrunnable;
import net.minecraft.util.org.apache.commons.lang3.time.DurationFormatUtils;

public class rebootCommand implements CommandExecutor, TabCompleter{
	
	private static final List<String> COMPLETIONS = ImmutableList.of("start", "stop");

	private final LorexHCF plugin;

	public rebootCommand(LorexHCF plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!sender.hasPermission("Core.staff.advanced")) {
			sender.sendMessage(ChatColor.RED + "No permission.");
			return true;
		}

		if (args.length > 0) {
			sender.sendMessage(
					ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------");

			sender.sendMessage(ChatColor.GRAY + "Usage » /reboot start <duration>");

			sender.sendMessage(ChatColor.GRAY + "Usage » /reboot stop");
			
			sender.sendMessage(
					ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------");
			if (args[0].equalsIgnoreCase("start")) {
				if (args.length < 2) {
					sender.sendMessage(ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString()
							+ "-------------------------------------");

					sender.sendMessage(ChatColor.GRAY + "Usage » /reboot start <duration>");

					sender.sendMessage(ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString()
							+ "-------------------------------------");

					return true;
				}

				long duration = JavaUtils.parse(args[1]);

				if (duration == -1L) {
					sender.sendMessage(ChatColor.RED + "'" + args[0] + "' is an invalid duration.");
					return true;
				}

				if (duration < 1000L) {
					sender.sendMessage(ChatColor.RED + "reboot time must last for at least 20 ticks.");
					return true;
				}

				rebootrunnable rebootrunnable = plugin.getreboottimer().getrebootrunnable();

				if ( rebootrunnable  != null) {
					sender.sendMessage(
							ChatColor.RED + "reboot is already enabled, use /" + label + " cancel to end it.");
					return true;
				}

				plugin.getreboottimer().start(duration);
				sender.sendMessage(ChatColor.RED + "Started reboot for "
						+ DurationFormatUtils.formatDurationWords(duration, true, true) + ".");
				return true;
			}

				if (args[0].equalsIgnoreCase("stop") || args[0].equalsIgnoreCase("cancel")) {
					if (plugin.getreboottimer().cancel()) {
						
						Bukkit.broadcastMessage(("§7§m--------------------------------"));
						Bukkit.broadcastMessage((ChatColor.translateAlternateColorCodes('&', "§7The Server is not &6&lREBOOTING&7.")));
						Bukkit.broadcastMessage(("§7§m--------------------------------"));
						return true;
					}
			

		sender.sendMessage(
				ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------");

		sender.sendMessage(ChatColor.GRAY + "Usage » /reboot start <duration>");

		sender.sendMessage(ChatColor.GRAY + "Usage » /reboot stop");

		sender.sendMessage(
				ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------------");
	}
	}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return args.length == 1 ? BukkitUtils.getCompletions(args, COMPLETIONS) : Collections.emptyList();
	}
}


