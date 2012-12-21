package net.bitjump.launchme.transport.types;

import net.bitjump.launchme.LaunchMe;
import net.bitjump.launchme.managers.LocaleManager;
import net.bitjump.launchme.managers.TargetManager;
import net.bitjump.launchme.transport.TargetedTransport;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class TTeleporter extends TargetedTransport
{

	@Override
	public String getThirdLine() 
	{
		return "^\\s*[0-9]+(?:\\.[0-9]*)?\\s*$";
	}

	@Override
	public void activateTransport(Player p, Sign s)
	{
		String[] lines = s.getLines();
		
		if(p.getLocation().getBlock().getRelative(0, -1, 0).getTypeId() != LaunchMe.config.getInt("transports.teleporter.deactivate", 42))
		{
			Location en = null;
			try
			{
				en = TargetManager.get(lines[1]);
				en.setY(en.getY() + 2);
			}
			catch(NullPointerException e)
			{
				p.sendMessage(LocaleManager.get("tteleporter.targetmissing", "This teleporter has no known target!"));
				return;
			}
			
			final Location end = en;
		
			if(LaunchMe.econ)
			{
				try
				{
					Double price = Double.parseDouble(lines[2]);
					if(!LaunchMe.economy.has(p.getName(), price))
					{
						p.sendMessage(LocaleManager.get("econ.nofunds", "Not enough money to use this transport!"));
						return;
					}
						
					LaunchMe.economy.withdrawPlayer(p.getName(), price);
					p.sendMessage(LocaleManager.get("econ.withdraw", "$%money% withdrawn from your account!").replaceAll("%money%", price + "0"));
				}
				catch(Exception e)
				{
					
				}
			}
			
			// p.sendMessage(ChatColor.GREEN + "Whoomp!");
			p.sendMessage(ChatColor.GREEN + LocaleManager.get("teleporter.use", "Whoomp!"));
			p.teleport(end);
		}
	}

}
