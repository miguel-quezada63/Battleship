package sample.model;
import sample.utility.ShipType;

public class Ship {
    private ShipType shipType;
    private int availableSpaces;

    public Ship(ShipType shipType){
        this.shipType = shipType;
        this.availableSpaces = shipType.getSpaces();
    }

    public boolean isSunk(){
        return availableSpaces <= 0;
    }

    public void hit(){
        --availableSpaces;
    }

    public ShipType getShipType() {
        return shipType;
    }
}