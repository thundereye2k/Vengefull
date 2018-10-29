package me.bruce.factions.commands;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import LorexMC.us.utils.BukkitUtils;
import LorexMC.us.utils.internal.com.bruce.base.BaseConstants;
import LorexMC.us.utils.internal.com.bruce.base.BasePlugin;

public class FixCommand implements CommandExecutor{
	   
    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        Player target;
        if (args.length > 0) {
            target = BukkitUtils.playerWithNameOrUUID(args[0]);
        }
        else {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Cannot use in console");
                return true;
            }
            target = (Player)sender;
        }
        if (target == null) {
            sender.sendMessage(String.format(BaseConstants.PLAYER_WITH_NAME_OR_UUID_NOT_FOUND, args[0]));
            return true;
        }
        final Set<ItemStack> toRepair = new HashSet<ItemStack>();
        if (args.length >= 1 && args[1].equalsIgnoreCase("all")) {
            final PlayerInventory targetInventory = target.getInventory();
            toRepair.addAll(Arrays.asList(targetInventory.getContents()));
            toRepair.addAll(Arrays.asList(targetInventory.getArmorContents()));
        }
        else {
            toRepair.add(target.getItemInHand());
        }
        for (final ItemStack stack : toRepair) {
            if (stack != null && stack.getType() != Material.AIR) {
                stack.setDurability((short)0);
            }
        }
        sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.GRAY + "Repaired " + ((toRepair.size() > 1) ? "all" : "item in hand") + " of " + target.getName() + '.');
        return true;
    }

}
