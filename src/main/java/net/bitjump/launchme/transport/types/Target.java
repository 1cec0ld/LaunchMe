package net.bitjump.launchme.transport.types;

import net.bitjump.launchme.managers.TargetManager;
import net.bitjump.launchme.transport.PassiveTransport;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class Target extends PassiveTransport
{
	@Override
	public void activateTransport(Player p, Sign s) 
	{

	}
	
	@Override
	public void onCreate(Player p, Sign s, String[] lines)
	{
		TargetManager.add(lines[1], s.getLocation().add(0.5, 2.0, 0.5));
	}
	
}
