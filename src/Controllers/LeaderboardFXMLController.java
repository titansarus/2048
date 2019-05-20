package Controllers;

import Model.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LeaderboardFXMLController {

    @FXML
    public Button btnExit;
    @FXML
    public GridPane gridLeaderBoard;
    @FXML
    public Label lblLoginedUser;

    void updateLoginedUser() {
        if (Account.getLoginedAccount() == null) {
            lblLoginedUser.setText("No User Logined");
        } else {
            lblLoginedUser.setText(Account.getLoginedAccount().getUsername());
        }
    }

    void makeGrid() {


        Account.sortAccounts();
        int n = Account.getAllOfAccounts().size();
        gridLeaderBoard.add(new Label("Rank"), 0, 0);
        gridLeaderBoard.add(new Label("Username"), 1, 0);
        gridLeaderBoard.add(new Label("High Score"), 2, 0);
        for (int i = 1; i <= n; i++) {
            gridLeaderBoard.add(new Label(String.valueOf(i)), 0, i);
        }
        for (int i = 1; i <= n; i++) {
            gridLeaderBoard.add(new Label(Account.getAllOfAccounts().get(i - 1).getUsername()), 1, i);
        }

        for (int i = 1; i <= n; i++) {
            gridLeaderBoard.add(new Label(String.valueOf(Account.getAllOfAccounts().get(i - 1).getHighscore())), 2, i);
        }
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
