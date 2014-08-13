package GGU.utility.config;

/*

EngineConfiguration class which holds all the needed information for the program to run.

Used by the game engine to determine its configuration, hence the name.

 */

import GGU.utility.parsers.ini.INIFile;

public abstract class ConfigurationInterface {

    public abstract void parse(Configuration config);
    public abstract INIFile createDefault();

}
