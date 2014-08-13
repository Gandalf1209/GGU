package GGU.utility.data.opengl;

import GGU.utility.display.OpenGLUtility;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;


/*
Utility class for loading and using textures loaded on the graphics card using OpenGL
Used: To load and render images using OpenGL
 */

public class OpenGLTexture {

    BufferedImage raw;
    int id;

    float texX1 = 0, texX2 = 1, texX3 = 1, texX4 = 0; //Ewwww
    float texY1 = 0, texY2 = 0, texY3 = 1, texY4 = 1; //Ewwwwwwwwwwww

    float r, g, b, a;

    //Disgusting constructors
    ////////////////////////
    public OpenGLTexture(BufferedImage image){
        this.raw = image;
        this.id = OpenGLUtility.loadTexture(image);

        init();
    }
    public OpenGLTexture(OpenGLTexture texture){
        this.raw = texture.getImage();
        this.id = texture.getID();

        init();
    }
    public OpenGLTexture(ByteBuffer buffer, int width, int height){
        this.id = OpenGLUtility.createTexture();
        OpenGLUtility.updateTexture(this.id, buffer, width, height);

        init();
    }
    public OpenGLTexture(int ID, float texX1, float texY1, float texX2, float texY2, float texX3, float texY3, float texX4, float texY4){
        //This is used with spritesheets
        this.id = ID;

        this.texX1 = texX1; this.texY1 = texY1;
        this.texX2 = texX2; this.texY2 = texY2;
        this.texX3 = texX3; this.texY3 = texY3;
        this.texX4 = texX4; this.texY4 = texY4;

        init();

    }
    public void init(){
        this.r = 1f;
        this.g = 1f;
        this.b = 1f;
        this.a = 1f;
    }
    ///////////////////////////////


    public void bind(){
        glBindTexture(GL_TEXTURE_2D, this.id);
    }

    public void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void delete(){
        glDeleteTextures(this.id);
    }

    public void draw(float x, float y, float width, float height, float z){

        bind();
        glColor4f(r, g, b, a);
        glBegin(GL_QUADS);

        glTexCoord2f(texX1, texY1);
        glVertex3f(x, y, z);

        glTexCoord2f(texX2, texY2);
        glVertex3f(x + width, y, z);

        glTexCoord2f(texX3, texY3);
        glVertex3f(x + width, y + height, z);

        glTexCoord2f(texX4, texY4);
        glVertex3f(x, y + height, z);

        glEnd();

        unbind();
    }
    public void setRGBAOverlay(float r, float g, float b, float a){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public int getID(){
        return this.id;
    }
    public BufferedImage getImage(){ return this.raw; }


}
