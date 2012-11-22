package net.bitjump.launchme.listeners;

import net.bitjump.launchme.LocaleManager;
import net.bitjump.launchme.transport.TransportManager;
import net.bitjump.launchme.transport.TransportType;

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
		
		if (TransportManager.hasType(lines[0].toLowerCase().substring(1, lines[0].length() - 1)))
		{
			TransportType type = TransportManager.getType(lines[0].toLowerCase().substring(1, lines[0].length() - 1));
			
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
			// type.created++;
		}
	}

}
