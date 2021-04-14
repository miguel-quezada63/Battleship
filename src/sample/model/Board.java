package sample.model;

import sample.utility.Orientation;
import sample.utility.ShipType;

public class Board {
    final static int MAX_ROW = 11;
    final static int MAX_COL = 11;
    Cell[][] grid = new Cell[MAX_ROW][MAX_COL]; // Grid holding cells for game

    // Initialize our grid
    public Board() {
        initGrid();
    }

    // place empty cells throughout our grid with respective coordinates
    void initGrid() {
        for(int row = 1; row < 11; ++row) {
            for(int col = 1; col < 11; ++col) {
                grid[row][col] = new Cell(row, col);
            }
        }
    }
    // Return the respective cell based on row and col
    public Cell getCellByCoord(int row, int col){
        return grid[row][col];
    }

    public Cell[][] getGrid() { return this.grid; }
}
