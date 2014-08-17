package GGU.utility.audio;

import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

//Uses paulscode

public class AudioManager {

	static SoundSystem system;



    public static void create(){

        try {

            SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
            SoundSystemConfig.setCodec("wav", CodecWav.class);

            system = new SoundSystem(LibraryLWJGLOpenAL.class);

        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }

    }

    public static SoundSystem getSystem(){
        return system;
    }

	public static void onClose(){
		system.cleanup();
	}
}
