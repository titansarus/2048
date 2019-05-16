package View;

import Controller.GameController;
import Model.Account;
import Model.Game;
import ProgramExceptions.MyExceptions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.ScannerStatic.scanner;

public class AccountMenu extends AbsMenu {
    private static final int HELP = 0, BACK = 1, CREATE_ACCOUNT = 2, LOGIN_ACCOUNT = 3, LOGOUT = 4, SHOW_CURRENT_ACCOUNT = 5, SHOW_LEADERBOARD = 6;
    public static ArrayList<Pattern> patterns = new ArrayList<>();

    {
        patterns.add(Pattern.compile("^(?i)help\\s*$"));
        patterns.add(Pattern.compile("^(?i)back\\s*$"));
        patterns.add(Pattern.compile("^(?i)create account\\s*$"));
        patterns.add(Pattern.compile("^login (\\S+) (\\S+)\\s*$"));
        patterns.add(Pattern.compile("^logout\\s*$"));
        patterns.add(Pattern.compile("^(?i)Show Account\\s*$"));
        patterns.add(Pattern.compile("^(?i)Show Leaderboard\\s*$"));
    }


    @Override
    public void functionDeterminer(Matcher matcher, int i) {
        if (i == HELP) {
            this.showHelp();
        }
        if (i == CREATE_ACCOUNT) {
            try {

                GameController.accountCreatorValueGettings();
            } catch (MyExceptions e) {
                errorPrinter(e);
            }
        }
        if (i == BACK) {
            GameController.backToLastMenu();
        }
        if (i == LOGIN_ACCOUNT) {
            String user = matcher.group(1);
            String pass = matcher.group(2);
            try {
                GameController.login(user, pass);
            } catch (MyExceptions e) {
                errorPrinter(e);
            }
        }

        if (i == LOGOUT) {
            GameController.logout();
        }
        if (i == SHOW_CURRENT_ACCOUNT) {
            GameController.currentAccountPresenter();
        }

        if (i==SHOW_LEADERBOARD)
        {
            GameController.showleaderboard();
        }
    }

    public static String usernamePrompt() {
        System.out.println(ConstantMessages.USERNAME_PROMPT.getMessage());
        String username = scanner.nextLine();
        return username;
    }

    public static String passwordPrompt() {
        System.out.println(ConstantMessages.PASSWORD_PROMPT.getMessage());
        String password = scanner.nextLine();
        return password;
    }

    public static void showAccount(Account account) {
        System.out.println("Account username: " + account.getUsername());
        System.out.println("Highscore: " + account.getHighscore());
    }

    public static void showAllAccounts(ArrayList<Account> accounts) {
        int counter = 1;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) != null) {
                System.out.print(counter++);
                System.out.println(". "+accounts.get(i).toString());
            }
        }
    }

    @Override
    public void getInput() {
        getInputFromPattern(patterns);
    }

    @Override
    public void showMenu() {

    }

    @Override
    public void showHelp() {
        System.out.println("YOU ARE IN ACCOUNT MENU!");
    }
}
