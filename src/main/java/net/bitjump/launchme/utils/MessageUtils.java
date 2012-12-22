package net.bitjump.launchme.utils;

import org.bukkit.ChatColor;

public class MessageUtils 
{
	public static String getPrefix()
	{
		return ChatColor.GOLD + "[" + ChatColor.DARK_PURPLE + "LaunchMe" + ChatColor.GOLD +"] ";
	}
	
	public static String colorize(String s)
	{
		return s.replaceAll("&", "\u00a7").replaceAll("\u00a7\u00a7", "&");
	}
	
	public static String getMessage(String s)
	{
		return getPrefix() + s;
	}

}
