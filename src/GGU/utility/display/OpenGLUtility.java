package GGU.utility.display;

import GGU.utility.data.loaders.image.ImageLoader;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * OpenGL utility which is used by a lot of things. Basically just code re-usability.
 */
public class OpenGLUtility {

    public static void setAlpha(float alpha){
        glColor4f(1, 1, 1, alpha);
    }


    public static int createTexture(){
        return glGenTextures();
    }
    public static int loadTexture(BufferedImage image){

        int pointer = createTexture();

        ByteBuffer buffer = ImageLoader.getByteBuffer(image);

        glBindTexture(GL_TEXTURE_2D, pointer);
        //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        glBindTexture(GL_TEXTURE_2D, 0);

        return pointer;

    }
    public static void updateTexture(int ID, ByteBuffer data, int width, int height){
        glBindTexture(GL_TEXTURE_2D, ID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
