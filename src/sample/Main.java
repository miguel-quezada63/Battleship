package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    final double MIN_WIDTH = 1000;
    final double MIN_HEIGHT = 750;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/menu.fxml"));
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(new Scene(root, MIN_WIDTH,MIN_HEIGHT ));
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
