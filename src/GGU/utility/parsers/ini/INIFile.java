package GGU.utility.parsers.ini;

import GGU.debug.Debug;

import java.util.ArrayList;

/*

INI file structure class

Use: used to hold lots of parameters.

 */

public class INIFile {

    String path;
    ArrayList<INISection> sections;

    public INIFile(){
        this.sections = new ArrayList<INISection>();
    }

    public void addSection(INISection section){
        this.sections.add(section);
    }

    public INISection getSection(String name){

        for(INISection section : sections){

            if(section.getName().equals(name)){
                return section;
            }

        }

        Debug.error("Section '" + name + "' does not exist!");

        return null;

    }
    public ArrayList<INISection> getSections(){
        return sections;
    }

}
