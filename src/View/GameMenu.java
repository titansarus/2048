package View;


import Controller.GameController;
import Model.Account;
import Model.Block;
import Model.Game;
import ProgramExceptions.MyExceptions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu extends AbsMenu {

    private static final int HELP = 0, BACK = 1, SHOW_BOARD = 2, MOVE = 3 , SHOW_SCORE=4,EXIT=5;
    public static ArrayList<Pattern> patterns = new ArrayList<>();
    Game game;


    {
        patterns.add(Pattern.compile("^(?i)help\\s*$"));
        patterns.add(Pattern.compile("^(?i)back\\s*$"));
        patterns.add(Pattern.compile("^(?i)Show Board\\s*$"));
        patterns.add(Pattern.compile("^(?i)([WDSA])\\s*$"));
        patterns.add(Pattern.compile("^(?i)Show Score\\s*$"));
        patterns.add(Pattern.compile("^(?i)exit\\s*$"));
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
//        for (int i = 0; i < game.getN(); i++) {
//            game.shiftRowLeft(i);
//        }
    }

    public void showBoard(Block[][] blocks, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != null) {
                    System.out.format("%d ", blocks[i][j].getNum());
                } else {
                    System.out.format("0 ");
                }
            }
            System.out.println();
        }
    }
    public void showScore(int n)
    {
        System.out.println("Score: "+n);
    }

    @Override
    public void functionDeterminer(Matcher matcher, int i) {
        if (i == HELP) {
            //   game.randomNumberPutter(2);
            this.showHelp();
        }
        if (i == BACK) {
            GameController.backFromGame(game);
        }
        if (i == SHOW_BOARD) {

            showBoard(game.getBoard(), game.getN());
        }
        if (i == MOVE) {
            char c = matcher.group(1).charAt(0);
            GameController.mover(game, c);
        }
        if (i==SHOW_SCORE)
        {
            showScore(game.getScore());
        }
        if(i==EXIT)
        {
            GameController.exit();
        }

    }
}
