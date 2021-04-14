package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import sample.model.Game;
import sample.utility.Orientation;

public class PlacementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button orientationBtn;

    @FXML
    private Text orientationText;

    @FXML
    void initialize() {

        orientationBtn.setOnMouseClicked(e->{
            if(Game.getPlayerBoard().getOrientation()== Orientation.HORIZONTAL){
                Game.getPlayerBoard().setOrientation(Orientation.VERTICAL);
                orientationText.setText("Current orientation,Vertical");
            }else{
                Game.getPlayerBoard().setOrientation(Orientation.HORIZONTAL);
                orientationText.setText("Current orientation,Horizontal");
            }

        });

    }
}
