package GGU.utility.config;

import GGU.utility.OutputUtility;
import GGU.utility.parsers.ini.INIFile;
import GGU.utility.parsers.ini.INIParameter;
import GGU.utility.parsers.ini.INISection;

/*

    Configuration class which takes in an INI file and converts it into an EngineConfiguration

    Used at the very start of the program to load the default or custom configuration.

 */

public class Configuration {

    INIFile config;
    ConfigurationInterface engine;

    public Configuration(INIFile config, ConfigurationInterface engine){
        this.config = config;

        loadConfiguration(engine);
    }
    public ConfigurationInterface getVariables(){
        return engine;
    }

    public void outputConfiguration(){

        for(INISection section : config.getSections()){

            OutputUtility.outputLine("[" + section.getName() + "]");
            OutputUtility.increment();

            for(INIParameter parameter : section.getParams()){

                OutputUtility.outputLine(parameter.getName() + " = " + parameter.getStringValue());

            }

            OutputUtility.deincrement();
        }

    }

    public void loadConfiguration(ConfigurationInterface config){
        /*
        engine = new EngineConfiguration();

        */

        this.engine = config;
        this.engine.parse(this);

    }

    public INIFile getConfig(){
        return config;
    }


}
