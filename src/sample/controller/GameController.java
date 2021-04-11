package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import sample.model.Board;

public class GameController {
    private Board yourBoard;
    private Board enemyBoard;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gameYouBoard;

    @FXML
    private GridPane gameOpponentBoard;

    @FXML
    void initialize() {
        createNewGame();
    }

    private void createNewGame() {

    }
}