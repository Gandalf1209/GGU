package GGU.utility.config;

import GGU.utility.FileUtility;
import GGU.utility.parsers.ini.INIFile;
import GGU.utility.parsers.ini.INIParser;

/*

    Configuration loader determines which INI file to read the engine configuration from including the creation of a default configuration

    Use: used by the engine to get the needed configuration class, depending on whether a custom configuration exists

 */

public class ConfigurationLoader {

    //static String DEFAULT_CONFIGURATION_PATH = "data/config/default.ini";
    //static String EDITED_CONFIGURATION_PATH = "data/config/config.ini";
    static String DEFAULT_CONFIGURATION_PATH;
    static String EDITED_CONFIGURATION_PATH;

    public static void setConfigurationPaths(String defaultPath, String editedPath){
        DEFAULT_CONFIGURATION_PATH = defaultPath;
        EDITED_CONFIGURATION_PATH = editedPath;
    }

    public static Configuration getConfiguration(ConfigurationInterface type){

        boolean configAvailable = FileUtility.doesFileExist(EDITED_CONFIGURATION_PATH);
        boolean defaultAvailable = FileUtility.doesFileExist(DEFAULT_CONFIGURATION_PATH);

        String chosenPath = EDITED_CONFIGURATION_PATH;

        if(configAvailable == false){
            chosenPath = DEFAULT_CONFIGURATION_PATH;
        }

        if(defaultAvailable == false){
            createDefaultConfiguration(type);
        }

        INIFile ini = INIParser.parseFile(FileUtility.readFile(chosenPath));
        Configuration configuration = new Configuration(ini, type);
        return configuration;

    }
    public static void createDefaultConfiguration(ConfigurationInterface type){
        INIFile file = type.createDefault();
        INIParser.saveFile(file, DEFAULT_CONFIGURATION_PATH);
    }

}
