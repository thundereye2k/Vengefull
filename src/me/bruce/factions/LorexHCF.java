package me.bruce.factions;

import java.io.File;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import LorexMC.us.utils.internal.com.bruce.base.BasePlugin;
import lombok.Getter;
import me.bruce.factions.armors.PvpClassManager;
import me.bruce.factions.armors.bard.EffectRestorer;
import me.bruce.factions.commands.BallerRevive;
import me.bruce.factions.commands.BroadCastCommand;
import me.bruce.factions.commands.Buy;
import me.bruce.factions.commands.CobbleCommand;
import me.bruce.factions.commands.CoordsCommand;
import me.bruce.factions.commands.FRCOMMAND;
import me.bruce.factions.commands.FixCommand;
import me.bruce.factions.commands.FreezeCommand;
import me.bruce.factions.commands.GiveCrowbarCommand;
import me.bruce.factions.commands.GlowstoneMountain;
import me.bruce.factions.commands.GoppleCommand;
import me.bruce.factions.commands.HelpCommand;
import me.bruce.factions.commands.HubCommand;
import me.bruce.factions.commands.InveeSeeCommand;
import me.bruce.factions.commands.LFFCommand;
import me.bruce.factions.commands.LogoutCommand;
import me.bruce.factions.commands.MapKitCommand;
import me.bruce.factions.commands.MedicCommand;
import me.bruce.factions.commands.OresCommand;
import me.bruce.factions.commands.Panic;
import me.bruce.factions.commands.PingCommand;
import me.bruce.factions.commands.PlayTimeCommand;
import me.bruce.factions.commands.PotsCommand;
import me.bruce.factions.commands.Pro;
import me.bruce.factions.commands.PvPCommand;
import me.bruce.factions.commands.RefundCommand;
import me.bruce.factions.commands.RegenCommand;
import me.bruce.factions.commands.RenameCommand;
import me.bruce.factions.commands.RulesCommand;
import me.bruce.factions.commands.SPCOMMAND;
import me.bruce.factions.commands.SetPrefixCommand;
import me.bruce.factions.commands.SettingsCommand;
import me.bruce.factions.commands.SpawnCommand;
import me.bruce.factions.commands.StaffInformation;
import me.bruce.factions.commands.StaffSettings;
import me.bruce.factions.commands.StatsCommand;
import me.bruce.factions.commands.TLCommand;
import me.bruce.factions.commands.Tablist;
import me.bruce.factions.commands.TablistCommand;
import me.bruce.factions.commands.ToggleCapzoneEntryCommand;
import me.bruce.factions.commands.ToggleLightningCommand;
import me.bruce.factions.commands.Vip;
import me.bruce.factions.commands.WB;
import me.bruce.factions.commands.ZorexDeveloper;
import me.bruce.factions.commands.ZorexRevive;
import me.bruce.factions.commands.nv;
import me.bruce.factions.commands.randomtp;
import me.bruce.factions.deathban.Deathban;
import me.bruce.factions.deathban.DeathbanListener;
import me.bruce.factions.deathban.DeathbanManager;
import me.bruce.factions.deathban.FlatFileDeathbanManager;
import me.bruce.factions.deathban.StaffReviveCommand;
import me.bruce.factions.deathban.lives.LivesExecutor;
import me.bruce.factions.economy.EconomyCommand;
import me.bruce.factions.economy.EconomyManager;
import me.bruce.factions.economy.FlatFileEconomyManager;
import me.bruce.factions.economy.PayCommand;
import me.bruce.factions.economy.ShopSignListener;
import me.bruce.factions.endoftheworld.EotwCommand;
import me.bruce.factions.endoftheworld.EotwHandler;
import me.bruce.factions.endoftheworld.EotwListener;
import me.bruce.factions.eventutils.CaptureZone;
import me.bruce.factions.eventutils.EventExecutor;
import me.bruce.factions.eventutils.EventScheduler;
import me.bruce.factions.faction.FactionExecutor;
import me.bruce.factions.faction.FactionManager;
import me.bruce.factions.faction.FactionMember;
import me.bruce.factions.faction.FactionUser;
import me.bruce.factions.faction.FlatFileFactionManager;
import me.bruce.factions.faction.args.FactionClaimChunkArgument;
import me.bruce.factions.faction.claim.Claim;
import me.bruce.factions.faction.claim.ClaimHandler;
import me.bruce.factions.faction.claim.ClaimWandListener;
import me.bruce.factions.faction.claim.Subclaim;
import me.bruce.factions.faction.claim.SubclaimWandListener;
import me.bruce.factions.faction.type.CapturableFaction;
import me.bruce.factions.faction.type.ClaimableFaction;
import me.bruce.factions.faction.type.ConquestFaction;
import me.bruce.factions.faction.type.EndPortalFaction;
import me.bruce.factions.faction.type.Faction;
import me.bruce.factions.faction.type.GlowstoneMountainFaction;
import me.bruce.factions.faction.type.KothFaction;
import me.bruce.factions.faction.type.PlayerFaction;
import me.bruce.factions.faction.type.RoadFaction;
import me.bruce.factions.faction.type.SpawnFaction;
import me.bruce.factions.keysale.keysaleCommand;
import me.bruce.factions.koth.KothExecutor;
import me.bruce.factions.listener.AutoSmeltOreListener;
import me.bruce.factions.listener.BookDeenchantListener;
import me.bruce.factions.listener.BorderListener;
import me.bruce.factions.listener.BottledExpListener;
import me.bruce.factions.listener.ChatListener;
import me.bruce.factions.listener.Cooldowns;
import me.bruce.factions.listener.CoreListener;
import me.bruce.factions.listener.CreativeClickListener;
import me.bruce.factions.listener.CrowbarListener;
import me.bruce.factions.listener.DeathMessageListener;
import me.bruce.factions.listener.DeathSignListener;
import me.bruce.factions.listener.ElevatorListener;
import me.bruce.factions.listener.EndListener;
import me.bruce.factions.listener.EntityLimitListener;
import me.bruce.factions.listener.EventSignListener;
import me.bruce.factions.listener.ExpMultiplierListener;
import me.bruce.factions.listener.FactionListener;
import me.bruce.factions.listener.FastEverythingListener;
import me.bruce.factions.listener.FoundDiamondsListener;
import me.bruce.factions.listener.ItemRemoverListener;
import me.bruce.factions.listener.MineListener;
import me.bruce.factions.listener.PlayTimeManager;
import me.bruce.factions.listener.PortalListener;
import me.bruce.factions.listener.PotListener;
import me.bruce.factions.listener.ProtectionListener;
import me.bruce.factions.listener.SignSubclaimListener;
import me.bruce.factions.listener.StaffModeListener;
import me.bruce.factions.listener.StrengthListener;
import me.bruce.factions.listener.UnRepairableListener;
import me.bruce.factions.listener.UserManager;
import me.bruce.factions.listener.VanishListener;
import me.bruce.factions.listener.WorldListener;
import me.bruce.factions.listener.combatloggers.CombatLogListener;
import me.bruce.factions.listener.combatloggers.CustomEntityRegistration;
import me.bruce.factions.listener.fixes.ArmorFixListener;
import me.bruce.factions.listener.fixes.BlockHitFixListener;
import me.bruce.factions.listener.fixes.BlockJumpGlitchFixListener;
import me.bruce.factions.listener.fixes.BoatGlitchFixListener;
import me.bruce.factions.listener.fixes.ColonCommandFixListener;
import me.bruce.factions.listener.fixes.CreatureSpawn;
import me.bruce.factions.listener.fixes.EnchantLimitListener;
import me.bruce.factions.listener.fixes.EnderChestRemovalListener;
import me.bruce.factions.listener.fixes.HungerFixListener;
import me.bruce.factions.listener.fixes.InfinityArrowFixListener;
import me.bruce.factions.listener.fixes.PearlGlitchListener;
import me.bruce.factions.listener.fixes.PortalTrapFixListener;
import me.bruce.factions.listener.fixes.PotionLimitListener;
import me.bruce.factions.listener.fixes.VoidGlitchFixListener;
import me.bruce.factions.listener.render.ProtocolLibHook;
import me.bruce.factions.listener.render.VisualiseHandler;
import me.bruce.factions.listener.render.WallBorderListener;
import me.bruce.factions.prefix.PrefixInv;
import me.bruce.factions.prefix.PrefixMenu;
import me.bruce.factions.prefix.prefix;

