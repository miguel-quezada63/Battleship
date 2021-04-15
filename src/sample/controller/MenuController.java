package sample.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Button menuPlayBtn;

    @FXML
    private Button menuLoadBtn;

    @FXML
    private Button menuQuitBtn;

    @FXML
    public void play() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/placement.fxml"));
            Scene s = menuPlayBtn.getScene();
            s.setRoot(newRoot);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void load(){
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/load.fxml"));
            Scene s = menuLoadBtn.getScene();
            s.setRoot(newRoot);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void quit() {
        Stage s = (Stage) menuQuitBtn.getScene().getWindow();
        s.close();
    }

}