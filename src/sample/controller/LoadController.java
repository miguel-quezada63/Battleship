
package sample.controller;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JSON Files", "*.json")
            );
            Stage stage = (Stage) chooseFileBtn.getScene().getWindow();
            selectedFile = fileChooser.showOpenDialog(stage);
            System.out.println(selectedFile);
        });

        loadGameBtn.setOnAction(e -> {
            if(selectedFile == null)
            {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("File not found");
                errorAlert.setContentText("Please select a different file to load game");
                errorAlert.showAndWait();
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


