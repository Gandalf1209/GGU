package GGU.utility.data.loaders.image;

import GGU.utility.data.loaders.LoadingTicket;

/**
 *
 */
public class ImageLoadingTicket extends LoadingTicket{

    String name, path;
    boolean load;

    public ImageLoadingTicket(String name, String path, boolean load){
        super(name, path);
        this.load = load;
    }

    public boolean loadIntoOpenGL(){
        return load;
    }

}
