package net.bitjump.launchme.managers;

import java.io.File;

import net.bitjump.launchme.LaunchMe;
import net.bitjump.launchme.utils.FileUtils;
import net.bitjump.launchme.utils.MessageUtils;

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
	
	public static String get(String s, String def)
	{
		if( !(LaunchMe.config.getBoolean("name")) )
		{
			return localeFile.getString(s, def).replaceAll("&", "\u00a7").replaceAll("\u00a7\u00a7", "&");
		}
		
		return MessageUtils.getPrefix() + localeFile.getString(s, def).replaceAll("&", "\u00a7").replaceAll("\u00a7\u00a7", "&");
				
	}
	
}
