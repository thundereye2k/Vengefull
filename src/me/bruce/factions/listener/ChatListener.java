package me.bruce.factions.listener;

import com.google.common.collect.ImmutableSet;
import me.bruce.factions.LorexHCF;
import me.bruce.factions.faction.event.FactionChatEvent;
import me.bruce.factions.faction.struct.ChatChannel;
import me.bruce.factions.faction.type.PlayerFaction;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.google.common.collect.MapMaker;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
	private static final String EOTW_CAPPER_PREFIX = ChatColor.YELLOW + " TESTEOTWPREFIX ";
	private static final ImmutableSet<UUID> EOTW_CAPPERS;
	private static final String DOUBLE_POST_BYPASS_PERMISSION = "hcf.doublepost.bypass";

	static {
		ImmutableSet.Builder<UUID> builder = ImmutableSet.builder();

		EOTW_CAPPERS = builder.build();
	}

	private static final Pattern PATTERN = Pattern.compile("\\W");
	private final Map<UUID, String> messageHistory;
	private final LorexHCF plugin;

	public ChatListener(LorexHCF plugin) {
		this.plugin = plugin;

		this.messageHistory = new MapMaker().makeMap();
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event) {

		String message = event.getMessage();
		Player player = event.getPlayer();

		PlayerFaction playerFaction = this.plugin.getFactionManager().getPlayerFaction(player);
		ChatChannel chatChannel = playerFaction == null ? ChatChannel.PUBLIC
				: playerFaction.getMember(player).getChatChannel();

		Set<Player> recipients = event.getRecipients();
		if ((chatChannel == ChatChannel.FACTION) || (chatChannel == ChatChannel.ALLIANCE)) {
			if (isGlobalChannel(message)) {
				message = message.substring(1, message.length()).trim();
				event.setMessage(message);
			} else {
				Collection<Player> online = playerFaction.getOnlinePlayers();
				if (chatChannel == ChatChannel.ALLIANCE) {
					Collection<PlayerFaction> allies = playerFaction.getAlliedFactions();
					for (PlayerFaction ally : allies) {
						online.addAll(ally.getOnlinePlayers());
					}
				}
				recipients.retainAll(online);
				event.setFormat(chatChannel.getRawFormat(player));

				Bukkit.getPluginManager().callEvent(
						new FactionChatEvent(true, playerFaction, player, chatChannel, recipients, event.getMessage()));
				return;
			}
			
		}
		event.setCancelled(true);
        final String rank = ChatColor.translateAlternateColorCodes('&', "&7" + PermissionsEx.getUser(player).getPrefix()).replace("_", " ");
        String displayName = player.getDisplayName();
        displayName = String.valueOf(rank) + displayName;
        String tag = (playerFaction == null) ? (ChatColor.RED + "-") : playerFaction.getDisplayName((CommandSender)Bukkit.getConsoleSender());
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[" + tag + ChatColor.GOLD + "] " + displayName + ChatColor.WHITE + ": " + message);
        for (final Player recipient : event.getRecipients()) {
            if (playerFaction != null) {
                tag = ((playerFaction == null) ? (ChatColor.RED + "*") : playerFaction.getDisplayName((CommandSender)recipient));
                if (player.hasPermission("kitmap.staff")) {
                    recipient.sendMessage(ChatColor.GOLD + "[" + tag + ChatColor.GOLD + "] " + displayName + ChatColor.WHITE + ": " + ChatColor.WHITE + message);
                }
                else {
                    recipient.sendMessage(ChatColor.GOLD + "[" + tag + ChatColor.GOLD + "] " + displayName + ChatColor.WHITE + ": " + message);
                }
            }
            else if (player.hasPermission("kitmap.staff")) {
                recipient.sendMessage(ChatColor.GOLD + "[�c-�6] " + ChatColor.WHITE + displayName + ChatColor.WHITE + ": " + ChatColor.WHITE + message);
            }
            else {
                recipient.sendMessage(ChatColor.GOLD + "[�c-�6] " + ChatColor.WHITE + displayName + ChatColor.WHITE + ": " + message);
            }
        }
    }
	private boolean isGlobalChannel(String input) {
		int length = input.length();
		if ((length <= 1) || (!input.startsWith("!"))) {
			return false;
		}
		for (int i = 1; i < length; i++) {
			char character = input.charAt(i);
			if (character != ' ') {
				if (character != '/') {
					break;
				}
				return false;
			}
		}
		return true;
	}
}
