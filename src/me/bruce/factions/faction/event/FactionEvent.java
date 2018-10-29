package me.bruce.factions.faction.event;

import com.google.common.base.Preconditions;

import me.bruce.factions.faction.type.Faction;

import org.bukkit.event.Event;

/**
 * Represents a faction related event
 */
public abstract class FactionEvent extends Event {

	protected final Faction faction;

	public FactionEvent(final Faction faction) {
		this.faction = Preconditions.checkNotNull(faction, "Faction cannot be null");
	}

	FactionEvent(final Faction faction, boolean async) {
		super(async);
		this.faction = Preconditions.checkNotNull(faction, "Faction cannot be null");
	}

	/**
	 * Returns the {@link FactionListener} involved in this event
	 *
	 * @return the {@link FactionListener} that is involved in this event
	 */
	public Faction getFaction() {
		return faction;
	}
}