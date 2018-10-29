package me.bruce.factions.faction.type;

import org.bukkit.command.CommandSender;

import me.bruce.factions.ymls.SettingsYML;
import java.util.Map;

/**
 * Represents the {@link WildernessFaction}.
 */
public class WildernessFaction extends Faction {

	public WildernessFaction() {
		super("Wilderness");
	}

	public WildernessFaction(Map<String, Object> map) {
		super(map);
	}

	@Override
	public String getDisplayName(CommandSender sender) {
		return SettingsYML.WILDERNESS_COLOUR + getName();
	}
}
