package net.bitjump.launchme.listeners;

import net.bitjump.launchme.LocaleManager;
import net.bitjump.launchme.transport.TransportManager;
import net.bitjump.launchme.transport.TransportType;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener 
{
	@EventHandler
	public void onSignChange(SignChangeEvent e)
	{
		Player p = e.getPlayer();
		String[] lines = e.getLines();
		
		String ss = "";
		try
		{
			ss = lines[0].toLowerCase().substring(1, lines[0].length() - 1);
		}
		catch(StringIndexOutOfBoundsException ex)
		{
			return;
		}
		
		if (TransportManager.hasType(ss))
		{
			TransportType type = TransportManager.getType(ss);
			
			if(!p.hasPermission("launchme." + type.getName().toLowerCase() + ".create"))
			{
				p.sendMessage(LocaleManager.get(type.getName().toLowerCase() + ".nopermission"));
				e.setCancelled(true);
				return;
			}
			
			if(!type.matchSecondLine(lines[1]))
			{
				p.sendMessage(LocaleManager.get("sign.badcoords"));
				e.setCancelled(true);
				return;
			}
			
			if(!type.matchThirdLine(lines[2]))
			{
				p.sendMessage(LocaleManager.get("sign.badprice"));
				e.setCancelled(true);
				return;
			}
			
			if(!type.matchFourthLine(lines[3]))
			{
				p.sendMessage(LocaleManager.get("sign.badworld"));
				e.setCancelled(true);
				return;
			}
			
			p.sendMessage(LocaleManager.get(type.getName().toLowerCase() + ".success"));
			type.onCreate(p, (Sign) e.getBlock().getState());
			// type.created++;
		}
	}

}
