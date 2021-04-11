package sample.model;

import sample.utility.Orientation;
import sample.utility.ShipType;


public class Board {
    Cell[][] grid = new Cell[10][10];

    public Board() {
        initGrid();
    }

    void initGrid() {
        Ship ship = new Ship(ShipType.CARRIER, Orientation.VERTICAL);
        for(int y = 0; y < 10; ++y){
            for(int x = 0; x < 10; ++x){
                if(x == 0 && y < ShipType.CARRIER.getSpaces())
                    grid[y][x] = new Cell(x, y, ship);
                else
                    grid[y][x] = new Cell(x, y);
            }
        }
    }

    public Cell getCellByCoord(int x, int y){
        return grid[y - 1][x - 1];
    }
}
