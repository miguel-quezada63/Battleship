package sample.model;

import sample.utility.Orientation;
import sample.utility.Player;
import sample.utility.ShipType;

public class Game {
    private static final int X = 0;
    private static final int Y = 1;
    private static Board p1Board = new Board();
    private static Board p2Board = new Board();
    private static Player curPlayer;
    private static int turnNum;
    private static int[] curMove;
    private static boolean started = false;

    public static void init() {
        turnNum = 0; // Set turns back to 0
        curMove = new int[2];
        curPlayer = Player.P1; // set current player to Player 1
        p1Board.initGrid(); // reset p1's board
        p2Board.initGrid(); // reset p2's board
        setStarted(true);
    }

    public static void nextTurn() {
        curMove = new int[2]; // reset current move
        alternateCurPlayer();
        ++turnNum; // increase turn number
    }

    public static boolean placeShipOnBoard(int headRow, int headCol, ShipType shipType, Orientation orientation, boolean isP1){
        Board board = isP1 ? p1Board : p2Board;
        return board.placeShip(headRow, headCol, shipType, orientation);
    }

    public static boolean hitCell(){
        Board board = getOpponentBoard();
        Cell c =  board.getCellByCoord(curMove[0], curMove[1]); // get cell within the specified board in which the user clicked
        c.hit(); // hit that cell in the specified board
        // If a ship exists on this tile and this ship has been sunk, print to console and reduce availableShips
        if(c.getShip() != null && c.getShip().isSunk()) {
            System.out.println(c.getShip().getShipType() + " " + "has been sunk");
            board.sinkShip();
        }
        return c.getShip() != null && c.isHit();
    }

    public static void loadTurn(int turn, Player playerTurn){
        turnNum = turn;
        curPlayer = playerTurn;
    }

    public static Player getCurPlayer() {
        return curPlayer;
    }

    public static int getTurnNum() {
        return turnNum;
    }

    public static Board getPlayerBoard(){
        return curPlayer == Player.P1 ? p1Board : p2Board;
    }

    public static Board getOpponentBoard() {
        return curPlayer == Player.P1 ? p2Board : p1Board;
    }

    public static void setCurMove(int x, int y) {
        curMove[X] = x;
        curMove[Y] = y;
    }

    public static boolean detectWin(){
        return p1Board.getAvailableShips() <= 0 || p2Board.getAvailableShips() <= 0;
    }
    public static void setStarted(boolean started) {
        Game.started = started;
    }

    public static void alternateCurPlayer() {
        curPlayer = curPlayer == Player.P1 ? Player.P2 : Player.P1; // alternate players
    }

    public static Board getP1Board(){
        return p1Board;
    }

    public static Board getP2Board(){
        return p2Board;
    }

    public static Player getWinner(){
        if(!detectWin()) return null;
        return p2Board.getAvailableShips() <= 0 ? Player.P1 : Player.P2;
    }
}
