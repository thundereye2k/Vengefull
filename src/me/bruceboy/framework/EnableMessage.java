package me.bruceboy.framework;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class EnableMessage extends JavaPlugin {
	
	public void onEnable() {
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9&lAuraPots &8» &7The Base Plugin has been enabled"));
	}

}
