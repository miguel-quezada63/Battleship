
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
    private Button menuBtn;


    @FXML
    void initialize() {
        chooseFileBtn.setOnAction(e -> {
            //opens file chooser dialog box to choose text file in order to load previous game
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("TEXT Files (.txt)", "*.txt")
            );
            Stage stage = (Stage) chooseFileBtn.getScene().getWindow();
            selectedFile = fileChooser.showOpenDialog(stage);
            System.out.println(selectedFile);
        });

        menuBtn.setOnAction(e ->{
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/menu.fxml"));
                Scene s = menuBtn.getScene();
                s.setRoot(newRoot);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        loadGameBtn.setOnAction(e -> {
            if(selectedFile == null)
            {
                //error popup that opens when you try to load a null file
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("File not found");
                errorAlert.setContentText("Please select a different file to load game");
                errorAlert.showAndWait();
            }
            else {
                //calls loadGame function to load the game into the board
                Load.loadGame(selectedFile);
                try {
                    Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/Game.fxml"));
                    Scene s = loadGameBtn.getScene();
                    s.setRoot(newRoot);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}


