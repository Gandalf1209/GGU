package GGU.utility.data.fonts;

import GGU.utility.data.loaders.spritesheet.SpriteSheet;
import GGU.utility.data.opengl.OpenGLTexture;

/**
 * Game font class used to draw text in OpenGL
 *
 * SpriteSheet letters must ALL be the same width and height or stuff will happen...
 */
public class GameFont {

    String[] map;
    SpriteSheet sheet;

    public GameFont(String[] map, SpriteSheet sheet){
        this.map = map;
        this.sheet = sheet;
    }

    public OpenGLTexture getCharacter(char character) {

        for(int line = 0; line < map.length; line++){

            String list = map[line];

            for(int i = 0; i < list.length(); i++){

                char c = list.charAt(i);

                if(c == character){
                    return sheet.getSprite(i, line);
                }

            }

        }

        return sheet.getSprite(0, 0);

    }

    public OpenGLTexture[] getText(String text) {

        OpenGLTexture[] textures = new OpenGLTexture[text.length()];

        for(int i = 0; i < text.length(); i++){
            textures[i] = getCharacter(text.charAt(i));
        }

        return textures;

    }

}
