package ViewFXML;

import Model.Account;
import ProgramExceptions.MyExceptions;
import ProgramExceptions.UserExistException;
import View.ConstantMessages;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sun.tools.jar.Main;

import java.io.IOException;
import java.util.Deque;


public class LoginFXMLController {
    @FXML
    public Button btnLogin;

    @FXML
    public TextField txtUsername;

    @FXML
    public TextField txtPassword;

    @FXML
    public Label lblStatus;
    @FXML
    public Label lblLoginedUser;


public void handleBtnMainMenu()
{
    Parent root = null;
    FXMLLoader fxmlLoader = null;
    try {
        fxmlLoader = new  FXMLLoader(getClass().getResource("./MainMenu.fxml"));
        root = fxmlLoader.load();
        int i =0;
        System.out.println(i);
    } catch (IOException e) {
        e.printStackTrace();
    }
    Scene scene = new Scene(root);
    Container.scenes.addLast(scene);
    Container.stage.setScene(Container.scenes.getLast());
    Container.stage.show();
}

    public void updateLoginedUser() {
        if (Account.getLoginedAccount() == null) {
            lblLoginedUser.setText("No User Logined");
        } else {
            lblLoginedUser.setText(Account.getLoginedAccount().getUsername());
        }
    }

    public void handleBtnLogout() {
        Account.setLoginedAccount(null);
        updateLoginedUser();
    }

    public void handleBtnLogin() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        try {
            Account.login(username, password);
            lblStatus.setText(ConstantMessages.LOGIN_SUCCESSFUL.getMessage());
            lblStatus.setStyle("-fx-text-fill: black");
            lblStatus.setVisible(true);
            updateLoginedUser();

        } catch (MyExceptions e) {
            lblStatus.setText(e.getMessage());
            lblStatus.setStyle("-fx-text-fill: red");
            lblStatus.setVisible(true);
        }

    }

    public void handleSignUp() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        try {
            Account account = new Account(username, password);
            lblStatus.setText(ConstantMessages.USER_CREATED.getMessage() + username);
            lblStatus.setStyle("-fx-text-fill: black");
            lblStatus.setVisible(true);
            updateLoginedUser();
        } catch (UserExistException e) {
            lblStatus.setText(e.getMessage());
            lblStatus.setStyle("-fx-text-fill: red");
            lblStatus.setVisible(true);
        }

    }
}
