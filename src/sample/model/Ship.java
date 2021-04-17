package sample.model;
import sample.utility.Orientation;
import sample.utility.ShipType;

public class Ship {
    private ShipType shipType;
    private int availableSpaces;
    Orientation orientation;

    public Ship(ShipType shipType, Orientation orientation){
        this.shipType = shipType;
        this.orientation = orientation;
        this.availableSpaces = shipType.getSpaces();
    }

    public boolean isSunk(){
        return availableSpaces <= 0;
    }

    public void hit(){
        --availableSpaces;
    }

    public ShipType getShipType() {return shipType; }

    public Orientation getOrientation() { return orientation; }
}