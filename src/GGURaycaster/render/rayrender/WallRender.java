package GGURaycaster.render.rayrender;

import GGURaycaster.data.map.Wall;
import GGURaycaster.render.RayIntersect;
import GGURaycaster.render.Raycaster;
import GGURaycaster.render.data.Camera;
import GGURaycaster.render.data.Screen;

import java.awt.image.BufferedImage;

//TODO optimise this class
public class WallRender {

    Raycaster raycaster;

    public WallRender(Raycaster raycaster){
        this.raycaster = raycaster;
    }

    public int[] projectWall(RayIntersect intercept, BufferedImage texture, double distance, int x){

        Screen screen = raycaster.getBuffer();
        Camera camera = raycaster.getCamera();

        int[] wall = this.getWallProjection(screen, camera, intercept.getWall(), distance);
        int wallY1 = wall[0];
        int wallY2 = wall[1];

        int textureWidth = texture.getWidth();
        int textureHeight = texture.getHeight();

        int pixelX = (int)(intercept.getIntercept() * (double)(textureWidth));
        if(pixelX >= textureWidth){
            pixelX = textureWidth-1;
        }

        int yDiff = wallY2 - wallY1;

        int screenY1 = wallY1;
        if(screenY1 < 0){
            screenY1 = 0;
        }
        int screenY2 = wallY2;
        if(screenY2 > screen.getHeight()){
            screenY2 = screen.getHeight();
        }

        for(int y = screenY1; y < screenY2; y++){

            int pixelY = (int)((((double)(y - wallY1))/(double)yDiff) * (textureHeight));
            if(pixelY >= textureHeight){
                pixelY = textureHeight-1;
            }

            int pixel = Shader.getColour(texture.getRGB(pixelX, pixelY), distance);

            screen.setPixel(x, y, pixel, distance);

        }

        return wall;
    }
    public int[] getWallProjection(Screen screen, Camera camera, Wall wall, double distance){

        double y1 = (wall.height + wall.position) - camera.getY();
        double y2 = wall.position - camera.getY();

        double newY1 = (y1 / distance) * camera.getProjectionDistance() * camera.getAspectRatio();
        double newY2 = (y2 / distance) * camera.getProjectionDistance() * camera.getAspectRatio();

        int wallY1 = (int)((((-newY1) + 1)/2) * ((double)screen.getHeight()));
        int wallY2 = (int)((((-newY2) + 1)/2) * ((double)screen.getHeight())) + 1;

        return new int[]{wallY1, wallY2};

    }

}
