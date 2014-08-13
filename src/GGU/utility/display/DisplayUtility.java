package GGU.utility.display;

import GGU.EngineConfiguration;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 *
 * Used to get the best display mode to set the program to.
 *
 */
public class DisplayUtility {

    public static DisplayMode getPreferredDisplay() throws LWJGLException {

        DisplayMode chosen = Display.getDisplayMode();
        DisplayMode[] modes = Display.getAvailableDisplayModes();

        for(DisplayMode display : modes){
            if(isDisplayModeBetter(chosen, display) == true){
                chosen = display;
            }
        }

        return chosen;

    }
    public static boolean isDisplayModeBetter(DisplayMode current, DisplayMode isBetter){

        //Formatted weirdly to quickly see what the preferred settings are
        if(
           isBetter.getWidth() >= current.getWidth() &&
           isBetter.getHeight() >= current.getHeight() &&
           isBetter.getFrequency() >= current.getFrequency() &&
           isBetter.getBitsPerPixel() >= current.getBitsPerPixel() &&
           isBetter.isFullscreenCapable()
          ){

            return true;
        }

        return false;
    }

    //Returns null if does not exist
    public static DisplayMode doesDisplayModeExist(EngineConfiguration config) throws LWJGLException{

        DisplayMode[] modes = Display.getAvailableDisplayModes();
        DisplayMode chosen = null;

        for(DisplayMode mode : modes){
            int width = mode.getWidth();
            int height = mode.getHeight();

            if(width == config.DISPLAY_WIDTH && height == config.DISPLAY_HEIGHT){
                if(chosen == null){
                    chosen = mode;
                }else{
                    if(isDisplayModeBetter(chosen, mode)){
                        chosen = mode;
                    }
                }
            }

        }

        return chosen;

    }

}