import me.bruce.factions.rank.RankCommand;
import me.bruce.factions.rank.RankExecutor;
import me.bruce.factions.reboot.rebootCommand;
import me.bruce.factions.scoreboard.ScoreboardHandler;
import me.bruce.factions.staff.StaffModeCommand;
import me.bruce.factions.staff.VanishCommand;
import me.bruce.factions.startoftheworld.SotwCommand;
import me.bruce.factions.startoftheworld.SotwListener;
import me.bruce.factions.startoftheworld.SotwTimer;
import me.bruce.factions.timer.TimerExecutor;
import me.bruce.factions.timer.TimerManager;
import me.bruce.factions.ymls.AThing;
import me.bruce.factions.ymls.SettingsYML;
import me.bruceboy.framework.commands.Fly;
import me.bruceboy.framework.commands.Gamemode;
import me.bruceboy.framework.commands.ListCommand;
import me.bruceboy.framework.commands.Teleport;
import me.bruceboy.framework.commands.tphere;
import me.tablist.api.tabapi.Tab;
import me.tablist.api.tablist.adapter.TablistAdapter;
//import src.me.bruceboy.tablist.lorexmc.utils.main.TabCreator;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class LorexHCF extends JavaPlugin {
	public static Permission permission = null;
	public static Economy economy = null;
	public static Economy econ = null;
	public static Chat chat = null;
    public static final String PREFIX;
    public static final String NOPLAYER;
    public static final String PREFIXNOPERMS;
    public static final String NOPERMS;
    AThing utils = new AThing(this);


	@Getter
	private static LorexHCF instance;
	@Getter
	private PlayTimeManager playTimeManager;
	@Getter
	private Random random = new Random();
	@Getter
	private ClaimHandler claimHandler;
	@Getter
	private DeathbanManager deathbanManager;
	@Getter
	private EconomyManager economyManager;
	@Getter
	private EffectRestorer effectRestorer;
	@Getter
	private EotwHandler eotwHandler;
	@Getter
	private EventScheduler eventScheduler;
	@Getter
	private FactionManager factionManager;
	@Getter
	private FoundDiamondsListener foundDiamondsListener;
	@Getter
	private PvpClassManager pvpClassManager;
	@Getter
	private ScoreboardHandler scoreboardHandler;
	@Getter
	private SotwTimer sotwTimer;
	@Getter
	private static me.bruce.factions.keysale.keysaletimer keysaletimer;
	@Getter
	private static me.bruce.factions.reboot.reboottimer reboottimer;
	@Getter
	private TimerManager timerManager;
	@Getter
	private FactionUser factionUser;
	@Getter
	private UserManager userManager;
	@Getter
	private VisualiseHandler visualiseHandler;
	@Getter
	private WorldEditPlugin worldEdit;

	private CombatLogListener combatLogListener;

	public CombatLogListener getCombatLogListener() {
		return this.combatLogListener;
	}
	public static String PREFIX1 = ChatColor.GOLD + "" + ChatColor.BOLD + "Zorex" + ChatColor.GRAY + " » ";

	public void resetGlowstoneMountain() {
		for (int x = 502; x <= 582; x++) {
			for (int y = 76; y <= 112; y++) {
				for (int z = 594; z > 514; z--) {
					Block block = Bukkit.getWorld("world_nether")
							.getBlockAt(new Location(Bukkit.getWorld("world_nether"), -1 * x, y, z));
					if (block.getType().equals(Material.AIR)) {
						block.setType(Material.GLOWSTONE);
					}
				}
			}
		}
	}

	@Override
	public void onEnable() {
		//new TabList(this, new me.bruceboy.tablist.lorexmc.utils.main.TabCreator());
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "HCF Enabled");
		setupEconomy();
		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
		Cooldowns.createCooldown("medic_cooldown");
		Cooldowns.createCooldown("lff_cooldown");
		Cooldowns.createCooldown("revive_cooldown");
	
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7The core has been &aenabled!&7."));
		instance = this;
		BasePlugin.getPlugin().init(this);

		ProtocolLibHook.hook(this);

		Plugin wep = getServer().getPluginManager().getPlugin("WorldEdit");
		Plugin kits = getServer().getPluginManager().getPlugin("VengefulKits");
		this.worldEdit = wep instanceof WorldEditPlugin && wep.isEnabled() ? (WorldEditPlugin) wep : null;
		CustomEntityRegistration.registerCustomEntities();

		SettingsYML.init(this);
		this.effectRestorer = new EffectRestorer(this);
		this.registerConfiguration();
		this.registerCommands();
	//	getCommand("list").setExecutor(new ListCommand());
		//getCommand("fly").setExecutor(new Fly());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("author").setExecutor(new ZorexDeveloper());
		getCommand("stats").setExecutor(new StatsCommand());
		this.registerManagers();
		this.registerListeners();
		this.setupPermissions();
		this.resetGlowstoneMountain();
		this.prefixcommands();
		this.registerEvents1();
		tab();
		

		new BukkitRunnable() {
			@Override
			public void run() {
				getServer().broadcastMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Saving" + "\n" + ChatColor.RED
						+ "All the faction data to the database.");
				saveData();
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
						"&7&m----------------------------------------------"));
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
						"&cJoin our discord at: https://discord.gg/eZS8Brc"));
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
						"&7&m----------------------------------------------"));
			}
		}.runTaskTimerAsynchronously(this, TimeUnit.MINUTES.toMillis(10L), TimeUnit.SECONDS.toMillis(10L));
	}
	public void tab() {
		TablistAdapter ta = new TablistAdapter(this, utils);
			Bukkit.getServer().getPluginManager().registerEvents(new Tab(this, utils, ta), this);
		}
	
	   


	private void saveData() {
		this.combatLogListener.removeCombatLoggers();
		this.deathbanManager.saveDeathbanData();
		this.economyManager.saveEconomyData();
		this.factionManager.saveFactionData();
		this.timerManager.saveTimerData();
		this.userManager.saveUserData();
	}

	@Override
	public void onDisable() {
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7Economy has been &asaved! &7(1/6)"));
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7Faction has been &asaved! &7(2/6)"));
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7Key Data has been &asaved! &7(3/6)"));
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7Cooldowns has been &asaved! &7(4/6)"));
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7Player Data has been &asaved! &7(5/6)"));
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7Player Vaults has been &asaved! &7(6/6)"));
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lZorex &8» &7Saving is now &acomplete!&7."));
		this.pvpClassManager.onDisable();
		this.scoreboardHandler.clearBoards();
		this.factionManager.saveFactionData();
		this.deathbanManager.saveDeathbanData();
		this.economyManager.saveEconomyData();
		this.factionManager.saveFactionData();
		this.timerManager.saveTimerData();
		this.userManager.saveUserData();
		this.playTimeManager.savePlaytimeData();
		this.saveData();

		instance = null; // always initialise last
	}

	public static Chat getChat() {
		return LorexHCF.chat;
	}

	private boolean setupPermissions() {
		final RegisteredServiceProvider permissionProvider = this.getServer().getServicesManager()
				.getRegistration((Class) Permission.class);
		if (permissionProvider != null) {
			permission = (Permission) permissionProvider.getProvider();
		}
		return permission != null;
	}

	private boolean setupChat() {
		final RegisteredServiceProvider chatProvider = this.getServer().getServicesManager()
				.getRegistration((Class) Chat.class);
		if (chatProvider != null) {
			LorexHCF.chat = (Chat) chatProvider.getProvider();
		}
		return LorexHCF.chat != null;
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	private void registerConfiguration() {
		ConfigurationSerialization.registerClass(CaptureZone.class);
		ConfigurationSerialization.registerClass(Deathban.class);
		ConfigurationSerialization.registerClass(Claim.class);
		ConfigurationSerialization.registerClass(Subclaim.class);
		ConfigurationSerialization.registerClass(Deathban.class);
		ConfigurationSerialization.registerClass(FactionUser.class);
		ConfigurationSerialization.registerClass(ClaimableFaction.class);
		ConfigurationSerialization.registerClass(ConquestFaction.class);
		ConfigurationSerialization.registerClass(CapturableFaction.class);
		ConfigurationSerialization.registerClass(KothFaction.class);
		ConfigurationSerialization.registerClass(EndPortalFaction.class);
		ConfigurationSerialization.registerClass(Faction.class);
		ConfigurationSerialization.registerClass(FactionMember.class);
		ConfigurationSerialization.registerClass(PlayerFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.class);
		ConfigurationSerialization.registerClass(SpawnFaction.class);
		ConfigurationSerialization.registerClass(GlowstoneMountainFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.NorthRoadFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.EastRoadFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.SouthRoadFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.WestRoadFaction.class);
	}

	public void registerListeners() {
		PluginManager manager = this.getServer().getPluginManager();
		this.playTimeManager = new PlayTimeManager(this);
		manager.registerEvents(this.playTimeManager, this);
		// If Kit_MAP = true then this will enable and the false shit wont enable.
		manager.registerEvents(this.combatLogListener = new CombatLogListener(this), this);
		manager.registerEvents(new BlockHitFixListener(), this);
		manager.registerEvents(new BlockJumpGlitchFixListener(), this);
		manager.registerEvents(new BoatGlitchFixListener(), this);
		manager.registerEvents(new BookDeenchantListener(), this);
		manager.registerEvents(new FactionClaimChunkArgument(this), this);
		manager.registerEvents(new BorderListener(), this); // I never fix the pearl pass border in this version might
															// wanna fix it
		manager.registerEvents(new CreatureSpawn(), this);
		manager.registerEvents(new CobbleCommand(), this);
		manager.registerEvents(new BottledExpListener(), this);
		manager.registerEvents(new PortalTrapFixListener(this), this);
		manager.registerEvents(new ChatListener(this), this);
		manager.registerEvents(new ClaimWandListener(this), this);
		manager.registerEvents(new CoreListener(this), this);
		manager.registerEvents(new ElevatorListener(this), this);
		manager.registerEvents(new CrowbarListener(this), this);
		manager.registerEvents(new DeathMessageListener(this), this);
		manager.registerEvents(new DeathSignListener(this), this);
		manager.registerEvents(new PotsCommand(this), this);
		manager.registerEvents(new SettingsCommand(), this);
		manager.registerEvents(new Tablist(), this);
		manager.registerEvents(new ShopSignListener(this), this);

		// If KIT_MAP = false then this will enable if its true this enables.
		if (SettingsYML.KIT_MAP == false) {
			manager.registerEvents(new DeathbanListener(this), this);
		}
		manager.registerEvents(new EnchantLimitListener(), this);
		manager.registerEvents(new EnderChestRemovalListener(), this);
		manager.registerEvents(new EntityLimitListener(), this);
		manager.registerEvents(new EndListener(), this);
		manager.registerEvents(new EotwListener(this), this);
		manager.registerEvents(new EventSignListener(), this);
		manager.registerEvents(new ExpMultiplierListener(), this);
		manager.registerEvents(new FactionListener(this), this);
		manager.registerEvents(this.foundDiamondsListener = new FoundDiamondsListener(this), this);
		manager.registerEvents(new FastEverythingListener(this), this);
		manager.registerEvents(new InfinityArrowFixListener(), this);
		manager.registerEvents(new PearlGlitchListener(this), this);
		manager.registerEvents(new PortalListener(this), this);
		manager.registerEvents(new PotionLimitListener(), this);
		manager.registerEvents(new ProtectionListener(this), this);
		manager.registerEvents(new SubclaimWandListener(this), this);
		manager.registerEvents(new SignSubclaimListener(this), this);
		manager.registerEvents(new SotwListener(this), this);
		manager.registerEvents(new VoidGlitchFixListener(), this);
		manager.registerEvents(new UnRepairableListener(), this);
		manager.registerEvents(new WallBorderListener(this), this);
		manager.registerEvents(new AutoSmeltOreListener(), this);
		manager.registerEvents(new WorldListener(this), this);
		manager.registerEvents(new MineListener(this), this);
		manager.registerEvents(new ItemRemoverListener(this), this);
		manager.registerEvents(new PotListener(this), this); // this pot listener is aids btw
		// manager.registerEvents(new GlowstoneListener (this), this); 
		manager.registerEvents(new StrengthListener(), this);
		manager.registerEvents(new CreativeClickListener(), this);
		manager.registerEvents(new ArmorFixListener(), this);
		manager.registerEvents(new HungerFixListener(this), this);
		manager.registerEvents(new ColonCommandFixListener(), this);
		manager.registerEvents(new RefundCommand(this), this);
		manager.registerEvents(new InveeSeeCommand(), this);
		//manager.registerEvents((Listener)new StaffModeListener(), (Plugin)this);
		//manager.registerEvents((Listener)new VanishListener(), this);
		manager.registerEvents(new me.bruce.factions.KIts.KitsInteract(), this);
		manager.registerEvents(new me.bruce.factions.KIts.KitsInvClick(), this);
		manager.registerEvents(new StaffSettings(), this);
		manager.registerEvents(new TablistCommand(), this);
	}

	private void registerCommands() {
		getCommand("vanish").setExecutor(new VanishCommand());
		getCommand("zorex").setExecutor(new ZorexRevive(this));
		getCommand("vip").setExecutor(new Vip(this));
		getCommand("pro").setExecutor(new Pro(this));
		getCommand("rules").setExecutor(new RulesCommand());
		getCommand("baller").setExecutor(new BallerRevive(this));
		getCommand("tl").setExecutor(new TLCommand());
		getCommand("invsee").setExecutor(new InveeSeeCommand());
		getCommand("fix").setExecutor(new FixCommand());
		getCommand("broadcast").setExecutor(new BroadCastCommand());
		getCommand("cobble").setExecutor(new CobbleCommand());
		getCommand("hub").setExecutor(new HubCommand(this));
		getCommand("amethyst").setExecutor(new MedicCommand(this));
		getCommand("crowgive").setExecutor(new GiveCrowbarCommand());
		getCommand("hcfhelp").setExecutor(new HelpCommand());
		getCommand("coords").setExecutor(new CoordsCommand());
		getCommand("conquest").setExecutor(new me.bruce.factions.conquest.ConquestExecutor(this));
		getCommand("economy").setExecutor(new EconomyCommand(this));
		getCommand("refund").setExecutor(new RefundCommand(this));
		getCommand("eotw").setExecutor(new EotwCommand(this));
		getCommand("panic").setExecutor(new Panic());
		getCommand("tablistgui").setExecutor(new TablistCommand());
		getCommand("event").setExecutor(new EventExecutor(this));
		getCommand("hcfhelp").setExecutor(new HelpCommand());
		getCommand("faction").setExecutor(new FactionExecutor(this));
		getCommand("tp").setExecutor(new Teleport());
		getCommand("wb").setExecutor(new WB());
		getCommand("nv").setExecutor(new nv());
		getCommand("here").setExecutor(new tphere());
//		getCommand("vanish").setExecutor((CommandExecutor)new VanishCommand());
		getCommand("gopple").setExecutor(new GoppleCommand(this));
		getCommand("gamemode").setExecutor( new Gamemode());
		getCommand("koth").setExecutor(new KothExecutor(this));
		getCommand("lives").setExecutor(new LivesExecutor(this));
		getCommand("ping").setExecutor(new PingCommand());
		//getCommand("slowchat").setExecutor(new SlowChat(this));
		//getCommand("disablechat").setExecutor(new DisableChat(this));
		getCommand("freeze").setExecutor(new FreezeCommand(this));
		getCommand("logout").setExecutor(new LogoutCommand(this));
		//tCommand("staffmode").setExecutor((CommandExecutor)new StaffModeCommand());
		getCommand("mapkit").setExecutor(new MapKitCommand(this));
		getCommand("pay").setExecutor(new PayCommand(this));
		getCommand("tablist").setExecutor(new Tablist());
		getCommand("sp").setExecutor(new SPCOMMAND());
		getCommand("fr").setExecutor(new FRCOMMAND());
		getCommand("pvptimer").setExecutor(new PvPCommand(this));
		getCommand("reboot").setExecutor(new rebootCommand(this));
		getCommand("staffgui").setExecutor(new StaffSettings());
		getCommand("pots").setExecutor(new PotsCommand(this));
		getCommand("regen").setExecutor(new RegenCommand(this));
		getCommand("keysale").setExecutor(new keysaleCommand(this));
		getCommand("sotw").setExecutor(new SotwCommand(this));
		getCommand("staffrevive").setExecutor(new StaffReviveCommand(this));
		getCommand("staffguide").setExecutor(new StaffInformation());
		getCommand("timer").setExecutor(new TimerExecutor(this));
		getCommand("lff").setExecutor(new LFFCommand(this));
		getCommand("togglecapzoneentry").setExecutor(new ToggleCapzoneEntryCommand(this));
		getCommand("togglelightning").setExecutor(new ToggleLightningCommand(this));
		getCommand("rename").setExecutor(new RenameCommand(this));
		getCommand("ores").setExecutor(new OresCommand(this));
		getCommand("playtime").setExecutor(new PlayTimeCommand());
		getCommand("settings").setExecutor(new SettingsCommand());
		getCommand("rank").setExecutor(new RankExecutor(this));
		getCommand("glowstonemountain").setExecutor(new GlowstoneMountain(this));
		getCommand("buy").setExecutor(new Buy());
		getCommand("setprefix").setExecutor(new SetPrefixCommand());
		getCommand("randomtp").setExecutor(new randomtp());
		getCommand("staffgui").setExecutor(new StaffSettings());

		Map<String, Map<String, Object>> map = getDescription().getCommands();
		for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
			PluginCommand command = getCommand(entry.getKey());
			command.setPermission("zorex." + entry.getKey());
			command.setPermissionMessage(ChatColor.RED + "You do not have permissions to execute this command.");
		}
	}
	private void registerManagers() {
		this.claimHandler = new ClaimHandler(this);
		this.deathbanManager = new FlatFileDeathbanManager(this);
		this.economyManager = new FlatFileEconomyManager(this);
		this.eotwHandler = new EotwHandler(this);
		this.eventScheduler = new EventScheduler(this);
		this.factionManager = new FlatFileFactionManager(this);
		this.pvpClassManager = new PvpClassManager(this);
		this.sotwTimer = new SotwTimer();
		this.keysaletimer = new me.bruce.factions.keysale.keysaletimer();
		this.reboottimer = new me.bruce.factions.reboot.reboottimer();
		this.timerManager = new TimerManager(this); // needs to be registered before ScoreboardHandler
		this.scoreboardHandler = new ScoreboardHandler(this);
		this.userManager = new UserManager(this);
		this.visualiseHandler = new VisualiseHandler();
	}
	
	public void prefixcommands() {
		this.getCommand("prefix").setExecutor((CommandExecutor)new prefix());
	}
    private void registerEvents1() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents((Listener)new PrefixMenu(), (Plugin)this);
        pm.registerEvents((Listener)new PrefixInv(), (Plugin)this);

    }
    

	private void registerCooldowns() {
		Cooldowns.createCooldown("medic_cooldown");
	}



	public PlayTimeManager getPlayTimeManager() {
		return this.playTimeManager;
	}

	public static LorexHCF getInstance() {
		return LorexHCF.instance;
	}

	public Random getRandom() {
		return this.random;
	}



	public ClaimHandler getClaimHandler() {
		return this.claimHandler;
	}

	public DeathbanManager getDeathbanManager() {
		return this.deathbanManager;
	}

	public EconomyManager getEconomyManager() {
		return this.economyManager;
	}

	public EffectRestorer getEffectRestorer() {
		return this.effectRestorer;
	}

	public EotwHandler getEotwHandler() {
		return this.eotwHandler;
	}

	public static SotwTimer getSotwRunnable() {
		return LorexHCF.getSotwRunnable();
	}

	public FactionUser getFactionUser() {
		return this.factionUser;
	}

	public EventScheduler getEventScheduler() {
		return this.eventScheduler;
	}

	public FactionManager getFactionManager() {
		return this.factionManager;
	}

	public FoundDiamondsListener getFoundDiamondsListener() {
		return this.foundDiamondsListener;
	}

	public PvpClassManager getPvpClassManager() {
		return this.pvpClassManager;
	}

	public ScoreboardHandler getScoreboardHandler() {
		return this.scoreboardHandler;
	}
	public static me.bruce.factions.keysale.keysaletimer getkeysalerunnable() {
		return LorexHCF.getkeysalerunnable();
	}
	public static me.bruce.factions.keysale.keysaletimer getkeysaletimer() {
		return keysaletimer;
	}
	public static me.bruce.factions.reboot.reboottimer getreboottimer() {
		return  reboottimer;
	}



	public SotwTimer getSotwTimer() {
		return this.sotwTimer;
	}
	public me.bruce.factions.reboot.reboottimer getrebootrunnable() {
		return this.reboottimer;
	}

	public TimerManager getTimerManager() {
		return this.timerManager;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	public VisualiseHandler getVisualiseHandler() {
		return this.visualiseHandler;
	}

	public WorldEditPlugin getWorldEdit() {
		return this.worldEdit;
	}

	public static ChatColor getRemaining(long remaining, boolean b, boolean c) {
		// TODO Auto-generated method stub
		return null;
	}
    static {
        PREFIX = ChatColor.GOLD + ChatColor.BOLD.toString() + "Zorex §8» " + ChatColor.GRAY;
        NOPLAYER = ChatColor.RED + "You must be a player to use this command. ";
        PREFIXNOPERMS = ChatColor.RED + "You lack the sufficient permissions to use this prefix. ";
        NOPERMS = ChatColor.RED + "You lack the sufficient permissions to execute this command. ";
    }

}