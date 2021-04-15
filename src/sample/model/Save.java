package sample.model;

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
                fileWriter.write("IsHit: " + c.isHit() + " Ship: " + c.getShip() + "\n");
                fileWriter.write("\n");
            }
        }
        for(int row = 1; row < 11; ++row){
            for(int col = 1; col < 11; ++col){
                Cell c = Game.getOpponentBoard().getGrid()[row][col];
                fileWriter.write("CELL: ");
                fileWriter.write("Row: " + c.getRow() + " Col: " + c.getCol() + "\n");
                fileWriter.write("IsHit: " + c.isHit() + " Ship: " + c.getShip() + "\n");
                fileWriter.write("\n");
            }
        }
        fileWriter.close();
    }
}
