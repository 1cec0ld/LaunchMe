package net.bitjump.launchme.transport;

public abstract class PassiveTransport extends TransportType
{	
	@Override
	public String getSecondLine()
	{
		return "";
	}
	
	@Override
	public String getThirdLine()
	{
		return "";
	}
	
	@Override
	public String getFourthLine()
	{
		return "";
	}
	
	@Override
	public boolean matchSecondLine(String s)
	{
		return true;
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