package LorexMC.us.utils.internal.com.bruce.base;

import java.util.*;
import org.bukkit.plugin.java.*;

import LorexMC.us.utils.PersistableLocation;
import LorexMC.us.utils.SignHandler;
import LorexMC.us.utils.other.chat.*;
import LorexMC.us.utils.other.cuboid.*;
import LorexMC.us.utils.other.itemdb.*;
import me.bruce.factions.listener.PlayTimeManager;

import org.bukkit.configuration.serialization.*;

import java.io.*;

public class BasePlugin {
	private Random random;
	private static ItemDb itemDb;
	private SignHandler signHandler;
	private static BasePlugin plugin;
	private JavaPlugin javaPlugin;

	private BasePlugin() {
		this.random = new Random();
	}

	public void init(final JavaPlugin plugin) {
		this.javaPlugin = plugin;
		ConfigurationSerialization.registerClass(PersistableLocation.class);
		ConfigurationSerialization.registerClass(Cuboid.class);
		ConfigurationSerialization.registerClass(NamedCuboid.class);
		this.itemDb = new SimpleItemDb(plugin);
		this.signHandler = new SignHandler(plugin);
		try {
			Lang.initialize("en_US");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void disable() {
		this.signHandler.cancelTasks(null);
		this.javaPlugin = null;
		BasePlugin.plugin = null;
	}

	public Random getRandom() {
		return this.random;
	}

	public static ItemDb getItemDb() {
		return itemDb;
	}

	public SignHandler getSignHandler() {
		return this.signHandler;
	}

	public static BasePlugin getPlugin() {
		return BasePlugin.plugin;
	}

	public JavaPlugin getJavaPlugin() {
		return this.javaPlugin;
	}

	static {
		BasePlugin.plugin = new BasePlugin();
	}

	public PlayTimeManager getPlayTimeManager() {
		// TODO Auto-generated method stub
		return null;
	}
}
