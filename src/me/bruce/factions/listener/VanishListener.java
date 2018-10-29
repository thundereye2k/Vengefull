package me.bruce.factions.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class VanishListener implements Listener{
	    public static Map<Player, Player> examineTasks;
	    static VanishListener instance;
	    private static ArrayList<Player> Vanish;
	    
	    static {
	        VanishListener.examineTasks = new HashMap<Player, Player>();
	        VanishListener.instance = new VanishListener();
	        VanishListener.Vanish = new ArrayList<Player>();
	    }
	    

	    public static VanishListener getInstance() {
	        return VanishListener.instance;
	    }
	    
	    public static boolean isVanished(final Player p) {
	        return VanishListener.Vanish.contains(p);
	    }
	    
	    @EventHandler
	    public void onJoin(final PlayerJoinEvent e) {
	        final Player player = e.getPlayer();
	        Player[] onlinePlayers;
	        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
	            final Player online = onlinePlayers[i];
	            if (isVanished(online)) {
	                if (player.hasPermission("core.mod")) {
	                    player.showPlayer(online);
	                }
	                else {
	                    player.hidePlayer(online);
	                }
	            }
	        }
	    }
	    
	    @EventHandler
	    public void onDamage(final EntityDamageEvent e) {
	        if (!(e.getEntity() instanceof Player)) {
	            return;
	        }
	        final Player p = (Player)e.getEntity();
	        if (isVanished(p)) {
	            e.setCancelled(true);
	        }
	    }
	    
	    @EventHandler
	    public void onDrop(final PlayerDropItemEvent e) {
	        if (isVanished(e.getPlayer())) {
	            e.setCancelled(true);
	        }
	    }
	    
	    @EventHandler
	    public void onDrop(final PlayerPickupItemEvent e) {
	        if (isVanished(e.getPlayer())) {
	            e.setCancelled(true);
	        }
	    }
	    
	    @EventHandler
	    public void onDamage(final EntityDamageByEntityEvent event) {
	        if (event.getDamager() instanceof Player) {
	            final Player player = (Player)event.getDamager();
	            if (isVanished(player)) {
	                event.setCancelled(true);
	            }
	        }
	    }
	    
	    public void setVanish(final Player p, final boolean b) {
	        if (b) {
	            VanishListener.Vanish.add(p);
	            Player[] onlinePlayers;
	            for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
	                final Player online = onlinePlayers[i];
	                if (online.hasPermission("core.mod")) {
	                    online.showPlayer(p);
	                }
	                else {
	                    online.hidePlayer(p);
	                }
	            }
	        }
	        if (!b) {
	            VanishListener.Vanish.remove(p);
	            Player[] onlinePlayers2;
	            for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length, j = 0; j < length2; ++j) {
	                final Player online = onlinePlayers2[j];
	                online.showPlayer(p);
	            }
	        }
	    }
	    
	    public ArrayList<Player> listInVanish() {
	        return VanishListener.Vanish;
	    }
	}


