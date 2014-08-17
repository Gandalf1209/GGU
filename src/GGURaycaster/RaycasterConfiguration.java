package GGURaycaster;

import GGU.utility.config.Configuration;
import GGU.utility.config.ConfigurationInterface;
import GGU.utility.parsers.ini.INIFile;
import GGU.utility.parsers.ini.INIParameter;
import GGU.utility.parsers.ini.INIParameterType;
import GGU.utility.parsers.ini.INISection;

/**
 * Configuration used to intiailise the raycaster
 */
public class RaycasterConfiguration extends ConfigurationInterface {

    public int
            RESOLUTION_X,
            RESOLUTION_Y,
            TRIGPRECISION
                   ;
    public double
            STATIC_CLIP;

    public void parse(Configuration config){

        INIFile file = config.getConfig();

        INISection rendering = file.getSection("rendering");

        RESOLUTION_X = rendering.getParameter("resolutionX").getIntegerValue();
        RESOLUTION_Y = rendering.getParameter("resolutionY").getIntegerValue();
        TRIGPRECISION = rendering.getParameter("trigprecision").getIntegerValue();
        STATIC_CLIP = rendering.getParameter("staticclip").getDoubleValue();

    }

    public INIFile createDefault() {

        INIFile file = new INIFile();

        INISection display = new INISection("rendering");
        display.addParameter(new INIParameter("resolutionX", "" + 320, INIParameterType.INT));
        display.addParameter(new INIParameter("resolutionY", "" + 240, INIParameterType.INT));
        display.addParameter(new INIParameter("trigprecision", "" + 65536, INIParameterType.INT));
        display.addParameter(new INIParameter("staticclip", "" + 0.2, INIParameterType.DECIMAL));
        file.addSection(display);

        return file;

    }

}
