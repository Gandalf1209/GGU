package GGU.utility.data.loaders.audio;

import GGU.debug.Debug;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;

import java.io.File;

/**
 * Created by Admin on 14/06/2014.
 */
public class AudioLoader {

    public static Audio loadBackgroundAudio(SoundSystem system, String path, String name){
        if(loadBackgroundMusic(system, path, name)){
            return new Audio(system, name);
        }

        Debug.error("Failed to load audio: '" + path + "' - '" + name + "'");

        return new Audio(system, "");
    }

    public static Audio loadAudio(SoundSystem system, String path, String name){
        if(loadSoundEffect(system, path, name)){
           return new Audio(system, name);
        }

        Debug.error("Failed to load audio: '" + path + "' - '" + name + "'");

        return new Audio(system, "");
    }

    public static boolean loadBackgroundMusic(SoundSystem system, String location, String id){
        try{

            File file = new File(location);
            String fileName = file.getName();


            system.newStreamingSource(true, id, new File(location).toURI().toURL(), fileName, true, 0, 0, 0,
                    SoundSystemConfig.ATTENUATION_ROLLOFF, SoundSystemConfig.getDefaultRolloff());

            return true;

        }catch(Exception e){

            Debug.error("Error when loading background music '" + id + "' message: " + e.getMessage());

        }

        return false;
    }

    public static boolean loadSoundEffect(SoundSystem system, String location, String id){

        try{

            File file = new File(location);
            String fileName = file.getName();

            system.newStreamingSource(true, id, new File(location).toURI().toURL(), fileName, false, 0, 0, 0,
                    SoundSystemConfig.ATTENUATION_ROLLOFF, SoundSystemConfig.getDefaultRolloff());

            return true;

        }catch(Exception e){

            Debug.error("Error when loading sound effect '" + id + "' message: " + e.getMessage());

        }

        return false;

    }

}
