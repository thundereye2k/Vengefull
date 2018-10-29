package me;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Utils {
	public static String c(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
	public static void broadcast(String message) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(Utils.c(message));
		}
	}
	

	
	public String getTabRank(Player p) {
        final String prefix = PermissionsEx.getUser(p).getGroups()[0].getPrefix().replace("[", "").replace("]", "");
        final String rank = PermissionsEx.getUser(p).getGroups()[0].getName();
            if (prefix.equals("&4")) {
            	return Utils.c("&4" + rank);
            } else if (prefix.equals("&c")) {
            	return Utils.c("&c" + rank);
            } else if (prefix.equals("&6")) {
            	return Utils.c("&6" + rank);
            } else if (prefix.equals("&e")) {
            	return Utils.c("&e" + rank);
            } else if (prefix.equals("&2")) {
            	return Utils.c("&2" + rank);
            } else if (prefix.equals("&a")) {
            	return Utils.c("&a" + rank);
            } else if (prefix.equals("&b")) {
            	return Utils.c("&b" + rank);
            } else if (prefix.equals("&3")) {
            	return Utils.c("&3" + rank);
            } else if (prefix.equals("&1")) {
            	return Utils.c("&1" + rank);
            } else if (prefix.equals("&9")) {
            	return Utils.c("&9" + rank);
            } else if (prefix.equals("&d")) {
            	return Utils.c("&d" + rank);
            } else if (prefix.equals("&5")) {
            	return Utils.c("&5" + rank);
            } else if (prefix.equals("&f")) {
            	return Utils.c("&f" + rank);
            } else if (prefix.equals("&7")) {
            	return Utils.c("&7" + rank);
            } else if (prefix.equals("&8")) {
            	return Utils.c("&8" + rank);
            } else if (prefix.equals("&0")) {
            	return Utils.c("&0" + rank);
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
