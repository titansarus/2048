package View;

import Controller.GameController;
import ProgramExceptions.MyExceptions;
import ProgramExceptions.UserExistException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.ScannerStatic.scanner;

public class AccountMenu extends AbsMenu {
    private static final int HELP = 0, BACK = 1, CREATE_ACCOUNT = 2 , LOGIN_ACCOUNT = 3;
    public static ArrayList<Pattern> patterns = new ArrayList<>();

    {
        patterns.add(Pattern.compile("^(?i)help$"));
        patterns.add(Pattern.compile("^(?i)back$"));
        patterns.add(Pattern.compile("(?i)create account"));
        patterns.add(Pattern.compile("^login (\\S+) (\\S+)$"));
    }


    @Override
    public void functionDeterminer(Matcher matcher, int i) {
        if (i == HELP) {
            this.showHelp();
        }
        if (i == CREATE_ACCOUNT) {
            try {
                System.out.println(ConstantMessages.USERNAME_PROMPT.getMessage());
                String username = scanner.nextLine();

                System.out.println(ConstantMessages.PASSWORD_PROMPT.getMessage());
                String password = scanner.nextLine();
                GameController.accountCreator(username, password);
            } catch (MyExceptions e) {
                errorPrinter(e);
            }
        }
        if (i == BACK) {
            GameController.backToLastMenu();
        }
        if (i == LOGIN_ACCOUNT)
        {
            String user = matcher.group(1);
            String pass = matcher.group(2);
            try{
                GameController.login(user,pass);
            }
            catch (MyExceptions e)
            {
                errorPrinter(e);
            }
        }
    }

    @Override
    public void getInput() {
        getInputFromPatter(patterns);
    }

    @Override
    public void showMenu() {

    }

    @Override
    public void showHelp() {
        System.out.println("YOU ARE IN ACCOUNT MENU!");
    }
}
