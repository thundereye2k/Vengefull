package me.bruce.factions.startoftheworld;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import me.bruce.factions.LorexHCF;

public class SotwListener implements Listener {

	private final LorexHCF plugin;

	public SotwListener(LorexHCF plugin) {
		this.plugin = plugin;
	}

    @EventHandler
    public void entityDamageByEntity(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            final Player d = (Player)e.getDamager();
            if (this.plugin.getSotwTimer().getSotwRunnable() != null && SotwCommand.enabled.contains(d.getUniqueId()) && !SotwCommand.enabled.contains(p.getUniqueId())) {
                d.sendMessage(LorexHCF.PREFIX + "Cannot hit this player because they do not have sotw enabled on");
                e.setCancelled(true);
            }
            else if (this.plugin.getSotwTimer().getSotwRunnable() != null && !SotwCommand.enabled.contains(d.getUniqueId()) && SotwCommand.enabled.contains(p.getUniqueId())) {
                d.sendMessage(LorexHCF.PREFIX + "Cannot hit that player");
                e.setCancelled(true);
            }
            else if (this.plugin.getSotwTimer().getSotwRunnable() != null  && !SotwCommand.enabled.contains(d.getUniqueId()) && !SotwCommand.enabled.contains(p.getUniqueId())) {
            	d.sendMessage(LorexHCF.PREFIX + "You cannot hit players whislt sotw is active if you would like to do /sotw enable!");
            	e.setCancelled(true);
            }
           
        }
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onEntityDamage(final EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && this.plugin.getSotwTimer().getSotwRunnable() != null) {
            final Player p = (Player)event.getEntity();
            if (SotwCommand.enabled.contains(p.getUniqueId())) {
                event.setCancelled(false);
                return;
            }
            if (event.getCause() != EntityDamageEvent.DamageCause.SUICIDE && this.plugin.getSotwTimer().getSotwRunnable() != null) {
                event.setCancelled(true);
            }
        }
    }
}
