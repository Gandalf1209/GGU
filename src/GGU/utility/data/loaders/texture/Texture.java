package GGU.utility.data.loaders.texture;

import GGU.utility.data.opengl.OpenGLTexture;

/**
 * Created by Callum on 07/06/2014.
 */
public class Texture {

    String name;
    OpenGLTexture texture;

    public Texture(String name, OpenGLTexture texture){
        this.name = name;
        this.texture = texture;
    }

    public String getName(){
        return this.name;
    }
    public OpenGLTexture getTexture(){
        return this.texture;
    }

}
