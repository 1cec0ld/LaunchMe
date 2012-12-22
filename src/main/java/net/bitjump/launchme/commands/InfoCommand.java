package net.bitjump.launchme.commands;

import net.bitjump.launchme.LaunchMe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class InfoCommand extends Subcommand 
{
	public String getPermission()
	{
		return "launchme.info";
	}
	
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{	
		sender.sendMessage(new String[] 
			{
				ChatColor.GOLD + "--------------- " + ChatColor.DARK_PURPLE + "LaunchMe" + ChatColor.GOLD + " ---------------",
				ChatColor.GOLD + "- " + ChatColor.DARK_PURPLE + "Version: " + ChatColor.GREEN + LaunchMe.version,
				ChatColor.GOLD + "- " + ChatColor.DARK_PURPLE + "Author: " + ChatColor.GREEN + LaunchMe.author,
				ChatColor.GOLD + "- " + ChatColor.DARK_PURPLE + "Help: " + ChatColor.GREEN + "/lm help [page]",
				ChatColor.GOLD + "---------------------------------------"
			}
		);
	}

}
