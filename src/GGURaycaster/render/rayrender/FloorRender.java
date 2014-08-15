package GGURaycaster.render.rayrender;

import GGU.utility.OutputUtility;
import GGU.utility.math.TrigTable;
import GGURaycaster.data.map.Map;
import GGURaycaster.data.map.Tile;
import GGURaycaster.render.Raycaster;
import GGURaycaster.render.data.Camera;
import GGURaycaster.render.data.Screen;

import java.awt.image.BufferedImage;

public class FloorRender {

    Raycaster raycaster;
    TrigTable math;

    public FloorRender(Raycaster raycaster){
        this.raycaster = raycaster;
        this.math = raycaster.getTable();
    }

    public void renderPlanes(int x, int wallY1, int wallY2){

        Screen screen = raycaster.getBuffer();
        Camera camera = raycaster.getCamera();
        Map map = raycaster.getMap();

        double sin = math.sin(camera.getAngle());
        double cos = math.cos(camera.getAngle());

        double screenWidth = screen.getWidth();
        double xPos = ((((double)x)/screenWidth) - 0.5) * 2;
        double xGrad = xPos / camera.getProjectionDistance();

        if(camera.getY() < map.getCeilingHeight() && map.hasCeiling()){
            renderPlane(screen, camera, map, 0, wallY1, x, xGrad, sin, cos, true);
        }
        if(camera.getY() > 0){
            renderPlane(screen, camera, map, wallY2, screen.getHeight(), x, xGrad, sin, cos, false);
        }

    }
    public void renderPlane(Screen screen, Camera camera, Map map, int wallY1, int wallY2, int x, double xGrad, double sin, double cos, boolean ceiling){

        //Pre-computed constants - Ignore these
        double halfheight = screen.getHeight() / 2;
        double aspect = halfheight * camera.getAspectRatio();
        double inverse = 1 / camera.getAspectRatio();
        double projection = camera.getProjectionDistance();
        double ceilingoffset = map.getCeilingHeight() - camera.getY();
        double cameraX = camera.getX();
        double cameraY = camera.getY();
        double cameraZ = camera.getZ();

        int startY = wallY1;
        int endY = wallY2;

        if(ceiling == true){
            endY = (int)halfheight;
        }else{
            startY = (int)halfheight;
        }

        ///////////////////////////////////////////
        //Disgusting for optimisation reasons

        //Floor
        for(double i = startY; i < endY; i++){



            double pos = ((i / aspect) - inverse);
            double grad = projection / pos;

            double distance;
            if(ceiling == true){
                distance = -(grad * ceilingoffset);
            }else{
                distance = (grad * cameraY);
            }

            double zPixel = distance;

            double xOff = (xGrad * distance);
            double yOff = distance;

            double newDifX = ((xOff * cos) + (yOff * sin));
            double newDifY = - ((yOff * cos) - (xOff * sin));

            double xTile = cameraX + newDifX;
            double yTile = cameraZ + newDifY;

            if(xTile < 0){
                continue;
            }
            if(yTile < 0){
                continue;
            }

            Tile tile = map.getTile((int)xTile, (int)yTile);

            if(tile == null){
                continue;
            }


            BufferedImage texture;
            if(ceiling == true){
                texture = tile.getCeiling();
            }else{
                texture = tile.getFloor();
            }

            int texX = (int)(((xTile - (int)xTile)) * ((double)texture.getWidth() - 1));
            int texY = (int)(((yTile - (int)yTile)) * ((double)texture.getHeight() - 1));

            int pix = Shader.getColour(texture.getRGB(texX, texY), distance);


            screen.setPixel(x, (int)i, pix, zPixel);

        }
    }

}
