package net.bitjump.launchme;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import net.bitjump.launchme.commands.*;
import net.bitjump.launchme.listeners.*;
import net.bitjump.launchme.managers.*;
import net.bitjump.launchme.transport.TransportManager;
import net.bitjump.launchme.transport.types.*;
import net.bitjump.launchme.utils.OMBLogger;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class LaunchMe extends JavaPlugin 
{	
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public static JavaPlugin instance;
	public static Economy economy;
	
	public static FileConfiguration config;
	public static PluginDescriptionFile pdf;
	public static Metrics metrics;
	
	public static String name;
	public static String version;
	public static String author;

	public static Set<Player> active = new HashSet<Player>();
	public static boolean econ = false;
	public static boolean spout = false;
	
	public PlayerListener playerListener;
	public SignListener signListener;
	public BlockListener blockListener;

	public void onDisable()
	{
		pdf = getDescription();
		OMBLogger.info(pdf.getName() + " version " + pdf.getVersion() + "is now disabled!");
		getServer().getScheduler().cancelAllTasks();
	}

	public void onEnable()
	{
		pdf = getDescription();

		name = pdf.getName();
		version = pdf.getVersion();
		author = pdf.getAuthors().get(0);

		OMBLogger.initialize(logger, name);

		OMBLogger.info("Plugin initializing...");

		instance = this;
		
		ConfigManager.setupConfig();
		LocaleManager.setupLocale();
		TargetManager.setupTargets();
		
		if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null)
		{
			econ = setupEconomy();
		}
		
		if(Bukkit.getServer().getPluginManager().getPlugin("Spout") != null)
		{
			spout = true;
		}
		
		setupTypes();
		
		setupCommands();
		
		logger.setFilter(new KickFilter());
		
		OMBLogger.info("Registering events...");

		playerListener = new PlayerListener();
		signListener = new SignListener();
		blockListener = new BlockListener();
		
		getServer().getPluginManager().registerEvents(playerListener, this);
		getServer().getPluginManager().registerEvents(signListener, this);
		getServer().getPluginManager().registerEvents(blockListener, this);
		
		OMBLogger.info(name + " version " + version + " enabled!");
		
		instance = this;
		
		try 
		{
			metrics = new Metrics(this);
		    metrics.start();
		} 
		catch (IOException e) 
		{
		    // Failed to submit the stats :-(
		}
		
		instance = this;
		
		/*Metrics.Graph g = metrics.createGraph("Transport Usage");
		
		g.addPlotter(new Metrics.Plotter("Cannon Uses") 
		{			
			@Override
			public int getValue() 
			{
				return TransportManager.getType("cannon").uses;
			}
		});
		
		g.addPlotter(new Metrics.Plotter("Teleporter Uses") 
		{	
			@Override
			public int getValue() 
			{
				return TransportManager.getType("teleporter").uses;
			}
		});
		
		g.addPlotter(new Metrics.Plotter("Landing Pad Uses") 
		{			
			@Override
			public int getValue() 
			{
				return TransportManager.getType("land").uses;
			}
		});

		Metrics.Graph g2 = metrics.createGraph("Transport Creation");
		
		g2.addPlotter(new Metrics.Plotter("Cannons Created") 
		{	
			@Override
			public int getValue() 
			{
				return TransportManager.getType("cannon").created;
			}
		});
		
		g2.addPlotter(new Metrics.Plotter("Teleporters Created") 
		{			
			@Override
			public int getValue() 
			{
				return TransportManager.getType("teleporter").created;
			}
		});
		
		g2.addPlotter(new Metrics.Plotter("Landing Pads Created") 
		{			
			@Override
			public int getValue() 
			{
				return TransportManager.getType("land").created;
			}
		}); */
	}
	
	private void setupTypes()
	{
		if(config.getBoolean("transports.cannon.enabled", true)) TransportManager.addType(new Cannon());
		if(config.getBoolean("transports.teleporter.enabled", true)) TransportManager.addType(new Teleporter());
		if(config.getBoolean("transports.land.enabled", true)) TransportManager.addType(new Land());
		if(config.getBoolean("transports.target.enabled", true)) 
		{
			TransportManager.addType(new Target());
			TransportManager.addType(new TCannon());
			TransportManager.addType(new TTeleporter());
		}
	}
	
	private void setupCommands()
	{
		getCommand("launchme").setExecutor(new CommandManager());
		
		CommandManager.addComand(Arrays.asList("info", "i"), new InfoCommand());
		CommandManager.addComand(Arrays.asList("help"), new HelpCommand());
		CommandManager.addComand(Arrays.asList("targets", "warps"), new TargetsCommand());
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
