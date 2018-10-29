package me.bruce.factions.listener.combatloggers;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import java.util.UUID;
import me.bruce.factions.LorexHCF;

public abstract interface LoggerEntity {
	public abstract void postSpawn(LorexHCF paramHCF);

	public abstract CraftPlayer getBukkitEntity();

	public abstract UUID getUniqueID();

	public abstract void destroy();
}
