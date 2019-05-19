package ViewFXML;

import Model.Account;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LeaderboardFXMLController {

    @FXML
    public Button btnExit;
    @FXML
    public GridPane gridLeaderBoard;

    public void makeGrid() {

        //THIS COMMENTS ARE FOR TEST PURPOSE OF THE GRID!
//        Account account = new Account("User1" , "123");
//        Account account1 = new Account("User2" , "1234");
//        Account account2 = new Account("User3" , "1");
//        Account account3 = new Account("User4" , "2");
//        Account account4= new Account("User5" , "2");
//        account.setHighscore(100);
//        account1.setHighscore(200);
//        account2.setHighscore(200);
//        account3.setHighscore(200);
//        account4.setHighscore(200);
//        Account.sortAccounts();
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
