package GGURaycaster.data.map;

import java.awt.image.BufferedImage;

public class Wall {

    BufferedImage texture;

    public final double height = 1;
    public final double position = 0;

    public Wall(BufferedImage texture){
        this.texture = texture;
    }

    public BufferedImage getWall(){
        return texture;
    }
}
