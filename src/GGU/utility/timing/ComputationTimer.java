package GGU.utility.timing;

import GGU.utility.OutputUtility;

import java.util.ArrayList;

//Average timer, mainly used for program optimisation (hence the name)
public class ComputationTimer {

    static ArrayList<ComputationTicket> tickets;

    //begin timing
    public static void startLog(String name){
        if(tickets == null){
            tickets = new ArrayList<ComputationTicket>();
        }
        if(getLogObject(name) == null){
            tickets.add(new ComputationTicket(name));
        }

        Timer.startTimer("COMPUTATION - " + name);
    }
    //end timing and log average
    public static void recordLog(String name){
        long value = Timer.endTimer("COMPUTATION - " + name);
        if(value > Integer.MAX_VALUE){
            OutputUtility.outputLine("-Timer Error-");
            OutputUtility.increment();
            OutputUtility.outputLine("Computation timer '" + name + "' recorded value exceeds maximum value '" + Integer.MAX_VALUE + "'");
            OutputUtility.outputLine("Consider creating sub-timers or making sure you're ending each timer you start");
            OutputUtility.deincrement();
            return;
        }
        ComputationTicket ticket = getLogObject(name);
        if(ticket == null){
            OutputUtility.outputLine("-Timer Error-");
            OutputUtility.increment();
            OutputUtility.outputLine("Computation timer '" + name + " does not exist");
            OutputUtility.deincrement();
            return;
        }

        ticket.log(value);
    }
    private static ComputationTicket getLogObject(String name){
        for(ComputationTicket ticket : tickets){
            if(ticket.name.equals(name)){
                return ticket;
            }
        }
        return null;
    }
    public static double getLog(String name){
        ComputationTicket ticket = getLogObject(name);
        if(ticket != null){
            return ticket.time;
        }
        return -1;
    }
    public static void endAll(){

        if(tickets == null){
            return;
        }

        OutputUtility.outputLine("-Computation Timers-");
        OutputUtility.increment();
        for(ComputationTicket ticket : tickets){
            OutputUtility.outputLine("'" + ticket.name + "'");
            OutputUtility.increment();
            OutputUtility.outputLine("Average Time: " + ticket.time);
            OutputUtility.outputLine("Amount Of Recordings: " + ticket.recordings);
            OutputUtility.deincrement();
        }
        OutputUtility.deincrement();
    }

}
class ComputationTicket {

    String name;
    double time; //Overall, average computation time
    int recordings; //Amount of recordings to acquire this number

    public ComputationTicket(String name){
        this.name = name;
        this.recordings = 0;
        this.time = 0;
    }

    public void log(double value){
        if(recordings == Integer.MAX_VALUE - 1){
            return;
        }

        recordings++;
        double grad = 1 / ((double)recordings);
        double negGrad = 1 - grad;

        time = (negGrad * time) + (grad * value);

    }

}