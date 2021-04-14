package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PlacementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane placementBoardGrid;

    @FXML
    private Text placementShipText;

    @FXML
    private Text placementSizeText;

    @FXML
    private Text placementOrientationText;

    @FXML
    private Button placementOrientationBtn;

    @FXML
    private Button placementNextBtn;

    @FXML
    void initialize() {

    }
}
