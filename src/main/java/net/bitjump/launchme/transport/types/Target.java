package net.bitjump.launchme.transport.types;

import net.bitjump.launchme.TargetManager;
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
	public void onCreate(Player p, Sign s)
	{
		TargetManager.add(s.getLine(1), s.getLocation());
		created++;
	}
	
}
