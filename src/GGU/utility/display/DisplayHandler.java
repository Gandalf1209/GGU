package GGU.utility.display;

import GGU.Engine;
import GGU.EngineConfiguration;
import GGU.debug.Debug;
import GGU.utility.OutputUtility;
import GGU.utility.data.loaders.image.ImageLoader;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.nio.ByteBuffer;

/**
 * Handles the display of the program (the window it runs in)
 *
 * Also loads depending on engine configuration and which display mode is the most suitable for the PC it's running on.
 */
public class DisplayHandler {

    public static void create(EngineConfiguration config){

        try {

            createDisplayMode(config);
            createIcons(config);
            Display.create();

        }catch(LWJGLException e){
            Debug.error("Error creating display: " + e.getMessage());
        }
    }

    public static void createIcons(EngineConfiguration config){
        EngineConfiguration engine = config;
        String icon16 = engine.DISPLAY_ICON_16;
        String icon32 = engine.DISPLAY_ICON_32;

        ByteBuffer[] icons = new ByteBuffer[2];
        icons[0] = ImageLoader.getByteBuffer(ImageLoader.getBufferedImage(icon16));
        icons[1] = ImageLoader.getByteBuffer(ImageLoader.getBufferedImage(icon32));

        Display.setIcon(icons);
    }

    public static void createDisplayMode(EngineConfiguration config) throws LWJGLException{
        DisplayMode configured = DisplayUtility.doesDisplayModeExist(config);

        if(configured == null){
            createBestDisplay();
        }else{
            createChosenDisplay(config, configured);
        }
    }
    public static void createBestDisplay() throws LWJGLException{

        DisplayMode displayMode = DisplayUtility.getPreferredDisplay();

        //Output the displaymode
        OutputUtility.outputLine("-Chosen Display Mode-");

        OutputUtility.increment();
        OutputUtility.outputLine("Width: " + displayMode.getWidth());
        OutputUtility.outputLine("Height: " + displayMode.getHeight());
        OutputUtility.outputLine("Frequency: " + displayMode.getFrequency());
        OutputUtility.outputLine("Color range: " + displayMode.getBitsPerPixel());
        OutputUtility.outputLine("Fullscreen Capable: " + displayMode.isFullscreenCapable());
        OutputUtility.deincrement();


        if (displayMode.isFullscreenCapable()) {
            Display.setFullscreen(true);
        }

        Display.setDisplayMode(displayMode);
    }
    public static void createChosenDisplay(EngineConfiguration config, DisplayMode chosen) throws LWJGLException{

        if(config.DISPLAY_FULLSCREEN){
            Display.setFullscreen(true);
        }

        Display.setDisplayMode(chosen);

    }

    public static void update(){
        Display.update();

        if(Engine.getConfiguration().DEVELOPER_LIMITFPS) {
            Display.sync(Engine.getConfiguration().DEVELOPER_TARGET_FPS);
        }
    }
    public static void destroy(){
        Display.destroy();
    }

    public static boolean closed(){
        return Display.isCloseRequested();
    }
    public static int getWidth(){
        return Display.getDisplayMode().getWidth();
    }
    public static int getHeight(){
        return Display.getDisplayMode().getHeight();
    }

}
