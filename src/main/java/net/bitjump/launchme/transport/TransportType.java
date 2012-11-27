package net.bitjump.launchme.transport;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public abstract class TransportType 
{
	private final String name = getClass().getSimpleName();
	
	public int uses = 0; 
	public int created = 0;
	
	public abstract String getSecondLine();
	public abstract String getThirdLine();
	public abstract String getFourthLine();
	
	public abstract boolean matchSecondLine(String s);
	public abstract boolean matchThirdLine(String s);
	public abstract boolean matchFourthLine(String s);
	
	public abstract void activateTransport(Player p, Sign s);
	
	public String getName()
	{
		return name;
	}
	
	public void onCreate(Player p, Sign s, String[] lines)
	{
		created++;
	}

}
