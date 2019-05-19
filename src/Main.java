import Controller.GameController;
import View.MatchPatterns;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Deque;
import java.util.LinkedList;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
        new MatchPatterns();
        GameController gameController = new GameController();
        gameController.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

}
