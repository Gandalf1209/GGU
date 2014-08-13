package GGU.utility.parsers.ini;

import GGU.debug.Debug;
import GGU.utility.FileUtility;
import GGU.utility.parsers.StringParser;

import java.util.ArrayList;

/*

INIParser - it's in the name - Parses INI files into the structure that is used by the program.

Use: used by the Configuration class to load the engine configuration using an INI file.

 */

public class INIParser {

    public static INIFile parseFile(ArrayList<String> contents){
        INIFile file = new INIFile();

        INISection section = null;

        for(String line : contents){

            //String line = data.replaceAll("\\s", "");

            if(isLine(line) == false){
                continue;
            }
            if(isComment(line) == true){
                continue;
            }

            if(isSection(line) == true){
                section = addSection(file, line);
                continue;
            }
            if(isParameter(line) == true){

                if(section == null){
                    Debug.error("Parameter defined before section in INI file '" + line + "'");
                }

                addParameter(file, line, section);
                continue;
            }

        }

        return file;
    }
    public static void saveFile(INIFile file, String path){

        FileUtility.createFile(path);

        ArrayList<String> contents = new ArrayList<String>();

        for(INISection section : file.getSections()){

            String sectionOutput = "[" + section.getName() + "]";
            contents.add(sectionOutput);

            for(INIParameter parameter : section.getParams()){

                String parameterOutput = parameter.getName() + "=" + parameter.getStringValue();
                contents.add(parameterOutput);

            }

        }

        FileUtility.writeFile(path, contents);
    }

    private static INISection addSection(INIFile file, String line){

        String name = line.substring(1, line.length()-1);
        INISection section = new INISection(name);
        file.addSection(section);

        return section;
    }
    private static void addParameter(INIFile file, String line, INISection section){

        String[] data = line.split("=");

        String name = data[0];
        String value = data[1];

        INIParameterType type = INIParameterType.UNKNOWN;

        if(StringParser.isBoolean(value)){
            type = INIParameterType.BOOL;
        }
        if(StringParser.isInteger(value)){
            type = INIParameterType.INT;
        }
        if(StringParser.isDouble(value)){
            type = INIParameterType.DECIMAL;
        }
        if(StringParser.isString(value)){
            type = INIParameterType.STRING;
        }

        if(type == INIParameterType.UNKNOWN) {
            Debug.error("'" + line + "' references UNKNOWN datatype. Only INTs, BOOLs, DECIMALs(double) and Strings (in speech marks) are supported.");
        }

        INIParameter param = new INIParameter(name, value, type);
        section.addParameter(param);

    }

    //Type checks
    public static boolean isSection(String line){

        if(line.charAt(0) == '[' && line.charAt(line.length() - 1) == ']'){
            return true;
        }

        return false;
    }
    public static boolean isParameter(String line){
        if(line.contains("=") == false){
            return false;
        }

        String[] split = line.split("=");
        if(split.length == 2){
            return true;
        }

        return false;
    }
    public static boolean isComment(String line){

        if(line.charAt(0) == ';'){
            return true;
        }
        return false;
    }
    public static boolean isLine(String line){
        if(line.length() > 0){
            return true;
        }
        return false;
    }
}
