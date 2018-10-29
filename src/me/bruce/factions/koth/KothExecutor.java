package me.bruce.factions.koth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import LorexMC.us.utils.other.command.ArgumentExecutor;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.koth.args.KothHelpArgument;
import me.bruce.factions.koth.args.KothNextArgument;
import me.bruce.factions.koth.args.KothScheduleArgument;
import me.bruce.factions.koth.args.KothSetCapDelayArgument;

/**
 * Command used to handle KingOfTheHills.
 */
public class KothExecutor extends ArgumentExecutor {

	private final KothScheduleArgument kothScheduleArgument;

	public KothExecutor(LorexHCF plugin) {
		super("koth");

		addArgument(new KothHelpArgument(this));
		addArgument(new KothNextArgument(plugin));
		addArgument(this.kothScheduleArgument = new KothScheduleArgument(plugin));
		addArgument(new KothSetCapDelayArgument(plugin));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 1) {
			this.kothScheduleArgument.onCommand(sender, command, label, args);
			return true;
		}

		return super.onCommand(sender, command, label, args);
	}
}
