package net.bitjump.launchme.commands;

import java.util.Arrays;
import java.util.List;

import net.bitjump.launchme.utils.MessageUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpCommand extends Subcommand 
{
	private List<String> cmds = Arrays.asList("help [page]", "info");
	private List<String> descs = Arrays.asList("Displays this menu.", "Displays plugin inforation");
	
	public String getPermission()
	{
		return "launchme.help";
	}
	
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{	
		int pages = cmds.size() / 5 + 1;
		
		if(args.length <= 1)
		{
			Bukkit.dispatchCommand(sender, "lm help 1");
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
				sender.sendMessage(MessageUtils.getMessage("Invalid help menu ID!"));
				return;
			}
			
			if(pages < id)
			{
				sender.sendMessage(MessageUtils.getMessage("Invalid help menu ID!"));
				return;
			}
			
			
			
			sender.sendMessage(ChatColor.GOLD + "---------- " + ChatColor.DARK_PURPLE + "LaunchMe Help " + ChatColor.GOLD + "(" + ChatColor.DARK_PURPLE + id + ChatColor.GOLD + "/" + ChatColor.DARK_PURPLE + pages + ChatColor.GOLD + ") ---------");
			
			for(int i = (id * 5 - 5); i < id * 5; i++)
			{
				if(!(cmds.size() > i)) break;
				
				StringBuilder sb = new StringBuilder(ChatColor.GOLD + "- " + ChatColor.DARK_PURPLE + cmds.get(i));
				sb.append(ChatColor.GOLD + " - " + ChatColor.GREEN);
				sb.append(descs.get(i));
				sender.sendMessage(sb.toString());
			}
			
			sender.sendMessage(ChatColor.GOLD + "----------------------------------------");
		}
	}

}
