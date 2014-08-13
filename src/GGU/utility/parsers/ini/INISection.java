package GGU.utility.parsers.ini;

import GGU.debug.Debug;

import java.util.ArrayList;

public class INISection {

    String name;
    ArrayList<INIParameter> params;

    public INISection(String name){
        this.name = name;
        this.params = new ArrayList<INIParameter>();
    }

    public void addParameter(INIParameter param){
        this.params.add(param);
    }

    public INIParameter getParameter(String name){

        for(INIParameter param : params){
            if(param.getName().equals(name)){
                return param;
            }
        }

        Debug.error("INI Parameter '" + name + "' does not exist in section '" + this.name + "'");

        return INIParameter.NULLPARAMETER;

    }

    public String getName(){
        return this.name;
    }

    public ArrayList<INIParameter> getParams(){
        return this.params;
    }

}
