package sample.utility;

public enum ShipType{
    CARRIER("CARRIER",5, "black"),
    BATTLESHIP("BATTLESHIP", 4, "lime"),
    CRUISER("CRUISER", 3, "coral"),
    SUBMARINE("SUBMARINE",3, "aqua"),
    DESTROYER("DESTROYER",2, "violet");
    final private String type;
    final private int spaces;
    final private String color;
    ShipType(String t, int s, String c){
        type = t;
        spaces = s;
        color = c;
    }
    public String getType(){
        return type;
    }
    public int getSpaces() {
        return spaces;
    }
    public String getColor(){
        return color;
    }
}
