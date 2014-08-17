package GGU;

import GGU.utility.OutputUtility;
import GGU.utility.audio.AudioManager;
import GGU.utility.config.ConfigurationLoader;
import GGU.utility.data.GameData;
import GGU.utility.data.loaders.ResourceLoader;
import GGU.utility.display.DisplayHandler;
import GGU.utility.display.OpenGLHandler;
import GGU.utility.timing.FPSTimer;
import game.states.State;

import java.util.ArrayList;

/*

RTS Engine super-class. This is the centre point of the entire engine.
Use: Loads the needed utilities and configurations and starts the whole thing off.

 */

public class Engine {

    //This configuration ALWAYS uses the ProgramConfiguration type
    static EngineConfiguration CONFIG;
    static State STARTING_STATE;
    static ArrayList<ResourceLoader> resources = new ArrayList<ResourceLoader>();

    public static void attachResource(ResourceLoader resource){
        resources.add(resource);
    }

    public static void invoke(State state, String configPath){
        STARTING_STATE = state;
        ConfigurationLoader.setConfigurationPaths(configPath + "_default", configPath);
        Engine.create();
    }

    private static void create(){

        Engine.CONFIG = (EngineConfiguration)ConfigurationLoader.getConfiguration(new EngineConfiguration()).getVariables();

        DisplayHandler.create(CONFIG);
        OpenGLHandler.create();
        AudioManager.create();
        FPSTimer.setTargetFPS(CONFIG.DEVELOPER_TARGET_FPS);

        GameData.intialise();
        for(ResourceLoader loader : resources){
            loader.addResources();
        }
        GameData.loadData();

        Engine.outputWarnings();
        Engine.loadState();

    }
    public static void outputWarnings(){
        OutputUtility.outputLine("");
        if(CONFIG.DEVELOPER_LIMITFPS == false) {
            OutputUtility.outputLine("Warning: !FPS NOT LIMITED, USE FPSUTILITY TO REGULATE RATE OF VARIABLE CHANGE!");
        }
    }
    public static void loadState(){

        STARTING_STATE.init();
        StateHandler.setState(STARTING_STATE);
        StateHandler.beginLoop();
    }

    public static void close(){
        StateHandler.close();
    }
    public static EngineConfiguration getConfiguration(){
        return CONFIG;
    }
    public static boolean isClosed(){
        return StateHandler.isClosed();
    }

}
