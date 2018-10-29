package me.bruce.factions.rank;

import LorexMC.us.utils.other.command.ArgumentExecutor;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.rank.args.RankSetArgument;
import me.bruce.factions.timer.args.TimerCheckArgument;
import me.bruce.factions.timer.args.TimerSetArgument;

public class RankExecutor extends ArgumentExecutor {

	public RankExecutor(LorexHCF plugin) {
		super("rank");

		addArgument(new RankSetArgument(plugin));
		//addArgument(new TimerSetArgument(plugin));
	}
}