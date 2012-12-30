package net.bitjump.launchme.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import net.bitjump.launchme.LaunchMe;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class TargetManager 
{
	private static FileConfiguration targetFile;
	private static File locale = new File(LaunchMe.instance.getDataFolder(), "targets.yml");
	
	public static void setupTargets()
	{				
		targetFile = YamlConfiguration.loadConfiguration(locale);
		
		save();
	}
	
	public static void set(String s, Location l)
	{		
		targetFile.set("targets." + s + ".world", l.getWorld().getName());
		targetFile.set("targets." + s + ".x", l.getX());
		targetFile.set("targets." + s + ".y", l.getY());
		targetFile.set("targets." + s + ".z", l.getZ());
		
		save();
	}
	
	public static Location get(String s)
	{		
		return new Location(Bukkit.getWorld(targetFile.getString("targets." + s + ".world")), targetFile.getDouble("targets." + s + ".x"), targetFile.getDouble("targets." + s + ".y"), targetFile.getDouble("targets." + s + ".z"));
	}
	
	public static void remove(String s)
	{
		targetFile.set("targets." + s, null);
		
		save();
	}
	
	public static void save()
	{
		try 
		{
			targetFile.save(locale);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static LinkedHashMap<String, Location> getTargets()
	{
		LinkedHashMap<String, Location> targets = new LinkedHashMap<String, Location>();
		
		List<String> ss = new ArrayList<String>(targetFile.getConfigurationSection("targets").getKeys(false));
		
		Collections.sort(ss);
		
		for(String s : ss)
		{
			targets.put(s, new Location(Bukkit.getWorld(targetFile.getString("targets." + s + ".world")), targetFile.getDouble("targets." + s + ".x"), targetFile.getDouble("targets." + s + ".y"), targetFile.getDouble("targets." + s + ".z")));
		}
		
		return targets;
	}
	
}
