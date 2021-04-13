package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import sample.model.Load;
import sample.model.Save;

import java.io.IOException;

public class SaveController {
    @FXML
    private Button saveGameBtn;

    @FXML
    void initialize() {
        saveGameBtn.setOnAction(e-> {
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/game.fxml"));
                Scene s = saveGameBtn.getScene();
                s.setRoot(newRoot);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("SAVE GAME!");
            Save.saveGame();
        });
    }
}
