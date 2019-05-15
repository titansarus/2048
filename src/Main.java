import Controller.GameController;
import View.MatchPatterns;

public class Main {

    public static void main(String[] args) {
        new MatchPatterns();
        GameController gameController = new GameController();
        gameController.start();
    }
}
