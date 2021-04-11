package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button menuPlayBtn;

    @FXML
    private Button menuLoadBtn;

    @FXML
    private Button menuQuitBtn;

    @FXML
    void initialize() {
        menuPlayBtn.setOnAction(e-> {
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/game.fxml"));
                Scene s = menuPlayBtn.getScene();
                s.setRoot(newRoot);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            System.out.println("PLAY CLICKED!");
        });

        menuLoadBtn.setOnAction(e-> {
            System.out.println("LOAD CLICKED!");
        });

        menuQuitBtn.setOnAction(e -> {
            Stage s = (Stage) menuQuitBtn.getScene().getWindow();
            s.close();
        });
    }

}