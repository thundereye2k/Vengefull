package me.bruce.factions.deathban.lives;

import LorexMC.us.utils.other.command.ArgumentExecutor;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.deathban.lives.args.*;

/**
 * Handles the execution and tab completion of the lives command.
 */
public class LivesExecutor extends ArgumentExecutor {

	public LivesExecutor(LorexHCF plugin) {
		super("lives");

		addArgument(new LivesCheckArgument(plugin));
		addArgument(new LivesCheckDeathbanArgument(plugin));
		addArgument(new LivesClearDeathbansArgument(plugin));
		addArgument(new LivesGiveArgument(plugin));
		addArgument(new LivesReviveArgument(plugin));
		addArgument(new LivesSetArgument(plugin));
		addArgument(new LivesSetDeathbanTimeArgument());
	}
}