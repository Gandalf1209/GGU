package GGU.utility.data.loaders.animation;

import GGU.utility.data.opengl.OpenGLTexture;

import java.util.ArrayList;

public class Animation {

    ArrayList<OpenGLTexture> frames;

    boolean looping;

    int frameLength; // Length of each frame (in milliseconds multiplied by FPSCounter multiplier)
    int frameNumber; // Frame the animation is currently at
    int frameTick;

    public Animation(boolean looping){
        this.frames = new ArrayList<OpenGLTexture>();

        this.frameLength = 1;
        this.frameNumber = 0;
        this.frameTick = 0;

        this.looping = looping;

    }
    //Quick define animation
    public Animation(OpenGLTexture[] frames, int speed, boolean looping){
        this.frames = new ArrayList<OpenGLTexture>();

        this.frameLength = speed;
        this.frameNumber = 0;
        this.frameTick = 0;

        this.looping = looping;

        this.addFrames(frames);

    }

    public void update(){

        this.frameTick++;

        if(this.frameTick >= this.frameLength){
            this.frameTick = 0;
            nextFrame();
        }

    }

    public void nextFrame(){
        this.frameNumber++;

        if(this.frameNumber >= frames.size()){
            if(this.looping == true){
                this.frameNumber = 0;
            }else{
                this.frameNumber = this.frames.size()-1;
            }
        }
    }


    public OpenGLTexture getFrame(){
        return frames.get(frameNumber);
    }
    public void setSpeed(int speed){
        this.frameLength = speed;
    }
    public void addFrame(OpenGLTexture frame){
        frames.add(frame);
    }
    public void addFrames(OpenGLTexture[] frames){
        for(OpenGLTexture frame : frames){
            this.frames.add(frame);
        }
    }
}
