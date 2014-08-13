package GGU.utility.parsers.ini;

/*

INIParameter class turns a string value into different fundamental datatypes depending on its declaration

Use: used by the INI section to hold value

 */

public class INIParameter {

    public static INIParameter NULLPARAMETER = new INIParameter("NULL", "NULL", INIParameterType.UNKNOWN);

    String name, value;
    INIParameterType type;

    int intValue;
    boolean boolValue;
    double doubleValue;

    public INIParameter(String name, String value, INIParameterType type){
        this.name = name;
        this.value = value;
        this.type = type;

        updateValues();
    }

    public void updateValues(){

        //Doesn't need to do anything in the case of a String

        switch(this.type){

            case INT:
                this.intValue = Integer.parseInt(this.value);
                break;

            case DECIMAL:
                this.doubleValue = Double.parseDouble(this.value);
                break;

            case BOOL:
                this.boolValue = Boolean.parseBoolean(this.value);
                break;
        }

    }

    public void setValue(String allValues){
        this.value = allValues;
        this.updateValues();
    }
    public void setValueType(INIParameterType type){
        this.type = type;
        this.updateValues();
    }

    public String getName(){
        return this.name;
    }
    //Every possible value type of parameter
    public String getStringValue(){

        if(type == INIParameterType.STRING){
            return this.value.substring(1, this.value.length()-1);
        }

        return this.value;
    }
    public int getIntegerValue(){
        return this.intValue;
    }
    public boolean getBooleanValue(){
        return this.boolValue;
    }
    public double getDoubleValue(){
        return this.doubleValue;
    }

}
