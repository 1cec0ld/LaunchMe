package net.bitjump.launchme.transport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;

public abstract class BasicTransport implements TransportType
{	
	private final String name = getClass().getSimpleName();
	
	public abstract String getSecondLine();
	public abstract String getThirdLine();
	
	@Override
	public String getFourthLine()
	{
		return "";
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public boolean matchSecondLine(String s)
	{
		Pattern p = Pattern.compile(getSecondLine());
		Matcher m = p.matcher(s);
		
		if(m.find())
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean matchThirdLine(String s)
	{
		Pattern p = Pattern.compile(getThirdLine());
		Matcher m = p.matcher(s);
		
		if(m.find())
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean matchFourthLine(String s)
	{
		if(Bukkit.getServer().getWorld(s) != null)
		{
			return true;
		}
		
		return false;
	}

}