package net.bitjump.launchme.commands;

public abstract class PlayerSubcommand extends Subcommand
{
	@Override
	public boolean needsPlayer()
	{
		return true;
	}

}
