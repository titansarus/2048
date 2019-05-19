package ViewFXML;

import Model.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class MainMenuFXMLController {
    @FXML
    public Button btnPlayGame;

    @FXML
    public Label lblLoginedUser;

    public  void handleLeaderboard() {
        Parent root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new  FXMLLoader(getClass().getResource("./Leaderboard.fxml"));
            root = fxmlLoader.load();
            int i =0;
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Container.scenes.addLast(scene);
        Container.stage.setScene(Container.scenes.getLast());

        ((LeaderboardFXMLController) fxmlLoader.getController()).makeGrid();
        ((LeaderboardFXMLController) fxmlLoader.getController()).updateLoginedUser();
        Container.stage.show();

    }
    public void updateLoginedUser() {
        if (Account.getLoginedAccount() == null) {
            lblLoginedUser.setText("No User Logined");
        } else {
            lblLoginedUser.setText(Account.getLoginedAccount().getUsername());
        }
    }

    public  void handlePlayGame() {

    }

    public  void handleBack() {
        if (Container.scenes.size()>0) {
            Container.scenes.removeLast();
            Container.stage.setScene(Container.scenes.getLast());

            Container.stage.show();
        }


    }

    public  void handleExit() {
        System.exit(0);
    }

}
