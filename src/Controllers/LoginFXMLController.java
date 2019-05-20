package Controllers;

import Model.Account;
import ProgramExceptions.MyExceptions;
import ProgramExceptions.NoLoginedAccountException;
import ViewFXML.ConstantMessages;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


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
    if (Account.getLoginedAccount()!=null) {
        Pane root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/ViewFXML/MainMenu.fxml"));
            root = fxmlLoader.load();
            int i = 0;
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Container.scenes.addLast(scene);
        Container.stage.setScene(Container.scenes.getLast());
        ((MainMenuFXMLController) fxmlLoader.getController()).updateLoginedUser();
        Container.stage.show();
    }
    else{
        Container.alertShower(new NoLoginedAccountException() , "No Logined Account");
    }
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
            Container.alertShower(e,"Login Failed");

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
        } catch (MyExceptions e) {
            lblStatus.setText(e.getMessage());
            lblStatus.setStyle("-fx-text-fill: red");
            lblStatus.setVisible(true);
            Container.alertShower (e , "Sign Up Failed");
        }

    }



}