package me.tablist.api.tablist;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketPlayOutPlayerInfo;
import net.minecraft.util.io.netty.channel.Channel;
import me.tablist.api.tinyprotocol.TinyProtocol;

public class Protocol {

	public static void enablePacketListener() {
		new TinyProtocol(Xernos.plugin) {

			@Override
			public Object onPacketOutAsync(Player receiver, Channel channel, Object packet) {

				if(packet instanceof PacketPlayOutPlayerInfo) {

					Object profile = Reflex.getValue(packet, "player");
					String name = Reflex.getValue(profile, "name", String.class);

					if(!name.startsWith("§")) {
						if(isClient18(receiver) && Reflex.getValue(packet, "action", int.class)==0) {
							if(!receiver.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) {
								String username = Reflex.getValue(packet, "username", String.class);
								Team team = receiver.getScoreboard().getTeam("\\u000181");
								team.addEntry(username);
							}
							
							return packet;
						}
						
						return null;
					}
				}

				return packet;
			}

		};
	}

	public static EntityPlayer getEntity(Player player) {
		return ((CraftPlayer)player).getHandle();
	}

	public static boolean isClient18(Player player) {
		return getEntity(player).playerConnection.networkManager.getVersion()>5;
	}

	public static void sendPacket(Player player, Packet packet) {
		getEntity(player).playerConnection.sendPacket(packet);
	}

}
