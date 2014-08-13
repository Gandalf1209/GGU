package GGU.utility.data.loaders.spritesheet;

import GGU.debug.Debug;

import java.util.ArrayList;

public class SpriteSheetList {

    public ArrayList<SpriteSheetTicket> tickets;

    public SpriteSheetList(){
        init();
    }

    public void init(){
        tickets = new ArrayList<SpriteSheetTicket>();
    }

    public void load(){
        for(SpriteSheetTicket ticket : tickets){
            loadTicket(ticket);
        }
    }

    public void add(String name, int width, int height){
        this.tickets.add(new SpriteSheetTicket(name, width, height));
    }
    public void loadTicket(SpriteSheetTicket ticket){
        ticket.load();
    }

    public SpriteSheet getSheet(String name){
        for(SpriteSheetTicket ticket : this.tickets){
            if(ticket.getName().equals(name)){
                return ticket.getSheet();
            }
        }
        Debug.error("SpriteSheet not found '" + name + "'");
        return null;
    }

}
