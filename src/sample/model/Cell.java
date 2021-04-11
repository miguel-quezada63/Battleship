package sample.model;

public class Cell {
    private Ship ship = null;
    private boolean isHit;
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.ship = null;
        this.isHit = false;
        this.x = x;
        this.y = y;
    }

    public Cell(Ship ship, int x, int y) {
        this.ship = ship;
        this.isHit = false;
        this.x = x;
        this.y = y;
    }

    public void hit() {
        this.isHit = true;
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
