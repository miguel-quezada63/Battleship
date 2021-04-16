package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WinController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane gameWinPane;

    @FXML
    private Button playAgainBtn;

    @FXML
    private Button quitGameButton;

    @FXML
    public void playAgain() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/placement.fxml"));
            Scene s = playAgainBtn.getScene();
            s.setRoot(newRoot);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void quitGame() {
        Stage s = (Stage) quitGameButton.getScene().getWindow();
        s.close();
    }

    @FXML
    void initialize() {

    }



}