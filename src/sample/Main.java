package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    final double WINDOW_WIDTH = 1000;
    final double WINDOW_HEIGHT = 750;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/menu.fxml")); // load our main fxml file
        primaryStage.setTitle("Battleship"); // set title of the window
        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT )); // set the scene to a new scene with the specified width and height
        primaryStage.setResizable(false); // don't allow window resizing
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
