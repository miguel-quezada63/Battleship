package sample.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    public static void saveGame(File save) throws IOException {
        FileWriter myWriter = new FileWriter(save);
        myWriter.write("Files in Java might be tricky, but it is fun enough!");
        myWriter.close();
    }
}
