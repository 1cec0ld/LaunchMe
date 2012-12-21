package net.bitjump.launchme.managers;

import java.io.File;

import net.bitjump.launchme.LaunchMe;
import net.bitjump.launchme.utils.FileUtils;

public class ConfigManager 
{
	public static void setupConfig()
	{
		LaunchMe.config = LaunchMe.instance.getConfig();
		
		File configFile = new File(LaunchMe.instance.getDataFolder(), "config.yml");
		
		if(!configFile.exists())
		{
		    configFile.getParentFile().mkdirs();
		    FileUtils.copy(LaunchMe.instance.getResource("config.yml"), configFile);
		}
		
		LaunchMe.config = LaunchMe.instance.getConfig();
	}

}
