package net.bitjump.launchme;

import java.io.File;

import net.bitjump.launchme.utils.FileUtils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocaleManager 
{
	private static FileConfiguration localeFile;
	
	public static void setupLocale()
	{		
		File locale = new File(LaunchMe.instance.getDataFolder(), "locale/locale_" + LaunchMe.config.getString("locale", "EN") + ".yml");
		
		if(!locale.exists())
		{
		    locale.getParentFile().mkdirs();
		    FileUtils.copy(LaunchMe.instance.getResource("locale/locale_EN.yml"), locale);
		}
		
		localeFile = YamlConfiguration.loadConfiguration(locale);
	}
	
	public static String get(String s)
	{
		return ChatColor.GOLD + "[" + ChatColor.DARK_PURPLE + "LaunchMe" + ChatColor.GOLD +"] " + localeFile.getString(s).replaceAll("&", "\u00a7").replaceAll("\u00a7\u00a7", "&");		
	}
}
