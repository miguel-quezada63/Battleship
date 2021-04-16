package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.model.Cell;
import sample.model.Game;
import sample.model.Save;
import sample.utility.Player;
import java.io.File;
import java.io.IOException;

public class GameController {
    private final static String HIT_COLOR = "-fx-background-color: #e71f32;";
    private final static String MISS_COLOR = "-fx-background-color: #ffffff;";
    private final static String FOCUS_COLOR = "-fx-background-color: #eae265;";
    private final static String DEFAULT_STYLES = "-fx-background-color: #848482; -fx-border-width: 2 2 0 0; -fx-border-color:  #5a7797;";
    private final static String DEFAULT_STYLES_NO_BG = "-fx-border-width: 1 1 0 0; -fx-border-color:  #5a7797;";
    private final static String FULL_OPACITY = "-fx-opacity: 1.0;";
    private Node selectedItem;
    private boolean alreadyFired;
    @FXML
    private GridPane gameYouBoard;
    @FXML
    private GridPane gameOpponentBoard;
    @FXML
    private Button gameFireBtn;
    @FXML
    private Button gameNextBtn;
    @FXML
    private Pane gameNextTurnPane;
    @FXML
    private Text gameConfirmTurnText;
    @FXML
    private Button saveMenuBtn;
    @FXML
    private Button rulesMenuBtn;
    @FXML
    private Button quitToMenuBtn;

