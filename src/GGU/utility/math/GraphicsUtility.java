package GGU.utility.math;

public class GraphicsUtility {

    public static int[] splitColour(int colour){

        int[] pixel = new int[4];

        pixel[0] = ((colour>>24) & 0xFF);
        pixel[1] = ((colour>>16) & 0xFF);
        pixel[2] = ((colour>>8) & 0xFF);
        pixel[3] = (colour & 0xFF);

        return pixel;
    }
    public static int buildColour(int alpha, int red, int green, int blue){
        int colour = ((alpha & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
        return colour;

    }
    public static int[] applyAlpha(int colour, LookupTable table){
        int[] argb = splitColour(colour);
        double grad = table.alpha(argb[0]);

        return new int[]{
                (int)((double)argb[1] * grad),
                (int)((double)argb[2] * grad),
                (int)((double)argb[3] * grad),
        };
    }
    public static double interpolate(double a, double b, double grad){
        return (a * grad) + (b * (1 - grad));
    }

}
