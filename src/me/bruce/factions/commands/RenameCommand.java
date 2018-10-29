package me.bruce.factions.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.bruce.factions.LorexHCF;

public class RenameCommand implements CommandExecutor {

	private LorexHCF plugin;

	public RenameCommand(LorexHCF plugin) {
		this.plugin = plugin;
	}

	public String getUsage(String label) {
		return String.valueOf('/') + label + ' ' + "<name>";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command is only executable by players.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length < 1) {
			p.sendMessage(ChatColor.RED + "Usage: " + this.getUsage(label));
			p.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&7If you want to use any color make sure you do & and then the color code."));
		} else {
			ItemStack hand = p.getItemInHand();
			if (hand.getType() != Material.AIR) {
				String name = "";
				for (int i = 0; i != args.length; i++)
					name += args[i] + " ";

				ItemMeta meta = hand.getItemMeta();
				meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r" + name));
				hand.setItemMeta(meta);

				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have successfully renamed your &c"
						+ hand.getType().name().toLowerCase() + " &7to &r" + name));
			} else {
				p.sendMessage(ChatColor.RED + "You cannot rename Air!");

			}
		}
		return true;
	}

}
