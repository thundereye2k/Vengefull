package me.bruce.factions.faction.type;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * Represents the {@link SpawnFaction}.
 */
public class SpawnFaction extends ClaimableFaction implements ConfigurationSerializable {

	public SpawnFaction() {
		super("Spawn");

		this.safezone = true;
	}

	public SpawnFaction(Map<String, Object> map) {
		super(map);
	}

	@Override
	public boolean isDeathban() {
		return false;
	}
}