package GGU.debug;

/**
 * Class used to help debug programs
 *
 * Use: Only handles error messages at the moment.
 *
 * Considering outputing lots of error information into a file, which is then sent to an email address (my bug-handling email address..)
 *
 */
public class Debug {
    public static void error(String message){
        System.err.println(message);
    }

}
