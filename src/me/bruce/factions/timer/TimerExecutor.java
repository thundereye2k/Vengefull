package me.bruce.factions.timer;

import LorexMC.us.utils.other.command.ArgumentExecutor;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.timer.args.TimerCheckArgument;
import me.bruce.factions.timer.args.TimerSetArgument;

/**
 * Handles the execution and tab completion of the timer command.
 */
public class TimerExecutor extends ArgumentExecutor {

	public TimerExecutor(LorexHCF plugin) {
		super("timer");

		addArgument(new TimerCheckArgument(plugin));
		addArgument(new TimerSetArgument(plugin));
	}
}