package sample.model;

public class Cell {
    private Ship ship;
    private boolean isHit;
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.ship = null;
        this.isHit = false;
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, Ship ship) {
        this.ship = ship;
        this.isHit = false;
        this.x = x;
        this.y = y;
    }

    public void hit() {
        this.isHit = true;
        if(ship != null)
            ship.hit();
    }

    public Ship getShip() {
        return ship;
    }

    public boolean isHit() {
        return isHit;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
