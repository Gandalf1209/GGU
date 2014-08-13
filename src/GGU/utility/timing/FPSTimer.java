package GGU.utility.timing;

/**
 * Used to count FPS and change values accordingly.
 *
 * This is important because it keeps the speed of the game running at the same pace when the FPS is lower than the proper amount.
 * For instance, characters in the game will move at the same pace (faster in the actual game) when the FPS is lower than the needed amount.
 *
 */
public class FPSTimer {

    static double currentFPS, targetFPS;
    final static double multiplier = 1; //In case target FPS is lowered at some point during development

    public static void setTargetFPS(int FPS){
        FPSTimer.targetFPS = FPS;
    }
    public static void setCurrentFPS(int FPS){
        FPSTimer.currentFPS = FPS;
    }

    public static double getMultiplier(){
        return (targetFPS/currentFPS) * multiplier;
    }

    public static void startTimer(){
        Timer.startTimer("FPS Timer");
    }

    public static void endTimer(){
        long nanotime = Timer.endTimer("FPS Timer");
        long FPS = 1000000000/nanotime;

        FPSTimer.currentFPS = (double)FPS;

    }

    public static double getFPS(){
        return FPSTimer.currentFPS;
    }

}
