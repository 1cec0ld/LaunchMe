package net.bitjump.launchme.commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.bitjump.launchme.managers.TargetManager;
import net.bitjump.launchme.utils.MessageUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WarpsCommand extends Subcommand
{

	@Override
	public String getPermission() 
	{
		return "launchme.targets";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label,	String[] args) 
	{	
		LinkedHashMap<String, Location> locs = TargetManager.getTargets();
		
		List<String> targets = new ArrayList<String>(locs.keySet());
		
		int pages = targets.size() / 5 + 1;
		
		if(args.length <= 1)
		{
			Bukkit.dispatchCommand(sender, "lm targets 1");
		}
		else
		{
			int id;
			
			try
			{
				id = Integer.parseInt(args[1]);
			}
			catch(Exception e)
			{
				sender.sendMessage(MessageUtils.getMessage(ChatColor.RED + "Invalid target page ID!"));
				return;
			}
			
			if(pages < id)
			{
				sender.sendMessage(MessageUtils.getMessage(ChatColor.RED + "Not enough targets to fill " + id + " pages!"));
				return;
			}
				
			sender.sendMessage(ChatColor.GOLD + "---------- " + ChatColor.DARK_PURPLE + "LaunchMe Targets " + ChatColor.GOLD + "(" + ChatColor.DARK_PURPLE + id + ChatColor.GOLD + "/" + ChatColor.DARK_PURPLE + pages + ChatColor.GOLD + ") ---------");
			
			for(int i = (id * 5 - 5); i < id * 5; i++)
			{
				if(!(targets.size() > i)) break;
				
				Location loc = locs.get(targets.get(i));
				
				StringBuilder sb = new StringBuilder(ChatColor.GOLD + "- " + ChatColor.DARK_PURPLE + targets.get(i));
				
				sb.append(ChatColor.GOLD + " - " + ChatColor.DARK_PURPLE + (i + 1) + ChatColor.GOLD + " - ");
				sb.append(ChatColor.DARK_PURPLE + "World" + ChatColor.GOLD + ": " + ChatColor.GREEN);
				sb.append(loc.getWorld().getName() + ChatColor.GOLD + ", ");
				sb.append(ChatColor.DARK_PURPLE + "X" + ChatColor.GOLD + ": " + ChatColor.GREEN);
				sb.append(Double.toString(loc.getX()) + ChatColor.GOLD + ", ");
				sb.append(ChatColor.DARK_PURPLE + "Y" + ChatColor.GOLD + ": " + ChatColor.GREEN);
				sb.append(Double.toString(loc.getY()) + ChatColor.GOLD + ", ");
				sb.append(ChatColor.DARK_PURPLE + "Z" + ChatColor.GOLD + ": " + ChatColor.GREEN);
				sb.append(Double.toString(loc.getZ()));
				
				sender.sendMessage(sb.toString());
			}
			
			sender.sendMessage(ChatColor.GOLD + "-----------------------------------------");
		}
	}

}
