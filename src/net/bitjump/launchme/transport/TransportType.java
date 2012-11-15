package net.bitjump.launchme.transport;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public interface TransportType 
{
	public String getName();
	public String getSecondLine();
	public String getThirdLine();
	public String getFourthLine();
	
	public boolean matchSecondLine(String s);
	public boolean matchThirdLine(String s);
	public boolean matchFourthLine(String s);
	
	public void activateTransport(Player p, Sign s);
}
