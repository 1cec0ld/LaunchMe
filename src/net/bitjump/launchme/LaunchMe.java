package net.bitjump.launchme;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import net.bitjump.launchme.listeners.*;
import net.bitjump.launchme.transport.TransportManager;
import net.bitjump.launchme.transport.types.*;
import net.bitjump.launchme.utils.OMBLogger;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class LaunchMe extends JavaPlugin {
	
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public static JavaPlugin instance;
	public static Economy economy;
	
	public static FileConfiguration config;
	public static PluginDescriptionFile pdf;
	
	public static String name;
	public static String version;
	public static String author;

	public static Set<Player> launched = new HashSet<Player>();
	
	public PlayerListener playerListener;

	public void onDisable()
	{
		pdf = this.getDescription();
		OMBLogger.info(pdf.getName() + " version " + pdf.getVersion() + "is now disabled!");
		this.getServer().getScheduler().cancelAllTasks();
	}

	public void onEnable()
	{
		pdf = this.getDescription();

		name = pdf.getName();
		version = pdf.getVersion();
		author = pdf.getAuthors().get(0);

		OMBLogger.initialize(logger, name);

		OMBLogger.info("Plugin initializing...");

		ConfigManager.setupConfig();
		LocaleManager.setupLocale();
		setupEconomy();
		
		setupTypes();
		
		OMBLogger.info("Registering events...");

		playerListener = new PlayerListener();
		
		this.getServer().getPluginManager().registerEvents(playerListener, this);
		
		OMBLogger.info(name + " version " + version + " enabled!");
		
		instance = this;
	}
	
	public void setupTypes()
	{
		if(config.getBoolean("transports.cannon.enabled")) TransportManager.addType(new Cannon());
		if(config.getBoolean("transports.teleporter.enabled")) TransportManager.addType(new Teleporter());
	}

	public void setupDefaultCannonMessages()
	{
		FileConfiguration config = this.getConfig();
		if (config.get("messages.cannon.nocreate") == null)
		{
			this.getConfig().set("messages.cannon.nocreate", "You do not have the permission to create cannon signs!");
		}
		if (config.get("messages.cannon.success") == null)
		{
			this.getConfig().set("messages.cannon.success", "Cannon creation successful!");
		}
		if (config.get("messages.cannon.badcoords") == null)
		{
			this.getConfig().set("messages.cannon.badcoords", "Check your coordinates, are you sure they are numbers?");
		}
		if (config.get("messages.cannon.noprice") == null)
		{
			this.getConfig().set("messages.cannon.noprice", "Cannon creation successful; no price set.");
		}
		if (config.get("messages.cannon.nocannon") == null)
		{
			this.getConfig().set("messages.cannon.nocannon", "You need to be on a cannon to do this!");
		}
		if (config.get("messages.cannon.nolaunch") == null)
		{
			this.getConfig().set("messages.cannon.nolaunch", "You don't have the permission to launch!");
		}
		if (config.get("messages.cannon.ingame") == null)
		{
			this.getConfig().set("messages.cannon.ingame", "Must be done in-game.");
		}
		if (config.get("messages.cannon.price") == null)
		{
			this.getConfig().set("messages.cannon.price", "removed from your funds to use the cannon!");
		}
		if (config.get("messages.cannon.notcharged") == null)
		{
			this.getConfig().set("messages.cannon.notcharged", "Not charged to use this cannon!");
		}
	}

	public void setupDefaultTeleporterMessages()
	{
		FileConfiguration config = this.getConfig();
		if (config.get("messages.teleport.nocreate") == null)
		{
			this.getConfig().set("messages.teleport.nocreate", "You do not have the permission to create teleporter signs!");
		}
		if (config.get("messages.teleport.success") == null)
		{
			this.getConfig().set("messages.teleport.success", "Teleporter creation successful!");
		}
		if (config.get("messages.teleport.badcoords") == null)
		{
			this.getConfig().set("messages.teleport.badcoords", "Check your coordinates, are you sure they are numbers?");
		}
		if (config.get("messages.teleport.noteleporter") == null)
		{
			this.getConfig().set("messages.teleport.noteleporter", "You need to be on a teleport to do this!");
		}
		if (config.get("messages.teleport.noteleport") == null)
		{
			this.getConfig().set("messages.teleport.noteleport", "You don't have the permission to teleport!");
		}
		if (config.get("messages.teleport.ingame") == null)
		{
			this.getConfig().set("messages.teleport.ingame", "Must be done in-game.");
		}
	}

	private Boolean setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
		{
			economy = economyProvider.getProvider();
		}
		return (economy != null);
	}

}
