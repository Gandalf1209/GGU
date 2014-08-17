package GGURaycaster.render.rayrender;

import GGU.utility.math.LookupTable;
import GGURaycaster.data.entity.Entity;
import GGURaycaster.render.Raycaster;
import GGURaycaster.render.data.Camera;
import GGURaycaster.render.data.Screen;

import java.awt.image.BufferedImage;

//TODO optimise this class
public class EntityRender {

    Raycaster raycaster;
    LookupTable table;

    double sin, cos;

    public EntityRender(Raycaster raycaster){

        this.raycaster = raycaster;
        this.table = raycaster.getTable();

    }

    public void updateAngle(double angle){
        this.sin = table.sin(angle);
        this.cos = table.cos(angle);
    }

    public void renderEntity(Entity entity){

        Camera camera = raycaster.getCamera();
        Screen screen = raycaster.getBuffer();

        double halfWidth = screen.getWidth() / 2;
        double halfHeight = screen.getHeight() / 2;

        double xDiff = entity.getX() - camera.getX();
        double zDiff = entity.getZ() - camera.getZ();
        double yDiff = -(entity.getY() - camera.getY());

        double xPos = (xDiff * cos) + (zDiff * sin);
        double zPos = (zDiff * cos) - (xDiff * sin);

        if(zPos >= 0){
            return;
        }

        zPos = -zPos;

        double xOnScreen = (xPos / zPos) * camera.getProjectionDistance();
        double yOnScreen = (yDiff / zPos) * camera.getAspectRatio();

        double width = entity.getWidth() / zPos;
        double height = (entity.getHeight() / zPos) * camera.getAspectRatio();

        int xPixel = (int)((xOnScreen + 1) * halfWidth);
        int yPixel = (int)((yOnScreen + 1) * halfHeight);

        int pixelWidth = (int)(width * halfWidth);
        int pixelHeight = (int)(height * halfHeight);

        int offsetX = -pixelWidth/2;
        int offsetY = -pixelHeight/2;

        BufferedImage image = entity.getRender();

        double xGrad = ((double)image.getWidth()) / ((double)pixelWidth);
        double yGrad = ((double)image.getHeight()) / ((double) pixelHeight);

        int x1 = xPixel - pixelWidth/2;
        int x2 = xPixel + pixelWidth/2;

        int y1 = yPixel - pixelHeight/2;
        int y2 = yPixel + pixelHeight/2;

        if(x1 < 0){
            x1 = 0;
        }
        if(x2 >= screen.getWidth()){
            x2 = screen.getWidth() - 1;
        }
        if(y1 < 0){
            y1 = 0;
        }
        if(y2 >= screen.getHeight()){
            y2 = screen.getHeight() - 2;
        }

        for(int posX = x1; posX < x2; posX++){

            int x = posX - xPixel + pixelWidth/2;
            int screenX = xPixel + x + offsetX;
            int pixX = (int)(xGrad * ((double)x));

            for(int posY = y1; posY < y2; posY++){

                int y = posY - yPixel + pixelHeight/2;
                int screenY = yPixel + y + offsetY;
                int pixY = (int)(yGrad * ((double)y));

                int colour = 0;

                if(entity.isAlphaEnabled() == true){
                    int tex = image.getRGB(pixX, pixY);
                    int alpha = (tex >> 24) & 0xFF;
                    colour = Shader.getColourBlend(Shader.getColour(tex, zPos), screenX, screenY, screen, table.alpha(alpha), this.table);
                }else{
                    colour = Shader.getColour(image.getRGB(pixX, pixY), zPos);
                }
                if(colour == 0){
                    continue;
                }
                screen.setPixel(screenX, screenY, colour, zPos);
            }
        }

    }

}
