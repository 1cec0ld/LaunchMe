package net.bitjump.launchme.commands;

import net.bitjump.launchme.managers.TargetManager;
import net.bitjump.launchme.utils.MessageUtils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand extends PlayerSubcommand 
{
	public String getPermission()
	{
		return "launchme.warp";
	}
	
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{	
		Player p = (Player) sender;
		
		if(args.length >= 2)
		{
			Location en = null;
			
			try
			{
				en = TargetManager.get(args[1]);
				en.setY(en.getY() + 2);
			}
			catch(NullPointerException e)
			{
				p.sendMessage(MessageUtils.getMessage(ChatColor.RED + "This is not a valid warp."));
				return;
			}
			
			p.teleport(en);
			p.sendMessage(MessageUtils.getMessage(ChatColor.GREEN + "Teleported to " + args[1]));
		}
		else
		{
			p.sendMessage(MessageUtils.getMessage(ChatColor.RED + "Please specify a target!"));
			return;
		}
	}

}
