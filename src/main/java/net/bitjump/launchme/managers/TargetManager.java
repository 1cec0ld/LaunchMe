package net.bitjump.launchme.managers;

import java.io.File;
import java.io.IOException;

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
	
	public static void add(String s, Location l)
	{
		String loc = l.getWorld().getName() + "," +  Double.toString(l.getX()) + "," + Double.toString(l.getY()) + "," + Double.toString(l.getZ());
		targetFile.set("targets." + s, loc);
		
		save();
	}
	
	public static Location get(String s)
	{
		String[] ss = targetFile.getString("targets." + s).split(",");
		
		return new Location(Bukkit.getWorld(ss[0]), Double.parseDouble(ss[1]), Double.parseDouble(ss[2]), Double.parseDouble(ss[3]));
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
	
}
