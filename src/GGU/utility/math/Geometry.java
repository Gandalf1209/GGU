package GGU.utility.math;

public class Geometry {

    public static double[] rotatePoint(double x, double z, double xPoint, double zPoint, double sin, double cos){

        double xDif = x - xPoint;
        double zDif = z - zPoint;

        double newX = (xDif * cos) + (zDif * sin);
        double newZ = (zDif * cos) - (xDif * sin);

        return new double []{newX, newZ};

    }


}
