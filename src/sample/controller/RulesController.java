package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;

public class RulesController {
    @FXML
    private Button rulesCloseBtn;

    @FXML
    void initialize() {
        rulesCloseBtn.setOnAction(e-> {
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/game.fxml"));
                Scene s = rulesCloseBtn.getScene();
                s.setRoot(newRoot);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("RULES CLOSED!");
        });
    }
}
