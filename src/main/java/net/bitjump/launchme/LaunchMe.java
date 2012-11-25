package net.bitjump.launchme;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import net.bitjump.launchme.listeners.*;
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
	
	public PlayerListener playerListener;
	public SignListener signListener;

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

		instance = this;
		
		ConfigManager.setupConfig();
		LocaleManager.setupLocale();
		
		if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null)
		{
			econ = setupEconomy();
		}
		
		setupTypes();
		
		logger.setFilter(new KickFilter());
		
		OMBLogger.info("Registering events...");

		playerListener = new PlayerListener();
		signListener = new SignListener();
		
		this.getServer().getPluginManager().registerEvents(playerListener, this);
		this.getServer().getPluginManager().registerEvents(signListener, this);
		
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
		
		Metrics.Graph g = metrics.createGraph("Transport Usage");
		
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

		/*Metrics.Graph g2 = metrics.createGraph("Transport Creation");
		
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
	
	public void setupTypes()
	{
		if(config.getBoolean("transports.cannon.enabled")) TransportManager.addType(new Cannon());
		if(config.getBoolean("transports.teleporter.enabled")) TransportManager.addType(new Teleporter());
		if(config.getBoolean("transports.land.enabled")) TransportManager.addType(new Land());
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
