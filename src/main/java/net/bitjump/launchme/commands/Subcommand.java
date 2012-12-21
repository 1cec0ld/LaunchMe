package net.bitjump.launchme.commands;

import net.bitjump.launchme.managers.LocaleManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class Subcommand
{
	public abstract String getPermission();
	
	public boolean needsPlayer()
	{
		return false;
	}
	
	public abstract void onCommand(CommandSender sender, Command cmd, String label, String[] args);

	public boolean runCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!sender.hasPermission(getPermission()))
		{
			sender.sendMessage(LocaleManager.get("commands.nopermission", "You do not have permission to perform this command!"));
		}
		else
		{
			if(needsPlayer())
			{
				if(sender instanceof Player)
				{
					onCommand(sender, cmd, label, args);
				}
			}
			else
			{
				onCommand(sender, cmd, label, args);
			}
		}
		
		return true;
	}
	
}
