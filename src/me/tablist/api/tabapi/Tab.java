package me.tablist.api.tabapi;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.bruce.factions.LorexHCF;
import me.tablist.api.tablist.Xernos;
import me.tablist.api.tablist.adapter.TablistAdapter;
import me.bruce.factions.ymls.AThing;



public class Tab implements Listener {
	
	LorexHCF plugin;
	AThing utils;
	TablistAdapter ta;
	public Tab(final LorexHCF plugin, final AThing utils, final TablistAdapter ta) {
		this.plugin = plugin;
		this.utils = utils;
		this.ta = ta;
		Xernos.setPlugin(this.plugin);
	}
		
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {


		Xernos tab = Xernos.createTablist(event.getPlayer());

		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(Xernos.hasTablist(event.getPlayer())) {
					ta.addTabEntry(event.getPlayer(), tab);
					return;
					
				}else {
					cancel();
				}
				
			}
			// this might cause the server to lag, but I use 1, 1 for HCF, I'd change it to 20, 20, but it's your decission.
		}.runTaskTimerAsynchronously(this.plugin, 20, 20); 

	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Xernos.deleteTablist(event.getPlayer());
	}

}
