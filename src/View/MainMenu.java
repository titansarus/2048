package View;

import Controller.GameController;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.ScannerStatic.scanner;
public class MainMenu extends AbsMenu {
    private static final int HELP = 0 , BACK = 1 , CREATE_ACCOUNT = 2;
    public static ArrayList<Pattern> patterns = new ArrayList<>();
    {
        patterns.add(Pattern.compile("^(?i)help$"));
        patterns.add(Pattern.compile("^(?i)back$"));
        patterns.add(Pattern.compile("(?i)create account"));
    }


    @Override
    public void functionDeterminer(Matcher matcher , int i)
    {
        if (i==HELP)
        {
            this.showHelp();
        }
        if (i==CREATE_ACCOUNT)
        {
            String username = scanner.nextLine();
            String password = scanner.nextLine();
            GameController.accountCreator(username,password);
        }
        if (i==BACK)
        {
            GameController.backToLastMenu();
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
        System.out.println("YOU ARE IN MAIN MENU!");
    }

}
