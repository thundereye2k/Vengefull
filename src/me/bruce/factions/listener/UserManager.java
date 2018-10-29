package me.bruce.factions.listener;

import org.bukkit.configuration.MemorySection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.google.common.base.MoreObjects;

import LorexMC.us.utils.Config;
import LorexMC.us.utils.compat.com.google.common.collect.GuavaCompat;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.FactionUser;

import java.util.*;

public class UserManager implements Listener {

	private final LorexHCF plugin;

	public final Map<UUID, FactionUser> users = new HashMap<>();
	private Config userConfig;

	public UserManager(LorexHCF plugin) {
		this.plugin = plugin;
		this.reloadUserData();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event) {
		UUID uuid = event.getPlayer().getUniqueId();
		users.putIfAbsent(uuid, new FactionUser(uuid));
	}

	/**
	 * Gets a map of {@link FactionUser} this manager holds.
	 *
	 * @return map of user UUID strings to their corresponding {@link FactionUser}.
	 */
	public Map<UUID, FactionUser> getUsers() {
		return users;
	}

	/**
	 * Gets a {@link FactionUser} by their {@link UUID} asynchronously.
	 *
	 * @param uuid
	 *            the {@link UUID} to get from
	 * @return the {@link FactionUser} with the {@link UUID}
	 */
	public FactionUser getUserAsync(UUID uuid) {
		synchronized (users) {
			FactionUser revert;
			FactionUser user = users.putIfAbsent(uuid, revert = new FactionUser(uuid));
			return GuavaCompat.firstNonNull(user, revert);
		}
	}

	/**
	 * Gets a {@link FactionUser} by their {@link UUID}.
	 *
	 * @param uuid
	 *            the {@link UUID} to get from
	 * @return the {@link FactionUser} with the {@link UUID}
	 */
	public FactionUser getUser(final UUID uuid) {
		final FactionUser revert;
		final FactionUser user = this.users.putIfAbsent(uuid, revert = new FactionUser(uuid));
		return (FactionUser) MoreObjects.firstNonNull((Object) user, (Object) revert);
	}

	/**
	 * Loads the user data from storage.
	 */
	public void reloadUserData() {
		this.userConfig = new Config(plugin, "faction-users");

		Object object = userConfig.get("users");
		if (object instanceof MemorySection) {
			MemorySection section = (MemorySection) object;
			Collection<String> keys = section.getKeys(false);
			for (String id : keys) {
				users.put(UUID.fromString(id), (FactionUser) userConfig.get(section.getCurrentPath() + '.' + id));
			}
		}
	}

	/**
	 * Saves the user data to storage.
	 */
	public void saveUserData() {
		Set<Map.Entry<UUID, FactionUser>> entrySet = users.entrySet();
		Map<String, FactionUser> saveMap = new LinkedHashMap<>(entrySet.size());
		for (Map.Entry<UUID, FactionUser> entry : entrySet) {
			saveMap.put(entry.getKey().toString(), entry.getValue());
		}

		userConfig.set("users", saveMap);
		userConfig.save();
	}
}
