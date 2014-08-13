package GGU.utility.parsers;

/*

Parses strings into different datatypes

 */

public class StringParser {

    //Is th string bound in speech marks?
    public static boolean isString(String line){
        if(line.charAt(0) == '"' && line.charAt(line.length()-1) == '"'){
            return true;
        }
        return false;
    }
    public static boolean isInteger(String line){
        for(int i = 0; i < line.length(); i++){
            char element = line.charAt(i);

            if(isNumber(element) == false){
                if(i == 0 && element == '-' && line.length() > 1){
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    public static boolean isBoolean(String line){

        String upper = line.toUpperCase();

        if(upper.equals("TRUE") || upper.equals("FALSE")){
            return true;
        }
        return false;
    }
    public static boolean isDouble(String line){

        if(line.contains(".") == false){
            return false;
        }

        boolean decimal = false; // To stop multiple decimal points

        for(int i = 0; i < line.length(); i++){
            char element = line.charAt(i);

            if(isNumber(element) == false){
                if(i == 0 && element == '-' && line.length() > 1){
                    continue;
                }
                if(i != 0 && element == '.' && decimal == false && line.length() > (i+1)){
                    decimal = true;
                    continue;
                }
                return false;
            }
        }
        return true;
    }


    public static boolean isNumber(char element){
        switch(element){
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
            case '5':
                return true;
            case '6':
                return true;
            case '7':
                return true;
            case '8':
                return true;
            case '9':
                return true;

        }

        return false;
    }

}
