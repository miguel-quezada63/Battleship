package sample.model;

public class Cell {
    private Ship ship;
    private boolean isHit;
    private int row;
    private int col;

    public Cell(int x, int y) {
        this.ship = null;
        this.isHit = false;
        this.row = x;
        this.col = y;
    }

    public Cell(int x, int y, Ship ship) {
        this.ship = ship;
        this.isHit = false;
        this.row = x;
        this.col = y;
    }

    public void hit() {
        this.isHit = true;
        if(ship != null)
            ship.hit();
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean isHit() {
        return isHit;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
