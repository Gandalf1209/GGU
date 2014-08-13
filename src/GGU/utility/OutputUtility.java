package GGU.utility;

public class OutputUtility {

    final static int TAB_LENGTH = 5;
    static int currentTab = 0;

    public static void increment(){
        currentTab += 1;
    }

    public static void deincrement(){
        currentTab -= 1;
        if(currentTab < 0){
            currentTab = 0;
        }
    }

    public static void outputLine(String line){
        String finalLine = "";

        for(int i = 0; i < currentTab*TAB_LENGTH; i++){
            finalLine += " ";
        }

        finalLine += line;

        System.out.println(finalLine);

    }

}
