package net.bitjump.launchme.commands;

import net.bitjump.launchme.LaunchMe;
import net.bitjump.launchme.utils.MessageUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class VersionCommand extends Subcommand 
{
	public String getPermission()
	{
		return "launchme.version";
	}
	
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{		
		sender.sendMessage(MessageUtils.getPrefix() + "You are running LaunchMe v" + LaunchMe.version + "!");
	}

}
