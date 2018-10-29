package me.bruceboy.tablist.lorexmc.utils.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.commands.Tablist;
import me.bruce.factions.faction.type.PlayerFaction;
import me.bruceboy.tablist.lorexmc.utils.TabAdapter;
import me.bruceboy.tablist.lorexmc.utils.TabTemplate;

public class TabCreator implements TabAdapter {
	@Override
	public TabTemplate getTemplate(Player player) {
		TabTemplate template = new TabTemplate();
		final PlayerFaction faction = LorexHCF.getInstance().getFactionManager().getPlayerFaction(player);

		//if (Tablist.defaulty.contains(player)) {

			// Middle Tab
			template.middle(0, "&8&m---------------");
			template.middle(1, "&6&lZorex");
			template.middle(2, "&8&m---------------");

			// Left Tab
			template.left(0, "&8&m---------------");
			template.left(1, "&6Store.Zorex.us");
			template.left(2, "&8&m---------------");
			template.left(4, "&6Player Info:");
			template.left(5, "&6Kills: " + player.getStatistic(Statistic.PLAYER_KILLS));
			template.left(6, "&6Death: " + player.getStatistic(Statistic.DEATHS));

			template.left(8, "");

			template.left(9, "&6Your Location");
			template.left(10, LorexHCF.getInstance().getFactionManager().getFactionAt(player.getLocation())
					.getDisplayName(player));
			template.left(11,
					ChatColor.WHITE + "(" + player.getLocation().getBlockX() + ", " + player.getLocation().getBlockZ()
							+ ")" + ChatColor.GRAY + " [" + getCardinalDirection(player) + "]");

			// FactionListener Stuff Currently Fixing
			if (faction != null) {
				template.left(14, "&6§lFaction");
				template.left(15, "&6Fac: &2" + faction.getName());
				template.left(16, "&6Online " + ChatColor.GREEN + faction.getOnlineMembers().size() + "&8/"
						+ ChatColor.GREEN + faction.getMembers().size());
				template.left(17, "&6Home: " + ChatColor.GRAY + ((faction.getHome() == null) ? "Not set"
						: (String.valueOf(faction.getHome().getBlockX()) + ", " + faction.getHome().getBlockZ())));
			} else {
				template.left(14, "&6§lFaction");
				template.left(15, "&6No Faction");
			}

			// Right Tab
			template.right(0, "&8&m---------------");
			template.right(1, "&6ts.Zorex.us");
			template.right(2, "&8&m---------------");
			template.right(4, "&6End Portal");
			template.right(5, "&fSpawn");
			template.right(6, "&f");
			template.right(7, "");
			template.right(8, "&6Map Kit:");
			template.right(9, "&fProt 2, Sharp 2");
			template.right(10, "");
			template.right(11, "&6World Border:");
			template.right(12, "&f2000");
			template.right(13, "");
			template.right(14, "&6Players Online:");
			template.right(15, "&f" + Bukkit.getServer().getOnlinePlayers().length);

			// Bottom
			template.left(19, "&8&m---------------");
			template.middle(19, "&8&m---------------");
			template.right(19, "&8&m---------------");

			// 1.8 users only
			template.farRight(8, "§4§lWARNING!");
			template.farRight(9, "§4we recommend");
			template.farRight(10, "§4you use version 1.7");
			template.farRight(11, "§4For Better");
			template.farRight(12, "§4Gaming Expierence.");

			return template;
		}

	

	public static String getCardinalDirection(Player player) {
		double rotation = (player.getLocation().getYaw() + 180F) % 360.0F;
		if (rotation < 0.0D) {
			rotation += 360.0D;
		}
		if ((0.0D <= rotation) && (rotation < 22.5D)) {
			return "N";
		}
		if ((22.5D <= rotation) && (rotation < 67.5D)) {
			return "NE";
		}
		if ((67.5D <= rotation) && (rotation < 112.5D)) {
			return "E";
		}
		if ((112.5D <= rotation) && (rotation < 157.5D)) {
			return "SE";
		}
		if ((157.5D <= rotation) && (rotation < 202.5D)) {
			return "S";
		}
		if ((202.5D <= rotation) && (rotation < 247.5D)) {
			return "SW";
		}
		if ((247.5D <= rotation) && (rotation < 292.5D)) {
			return "W";
		}
		if ((292.5D <= rotation) && (rotation < 337.5D)) {
			return "NW";
		}
		if ((337.5D <= rotation) && (rotation < 360.0D)) {
			return "N";
		}
		return null;
	}
}
