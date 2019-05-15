package View;


import Controller.GameController;
import Model.Account;
import Model.Game;
import ProgramExceptions.MyExceptions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu extends AbsMenu {

    private static final int HELP = 0, BACK = 1;
    public static ArrayList<Pattern> patterns = new ArrayList<>();

    {
        patterns.add(Pattern.compile("^(?i)help\\s*$"));
        patterns.add(Pattern.compile("^(?i)back\\s*$"));

    }


    Game game;

    public GameMenu(Game game) {
        this.game = game;
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
        System.out.println("You are in Game!");
    }

    @Override
    public void functionDeterminer(Matcher matcher, int i) {
        if (i == HELP) {
            this.showHelp();
        }
        if (i == BACK) {
            GameController.backToLastMenu();
        }

    }
}
