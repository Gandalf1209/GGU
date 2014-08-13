package GGU.utility.data.loaders.texture;

/*

Note: Must be called in the main thread and after ImageList has ran
Must only be used once!

 */

import GGU.debug.Debug;
import GGU.utility.data.loaders.image.ImageList;
import GGU.utility.data.loaders.image.ImageTicket;
import GGU.utility.data.opengl.OpenGLTexture;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TextureLoader {

    static ArrayList<Texture> textures;

    public static void load(ImageList list){

        textures = new ArrayList<Texture>();

        ArrayList<ImageTicket> tickets = list.getImageTickets();
        for(ImageTicket tick : tickets){

            if(tick.loadIntoOpenGL() == true){
                add(tick.getName(), tick.getImage());
            }

        }

    }

    public static void add(String name, BufferedImage image){
        textures.add(new Texture(name, new OpenGLTexture(image)));
    }

    public static OpenGLTexture getTexture(String name){
        for(Texture ticket : textures){
            if(ticket.getName().equals(name)){
                return ticket.getTexture();
            }
        }
        Debug.error("Texture '" + name + "' does not exist");
        return null;
    }

}
