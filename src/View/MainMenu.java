package View;

import Controller.GameController;
import ProgramExceptions.InvalidBoardSizeException;
import ProgramExceptions.MyExceptions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.ScannerStatic.scanner;

public class MainMenu extends AbsMenu {
    private static final int HELP = 0, BACK = 1, EXIT = 2, CREATE_GAME = 3;
    public static ArrayList<Pattern> patterns = new ArrayList<>();

    {
        patterns.add(Pattern.compile("^(?i)help\\s*$"));
        patterns.add(Pattern.compile("^(?i)back\\s*$"));
        patterns.add(Pattern.compile("^(?i)exit\\s*$"));
        patterns.add(Pattern.compile("^(?i)create game\\s*$"));
    }


    @Override
    public void functionDeterminer(Matcher matcher, int i) {
        if (i == HELP) {
            this.showHelp();
        }
        if (i == BACK) {
            GameController.backToLastMenu();
        }
        if (i == EXIT) {
            GameController.exit();
        }
        if (i == CREATE_GAME) {
            try {
                GameController.createNewGameInit();

            } catch (MyExceptions e) {
                e.printStackTrace();
            }
        }
    }

    public static int sizeOfBoardGetter() {
        System.out.println("Please Enter Size of Board: (Positive number)");
        String s = scanner.nextLine();
        int n;
        try {
            n = Integer.parseInt(s);
            if (n <= 0) {
                throw new InvalidBoardSizeException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidBoardSizeException();
        }
        return n;
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
        System.out.println("YOU ARE IN MAIN MENU!");
    }

}
