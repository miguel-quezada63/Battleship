package sample.model;

import sample.utility.Orientation;
import sample.utility.ShipType;


public class Board {
    final static int MAX_ROW = 11;
    final static int MAX_COL = 11;
    private Cell[][] grid = new Cell[MAX_ROW][MAX_COL]; // Grid holding cells for game
    private int shipsToPlace = 5;

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

    void loadGrid(int row, int col, boolean isHit, Ship ship)
    {
        grid[row][col] = new Cell(row,col, isHit, ship);
    }

    // get the grid of cells
    public Cell[][] getGrid() { return this.grid; }

    // Return the respective cell based on row and col
    public Cell getCellByCoord(int row, int col){
        return grid[row][col];
    }

    // place a ship on the board
    public boolean placeShip(int headRow, int headCol, ShipType shipType, Orientation orientation) {
        Cell[][] gridCopy = createGridCopy();
        Ship ship = new Ship(shipType);
        if(orientation.equals(Orientation.HORIZONTAL)) {
            for (int col = headCol; col < headCol + shipType.getSpaces(); ++col) {
                Cell c = gridCopy[headRow][col];
                if(c.getShip() != null) return false;
                c.setShip(ship);
            }
        } else {
            for (int row = headRow; row < headRow + shipType.getSpaces(); ++row) {
                Cell c = gridCopy[row][headCol];
                if(c.getShip() != null) return false;
                c.setShip(ship);
            }
        }
        grid = gridCopy;
        --shipsToPlace;
        return true;
    }

    private Cell[][] createGridCopy(){
        Cell[][] gridCopy = new Cell[MAX_ROW][MAX_COL];
        for(int row = 1; row < 11; ++row) {
            for(int col = 1; col < 11; ++col) {
                gridCopy[row][col] = new Cell(row, col, grid[row][col].getShip());
            }
        }
        return gridCopy;
    }

    public int getShipsToPlace() {
        return this.shipsToPlace;
    }

}
