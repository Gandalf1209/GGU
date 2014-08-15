package GGURaycaster.data.map;

import java.awt.image.BufferedImage;

public class Wall {

    BufferedImage texture;

    public double height = 1;
    public double position = 0;

    public Wall(BufferedImage texture){
        this.texture = texture;
    }

    public void setHeight(double height){
        this.height = height;
    }
    public void setPosition(double position){
        this.position = position;
    }

    public BufferedImage getWall(){
        return texture;
    }

}
