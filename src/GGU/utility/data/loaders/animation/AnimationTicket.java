package GGU.utility.data.loaders.animation;

import GGU.utility.data.opengl.OpenGLTexture;

/**
 * Created by Admin on 19/06/2014.
 */
public class AnimationTicket {

    OpenGLTexture[] frames;
    Animation loaded;
    String name;

    boolean loop;
    int speed;


    public AnimationTicket(String name, OpenGLTexture[] frames, boolean loop, int speed){
        this.name = name;
        this.frames = frames;
        this.loop = loop;
        this.speed = speed;
    }

    public void load(){
        this.loaded = new Animation(frames, speed, loop);
    }

    public Animation getAnimation(){
        return loaded;
    }

    public String getName(){
        return name;
    }
}
