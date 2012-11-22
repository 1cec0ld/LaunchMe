package net.bitjump.launchme.transport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TargetedTransport extends TransportType
{	
	public abstract String getSecondLine();
	public abstract String getThirdLine();
	
	@Override
	public String getFourthLine()
	{
		return "";
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
		return true;
	}
	
	@Override
	public boolean matchFourthLine(String s)
	{
		return true;
	}

}