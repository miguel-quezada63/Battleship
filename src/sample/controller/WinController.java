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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.model.Game;
import sample.utility.Player;

public class WinController {
    @FXML
    private Button winPlayBtn;

    @FXML
    private Button winQuitBtn;

    @FXML
    private Text winPlayerText;

    @FXML
    private void playAgain() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/placement.fxml"));
            Scene s = winPlayBtn.getScene();
            s.setRoot(newRoot);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    private void quitToMenu() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/menu.fxml"));
            Scene s = winQuitBtn.getScene();
            s.setRoot(newRoot);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        if(Game.getWinner() == Player.P1)
            winPlayerText.setText("PLAYER 1 HAS WON!");
        else
            winPlayerText.setText("PLAYER 2 HAS WON!");
    }



}