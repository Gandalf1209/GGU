package GGU.utility.data.loaders.animation;

import GGU.debug.Debug;
import GGU.utility.data.opengl.OpenGLTexture;

import java.util.ArrayList;

/**
 * Created by Admin on 19/06/2014.
 */
public class AnimationList {


    public ArrayList<AnimationTicket> tickets;

    public AnimationList(){
        init();
    }

    public void init(){
        tickets = new ArrayList<AnimationTicket>();
    }

    public void load(){
        for(AnimationTicket ticket : tickets){
            loadTicket(ticket);
        }
    }

    public void add(String name, OpenGLTexture[] frames, boolean loop, int speed){
        this.tickets.add(new AnimationTicket(name, frames, loop, speed));
    }
    public void loadTicket(AnimationTicket ticket){
        ticket.load();
    }

    public Animation getAnimation(String name){
        for(AnimationTicket ticket : this.tickets){
            if(ticket.getName().equals(name)){
                return ticket.getAnimation();
            }
        }
        Debug.error("SpriteSheet not found '" + name + "'");
        return null;
    }

}
