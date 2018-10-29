package me.bruce.factions.staff;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerVanishEvent extends PlayerEvent implements Cancellable
{
    private static final HandlerList handlers;
    private final boolean vanished;
    private final Collection<Player> viewers;
    private boolean cancelled;
    
    static {
        handlers = new HandlerList();
    }
    
    public PlayerVanishEvent(final Player player, final Collection<Player> viewers, final boolean vanished) {
        super(player);
        this.viewers = viewers;
        this.vanished = vanished;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerVanishEvent.handlers;
    }
    
    public Collection<Player> getViewers() {
        return this.viewers;
    }
    
    public boolean isVanished() {
        return this.vanished;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return PlayerVanishEvent.handlers;
    }
}
