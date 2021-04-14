
package sample.controller;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.model.Load;

public class LoadController {
    @FXML
    private Button loadGameBtn;
    @FXML
    private Button chooseFileBtn;

    @FXML
    void initialize() {
        chooseFileBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JSON Files", "*.json")
            );
            Stage stage = (Stage) chooseFileBtn.getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);

        });

        loadGameBtn.setOnAction(e -> {
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


