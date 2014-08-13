package GGURaycaster.render;

import GGURaycaster.data.map.Wall;

public class RayIntersect {

    Wall wall;

    double intercept;
    double x, z;
    double originX, originZ;
    double distance;

    public RayIntersect(Wall wall, double intercept, double x, double z, double originX, double originZ){
        this.wall = wall;
        this.intercept = intercept;
        this.x = x;
        this.z = z;
        this.originX = originX;
        this.originZ = originZ;

        this.distance = Math.sqrt(Math.pow(originX - x, 2) + Math.pow(originZ - z, 2));
    }

    public Wall getWall() {
        return wall;
    }

    public double getIntercept() {
        return intercept;
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public double getOriginX() {
        return originX;
    }

    public double getOriginZ() {
        return originZ;
    }

    public double getDistance() {
        return distance;
    }
}
