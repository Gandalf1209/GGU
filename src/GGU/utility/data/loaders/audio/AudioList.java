package GGU.utility.data.loaders.audio;

import GGU.debug.Debug;
import GGU.utility.OutputUtility;
import GGU.utility.data.loaders.LoadingList;
import GGU.utility.data.loaders.LoadingTicket;
import game.audio.AudioManager;

import java.util.ArrayList;


public class AudioList extends LoadingList{

    final String AUDIO_BASE = "data/resources/audio/";

    ArrayList<Audio> audio;

    @Override
    public void init(){
        super.init();
        audio = new ArrayList<Audio>();
    }

    @Override
    public void loadTicket(LoadingTicket ticket) {
        audio.add(AudioLoader.loadAudio(AudioManager.getSystem(), ticket.getPath(), ticket.getName()));
        OutputUtility.outputLine("Loaded audio: '" + ticket.getName() + "' path: '" + ticket.getPath() + "'");
    }

    @Override
    public void add(String name, String path){
        super.add(name, AUDIO_BASE + path);
    }

    public Audio getAudio(String name){
        for(Audio au : this.audio){
            if(name.equals(au.getName())){
                return au;
            }
        }
        Debug.error("Audio does not exist '" + name + "'");
        return null;
    }
}