    @FXML
    void initialize() {
        // hide and disable btns
        disableAndHide(gameFireBtn);
        disableAndHide(gameNextBtn);
        disableAndHide(gameNextTurnPane); // hide next turn pane until it is the next player's turn
        addGridEvents(); // add all grid buttons and btn events
        selectedItem = null; // selected item to null so that no conflicts occur on first turn
    }

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
                gameOpponentBoard.add(btn, col, row); // add the button to the grid
                // Create stack panes on player grid to display hits and misses
                StackPane sPane = new StackPane(); // create new stack pane object to be added to cell
                sPane.setStyle(DEFAULT_STYLES); // set default border styles on stack panes
                GridPane.setFillWidth(sPane, true); // fill width of cell
                GridPane.setFillHeight(sPane, true); // fill height of cell
                Cell cellPlayer = Game.getPlayerBoard().getCellByCoord(row, col); // get cell corresponding with current coordinates in loop
                // if the cell has a successful hit, display this on the board
                if(cellPlayer.isHit() && cellPlayer.getShip() != null)
                    sPane.setStyle(HIT_COLOR);
                else if(cellPlayer.isHit())
                    sPane.setStyle(MISS_COLOR);
                gameYouBoard.add(sPane, col, row); // add stack pane to respective cell on grid
            }
        }

        gameOpponentBoard.getChildren().forEach(item -> {
                item.setOnMouseClicked(e -> {
                    // If the user has not already fired at least one shot on the board
                    if(!alreadyFired) {
                        // If an item has been selected, after another selection, reset the styles on the previous item
                        if(selectedItem != null)
                            selectedItem.setStyle(DEFAULT_STYLES);
                        Integer row = GridPane.getRowIndex((Node) e.getSource()); // get row of the current cell
                        Integer col = GridPane.getColumnIndex((Node) e.getSource()); // get col of the current cell
                        System.out.println("Row: " + row + " Col: " + col);
                        if (row == null || col == null) return; // if either are null do nothing as it is an invalid position
                        Game.setCurMove(row, col); // set current move in order to hit for later
                        selectedItem = item; // select cell for future styling
                        item.setStyle(FOCUS_COLOR); // set background to show that the cell is selected
                        undisableAndUnhide(gameFireBtn); // unhide fire button
                    }
                });
        });
    }

    private void switchBoard() {
        for(int row = 1; row < 11; ++row){
            for(int col = 1; col < 11; ++col){
                Node oppN = getNode(row, col, gameOpponentBoard); // get node in board that corresponds to coordinate
                if (oppN == null) continue; // if node at position does not exist, skip this position
                Cell oppC = Game.getOpponentBoard().getCellByCoord(row, col); // get cell in game grid for given coordinate
                // if the cell is hit and a ship exists on this cell, display it as red
                if(oppC.isHit() && oppC.getShip() != null) {
                    oppN.setDisable(true);
                    oppN.setStyle(HIT_COLOR + DEFAULT_STYLES_NO_BG + FULL_OPACITY);
                }
                // else if it is only hit and no ship exists on this cell, display it as white
                else if(oppC.isHit()) {
                    oppN.setDisable(true);
                    oppN.setStyle(MISS_COLOR + DEFAULT_STYLES_NO_BG + FULL_OPACITY);
                }
                // if not hit, use default background color
                else {
                    oppN.setDisable(false);
                    oppN.setStyle(DEFAULT_STYLES + FULL_OPACITY);
                }
                Node playerN = getNode(row, col, gameYouBoard); // get node in board that corresponds to coordinate
                if(playerN == null) continue; // if node at position does not exist, skip this position
                Cell playerC = Game.getPlayerBoard().getCellByCoord(row, col); // get cell in game grid for given coordinate
                // if the cell is hit and a ship exists on this cell, display it as red
                if(playerC.isHit() && playerC.getShip() != null)
                    playerN.setStyle(HIT_COLOR + DEFAULT_STYLES_NO_BG + FULL_OPACITY);
                // else if it is only hit and no ship exists on this cell, display it as white
                else if(playerC.isHit())
                    playerN.setStyle(MISS_COLOR + DEFAULT_STYLES_NO_BG + FULL_OPACITY);
                // if not hit, use default background color
                else
                    playerN.setStyle(DEFAULT_STYLES);
            }
        }
        disableAndHide(gameNextBtn); // disable and hide next turn button for next player
        disableAndHide(gameFireBtn); // disable and hide fire button for next player
        alreadyFired = false; // reset already fired so that styling is correct
    }

    @FXML
    private void fire(){
        selectedItem.setDisable(true); // disable cell so that it cannot be fired upon again
        // hit target using curMove. If the hit was successful, display a hit on that cell
        if(Game.hitCell())
            selectedItem.setStyle(HIT_COLOR + FULL_OPACITY);
        // else, display a miss on that cell
        else
            selectedItem.setStyle(MISS_COLOR + FULL_OPACITY);
        Game.setCurMove(-1,-1);
        disableAndHide(gameFireBtn); // hide and disable fire btn
        undisableAndUnhide(gameNextBtn); // show and undisable next btn
        Game.nextTurn(); // initiate next turn in game
        alreadyFired = true; // set already fired to true so that the player cannot fire twice
        selectedItem = null; // unselect the fired upon cell
    }

    @FXML
    private void openRules(){
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/rules.fxml")); // get new root FXML file
            Scene s = rulesMenuBtn.getScene(); // get the current scene
            s.setRoot(newRoot); // set this scene to a new root (rules.fxml)
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML void save(){
        FileChooser fileChooser = new FileChooser(); //opens file chooser dialog menu to save game file
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName("BattleshipSave");
        // adds extension so you can only save as a text file
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files (.txt)", "*.txt"));
        Stage stage = (Stage) saveMenuBtn.getScene().getWindow();
        File saveFile = fileChooser.showSaveDialog(stage);
        if(saveFile != null)
        {
            try {
                Save.saveGame(saveFile);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @FXML void quit(){
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/menu.fxml"));
            Scene s = quitToMenuBtn.getScene();
            s.setRoot(newRoot);
            Game.init(); // reset game when quitting to menu
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    void nextTurn(){
        switchBoard(); // when next player turn is initiated, switch the boards.
        disableAndHide(gameNextTurnPane); // hide the player turn end screen
    }

    // Use the reverse player since we are switching turns when fire is pressed
    @FXML
    void nextTurnConfirm(){
        if(Game.getCurPlayer() == Player.P2)
            gameConfirmTurnText.setText("End of Player 1's turn, Player 2 turn up next!");
        else
            gameConfirmTurnText.setText("End of Player 2's turn, Player 1 turn up next!");
        undisableAndUnhide(gameNextTurnPane);
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

    private Node getNode(int row, int col, GridPane gp){
        for (Node node : gp.getChildren()) {
            Integer iRow = GridPane.getRowIndex(node);
            Integer iCol = GridPane.getColumnIndex(node);
            if(iRow == null || iCol == null) continue;
            if (iRow == row && iCol == col)
                return node;
        }
        return null;
    }

}