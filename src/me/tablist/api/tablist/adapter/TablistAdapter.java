package me.tablist.api.tablist.adapter;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.type.PlayerFaction;
import me.bruce.factions.ymls.AThing;
import me.bruce.factions.ymls.SettingsYML;
import me.tablist.api.tablist.Xernos;

public class TablistAdapter {


	public static String loading;
	LorexHCF plugin;
	AThing utils;
	public TablistAdapter(final LorexHCF lorexHCF, final AThing utils) {
		this.plugin = lorexHCF;
		this.utils = utils;
	}
	public static ArrayList<Player> tablist = new ArrayList<>();


	public void addTabEntry(Player player, Xernos tab) {
		final String spacer = "&7&m--------------";
								
		if(tablist.contains(player)) {
			setupHCF(tab, player);
		} else {
			setupNormal(player, tab);

		}

		if(tab.isClient18()) {
			//Just leave this as it is, you can change colors but it was a hard time finding the good position... 
			tab.setSlot(68, "&4&lWarning!");
			tab.setSlot(69, "&7We recommend");
			tab.setSlot(70, "&7use version 1.7.10");
			tab.setSlot(71, "&7for a better");
			tab.setSlot(72, "&7gaming experience");
			tab.setSlot(73, "&7and more fluid pvp!");
			
			
			tab.setSlot(10, spacer);
			tab.setSlot(61, spacer);

				
			//You can change this to whatever you want, or make it configurable.
				
				


		//	tab.setPlayerListHeaderFooter(header, footer);
			}
		}
	
	
	

    public static String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() + 180F) % 360.0F;
        if (rotation < 0.0D) {
            rotation += 360.0D;
        }
        if ((0.0D <= rotation) && (rotation < 22.5D)) {
            return "N";
        }
        if ((22.5D <= rotation) && (rotation < 67.5D)) {
            return "NE";
        }
        if ((67.5D <= rotation) && (rotation < 112.5D)) {
            return "E";
        }
        if ((112.5D <= rotation) && (rotation < 157.5D)) {
            return "SE";
        }
        if ((157.5D <= rotation) && (rotation < 202.5D)) {
            return "S";
        }
        if ((202.5D <= rotation) && (rotation < 247.5D)) {
            return "SW";
        }
        if ((247.5D <= rotation) && (rotation < 292.5D)) {
            return "W";
        }
        if ((292.5D <= rotation) && (rotation < 337.5D)) {
            return "NW";
        }
        if ((337.5D <= rotation) && (rotation < 360.0D)) {
            return "N";
        }
        return null;
    }
    
    
    public void setupHCF(Xernos tab, Player p) {
    	final PlayerFaction faction = LorexHCF.getInstance().getFactionManager().getPlayerFaction(p);
    	// Info
    	final String spacer = "&7&m--------------";

		tab.setSlot(1, spacer);
		tab.setSlot(2, spacer);
		tab.setSlot(3, spacer);

		tab.setSlot(4, "&6store.zorex.us");
		tab.setSlot(5, "&6&lZorex Network");
		tab.setSlot(6, "&6ts.zorex.us");
		
		tab.setSlot(13,  "&6&lPlayer Info");
		tab.setSlot(16, "&eGroup: &7" + LorexHCF.getInstance().permission.getPrimaryGroup(p));
		tab.setSlot(19, "&eKills: &7" + p.getStatistic(Statistic.PLAYER_KILLS));
		tab.setSlot(22, "&eDeaths: &7" + p.getStatistic(Statistic.DEATHS));
		tab.setSlot(25, "&ePing: &7" + p.getPing());
		
		
		if(faction != null) {
			tab.setSlot(14, "&6&lFaction");
			tab.setSlot(15, "&6&lFaction Info");
			tab.setSlot(18, "&eDTR: " + faction.getDeathsUntilRaidable());	
			tab.setSlot(17, "&eName: &a" + faction.getName());
			tab.setSlot(21, "&eOnline: &a" + faction.getOnlineMembers().size());
			tab.setSlot(24, "&eHome: &7" +  ((faction.getHome() == null) ? "&cNone"
			: (String.valueOf(faction.getHome().getBlockX()) + ", " + faction.getHome().getBlockZ())));
		}
		else {
			tab.setSlot(14, "");
			tab.setSlot(15, "");
			tab.setSlot(17, "");
			tab.setSlot(18, "");
			tab.setSlot(21, "");
			tab.setSlot(27, "");
			tab.setSlot(24, "");
		}

		
		

		tab.setSlot(7, spacer);
		tab.setSlot(8, spacer);
		tab.setSlot(9, spacer);

		tab.setSlot(58, spacer);
		tab.setSlot(59, spacer);
		tab.setSlot(60, spacer);


			String header = "&7&m----------------------------------------------------------------------------";
			
			String footer = "&7&m----------------------------------------------------------------------------";
			
			
			// New feature
			tab.setPlayerListHeaderFooter(header, footer);
		}

   	
    
	public static void setupNormal(Player player, Xernos tab) {
		int i = 1;
		int player2 = i - 1;
		for(Bukkit.getOnlinePlayers();;) {
			tab.setSlot(i, Bukkit.getOnlinePlayers()[player2].getPlayerListName());
			i++;
		}
	}
    
    public void setTabFaithful(Player p, Xernos tab) {
    	
    }
    
    public void setTabzHub(Player p, Xernos tab) {
    	
    }
    
    //public String getColorScheme(String message) {
    	//return this.plugin.getConfig().getString("CUSTOM_TAB_COLOR_SCHEME") + message;
  //  }

}
