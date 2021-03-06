package me.bruce.factions.faction.event;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import me.bruce.factions.faction.type.Faction;

/**
 * Event called when a {@link FactionListener} is about to be removed.
 */
public class FactionRemoveEvent extends FactionEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private boolean cancelled;
	private final CommandSender sender;

	public FactionRemoveEvent(Faction faction, CommandSender sender) {
		super(faction);
		this.sender = sender;
	}

	/**
	 * Gets the {@link CommandSender} attempting to remove this {@link FactionListener}.
	 *
	 * @return the {@link CommandSender}
	 */
	public CommandSender getSender() {
		return sender;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
