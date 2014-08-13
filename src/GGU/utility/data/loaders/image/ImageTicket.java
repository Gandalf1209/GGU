package GGU.utility.data.loaders.image;

import GGU.utility.data.loaders.LoadingTicket;

import java.awt.image.BufferedImage;

/**
 * Ticket for images, used by the image list
 *
 * Use: used to match a name to an image when being retrieved from the engine
 */
public class ImageTicket extends LoadingTicket{

    BufferedImage image;
    boolean load;

    public ImageTicket(String name, String path, BufferedImage image, boolean load) {
        super(name, path);
        this.image = image;
        this.load = load;
    }

    public BufferedImage getImage(){
        return this.image;
    }
    public boolean loadIntoOpenGL(){
        return load;
    }
}
