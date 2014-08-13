package GGURaycaster.render.raycaster;

import GGURaycaster.render.Raycaster;
import GGURaycaster.render.data.Screen;

import java.awt.image.BufferedImage;

public class RaycasterDebug {

    public static BufferedImage getDepthRender(Raycaster raycaster){

        Screen screen = raycaster.getBuffer();
        BufferedImage render = new BufferedImage(screen.getWidth(), screen.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int width = screen.getWidth();
        int height = screen.getHeight();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){

                double depth = screen.getDepth(x, y) + 1;
                if(depth < 1){
                    continue;
                }

                int red = (int)(0xFF / depth);
                int colour = (0xFF << 24) | (red << 16);
                render.setRGB(x, y, colour);

            }
        }

        return render;

    }

}
