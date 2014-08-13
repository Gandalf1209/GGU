package GGU.utility;

import GGU.debug.Debug;

import java.io.*;
import java.util.ArrayList;

/*

File utility class for reading, writing, deleting and creating files.

 */

public class FileUtility {

    //Returns null if file does not exist
    public static ArrayList<String> readFile (String path) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
            ArrayList<String> contents = new ArrayList<String>();

            String data = "";

            while((data = reader.readLine()) != null){
                contents.add(data);
            }

            reader.close();

            return contents;

        }catch(Exception e){

            Debug.error("File not found '" + path + "'");
            return null;

        }

    }

    public static void writeFile (String path, ArrayList<String> contents) {

        if(doesFileExist(path) == false){
            createFile(path);
        }

        File file = new File(path);

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for(String data : contents){
                writer.write(data);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean doesFileExist(String path){
        return new File(path).exists();
    }

    public static void createFile(String path){
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            Debug.error("Could not create file: " + path);
        }
    }

    public static void deleteFile(String path){
        File file = new File(path);
        file.delete();
    }

}
