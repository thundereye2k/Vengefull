package me.bruce.factions.eventutils;

import LorexMC.us.utils.other.command.ArgumentExecutor;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.eventutils.argument.*;

/**
 * Handles the execution and tab completion of the event command.
 */
public class EventExecutor extends ArgumentExecutor {

	public EventExecutor(LorexHCF plugin) {
		super("event");

		addArgument(new EventCancelArgument(plugin));
		addArgument(new EventCreateArgument(plugin));
		addArgument(new EventDeleteArgument(plugin));
		addArgument(new EventRenameArgument(plugin));
		addArgument(new EventSetAreaArgument(plugin));
		addArgument(new EventSetCapzoneArgument(plugin));
		addArgument(new EventStartArgument(plugin));
		addArgument(new EventUptimeArgument(plugin));
	}
}