package sample.utility;

public enum ShipType{
    CARRIER("CARRIER",5),
    BATTLESHIP("BATTLESHIP", 4),
    CRUISER("CRUISER", 3),
    SUBMARINE("SUBMARINE",3),
    DESTROYER("DESTROYER",2);
    final private String type;
    final private int spaces;
    ShipType(String t, int s){
        type = t;
        spaces = s;
    }
    public String getType(){
        return type;
    }
    public int getSpaces() {
        return spaces;
    }
}
