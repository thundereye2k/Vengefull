package me.bruce.factions.eventutils;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.eventutils.tracker.ConquestTracker;
import me.bruce.factions.eventutils.tracker.EventTracker;
import me.bruce.factions.eventutils.tracker.KothTracker;

public enum EventType {

	CONQUEST("Conquest", new ConquestTracker(LorexHCF.getInstance())), KOTH("KOTH",
			new KothTracker(LorexHCF.getInstance()));

	private final EventTracker eventTracker;
	private final String displayName;

	EventType(String displayName, EventTracker eventTracker) {
		this.displayName = displayName;
		this.eventTracker = eventTracker;
	}

	public EventTracker getEventTracker() {
		return eventTracker;
	}

	public String getDisplayName() {
		return displayName;
	}

	private static final ImmutableMap<String, EventType> byDisplayName;

	static {
		ImmutableMap.Builder<String, EventType> builder = new ImmutableBiMap.Builder<>();
		for (EventType eventType : values()) {
			builder.put(eventType.displayName.toLowerCase(), eventType);
		}

		byDisplayName = builder.build();
	}

	@Deprecated
	public static EventType getByDisplayName(String name) {
		return byDisplayName.get(name.toLowerCase());
	}
}
