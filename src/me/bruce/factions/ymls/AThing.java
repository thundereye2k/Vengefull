package me.bruce.factions.ymls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import me.bruce.factions.LorexHCF;

public class AThing {
	
	LorexHCF plugin;
	public AThing(final LorexHCF plugin) {
		this.plugin = plugin;
	}
	
	public static String c(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
	public static void broadcast(String message) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(AThing.c(message));
		}
	}
	

	
	public String getTabRank(Player p) {
        final String prefix = PermissionsEx.getUser(p).getGroups()[0].getPrefix().replace("[", "").replace("]", "");
        final String rank = PermissionsEx.getUser(p).getGroups()[0].getName();
            if (prefix.equals("&4")) {
            	return AThing.c("&4" + rank);
            } else if (prefix.equals("&c")) {
            	return AThing.c("&c" + rank);
            } else if (prefix.equals("&6")) {
            	return AThing.c("&6" + rank);
            } else if (prefix.equals("&e")) {
            	return AThing.c("&e" + rank);
            } else if (prefix.equals("&2")) {
            	return AThing.c("&2" + rank);
            } else if (prefix.equals("&a")) {
            	return AThing.c("&a" + rank);
            } else if (prefix.equals("&b")) {
            	return AThing.c("&b" + rank);
            } else if (prefix.equals("&3")) {
            	return AThing.c("&3" + rank);
            } else if (prefix.equals("&1")) {
            	return AThing.c("&1" + rank);
            } else if (prefix.equals("&9")) {
            	return AThing.c("&9" + rank);
            } else if (prefix.equals("&d")) {
            	return AThing.c("&d" + rank);
            } else if (prefix.equals("&5")) {
            	return AThing.c("&5" + rank);
            } else if (prefix.equals("&f")) {
            	return AThing.c("&f" + rank);
            } else if (prefix.equals("&7")) {
            	return AThing.c("&7" + rank);
            } else if (prefix.equals("&8")) {
            	return AThing.c("&8" + rank);
            } else if (prefix.equals("&0")) {
            	return AThing.c("&0" + rank);
            } else if (prefix.equals(null)) {
            	return rank;
            } else if (prefix.equals("")) {
            	return rank;
            } else if (prefix.equals(" ")) {
            	return rank;
            } else {
            	return prefix;
            }

	}
	

}
