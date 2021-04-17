package sample.model;

import sample.utility.ShipType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    public static void saveGame(File save) throws IOException {
        FileWriter fileWriter = new FileWriter(save);
        fileWriter.write("Turn Number: " + Game.getTurnNum() + "\n\n");
        fileWriter.write("Player Turn: " + Game.getCurPlayer() + "\n\n");
        fileWriter.write(Game.getCurPlayer() + " Board:" + "\n");
        for(int row = 1; row < 11; ++row){
            for(int col = 1; col < 11; ++col){
                Cell c = Game.getPlayerBoard().getGrid()[row][col];
                fileWriter.write("CELL: ");
                fileWriter.write("Row: " + c.getRow() + " Col: " + c.getCol() + "\n");
                fileWriter.write("IsHit: " + c.isHit());
                if(c.getShip() != null) {
                fileWriter.write(" Ship: " + c.getShip().getShipType() + "\n");
                fileWriter.write("Orientation: "+ c.getShip().getOrientation() + "\n");
                }
                else {
                    fileWriter.write(" Ship " + null + "\n");
                    fileWriter.write("Orientation: " + null + "\n");
                }
                fileWriter.write("\n");
            }
        }
        for(int row = 1; row < 11; ++row){
            for(int col = 1; col < 11; ++col){
                Cell c = Game.getOpponentBoard().getGrid()[row][col];
                fileWriter.write("CELL: ");
                fileWriter.write("Row: " + c.getRow() + " Col: " + c.getCol() + "\n");
                fileWriter.write("IsHit: " + c.isHit());
                if(c.getShip() != null) {
                    fileWriter.write(" Ship: " + c.getShip().getShipType() + "\n");
                    fileWriter.write("Orientation: "+ c.getShip().getOrientation() + "\n");
                }
                else {
                    fileWriter.write(" Ship " + null + "\n");
                    fileWriter.write("Orientation: " + null + "\n");
                }
                fileWriter.write("\n");
            }
        }
        fileWriter.close();
    }
}
