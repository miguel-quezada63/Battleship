package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sample.model.Cell;
import sample.model.Game;

public class GameController {
    Game game;
    @FXML
    private GridPane gameYouBoard;
    @FXML
    private GridPane gameOpponentBoard;

    @FXML
    void initialize() {
        createNewGame();
    }

    private void createNewGame() {
        game = new Game();
        addGridEvents();
    }

    private void addGridEvents() {
        for(int y = 1; y < 11; ++y) {
            for (int x = 1; x < 11; ++x) {
                Button btn1 = new Button("");
                Button btn2 = new Button("");
                btn1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btn1.setStyle("-fx-background-color: #e3ebff; -fx-border-width: 1 1 0 0; -fx-border-color:  #4392F1");
                btn2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btn2.setStyle("-fx-background-color: #e3ebff; -fx-border-width: 1 1 0 0; -fx-border-color:  #4392F1");
                gameYouBoard.add(btn1, x, y);
                gameOpponentBoard.add(btn2, x, y);
            }
        }
        gameYouBoard.getChildren().forEach(item -> {
            item.setOnMouseClicked(e -> {
                Integer x = GridPane.getRowIndex((Node) e.getSource());
                Integer y = GridPane.getColumnIndex((Node) e.getSource());
                if(x == null || y == null) return;
                Cell c =  game.getYouBoard().getCellByCoord(x, y);
                System.out.println(c.getX() + " " + c.getY());
            });
        });
        gameOpponentBoard.getChildren().forEach(item ->{
            item.setOnMouseClicked(e -> {
                Integer x = GridPane.getRowIndex((Node) e.getSource());
                Integer y = GridPane.getColumnIndex((Node) e.getSource());
                if(x == null || y == null) return;
                boolean hitTarget = game.hitCell(x, y, game.getOppBoard());
                if(hitTarget)
                    item.setStyle("-fx-background-color: #00ff00");
                else
                    item.setStyle("-fx-background-color: #ff0000");
            });
        });
    }

}