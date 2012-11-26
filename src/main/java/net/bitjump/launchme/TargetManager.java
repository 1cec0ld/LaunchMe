package net.bitjump.launchme;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class TargetManager 
{
	private static FileConfiguration targetFile;
	
	public static void setupTargets()
	{		
		File locale = new File("targets.yml");
		
		targetFile = YamlConfiguration.loadConfiguration(locale);
	}
	
	public static void add(String s, Location l)
	{
		String loc = l.getWorld().getName() + l.getX() + l.getY() + l.getZ();
		targetFile.set("targets." + s, loc);
	}
	
	public static Location get(String s)
	{
		String[] ss = targetFile.getString(s).split(",");
		
		return new Location(Bukkit.getWorld(ss[0]), Double.parseDouble(ss[1]), Double.parseDouble(ss[2]), Double.parseDouble(ss[3]));
	}
	
	public static void remove(String s)
	{
		targetFile.set(s, null);
	}
	
	
}
