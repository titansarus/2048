package Controllers;

import Model.Account;
import Model.Game;
import ProgramExceptions.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Optional;


public class MainMenuFXMLController {
    private static final int CLOSE_NUMBER = -1;
    @FXML
    public Button btnPlayGame;

    @FXML
    public Label lblLoginedUser;


    public void handleLeaderboard() {
        Pane root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/ViewFXML/Leaderboard.fxml"));
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

    void updateLoginedUser() {
        if (Account.getLoginedAccount() == null) {
            lblLoginedUser.setText("No User Logined");
        } else {
            lblLoginedUser.setText(Account.getLoginedAccount().getUsername());
        }
    }

    private int gettingSizeOfBoardFromUser() {
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
            fxmlLoader = new FXMLLoader(getClass().getResource("/ViewFXML/GameBoard.fxml"));
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
        scene.addEventHandler(KeyEvent.KEY_PRESSED, controller.moveEventHandler);
        Container.stage.show();

    }

    public String gettingNewUserName() {
        String newUsername = "";
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("New User Name");
        textInputDialog.setHeaderText("Please Enter a new UserName");
        textInputDialog.setContentText("UserName");
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            newUsername = result.get();
            if (Account.accountExist(newUsername)) {
                throw new UserExistException();
            }
            if (newUsername.length() <= 0) {
                throw new EmptyFieldException();
            }

        }

        return newUsername;
    }

    public void handlChangeUserName() {
        String newUserName = "";
        try {

            newUserName = gettingNewUserName();
        } catch (MyExceptions e) {
            Container.alertShower(e, "Invalid UserName");
            return;
        }

        Account.getLoginedAccount().setUsername(newUserName);
        updateLoginedUser();

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