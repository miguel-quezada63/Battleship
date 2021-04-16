package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import sample.model.Board;
import sample.model.Game;
import sample.model.Ship;
import sample.utility.Orientation;
import sample.utility.ShipType;


public class PlacementController {
    private final static String PLAYER_TEXT = ", PLEASE PLACE YOUR SHIPS";
    private final static String SHIP_TEXT = "CURRENT SHIP: ";
    private final static String SIZE_TEXT = "SHIP SIZE: ";
    private final static String DEFAULT_STYLES = "-fx-background-color: #848482; -fx-border-width: 2 2 0 0; -fx-border-color:  #5a7797;";
    private final static ShipType[] SHIP_ORDER = {ShipType.CARRIER, ShipType.BATTLESHIP, ShipType.CRUISER, ShipType.SUBMARINE, ShipType.DESTROYER};
    private Orientation orientationSelected = Orientation.HORIZONTAL;
    private int currentShip = 0;
    private boolean p1Placing = true;
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
    private Text placementVerifyText;
    @FXML
    private Text placementPlayerText;
    @FXML
    private Pane placementConfirmPane;

    @FXML
    void initialize(){
        disableAndHide(placementConfirmPane);
        disableAndHide(placementVerifyText);
        Game.init();
        addGridEvents();
    }

    // Add buttons to grid so that ships can be added on click
    private void addGridEvents() {
        for(int row = 1; row < 11; ++row) {
            for (int col = 1; col < 11; ++col) {
                Button btn = new Button(""); // create new button to place on grid
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // set btn width and height so that it fills the entire cell area
                btn.setStyle(DEFAULT_STYLES); // set default styles for btn
                placementBoardGrid.add(btn, col, row); // add the button to the grid
            }
        }
        placementBoardGrid.getChildren().forEach(item -> {
            //will essentially give me the coordinates of the where the user clicked and if properly then sets ship if not then prompt user
            item.setOnMouseClicked(e -> {
                Integer row = GridPane.getRowIndex((Node) e.getSource()); // get row of the current cell
                Integer col = GridPane.getColumnIndex((Node) e.getSource()); // get col of the current cell
                if (row == null || col == null) return; // if either are null, do not place an event listener on this position
                if(orientationSelected == Orientation.HORIZONTAL) {
                    if (!verifyHorizontal(col)) {
                        invalidSelection();
                        return;
                    }
                }
                else {
                    if (!verifyVertical(row)) {
                        invalidSelection();
                        return;
                    }
                }
                boolean placed = Game.placeShipOnBoard(row, col, SHIP_ORDER[currentShip], orientationSelected, p1Placing);
                if(!placed) {
                    invalidSelection();
                    return;
                }
                currentShip++;
                int shipNum = currentShip > 4 ? currentShip - 1 : currentShip;
                placementShipText.setText(SHIP_TEXT + SHIP_ORDER[shipNum].getType());
                placementSizeText.setText(SIZE_TEXT + SHIP_ORDER[shipNum].getSpaces() + " CELLS");
                placementVerifyText.setText("Your ship has been placed!");
                placementVerifyText.setFill(Color.LIMEGREEN);
                undisableAndUnhide(placementVerifyText);
                updateBoard();
                if (currentShip == 5 && !p1Placing) goToGame(); // placement done, go to game
                else if(currentShip == 5) nextPlayerPlacement(); // switch to p2 placing
            });
        });
    }

    // takes in the row and current size of the ship trying to be placed to verify that there is enough space
    private boolean verifyHorizontal(Integer col) {
        return (col + SHIP_ORDER[currentShip].getSpaces()) <= 11;
    }

    // takes in the col and current size of the ship trying to be placed to verify that there is enough space
    private boolean verifyVertical(Integer row){
        return (row + SHIP_ORDER[currentShip].getSpaces()) <= 11;
    }

    // display to user that their selection is invalid
    private void invalidSelection() {
        placementVerifyText.setText("The spot you selected was not valid, please choose another.");
        placementVerifyText.setFill(Color.RED);
        undisableAndUnhide(placementVerifyText);
    }

    private void updateBoard(){
        Board board = p1Placing ? Game.getP1Board() : Game.getP2Board();
        System.out.println("P1PLACING: " + p1Placing);
        for(int row = 1; row <= 10; ++row) {
            for(int col = 1; col <= 10; ++col) {
                Ship ship = board.getCellByCoord(row, col).getShip();
                if(ship == null) continue;
                ShipType st = ship.getShipType();
                if(st == ShipType.CARRIER)
                    placementBoardGrid.add(new Rectangle(30, 30), col, row);
                else if(st == ShipType.BATTLESHIP) {
                    Rectangle rect = new Rectangle(30, 30);
                    rect.setStyle("-fx-fill: lime");
                    placementBoardGrid.add(rect, col, row);
                } else if(st == ShipType.CRUISER) {
                    Rectangle rect = new Rectangle(30, 30);
                    rect.setStyle("-fx-fill: coral");
                    placementBoardGrid.add(rect, col, row);
                } else if(st == ShipType.SUBMARINE) {
                    Rectangle rect = new Rectangle(30, 30);
                    rect.setStyle("-fx-fill: aqua");
                    placementBoardGrid.add(rect, col, row);
                } else if(st == ShipType.DESTROYER) {
                    Rectangle rect = new Rectangle(30, 30);
                    rect.setStyle("-fx-fill: violet");
                    placementBoardGrid.add(rect, col, row);
                }
            }
        }
    }

    private void goToGame(){
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/game.fxml"));
            Scene s = placementOrientationBtn.getScene();
            s.setRoot(newRoot);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void nextPlayerPlacement(){
        undisableAndUnhide(placementConfirmPane);
        updateBoard();
        // Store grid headers for next player placement
        ArrayList<Node> nodeList = new ArrayList();
        // Add grid header nodes to list
        for(int i = 0; i <= 19; ++i)
            nodeList.add(placementBoardGrid.getChildren().get(i));
        placementBoardGrid.getChildren().clear();
        addGridEvents();
        // put grid headers back on board
        for(int i = 0; i <= 19; ++i)
            placementBoardGrid.getChildren().add(0, nodeList.get(i));
        currentShip = 0;
        p1Placing = false;
        placementPlayerText.setText("PLAYER 2" + PLAYER_TEXT);
        placementShipText.setText(SHIP_TEXT + SHIP_ORDER[currentShip].getType());
        placementSizeText.setText(SIZE_TEXT + SHIP_ORDER[currentShip].getSpaces() + " CELLS");
    }

    // Proceed to place player 2's ships
    @FXML
    public void placeNextShips(){
        disableAndHide(placementConfirmPane);
    }

    // Alternates orientation on click of change orientation btn
    @FXML
    public void changeOrientation(){
        if (orientationSelected == Orientation.HORIZONTAL){
            placementOrientationText.setText("CURRENT ORIENTATION: VERTICAL");
            orientationSelected = Orientation.VERTICAL;
        } else {
            placementOrientationText.setText("CURRENT ORIENTATION: HORIZONTAL");
            orientationSelected = Orientation.HORIZONTAL;
        }
    }

    // utility function for hiding and disabling a Node
    private <T extends Node> void disableAndHide(T el){
        el.setDisable(true);
        el.setVisible(false);
    }

    // utility function for unhiding and undisabling a Node
    private <T extends Node> void undisableAndUnhide(T el){
        el.setDisable(false);
        el.setVisible(true);
    }
}
