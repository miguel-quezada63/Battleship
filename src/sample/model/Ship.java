package sample.model;
import sample.utility.ShipType;
import sample.utility.Orientation;

public class Ship {
    private ShipType shipType;
    private Orientation orientation;
    private int availableSpaces;

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

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void SetOrientationH(){this.orientation = Orientation.HORIZONTAL;}

    public void SetOrientationV(){this.orientation = Orientation.VERTICAL;}

    public Orientation getOrientation() {
        return orientation;
    }

    public ShipType getShipType() {
        return shipType;
    }
}