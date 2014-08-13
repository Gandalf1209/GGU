package GGURaycaster.render.raycaster;

import GGU.utility.math.TrigTable;
import GGURaycaster.data.map.Map;
import GGURaycaster.data.map.Tile;
import GGURaycaster.render.RayIntersect;
import GGURaycaster.render.Raycaster;

public class WallFinder {

    Map map;
    TrigTable math;

    final int MAX_LOOPS;

    public WallFinder(Raycaster raycaster){
        this.map = raycaster.getMap();
        this.math = raycaster.getTable();

        //this.MAX_LOOPS = (int)Math.ceil(Math.sqrt((this.map.getWidth()*this.map.getWidth()) + (this.map.getHeight()*this.map.getHeight())));
        //this.MAX_LOOPS = 10;
        this.MAX_LOOPS = (int)Math.ceil(raycaster.getCamera().getViewDistance()) + 1;
    }

    public RayIntersect fireRay(double x, double z, double angle){

        double tan = math.tan(angle);

        RayIntersect vert = fireVerticleRay(x, z, angle, tan);
        RayIntersect horiz = fireHorizontalRay(x, z, angle, tan);

        boolean vertValid = validRay(vert);
        boolean horizValid = validRay(horiz);

        if(vertValid == false){
            if(horizValid == true) {
                return horiz;
            }else{
                return null;
            }
        }
        if(horizValid == false){
            return vert;
        }

        if(vert.getDistance() < horiz.getDistance()){
            return vert;
        }
        return horiz;

    }
    public boolean validRay(RayIntersect intersect){
        if(intersect == null || Double.isNaN(intersect.getX()) || Double.isNaN(intersect.getZ()) ||  intersect.getDistance() == 0){
            return false;
        }
        return true;
    }
    public RayIntersect fireVerticleRay(double x, double z, double angle, double tan){

        if(angle == Math.PI/2 || angle == (Math.PI*2) - (Math.PI/2)){
            return null;
        }

        boolean upRay = (math.cos(angle) > 0);

        double aY = (int)z;             if(!upRay) aY++;
        double aX = (-tan*(aY - z)) + x;

        double trajY = -1;              if(!upRay) trajY = 1;
        double trajX = (-tan * trajY);

        int tileOffset = -1;            if(!upRay) tileOffset = 0;

        for(int i = 0; i < MAX_LOOPS; i++){

            double posX = aX + (i * trajX);
            double posY = aY + (i * trajY);

            if(posX < 0){
                break;
            }

            Tile tile = map.getTile(posX, posY + tileOffset);

            if(tile == null){
                break;
            }
            if(tile.isSolid() == false || (posX == x && posY == z)){
                continue;
            }

            double intercept = posX - (int)posX;

            if(trajY > 0){
                intercept = 1 - intercept;
            }

            return new RayIntersect(tile.getVertWall(1), intercept, posX, posY, x, z);

        }

        return null;

    }
    public RayIntersect fireHorizontalRay(double x, double z, double angle, double tan){

        if(angle == 0 || angle == Math.PI){
            return null;
        }

        boolean rightRay = (math.sin(angle) > 0);

        double aX = (int)x;             if(rightRay) aX++;
        double aY = ((aX - x) / -tan) + z;

        double trajX = -1;              if(rightRay) trajX = 1;
        double trajY = -trajX / tan;

        int tileOffset = -1;            if(rightRay) tileOffset = 0;

        for(int i = 0; i < MAX_LOOPS; i++){

            double posX = aX + (i * trajX);
            double posY = aY + (i * trajY);

            if(posY < 0){
                break;
            }

            Tile tile = map.getTile(posX + tileOffset, posY);

            if(tile == null){
                break;
            }
            if(tile.isSolid() == false || (posX == x && posY == z)){
                continue;
            }

            double intercept = posY - (int)posY;

            if(trajX < 0){
                intercept = 1 - intercept;
            }

            return new RayIntersect(tile.getHorizWall(1), intercept, posX, posY, x, z);

        }

        return null;

    }

}
