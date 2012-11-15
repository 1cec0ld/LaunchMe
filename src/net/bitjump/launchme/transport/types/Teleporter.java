package net.bitjump.launchme.transport.types;

import net.bitjump.launchme.LaunchMe;
import net.bitjump.launchme.transport.BasicTransport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class Teleporter extends BasicTransport 
{

	@Override
	public String getSecondLine() 
	{
		return "^\\s*-?[0-9]+(?:\\.[0-9]*)?,\\s*-?[0-9]+(?:\\.[0-9]*)?,\\s*-?[0-9]+(?:\\.[0-9]*)?\\s*$";
	}

	@Override
	public String getThirdLine() 
	{
		return "^\\s*[0-9]+(?:\\.[0-9]*)?\\s*$";
	}

	@Override
	public void activateTransport(Player p, Sign s)
	{
		String[] lines = s.getLines();
		String[] coords = lines[1].split(",");
		
		if(p.getLocation().getBlock().getRelative(0, -1, 0).getTypeId() == LaunchMe.config.getInt("transports.teleporter.deactivate"))
		{
		
			final Location end = new Location(Bukkit.getWorld(lines[3]), Double.parseDouble(coords[0]), Double.parseDouble(coords[1]), Double.parseDouble(coords[2]));
		
			p.sendMessage(ChatColor.GREEN + "Whoomp!");
			p.teleport(end);
		}
	}

}
