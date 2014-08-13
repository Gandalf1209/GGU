package GGU;

import GGU.utility.display.DisplayHandler;
import GGU.utility.display.OpenGLHandler;
import GGU.utility.timing.FPSTimer;
import game.states.State;

/*

    The state handler handles program states. For instance, the running state or the loading state

    Use: used to switch between states, update and render them, including the updating of required utilities and FPS timing.

 */

public class StateHandler {

    static State current;
    static boolean close;

    public static void setState(State state){
        if(StateHandler.current != null) {
            StateHandler.current.close();
        }
        StateHandler.current = state;
    }

    public static void beginLoop(){
        while(StateHandler.close == false && DisplayHandler.closed() == false){
            StateHandler.loop();
        }
        StateHandler.close();
        DisplayHandler.destroy();
    }

    public static void loop(){
        FPSTimer.startTimer();

        DisplayHandler.update();
        OpenGLHandler.update();
        InputHandler.handle(StateHandler.current);

        StateHandler.current.update();
        if(isClosed()){return;}
        StateHandler.current.render();

        FPSTimer.endTimer();
    }

    public static void close(){

        StateHandler.close = true;
        StateHandler.current.close();
    }

    public static State getState(){
        return StateHandler.current;
    }
    public static boolean isClosed(){
        return StateHandler.close;
    }

}
