package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.model.Cell;
import sample.model.Game;
import sample.utility.Player;

import java.io.IOException;

public class GameController {
    private final static String HIT_COLOR = "-fx-background-color: #e71f32;";
    private final static String MISS_COLOR = "-fx-background-color: #ffffff;";
    private final static String FOCUS_COLOR = "-fx-background-color: #eae265;";
    private final static String DEFAULT_STYLES = "-fx-background-color: #848482; -fx-border-width: 2 2 0 0; -fx-border-color:  #5a7797;";
    private final static String DEFAULT_STYLES_NO_BG = "-fx-border-width: 1 1 0 0; -fx-border-color:  #5a7797;";
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
        Game.init(); // init game settings
        addGridEvents(); // add all grid buttons and btn events
    }

    private void addGridEvents() {
        for(int y = 1; y < 11; ++y) {
            for (int x = 1; x < 11; ++x) {
                Button btn = new Button(""); // create new button to place on grid
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // set btn width and height so that it fills the entire cell area
                btn.setStyle(DEFAULT_STYLES); // set default styles for btn
                Cell cell = Game.getOpponentBoard().getCellByCoord(x, y); // get cell corresponding with current coordinates in loop
                if(cell.isHit() && cell.getShip() != null) // if the cell has a successful hit, display this on the board
                    btn.setStyle(HIT_COLOR);
                else if(cell.isHit())
                    btn.setStyle(MISS_COLOR);
                gameOpponentBoard.add(btn, x, y); // add the button to the grid
                StackPane sPane = new StackPane();
                sPane.setStyle(DEFAULT_STYLES);
                GridPane.setFillWidth(sPane, true);
                GridPane.setFillHeight(sPane, true);
                gameYouBoard.add(sPane,x, y);
            }
        }

        gameOpponentBoard.getChildren().forEach(item -> {
                item.setOnMouseClicked(e -> {
                    if(!alreadyFired) {
                        if(selectedItem != null)
                            selectedItem.setStyle(DEFAULT_STYLES);
                        Integer x = GridPane.getRowIndex((Node) e.getSource()); // get row of the current cell
                        Integer y = GridPane.getColumnIndex((Node) e.getSource()); // get col of the current cell
                        if (x == null || y == null) return; // if either are null, do not place an event listener on this position
                        Game.setCurMove(x, y); // set current move in order to hit for later
                        selectedItem = item; // select cell for future styling
                        item.setStyle(FOCUS_COLOR);
                        undisableAndUnhide(gameFireBtn);
                    }
                });
        });

        gameFireBtn.setOnAction(e -> {
            if(Game.hitCell()) // hit target using curMove. If the hit was successful, display a hit on that cell
                selectedItem.setStyle(HIT_COLOR);
            else // else, display a miss on that cell
                selectedItem.setStyle(MISS_COLOR);
            Game.setCurMove(-1,-1);
            disableAndHide(gameFireBtn); // hide fire btn
            undisableAndUnhide(gameNextBtn); // show next btn
            alreadyFired = true; // set already fired to true so that the player cannot fire twice
            selectedItem = null; // unselect cell
        });

        gameNextBtn.setOnAction(e -> {
            if(Game.getCurPlayer() == Player.P1)
                gamePlayerTurnText.setText("End of Player 1's turn, Player 2 turn up next!");
            else
                gamePlayerTurnText.setText("End of Player 2's turn, Player 1 turn up next!");
            undisableAndUnhide(gameNextTurnPane);
            Game.nextTurn();
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
        for(int y = 1; y < 11; ++y){
            for(int x = 1; x < 11; ++x){
                Node oppN = getNode(x, y, gameOpponentBoard);
                if (oppN == null) continue;

                Cell oppC = Game.getOpponentBoard().getCellByCoord(x, y);
                if(oppC.isHit() && oppC.getShip() != null)
                    oppN.setStyle(HIT_COLOR + DEFAULT_STYLES_NO_BG);
                else if(oppC.isHit())
                    oppN.setStyle(MISS_COLOR + DEFAULT_STYLES_NO_BG);
                else
                    oppN.setStyle(DEFAULT_STYLES);
                Node playerN = getNode(x, y, gameYouBoard);
                if(playerN == null) continue;
                Cell playerC = Game.getPlayerBoard().getCellByCoord(x, y);
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