package net.bitjump.launchme.listeners;

import net.bitjump.launchme.TargetManager;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener
{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		if(e.getBlock().getState() instanceof Sign)
		{
			Sign s = (Sign) e.getBlock().getState();
			
			String ss = "";
			try
			{
				ss = s.getLine(0).substring(1, s.getLine(0).length() - 1);
			}
			catch(StringIndexOutOfBoundsException ex)
			{
				return;
			}
			
			if(ss.equals("target"))
			{
				TargetManager.remove(s.getLine(1));
			}
		}
	}

}
