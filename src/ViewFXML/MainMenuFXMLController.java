package ViewFXML;

import Model.Account;
import Model.Game;
import ProgramExceptions.InvalidBoardSizeException;
import ProgramExceptions.InvalidPasswordException;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;


public class MainMenuFXMLController {
    public static final int CLOSE_NUMBER = -1;
    @FXML
    public Button btnPlayGame;

    @FXML
    public Label lblLoginedUser;



    public void handleLeaderboard() {
        Pane root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("./Leaderboard.fxml"));
            root = fxmlLoader.load();
            int i = 0;
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

    public int gettingSizeOfBoardFromUser() {
        int n = CLOSE_NUMBER;
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Size of Board");
        textInputDialog.setHeaderText("Please Enter a number between 4 to 10");
        textInputDialog.setContentText("Board Size (n):");
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            try {
                n = Integer.parseInt(result.get());
                if (n > 10 || n < 4) {
                    n = CLOSE_NUMBER;
                    throw new InvalidBoardSizeException();
                }
            } catch (NumberFormatException e) {
                throw new InvalidBoardSizeException();

            }
        }

        return n;
    }

    public void handlePlayGame() {
        int n = 4;
        try {
            n = gettingSizeOfBoardFromUser();
        } catch (InvalidBoardSizeException e) {
            Container.alertShower(e, "Invalid BoardSize");
            return;
        }

        Game game = new Game(Account.getLoginedAccount(), n);
        Pane root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("./GameBoard.fxml"));
            root = fxmlLoader.load();
            int i = 0;
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
        root.getChildren().addAll(controller.blockTexts);
        controller.blockPainter();
        controller.updateLoginedUser();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) ->
        {
            if (key.getCode() == KeyCode.LEFT) {
                if (game.checkIsLeftMovePossibleForBoard()) {

                    game.shiftLeft();
                    //game.randomNumberPutter(1);
                } else {
                    return;
                }
            }
            if (key.getCode() == KeyCode.RIGHT) {
                if (game.checkIsRightMovePossibleForBoard()) {

                    game.shiftRight();
                    //game.randomNumberPutter(1);
                } else {
                    return;
                }
            }
            if (key.getCode() == KeyCode.UP) {
                if (game.checkIsUpMovePossibleForBoard()) {
                    game.shiftUp();
                    //game.randomNumberPutter(1);
                } else {
                    return;
                }
            }
            if (key.getCode() == KeyCode.DOWN) {
                if (game.checkIsDownMovePossibleForBoard()) {
                    game.shiftDown();
                    //game.randomNumberPutter(1);
                } else {
                    return;
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game.setChangeOfBlocksToFalse();
            game.randomNumberPutter(1);
            controller.blockPainter();
            controller.updateScoreLabel();
            if(!game.checkIsAnyMovePossible())
            {
                controller.handleBack();
            }

        });

        //TODO CHECK ENDING CONDITION

        Container.stage.show();

    }

    public void handleBack() {
        if (Container.scenes.size() > 0) {
            Container.scenes.removeLast();
            Container.stage.setScene(Container.scenes.getLast());

            Container.stage.show();
        }


    }

    public void handleExit() {
        System.exit(0);
    }

}
