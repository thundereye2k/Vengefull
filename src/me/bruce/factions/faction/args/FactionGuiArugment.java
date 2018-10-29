package me.bruce.factions.faction.args;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LorexMC.us.utils.other.command.CommandArgument;
import me.bruce.factions.LorexHCF;

public class FactionGuiArugment extends CommandArgument  {
	
	private final LorexHCF plugin;

	public FactionGuiArugment(LorexHCF plugin) {
		super("gui", "Shows who is in your faction etc.");
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + " <factionName>";
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can un-claim land from a faction.");
			return true;
		}

		Player p = (Player) sender;
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &c This command is coming soon..."));
		return true;
	}

}
