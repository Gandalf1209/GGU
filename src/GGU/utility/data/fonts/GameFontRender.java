package GGU.utility.data.fonts;

import GGU.utility.data.GameData;
import GGU.utility.data.opengl.OpenGLTexture;

public class GameFontRender {

    final static float CHAR_HEIGHT_RATIO = 2f;
    static GameFont DEFAULT_FONT;
    static GameFont USED_FONT;

    static float r, g, b, a;

    public static void load(){

        DEFAULT_FONT = new GameFont(
                new String[]
                        {
                        "¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬",
                        "¬¬¬¬¬¬¬¬¬¬¬¬¬¬£¬",
                        " !'#$%&'()*+,-./",
                        "0123456789:;<=>?",
                        "@ABCDEFGHIJKLMNO",
                        "PQRSTUVWXYZ[/]^_",
                        "'abcdefghijklmno",
                        "pqrstuvwxyz{|}~¬",
                        }
                ,
                GameData.getSpriteSheetList().getSheet("SHEET_FONT")
        );

        USED_FONT = DEFAULT_FONT;

        r = 1;
        g = 1;
        b = 1;
        a = 1;
    }

    public static void renderText(String text, float x, float y, float width, float z){

        OpenGLTexture[] textures = USED_FONT.getText(text);

        float char_width = width/((float)textures.length);
        float char_height = char_width * CHAR_HEIGHT_RATIO;

        for(int i = 0; i < textures.length; i++){
            float posX = x + (i * char_width);
            float posY = y;
            textures[i].setRGBAOverlay(r, g, b, a);
            textures[i].draw(posX, posY, char_width, char_height, z);
        }

    }

    public static void setFont(GameFont font){
        USED_FONT = font;
    }
    public static void setColour(float r, float g, float b, float a){
        GameFontRender.r = r;
        GameFontRender.g = g;
        GameFontRender.b = b;
        GameFontRender.a = a;
    }
}
