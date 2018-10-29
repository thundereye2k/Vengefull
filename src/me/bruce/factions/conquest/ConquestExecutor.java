package me.bruce.factions.conquest;

import LorexMC.us.utils.other.command.ArgumentExecutor;
import me.bruce.factions.LorexHCF;

public class ConquestExecutor extends ArgumentExecutor {

	public ConquestExecutor(LorexHCF plugin) {
		super("conquest");
		addArgument(new ConquestSetpointsArgument(plugin));
	}
}
