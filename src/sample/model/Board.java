package sample.model;

import sample.utility.Orientation;
import sample.utility.ShipType;

public class Board {
    final static int MAX_ROW = 10;
    final static int MAX_COL = 10;
    Cell[][] grid = new Cell[MAX_ROW][MAX_COL]; // Grid holding cells for game

    // Initialize our grid
    public Board() {
        initGrid();
    }

    // place empty cells throughout our grid with respective coordinates
    void initGrid() {
        for(int x = 0; x < 10; ++x) {
            for(int y = 0; y < 10; ++y) {
                grid[x][y] = new Cell(x, y);
            }
        }
    }
    // Return the respective cell based on x, y coordinate
    public Cell getCellByCoord(int x, int y){
        return grid[x - 1][y - 1];
    }
}
