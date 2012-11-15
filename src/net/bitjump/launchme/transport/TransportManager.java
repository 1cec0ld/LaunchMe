package net.bitjump.launchme.transport;

import java.util.HashMap;
import java.util.Map;


public class TransportManager 
{
	private static final Map<String, TransportType> transports = new HashMap<String, TransportType>();
	
	public static void addType(TransportType transport)
	{
		transports.put(transport.getName(), transport);
	}
	
	public static boolean hasType(String s)
	{
		if(transports.containsKey(s)) 
			return true;
		return false;
	}
	
	public static TransportType getType(String s)
	{
		return transports.get(s);
	}

}
