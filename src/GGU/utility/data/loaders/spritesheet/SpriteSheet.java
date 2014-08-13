package GGU.utility.data.loaders.spritesheet;

import GGU.utility.data.opengl.OpenGLTexture;

/**
 * Created by Admin on 18/06/2014.
 */
public class SpriteSheet {

    OpenGLTexture source;
    int sheet_width, sheet_height;

    OpenGLTexture[][] sheet;

    //Width of spritesheet and height of spritesheet
    public SpriteSheet(OpenGLTexture texture, int width, int height){
        this.source = texture;
        this.sheet_width = width;
        this.sheet_height = height;

        calculate();
    }
    public void calculate(){
        sheet = new OpenGLTexture[sheet_width][sheet_height];

        float sprite_width = 1/(float)sheet_width;
        float sprite_height = 1/(float)sheet_height;

       for(int x = 0; x < sheet_width; x++){
            for(int y = 0; y < sheet_height; y++){

                float xPos = x*sprite_width;
                float yPos = y*sprite_height;

                sheet[x][y] = new OpenGLTexture(
                        this.source.getID(),
                        xPos, yPos,
                        xPos + sprite_width, yPos,
                        xPos + sprite_width, yPos + sprite_height,
                        xPos, yPos + sprite_height
                );

            }
        }
    }

    public OpenGLTexture getSprite(int x, int y){
        return sheet[x][y];
    }

    public OpenGLTexture[] getColumn(int x){
        return sheet[x];
    }
    public OpenGLTexture[] getRow(int y){
        OpenGLTexture[] row = new OpenGLTexture[this.sheet_width];
        for(int x = 0; x < this.sheet_width; x++){
            row[x] = this.getSprite(x, y);
        }
        return row;
    }
}
