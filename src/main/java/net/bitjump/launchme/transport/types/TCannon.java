package net.bitjump.launchme.transport.types;

import net.bitjump.launchme.LaunchMe;
import net.bitjump.launchme.LocaleManager;
import net.bitjump.launchme.TargetManager;
import net.bitjump.launchme.transport.TargetedTransport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TCannon extends TargetedTransport
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
		
		if(p.getLocation().getBlock().getRelative(0, -1, 0).getTypeId() != LaunchMe.config.getInt("transports.cannon.deactivate", 42))
		{
			Location en = null;
			try
			{
				en = TargetManager.get(lines[1]);
				en.setY(LaunchMe.config.getInt("transport.cannon.height", 600));
			}
			catch(NullPointerException e)
			{
				p.sendMessage(LocaleManager.get("tcannon.targetmissing", "This cannon has no known target!"));
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
			
			//p.sendMessage(ChatColor.GREEN + "Whoosh!");
			p.sendMessage(ChatColor.GREEN + LocaleManager.get("tcannon.use", "Whoosh!"));
			p.getWorld().createExplosion(p.getLocation(), 0);
			
			LaunchMe.active.add(p);
			
			final Player pl = p;
			final int nyan = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(LaunchMe.instance, new Runnable()
			{
				public void run()
				{
					pl.setVelocity(new Vector(0, 10, 0));
				}
			}, 5, 5);
	
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(LaunchMe.instance, new Runnable()
			{
				public void run()
				{
					pl.teleport(end);
					if(pl.getGameMode().equals(GameMode.CREATIVE))
					{
						LaunchMe.active.remove(pl);
					}
					Bukkit.getServer().getScheduler().cancelTask(nyan);
				}
			}, 100); 
		}
	}

}
