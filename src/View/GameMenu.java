package View;


import Controller.GameController;
import Model.Account;
import Model.Game;
import ProgramExceptions.MyExceptions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu extends AbsMenu {

    private static final int HELP = 0, BACK = 1, SHOW_BOARD = 2;
    public static ArrayList<Pattern> patterns = new ArrayList<>();
    Game game;


    {
        patterns.add(Pattern.compile("^(?i)help\\s*$"));
        patterns.add(Pattern.compile("^(?i)back\\s*$"));
        patterns.add(Pattern.compile("^(?i)Show Board\\s*$"));

    }


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
    public void showBoard(int[][] a , int n)
    {
        for (int i =0; i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                System.out.format("%d ",a[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void functionDeterminer(Matcher matcher, int i) {
        if (i == HELP) {
         //   game.randomNumberPutter(2);
            this.showHelp();
        }
        if (i == BACK) {
            GameController.backToLastMenu();
        }
        if (i==SHOW_BOARD)
        {

            showBoard(game.getBoard() , game.getN());
        }

    }
}
