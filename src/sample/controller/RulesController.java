package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import sample.model.Load;

import java.io.IOException;

public class RulesController {
    @FXML
    private Button closeRules;

    @FXML
    void initialize() {
        closeRules.setOnAction(e-> {
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/game.fxml"));
                Scene s = closeRules.getScene();
                s.setRoot(newRoot);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("RULES CLOSED!");
            Load.loadGame();
        });
    }
}
