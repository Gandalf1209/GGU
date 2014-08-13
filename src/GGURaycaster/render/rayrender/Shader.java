package GGURaycaster.render.rayrender;

public class Shader {

    public static final double INTENSITY = 0.8;
    public static double VIEWDISTANCE  = 10;

    public static int getColour(int colour, double z){

        if(((colour >> 24) & 0xFF) == 0){
            return 0;
        }

        int newAlpha = (int)(0xFF * calculateShade(z));
        return (colour & 0x00FFFFFF) | (newAlpha << 24);

    }
    public static double calculateShade(double distance){

        if(distance > VIEWDISTANCE){
            return 0;
        }

        double grad = distance / VIEWDISTANCE;

        return (1 - grad)*(1 - grad);

    }
    public static void setViewDistance(double distance){
        VIEWDISTANCE = distance;
    }
}
