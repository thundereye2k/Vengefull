package me.bruce.factions.faction.type;

import java.util.Map;

public abstract class CapturableFaction extends EventFaction {

	public CapturableFaction(String name) {
		super(name);
	}

	public CapturableFaction(Map<String, Object> map) {
		super(map);
	}
}
