LaunchMe
=========
Introduction
-------------
LaunchMe is a transportation plugin for the Bukkit Minecraft server, geared to be flexible and easy to expand and modify, while still retaining speed and functionality.

It is as simple to set up as it is to use, and anyone can get involved with development and contribute to the growth of the project.

Installing
-----------
To install, simply drag the provided `LaunchMe.jar` into your `plugins/` directory, and reboot your server. The configuration will be automatically generated.

Permissions
------------
* launchme.cannon.create
 - Allows for cannon creation.
* launchme.cannon.use
 - Allows for cannon use.
* launchme.teleporter.create
 - Allows for teleporter creation.
* launchme.teleporter.use
 - Allows for teleporter use.
* launchme.land.create
 - Allows for landing pad creation.
* launchme.land.use
 - Allows for landing pad use.

Developers
-----------
If you wish to contribute to the LaunchMe project, the easiest way is by making a pull request! The easiest way to add new types of transport is by simply extending `TransportType` and implementing base behaviour in there. If it needs any extra code to make it function as desired (a prime example is the landing pad), simply edit the relevant files! 

Links
-------
[Bugs and feature requests!](https://github.com/Spudstabber/LaunchMe/issues)  
[Latest build!](http://ci.forkofdoom.com/job/LaunchMe/lastBuild/)

---------------