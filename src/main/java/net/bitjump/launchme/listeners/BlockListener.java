package net.bitjump.launchme.listeners;

import net.bitjump.launchme.managers.TargetManager;
import net.bitjump.launchme.utils.OMBLogger;

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
			
			String[] lines = s.getLines();
			
			String ss = "";
			try
			{
				ss = lines[0].toLowerCase().substring(1, lines[0].length() - 1);
			}
			catch(StringIndexOutOfBoundsException ex)
			{
				return;
			}
			
			if(ss.equals("target"))
			{
				TargetManager.remove(lines[1]);
				OMBLogger.info("Removed target " + lines[1]);
			}
		}
	}

}
