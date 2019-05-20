package ViewFXML;

import Model.Account;
import Model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.regex.Pattern;


public class MainMenuFXMLController {
    @FXML
    public Button btnPlayGame;

    @FXML
    public Label lblLoginedUser;

    public  void handleLeaderboard() {
        Pane root = null;
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
        Game game =new Game(Account.getLoginedAccount(),4);
        Pane root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new  FXMLLoader(getClass().getResource("./GameBoard.fxml"));
            root = fxmlLoader.load();
            int i =0;
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Container.scenes.addLast(scene);
        Container.stage.setScene(Container.scenes.getLast());
        game.randomNumberPutter(2);//TODO MAKE THIS COMPLETE MUST WORK FOR ALL N
        GameBoardFXMLController controller = (GameBoardFXMLController) fxmlLoader.getController();
        controller.setGame(game);
        controller.blockMaker();
        root.getChildren().addAll(controller.blocks);
        controller.blockPainter();

        Container.stage.show();

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
