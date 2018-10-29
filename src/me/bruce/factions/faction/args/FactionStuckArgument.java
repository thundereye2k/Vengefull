package me.bruce.factions.faction.args;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LorexMC.us.utils.DurationFormatter;
import LorexMC.us.utils.other.command.CommandArgument;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.type.SpawnFaction;
import me.bruce.factions.timer.type.StuckTimer;

public class FactionStuckArgument extends CommandArgument {
	private final LorexHCF plugin;

	public FactionStuckArgument(final LorexHCF plugin) {
		super("stuck", "Teleport to a safe position.", new String[] { "trap", "trapped" });
		this.plugin = plugin;
	}

	@Override
	public String getUsage(final String label) {
		return '/' + label + ' ' + this.getName();
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label,
			final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command is only executable by players.");
			return true;
		}
		final Player player = (Player) sender;
		if (player.getWorld().getEnvironment() != World.Environment.NORMAL) {
			sender.sendMessage(ChatColor.RED + "You can only use this command from the overworld.");
			return true;
		}
		if (plugin.getFactionManager().getFactionAt(((Player) sender).getLocation()) instanceof SpawnFaction) {
			sender.sendMessage(ChatColor.RED + "You cannot " + label + " " + this.getName() + " inside of Spawn");
			return true;
		}
		final StuckTimer stuckTimer = this.plugin.getTimerManager().getStuckTimer();
		if (!stuckTimer.setCooldown(player, player.getUniqueId())) {
			sender.sendMessage(ChatColor.YELLOW + "Your " + stuckTimer.getDisplayName() + ChatColor.YELLOW
					+ " timer has a remaining " + ChatColor.LIGHT_PURPLE
					+ DurationFormatUtils.formatDurationWords(stuckTimer.getRemaining(player), true, true)
					+ ChatColor.YELLOW + '.');
			return true;
		}
		sender.sendMessage(ChatColor.YELLOW + stuckTimer.getDisplayName() + ChatColor.YELLOW + " timer has started. "
				+ "\nTeleportation will commence in " + ChatColor.LIGHT_PURPLE
				+ DurationFormatter.getRemaining(stuckTimer.getRemaining(player), true, false) + ChatColor.YELLOW + ". "
				+ "\nThis will cancel if you move more than " + 5 + " blocks.");
		return true;
	}
}
