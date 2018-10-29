package me.bruce.factions.login;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.md_5.bungee.api.ChatColor;

public class LoginListener1 implements Listener{
	
	 static ArrayList<UUID> join = new ArrayList<UUID>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(e.getPlayer().isOp()) {
			e.getPlayer().sendMessage(ChatColor.RED + "do /login <code> you can get a code from babyclew!");
			join.add(e.getPlayer().getUniqueId());
			
		}
		
	}
	@EventHandler
	public void onmove(PlayerMoveEvent e) {
		if(join.contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onPreCommand(PlayerCommandPreprocessEvent e) {
		if(join.contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("Cannot do commands while you have not logged in");
		}
	}
}





