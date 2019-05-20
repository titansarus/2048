import Controllers.Container;
import Controllers.LoginFXMLController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;

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
