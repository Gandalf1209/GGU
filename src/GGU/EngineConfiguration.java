package GGU;

import GGU.utility.config.Configuration;
import GGU.utility.config.ConfigurationInterface;
import GGU.utility.parsers.ini.INIFile;
import GGU.utility.parsers.ini.INIParameter;
import GGU.utility.parsers.ini.INIParameterType;
import GGU.utility.parsers.ini.INISection;

import java.awt.*;

/**
 * Configuration used to intiailise the engine
 */
public class EngineConfiguration extends ConfigurationInterface {

    public int

            DISPLAY_WIDTH,
            DISPLAY_HEIGHT,

            DEVELOPER_TARGET_FPS
                    ;

    public boolean

            DISPLAY_FULLSCREEN,

            DEVELOPER_DEBUG,
            DEVELOPER_LIMITFPS

                    ;
    public String

            DISPLAY_ICON_16,
            DISPLAY_ICON_32

                    ;

    public void parse(Configuration config){

        INIFile file = config.getConfig();

        INISection display = file.getSection("display");

        DISPLAY_WIDTH = display.getParameter("width").getIntegerValue();
        DISPLAY_HEIGHT = display.getParameter("height").getIntegerValue();
        DISPLAY_FULLSCREEN = display.getParameter("fullscreen").getBooleanValue();
        DISPLAY_ICON_16 = display.getParameter("icon16").getStringValue();
        DISPLAY_ICON_32 = display.getParameter("icon32").getStringValue();

        INISection developer = file.getSection("developer");

        DEVELOPER_DEBUG = developer.getParameter("debug").getBooleanValue();
        DEVELOPER_TARGET_FPS = developer.getParameter("targetfps").getIntegerValue();
        DEVELOPER_LIMITFPS = developer.getParameter("limitfps").getBooleanValue();

    }

    public INIFile createDefault() {

        INIFile file = new INIFile();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        INISection display = new INISection("display");
        display.addParameter(new INIParameter("width", "" + (int) dimension.getWidth(), INIParameterType.INT));
        display.addParameter(new INIParameter("height", "" + (int) dimension.getHeight(), INIParameterType.INT));
        display.addParameter(new INIParameter("fullscreen", "true", INIParameterType.BOOL));
        display.addParameter(new INIParameter("icon16", "\"data/resources/gui/icons/icon16.png\"", INIParameterType.BOOL));
        display.addParameter(new INIParameter("icon32", "\"data/resources/gui/icons/icon32.png\"", INIParameterType.BOOL));
        file.addSection(display);

        INISection developer = new INISection("developer");
        developer.addParameter(new INIParameter("debug", "true", INIParameterType.BOOL));
        developer.addParameter(new INIParameter("targetfps", "60", INIParameterType.INT));
        developer.addParameter(new INIParameter("limitfps", "false", INIParameterType.BOOL));
        file.addSection(developer);

        return file;

    }

}
