package me.bruce.factions.faction.args;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import LorexMC.us.utils.other.command.CommandArgument;
import me.bruce.factions.LorexHCF;

public class FactionSettingsArgument extends CommandArgument {
	
	private final LorexHCF plugin;

	public  FactionSettingsArgument(LorexHCF plugin) {
		super("settings", "FactionListener Settings.");
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command is only executable by players.");
			return true;
		}
		Player p = (Player) sender;

		
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &cFeature coming soon..."));
		return true;
	}

}
