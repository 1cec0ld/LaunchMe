package net.bitjump.launchme.listeners;

import net.bitjump.launchme.LaunchMe;
import net.bitjump.launchme.LocaleManager;
import net.bitjump.launchme.transport.TransportManager;
import net.bitjump.launchme.transport.TransportType;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener
{
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e)
	{
		if(e.getFrom().getBlock().equals(e.getTo().getBlock())) return;
		
		Player p = e.getPlayer();
		
		Block b = p.getLocation().getBlock().getRelative(0, -2, 0);
		
		if(b.getState() instanceof Sign)
		{
			Sign s = (Sign) b.getState();
			
			String[] lines = s.getLines();
			
			if(TransportManager.hasType(lines[0]))
			{
				TransportType type = TransportManager.getType(lines[0]);
				
				if(!p.hasPermission("launchme." + type.getName().toLowerCase() +".use"))
				{
					p.sendMessage(LocaleManager.get(type.getName().toLowerCase() + ".nouse"));
					return;
				}
				
				type.activateTransport(p, s);
			}
		}
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e)
	{
		if (e.getReason().startsWith("You moved too") || e.getReason().startsWith("Flying is not"))
		{
			if (LaunchMe.launched.contains(e.getPlayer()))
			{
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e)
	{
		Entity en = e.getEntity();

		if(en instanceof Player)
		{
			Player p = (Player) en;
			
			if(e.getCause().equals(DamageCause.FALL))
			{
				if(LaunchMe.launched.contains(p))
				{
					e.setCancelled(true);
					LaunchMe.launched.remove(p);
				}
			}
		}
	}

}
