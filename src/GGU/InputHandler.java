package GGU;

import game.states.State;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Utility class for handling input for a state
 */
public class InputHandler {

    public static void handle(State state){
        handleKeyboard(state);
        handleMouse(state);
    }
    public static void handleKeyboard(State state){
        while(Keyboard.next()){
            int key = Keyboard.getEventKey();
            boolean pressed = Keyboard.getEventKeyState();

            if(pressed){
                state.keyPressed(key);
            }else{
                state.keyReleased(key);
            }

        }
    }
    public static void handleMouse(State state){
        while(Mouse.next() == true){
            int button = Mouse.getEventButton();
            int x = Mouse.getEventX();
            int y = Mouse.getEventY();

            boolean pressed = Mouse.getEventButtonState();

            if(pressed){
                state.mousePressed(button, x, y);
            }else{
                state.mouseReleased(button, x, y);
            }
        }
    }
}
