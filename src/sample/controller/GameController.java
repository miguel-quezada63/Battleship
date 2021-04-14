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
import sample.model.Cell;
import sample.model.Game;
import sample.utility.Player;

import java.awt.event.MouseEvent;
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
    private Text gamePlayerTurnText;
    @FXML
    private Button gamePlayTurnBtn;
    @FXML
    private Button saveMenuBtn;
    @FXML
    private Button rulesMenuBtn;

    @FXML
    void initialize() {
        System.out.println("Initialize Game");
        createNewGame();
    }

    private void createNewGame() {
        // hide and disable btns
        disableAndHide(gameFireBtn);
        disableAndHide(gameNextBtn);
        // hide next turn pane until it is the next player's turn
        gameNextTurnPane.setDisable(true);
        gameNextTurnPane.setVisible(false);
        selectedItem = null; // selected item to null so that no conflicts occur on first turn
        if (!Game.isStarted()) Game.init(); // init game settings
        addGridEvents(); // add all grid buttons and btn events
    }

    private void addGridEvents() {
        for(int row = 1; row < 11; ++row) {
            for (int col = 1; col < 11; ++col) {
                // build opponent board
                Button btn = new Button(""); // create new button to place on grid
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // set btn width and height so that it fills the entire cell area
                btn.setStyle(DEFAULT_STYLES); // set default styles for btn
                Cell cellOpp = Game.getOpponentBoard().getCellByCoord(row, col); // get cell corresponding with current coordinates in loop
                if(cellOpp.isHit() && cellOpp.getShip() != null) // if the cell has a successful hit, display this on the board
                    btn.setStyle(HIT_COLOR);
                else if(cellOpp.isHit())
                    btn.setStyle(MISS_COLOR);
                gameOpponentBoard.add(btn, col, row); // add the button to the grid
                // build player board
                StackPane sPane = new StackPane(); // create stack pane for each cell
                sPane.setStyle(DEFAULT_STYLES); // set default styles on stack panee
                GridPane.setFillWidth(sPane, true); // fill width of cell
                GridPane.setFillHeight(sPane, true); // fill height of cell
                Cell cellPlayer = Game.getPlayerBoard().getCellByCoord(row, col); // get cell corresponding with current coordinates in loop
                if(cellPlayer.isHit() && cellPlayer.getShip() != null) // if the cell has a successful hit, display this on the board
                    sPane.setStyle(HIT_COLOR);
                else if(cellPlayer.isHit())
                    sPane.setStyle(MISS_COLOR);
                gameYouBoard.add(sPane, col, row); // add stack pane to corresponding cell in gridpane
            }
        }

        gameOpponentBoard.getChildren().forEach(item -> {
                item.setOnMouseClicked(e -> {
                    if(!alreadyFired) {
                        if(selectedItem != null)
                            selectedItem.setStyle(DEFAULT_STYLES);
                        Integer row = GridPane.getRowIndex((Node) e.getSource()); // get row of the current cell
                        Integer col = GridPane.getColumnIndex((Node) e.getSource()); // get col of the current cell
                        // if either are null or the selected cell is already hit, do not set as current move.
                        if (row == null || col == null || Game.getOpponentBoard().getCellByCoord(row, col).isHit()) return;
                        Game.setCurMove(row, col); // set current move in order to hit for later
                        System.out.println(row + " " + col);
                        selectedItem = item; // select cell for future styling
                        item.setStyle(FOCUS_COLOR);
                        undisableAndUnhide(gameFireBtn);
                    }
                });
        });

        gameFireBtn.setOnAction(e -> {
            selectedItem.setDisable(true);
            if(Game.hitCell()) // hit target using curMove. If the hit was successful, display a hit on that cell
                selectedItem.setStyle(HIT_COLOR + FULL_OPACITY);
            else // else, display a miss on that cell
                selectedItem.setStyle(MISS_COLOR + FULL_OPACITY);
            Game.setCurMove(-1,-1);
            disableAndHide(gameFireBtn); // hide fire btn
            undisableAndUnhide(gameNextBtn); // show next btn
            Game.nextTurn();
            alreadyFired = true; // set already fired to true so that the player cannot fire twice
            selectedItem = null; // unselect cell
        });

        gameNextBtn.setOnAction(e -> {
            if(Game.getCurPlayer() == Player.P1)
                gamePlayerTurnText.setText("End of Player 1's turn, Player 2 turn up next!");
            else
                gamePlayerTurnText.setText("End of Player 2's turn, Player 1 turn up next!");
            undisableAndUnhide(gameNextTurnPane);

        });

        gamePlayTurnBtn.setOnAction(e -> {
            switchBoard();
            disableAndHide(gameNextTurnPane);
        });

        saveMenuBtn.setOnAction(e-> {
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/save.fxml"));
                Scene s = saveMenuBtn.getScene();
                s.setRoot(newRoot);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            System.out.println("SAVE CLICKED!");
        });

        rulesMenuBtn.setOnAction(e-> {
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("/sample/view/rules.fxml"));
                Scene s = rulesMenuBtn.getScene();
                s.setRoot(newRoot);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("RULES CLICKED!");
        });
    }

    private void switchBoard() {
        for(int row = 1; row < 11; ++row){
            for(int col = 1; col < 11; ++col) {
                Cell c = Game.getPlayerBoard().getGrid()[row][col];
                System.out.println("CELL: ");
                System.out.println("Row: " + c.getRow() + ", Col: " + c.getCol());
                System.out.println("IsHit: " + c.isHit() + ", Ship:" + c.getShip());
                System.out.println("\n\n");
            }
        }

        for(int row = 1; row < 11; ++row){
            for(int col = 1; col < 11; ++col){
                Node oppN = getNode(row, col, gameOpponentBoard); // Get current node in opponent's board
                if (oppN == null) continue; // if the node is null (no element exists with row and col), go to next iteration
                Cell oppC = Game.getOpponentBoard().getCellByCoord(row, col); // Get the cell in opponent board that corresponds with the node position
                if(oppC.isHit() && oppC.getShip() != null){ // if a cell is hit and a ship exists, style accordingly and disable cell button
                    oppN.setDisable(true);
                    oppN.setStyle(HIT_COLOR + DEFAULT_STYLES_NO_BG + FULL_OPACITY);
                }
                else if(oppC.isHit()) { // if the cell is only hit, style accordingly and disable button
                    oppN.setDisable(true);
                    oppN.setStyle(MISS_COLOR + DEFAULT_STYLES_NO_BG + FULL_OPACITY);
                }
                else { // if the cell has not been hit, use default styles and undisable cell button
                    oppN.setDisable(false);
                    oppN.setStyle(DEFAULT_STYLES + FULL_OPACITY);
                }
                Node playerN = getNode(row, col, gameYouBoard); // get player board
                if(playerN == null) continue;
                Cell playerC = Game.getPlayerBoard().getCellByCoord(row, col);
                if(playerC.isHit() && playerC.getShip() != null)
                    playerN.setStyle(HIT_COLOR + DEFAULT_STYLES_NO_BG);
                else if(playerC.isHit())
                    playerN.setStyle(MISS_COLOR + DEFAULT_STYLES_NO_BG);
                else
                    playerN.setStyle(DEFAULT_STYLES);
            }
        }
        disableAndHide(gameNextBtn);
        disableAndHide(gameFireBtn);
        alreadyFired = false;
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