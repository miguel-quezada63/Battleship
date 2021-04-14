package sample.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    public static void saveGame(File save) throws IOException {
        FileWriter fileWriter = new FileWriter(save);
        fileWriter.write("Turn Number: " + Game.getTurnNum() + "\n");
        fileWriter.write("Player Turn: " + Game.getCurPlayer() + "\n");
        fileWriter.write("Player 1 Board:" + "\n");




        fileWriter.close();
    }
}
