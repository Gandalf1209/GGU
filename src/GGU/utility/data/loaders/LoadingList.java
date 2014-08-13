package GGU.utility.data.loaders;

import java.util.ArrayList;

/*

Loading list class which acts as base class for all loaders which require 'tickets' AKA data and ID

 */

public abstract class LoadingList {

    public ArrayList<LoadingTicket> tickets;

    public LoadingList(){
        init();
    }

    public void init(){
        tickets = new ArrayList<LoadingTicket>();
    }

    public void load(){
        for(LoadingTicket ticket : tickets){
            loadTicket(ticket);
        }
    }

    public void add(String name, String path){
        this.tickets.add(new LoadingTicket(name, path));
    }

    public abstract void loadTicket(LoadingTicket ticket);

}
