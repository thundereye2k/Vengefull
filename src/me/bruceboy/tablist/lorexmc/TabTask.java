package me.bruceboy.tablist.lorexmc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import me.bruceboy.tablist.lorexmc.utils.Tab;
import me.bruceboy.tablist.lorexmc.utils.Tab.TabEntryPosition;
import me.bruceboy.tablist.lorexmc.utils.TabAdapter;
import me.bruceboy.tablist.lorexmc.utils.TabTemplate;

import java.util.*;

/*
    TODO: Clean this thing up
 */
public class TabTask extends BukkitRunnable {

	private final TabList tabList;

	public TabTask(TabList tabList, JavaPlugin plugin) {
		this.tabList = tabList;

		runTaskTimerAsynchronously(plugin, 2L, 2L);
	}

	@Override
	public void run() {
		TabAdapter adapter = tabList.getAdapter();
		if (adapter != null) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				Tab tab = tabList.getTabByPlayer(player);
				if (tab != null) {
					TabTemplate template = adapter.getTemplate(player);

					if (template == null || (template.getLeft().isEmpty() && template.getMiddle().isEmpty()
							&& template.getRight().isEmpty())) {
						for (Tab.TabEntryPosition position : tab.getPositions()) {
							Team team = player.getScoreboard().getTeam(position.getKey());
							if (team != null) {
								if (team.getPrefix() != null && !team.getPrefix().isEmpty()) {
									team.setPrefix("");
								}
								if (team.getSuffix() != null && !team.getSuffix().isEmpty()) {
									team.setSuffix("");
								}
							}
						}
						continue;
					}

					for (int i = 0; i < 20 - ((Set<TabEntryPosition>) template.getLeft()).size(); i++) {
						template.left("");
					}

					for (int i = 0; i < 20 - ((Set<TabEntryPosition>) template.getMiddle()).size(); i++) {
						template.middle("");
					}

					for (int i = 0; i < 20 - ((Set<TabEntryPosition>) template.getRight()).size(); i++) {
						template.right("");
					}

					List<Object> rows = Arrays.asList(template.getLeft(), template.getMiddle(),
							template.getRight(), template.getFarRight());
					for (int l = 0; l < rows.size(); l++) {
						for (int i = 0; i < ((Set<TabEntryPosition>) rows.get(l)).size(); i++) {
							Team team = tab.getByLocation(l, i);
							if (team != null) {
								Map.Entry<String, String> prefixAndSuffix = getPrefixAndSuffix((String) ((List<Object>) rows.get(l)).get(i));
								String prefix = prefixAndSuffix.getKey();
								String suffix = prefixAndSuffix.getValue();

								if (team.getPrefix().equals(prefix) && team.getSuffix().equals(suffix)) {
									continue;
								}

								team.setPrefix(prefix);
								team.setSuffix(suffix);
							}
						}
					}
				}
			}
		}
	}

	private Map.Entry<String, String> getPrefixAndSuffix(String object) {
		String prefix, suffix;

		object = ChatColor.translateAlternateColorCodes('&', object);

		if (object.length() > 16) {
			int splitAt = object.charAt(15) == ChatColor.COLOR_CHAR ? 15 : 16;
			prefix = object.substring(0, splitAt);
			String suffixTemp = ChatColor.getLastColors(prefix) + object.substring(splitAt);
			suffix = (suffixTemp.substring(0, Math.min(suffixTemp.length(), 16)));
		} else {
			prefix = object;
			suffix = "";
		}

		return new AbstractMap.SimpleEntry<>(prefix, suffix);
	}
}