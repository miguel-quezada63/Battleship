
package sample.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import sample.model.Load;

public class LoadController {
    @FXML
    private Button loadGameBtn;

    @FXML
    void initialize() {
        loadGameBtn.setOnAction(e-> {
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/game.fxml"));
                Scene s = loadGameBtn.getScene();
                s.setRoot(newRoot);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Load.loadGame();
            System.out.println("LOAD GAME!");
        });
    }
}


