
package web;

public class Parameter {
private String name;
private int value;

    public Parameter(String name, String value) {
    this.name = name;
    this.value = Integer.parseInt(value);           
    }

    public String getName(){
    return name;    
    }
    
    public int getValue (){
    return value;
    }
     
    public String printParameter (){
    return "<li>" + name +" - " + value + "</li>";
    }

    @Override
    public String toString() {
        return name + "   "+value;
    }
    
}