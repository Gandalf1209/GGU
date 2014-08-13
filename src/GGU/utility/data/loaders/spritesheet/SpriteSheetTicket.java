package GGU.utility.data.loaders.spritesheet;

import GGU.utility.data.loaders.texture.TextureLoader;

/**
 * Created by Admin on 18/06/2014.
 */
public class SpriteSheetTicket {

    String texture;
    int width, height;

    SpriteSheet sheet;

    public SpriteSheetTicket(String texture, int width, int height){
        this.texture = texture;
        this.width = width;
        this.height = height;
    }

    public void load(){
        this.sheet = new SpriteSheet(TextureLoader.getTexture(texture), this.width, this.height);
    }

    public SpriteSheet getSheet(){
        return sheet;
    }

    public String getName(){
        return texture;
    }

}
