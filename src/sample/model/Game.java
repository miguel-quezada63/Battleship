package sample.model;

public class Game {
    private Board youBoard;
    private Board oppBoard;

    public Game(){
        youBoard = new Board();
        oppBoard = new Board();
    }

    public boolean hitCell(int x, int y, Board board){
        Cell c =  board.getCellByCoord(x, y);
        c.hit();
        if(c.getShip() != null && c.getShip().isSunk())
            System.out.println(c.getShip().getShipType() + " " + "has been sunk");
        return c.isHit() && c.getShip() != null;
    }

    public Board getYouBoard() {
        return youBoard;
    }

    public Board getOppBoard() {
        return oppBoard;
    }


}
