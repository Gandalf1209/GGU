package GGU.utility.data.loaders;

/**
 * Base loading ticket, used to hold an ID (name) and path
 */
public class LoadingTicket {

    String name, path;

    public LoadingTicket(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public boolean isName(String is){
        return name.equals(is);
    }

}
