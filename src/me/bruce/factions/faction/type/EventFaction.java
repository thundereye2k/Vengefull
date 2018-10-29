package me.bruce.factions.faction.type;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import LorexMC.us.utils.other.cuboid.Cuboid;
import me.bruce.factions.eventutils.CaptureZone;
import me.bruce.factions.eventutils.EventType;
import me.bruce.factions.faction.claim.Claim;
import me.bruce.factions.faction.claim.ClaimHandler;

import java.util.List;
import java.util.Map;

public abstract class EventFaction extends ClaimableFaction {

	public EventFaction(String name) {
		super(name);
		this.setDeathban(true); // make cappable factions death-ban between reloads.
	}

	public EventFaction(Map<String, Object> map) {
		super(map);
	}

	@Override
	public String getDisplayName(Faction faction) {
		return ChatColor.BLUE + getName() + ' ' + ChatColor.GOLD + getEventType().getDisplayName();
	}

	@Override
	public String getDisplayName(CommandSender sender) {
		return ChatColor.BLUE + getName()
				+ " KoTH".replace("Clock", "§a§lClock §7(KoTH)").replace("Nuclear", "§b§lNuclear §7(KoTH)")
						.replace("SkyBridge", "§b§lSkyBridge §7(KoTH)")
						.replace("Botanic", "§5§lBotanic §7(KoTH)".replace("End", "§9§lEnd §7(KoTH)"));
	}

	public String getScoreboardName() {
		return ChatColor.BLUE + ChatColor.BOLD.toString()
				+ getName().replace("Clock", "§4§lClock").replace("Nuclear", "§a§lNuclear")
						.replace("SkyBridge", "§b§lSkyBridge").replace("Botanic", "§5§lBotanic")
						.replace("End", "§9§lEnd");
	}

	/**
	 * Sets the {@link Cuboid} area of this {@link KothFaction}.
	 *
	 * @param cuboid
	 *            the {@link Cuboid} to set
	 * @param sender
	 *            the {@link CommandSender} setting the claim
	 */
	public void setClaim(Cuboid cuboid, CommandSender sender) {
		removeClaims(getClaims(), sender);

		// Now add the new claim.
		Location min = cuboid.getMinimumPoint();
		min.setY(ClaimHandler.MIN_CLAIM_HEIGHT);

		Location max = cuboid.getMaximumPoint();
		max.setY(ClaimHandler.MAX_CLAIM_HEIGHT);

		addClaim(new Claim(this, min, max), sender);
	}

	/**
	 * Gets the {@link EventType} of this {@link CapturableFaction}.
	 *
	 * @return the {@link EventType}
	 */
	public abstract EventType getEventType();

	/**
	 * Gets the {@link CaptureZone}s for this {@link CapturableFaction}.
	 *
	 * @return list of {@link CaptureZone}s
	 */
	public abstract List<CaptureZone> getCaptureZones();
}
