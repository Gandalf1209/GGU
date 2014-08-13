package GGU.utility.timing;

import GGU.debug.Debug;

import java.util.ArrayList;

/**
 * Timing utility class.
 *
 * Use: used to initiate multiple timers for things like the FPS counter and the in-game clock...
 */
public class Timer {

    static ArrayList<TimerTicket> tickets;

    public static void startTimer(String ticket){

        if(tickets == null){
            tickets = new ArrayList<TimerTicket>();
        }

        tickets.add(new TimerTicket(ticket, System.nanoTime()));

    }
    public static long endTimer(String name){

        TimerTicket found = getTimer(name);
        long result = recordTimer(name); //Slower but looks nicer;
        tickets.remove(found);

        return result;

    }
    //Gets timer length but does not stop it
    public static long recordTimer(String name){
        TimerTicket found = getTimer(name);
        return System.nanoTime() - found.start;
    }
    public static TimerTicket getTimer(String name){
        for(TimerTicket ticket : tickets){
            if(ticket.name.equals(name)){
                return ticket;
            }
        }
        Debug.error("Timer ticket not found '" + name + "'");
        return null;
    }
}
//Private
class TimerTicket {

    String name;
    long start;

    public TimerTicket(String name, long start){
        this.name = name;
        this.start = start;
    }

}
