
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
    private File selectedFile;

    @FXML
    private Button loadGameBtn;
    @FXML
    private Button chooseFileBtn;

    @FXML
    void initialize() {
        chooseFileBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            /*fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JSON Files", "*.json")
            );*/
            Stage stage = (Stage) chooseFileBtn.getScene().getWindow();
            selectedFile = fileChooser.showOpenDialog(stage);
            System.out.println(selectedFile);
        });

        loadGameBtn.setOnAction(e -> {
            if(selectedFile == null)
            {
                System.out.println("Please select a different file!");
            }
            else {
                Load.loadGame(selectedFile);
                try {
                    Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/game.fxml"));
                    Scene s = loadGameBtn.getScene();
                    s.setRoot(newRoot);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.println("LOAD GAME!");
            }
        });
    }
}


