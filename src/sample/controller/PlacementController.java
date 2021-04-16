package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.model.Cell;
import sample.model.Game;
import sample.utility.Orientation;
import sample.utility.ShipType;
import sample.model.Game;
import sample.model.Board;


public class PlacementController {
    private final static String HIT_COLOR = "-fx-background-color: #e71f32;";
    private final static String MISS_COLOR = "-fx-background-color: #ffffff;";
    private final static String FOCUS_COLOR = "-fx-background-color: #eae265;";
    private final static String DEFAULT_STYLES = "-fx-background-color: #848482; -fx-border-width: 2 2 0 0; -fx-border-color:  #5a7797;";
    private Node selectedItem;
    private boolean properlyPlaced;
    private boolean alreadyFired;
    private Orientation orientationSelected = Orientation.HORIZONTAL; // orientation selected by user
    private String determineShip ="CARIER";
    private int determineShipSize=5;
    private ShipType boardShipType= ShipType.CARRIER;
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
    private Text placementConfirmationTxt;


    @FXML
    void initialize(){
        placeShips();
    }


    private void placeShips() {
        for(int row = 1; row < 11; ++row) {
            for (int col = 1; col < 11; ++col) {
                Button btn = new Button(""); // create new button to place on grid
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // set btn width and height so that it fills the entire cell area
                btn.setStyle(DEFAULT_STYLES); // set default styles for btn
                Cell cellOpp = Game.getOpponentBoard().getCellByCoord(row, col); // get cell corresponding with current coordinates in loop
                if(cellOpp.isHit() && cellOpp.getShip() != null) // if the cell has a successful hit, display this on the board
                    btn.setStyle(HIT_COLOR);
                else if(cellOpp.isHit())
                    btn.setStyle(MISS_COLOR);
                placementBoardGrid.add(btn, col, row); // add the button to the grid
                StackPane sPane = new StackPane();
                sPane.setStyle(DEFAULT_STYLES);
                GridPane.setFillWidth(sPane, true);
                GridPane.setFillHeight(sPane, true);
                Cell cellPlayer = Game.getPlayerBoard().getCellByCoord(row, col); // get cell corresponding with current coordinates in loop
                if(cellPlayer.isHit() && cellPlayer.getShip() != null) // if the cell has a successful hit, display this on the board
                    sPane.setStyle(HIT_COLOR);
                else if(cellPlayer.isHit())
                    sPane.setStyle(MISS_COLOR);
                placementBoardGrid.add(sPane, col, row);
            }
        }
        placementConfirmationTxt.setVisible(false);//sets invisible on begin
        //on click changes the orientation to the opposite of whatever it is
        placementOrientationBtn.setOnAction(e -> {
            if (orientationSelected == Orientation.HORIZONTAL){
                placementOrientationText.setText("CURRENT ORIENTATION: VERTICAL");
                orientationSelected = Orientation.VERTICAL; //sets the orientation to the opposite of what it was before the click
            } else {
                placementOrientationText.setText("CURRENT ORIENTATION: HORIZONTAL");
                orientationSelected = Orientation.HORIZONTAL;//sets the orientation to the opposite of what it was before the click
            }
        });

        placementNextBtn.setOnAction(e -> { //only changes when you have confirmed that the ship the user has placed is actually valid


        });


        placementBoardGrid.getChildren().forEach(item -> {//will essentially give me the cordinates of the where the user clicked and if properly then sets ship if not then prompt user
            item.setOnMouseClicked(e -> {
                Integer row = GridPane.getRowIndex((Node) e.getSource()); // get row of the current cell
                Integer col = GridPane.getColumnIndex((Node) e.getSource()); // get col of the current cell
                System.out.println("Row: " + row + " Col: " + col);
                if (row == null || col == null) return; // if either are null, do not place an event listener on this position
                Game.setCurMove(row, col); // set current move in order to hit for later
                System.out.println(row + " " + col);
                //sets confirmation to visible and allow next to be clicked
                if(verifyHorizontal(row,col,boardShipType) || verifyVertical(col,row,boardShipType)){
                    placementConfirmationTxt.setText("your ship has been placed!");
                    placementConfirmationTxt.setFill(Color.GREEN);
                } else {
                    placementConfirmationTxt.setText("The Spot Selected was not valid please choose another!");
                    placementConfirmationTxt.setFill(Color.RED);
                }
            });
        });

    }
    /*
    private void addGridEvents() {
        for(int row = 1; row < 11; ++row) {
            for (int col = 1; col < 11; ++col) {
                Button btn = new Button(""); // create new button to place on grid
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // set btn width and height so that it fills the entire cell area
                btn.setStyle(DEFAULT_STYLES); // set default styles for btn
                Cell cellOpp = Game.getOpponentBoard().getCellByCoord(row, col); // get cell corresponding with current coordinates in loop
                if(cellOpp.isHit() && cellOpp.getShip() != null) // if the cell has a successful hit, display this on the board
                    btn.setStyle(HIT_COLOR);
                else if(cellOpp.isHit())
                    btn.setStyle(MISS_COLOR);
                placementBoardGrid.add(btn, col, row); // add the button to the grid
                StackPane sPane = new StackPane();
                sPane.setStyle(DEFAULT_STYLES);
                GridPane.setFillWidth(sPane, true);
                GridPane.setFillHeight(sPane, true);
                Cell cellPlayer = Game.getPlayerBoard().getCellByCoord(row, col); // get cell corresponding with current coordinates in loop
                if(cellPlayer.isHit() && cellPlayer.getShip() != null) // if the cell has a successful hit, display this on the board
                    sPane.setStyle(HIT_COLOR);
                else if(cellPlayer.isHit())
                    sPane.setStyle(MISS_COLOR);
                placementBoardGrid.add(sPane, col, row);
            }
        }

        placementBoardGrid.getChildren().forEach(item -> {
            item.setOnMouseClicked(e -> {
                if(!alreadyFired) {
                    if(selectedItem != null)
                        selectedItem.setStyle(DEFAULT_STYLES);
                    Integer row = GridPane.getRowIndex((Node) e.getSource()); // get row of the current cell
                    Integer col = GridPane.getColumnIndex((Node) e.getSource()); // get col of the current cell
                    System.out.println("Row: " + row + " Col: " + col);
                    if (row == null || col == null) return; // if either are null, do not place an event listener on this position
                    Game.setCurMove(row, col); // set current move in order to hit for later
                    System.out.println(row + " " + col);
                    selectedItem = item; // select cell for future styling
                    item.setStyle(FOCUS_COLOR);
                }
            });
        });
    }
*/
    //takes in the row and current size of the ship trying to be placed to verify that there is enough space
    private static boolean verifyHorizontal(Integer row,Integer col,ShipType boardShipType){
        Integer verify = 10 - col; //checks to see if the spot selected has sufficient space for the current ship
        Integer colIn=col;//the column needs to be iterated through
        if(verify >= boardShipType.getSpaces()){//if there is enough space, check if that space is empty
            for(int i=0;i<boardShipType.getSpaces();i++ ){
                Cell c= Game.getPlayerBoard().getCellByCoord(row,colIn);//cell is equal to what ever is in that spot
                if(c.getShip()!=null)
                    return false;
                colIn++;//increases col to iterate through it
            }
                return true;//if it does not return false from the for loop then return true because we know its all null
        }else
            return false;

    }

    //takes in the col and current size of the ship trying to be placed to verify that there is enough space
    private static boolean verifyVertical(Integer col,Integer row,ShipType boardShipType){
        Integer verify = 10 - row; //checks to see if the spot selected has sufficient space for the current ship
        Integer rowIn=row;
        Integer colIn=col;
        if(verify >= boardShipType.getSpaces()){//if there is enough space, check if that space is empty
            for(int i=0;i<boardShipType.getSpaces();i++ ){
                Cell c= Game.getPlayerBoard().getCellByCoord(rowIn,colIn);//cell is equal to what ever is in that spot
                if(c.getShip()!=null)
                    return false;
                rowIn++;
            }
            return true;
        }else //if there is not enough space then return false
            return false;
    }




}
