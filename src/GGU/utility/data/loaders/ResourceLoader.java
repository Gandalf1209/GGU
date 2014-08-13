package GGU.utility.data.loaders;

import GGU.utility.data.GameData;
import GGU.utility.data.opengl.OpenGLTexture;

public abstract class ResourceLoader {

    String imagefolder, audiofolder;

    public ResourceLoader(String imagefolder, String audiofolder){
        this.imagefolder = imagefolder;
        this.audiofolder = audiofolder;
    }

    //For images, spritesheets and audio
    public abstract void addResources();
    public abstract void addAnimations();

    public void addImage(String name, String path, boolean texture){
        GameData.getImageList().add(name, path, texture);
    }
    public void addAudio(String name, String path){
        GameData.getAudioList().add(name, path);
    }
    public void addAnimation(String name, OpenGLTexture[] frames, boolean loop, int speed){
        GameData.getAnimationList().add(name, frames, loop, speed);
    }
    public void addSpriteSheet(String name, int width, int height){
        GameData.getSpriteSheetList().add(name, width, height);
    }

}
