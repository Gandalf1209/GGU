package GGU.utility.data.loaders.audio;

import paulscode.sound.SoundSystem;

/**
 * Created by Admin on 14/06/2014.
 */
public class Audio {

    SoundSystem system;
    String name;

    public Audio(SoundSystem system, String name){
        this.system = system;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setVolume(float volume){
        system.setVolume(this.name, volume);
    }
    public void play(){
        if(playing() == true){
            stop();
        }
        system.play(this.name);
    }
    public void stop(){
        if(playing() == true) {
            system.stop(this.name);
        }
    }
    public boolean playing(){
        return system.playing(this.name);
    }

}
