package GGU.utility.data.loaders.image;

import GGU.utility.OutputUtility;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*

Loads all the needed images in the game

 */

public class ImageList{

    static String IMAGE_BASE = "data/resources/";
    ArrayList<ImageTicket> loaded;
    ArrayList<ImageLoadingTicket> toLoad;


    public ImageList(){
        init();
    }

    public void init(){
        this.loaded = new ArrayList<ImageTicket>();
        this.toLoad = new ArrayList<ImageLoadingTicket>();
    }
    public void load(){

        for(ImageLoadingTicket ticket : toLoad){
            loadTicket(ticket);
        }
    }
    public void loadTicket(ImageLoadingTicket ticket) {

        BufferedImage image = ImageLoader.getBufferedImage(IMAGE_BASE + ticket.getPath());
        ImageTicket imageTicket = new ImageTicket(ticket.getName(), IMAGE_BASE + ticket.getPath(), image, ticket.loadIntoOpenGL());

        this.loaded.add(imageTicket);

        OutputUtility.outputLine("Successfully Loaded: " + imageTicket.getName() + " '" + imageTicket.getPath() + "'");

    }

    public void add(String name, String path, boolean load){

        this.toLoad.add(new ImageLoadingTicket(name, path, load));

    }

    public ArrayList<ImageTicket> getImageTickets(){
        return loaded;
    }
    public BufferedImage getImage(String name){
        for(ImageTicket ticket : this.getImageTickets()){
            if(ticket.getName().equals(name)){
                return ticket.getImage();
            }
        }
        return null;
    }
}
