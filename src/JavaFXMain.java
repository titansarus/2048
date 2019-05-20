import ViewFXML.Container;
import ViewFXML.LoginFXMLController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;
import Model.Account;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.security.Key;
import java.util.Deque;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class JavaFXMain extends Application {


    {
        Pane root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("./ViewFXML/Login.fxml"));
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Scene scene = new Scene(root);
        Container.scenes.add(scene);
        ((LoginFXMLController) fxmlLoader.getController()).updateLoginedUser();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage = Container.stage;


        primaryStage.setScene(Container.scenes.getLast());
        primaryStage.show();

    }


}
