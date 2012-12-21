package net.bitjump.launchme;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class KickFilter implements Filter
{
	@Override
	public boolean isLoggable(LogRecord arg0) 
	{
		if(arg0.getMessage().contains("floating too long!"))
		{
			return false;
		}
		
		return true;
	}
	
}
