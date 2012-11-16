package net.bitjump.launchme;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class KickFilter implements Filter
{
	@Override
	public boolean isLoggable(LogRecord arg0) 
	{
		boolean isLoggable = true;
		if(arg0.getMessage().endsWith("floating too long!"));
		{
			isLoggable = false;
		}
		
		return isLoggable;
	}

}
