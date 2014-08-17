package GGURaycaster.render.rayrender;

import GGU.utility.math.GraphicsUtility;
import GGU.utility.math.LookupTable;
import GGURaycaster.render.data.Screen;

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
    public static int getColourBlend(int colour, int x, int y, Screen screen, double alpha, LookupTable table){


        int[] screenRGB = GraphicsUtility.applyAlpha(screen.getPixel(x, y), table);
        int[] colourRGB = GraphicsUtility.applyAlpha(colour, table);

        return GraphicsUtility.buildColour(
                0xFF,
                (int)GraphicsUtility.interpolate(colourRGB[0], screenRGB[0], alpha),
                (int)GraphicsUtility.interpolate(colourRGB[1], screenRGB[1], alpha),
                (int)GraphicsUtility.interpolate(colourRGB[2], screenRGB[2], alpha)
        );

        /*
        double grad = (double)((screenPix >> 24) & 0xFF) / 255;
        int red = (int)((double)((screenPix >> 16) & 0xFF) * grad);
        int green = (int)((double)((screenPix >> 8) & 0xFF) * grad);
        int blue = (int)((double)(screenPix & 0xFF) * grad);

        int[] split = GraphicsUtility.splitColour(colour);
        double interp = ((double)split[0]) / 255;

        return GraphicsUtility.buildColour(
                0xFF,
                (int)GraphicsUtility.interpolate(split[1], red, interp),
                (int)GraphicsUtility.interpolate(split[2], green, interp),
                (int)GraphicsUtility.interpolate(split[3], blue, interp)
        );

        */

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
