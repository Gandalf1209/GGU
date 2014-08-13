package GGU.utility.display;

import static org.lwjgl.opengl.GL11.*;

/*

Handles the initialisation and updating of OpenGL

 */

public class OpenGLHandler {

    static float gradient;
    final static float view_depth = 1f;

    public static void create(){

        gradient = ((float)DisplayHandler.getHeight())/((float)DisplayHandler.getWidth());

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-1, 1, gradient, -gradient, -view_depth, view_depth);
        glMatrixMode(GL_MODELVIEW);

        glEnable(GL_TEXTURE_2D);
        glEnable (GL_BLEND);
        //glEnable(GL_DEPTH_TEST);

        glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    }
    public static void update(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
    }

    public static float getHeight(){
        return gradient;
    }
    public static float getDepthRange(){ return view_depth; }

}
