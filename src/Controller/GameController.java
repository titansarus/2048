package Controller;

import Model.Account;
import Model.Game;
import ProgramExceptions.*;
import View.*;

import java.util.Deque;
import java.util.LinkedList;


public class GameController {
    public static Deque<AbsMenu> menus = new LinkedList<>();

    {
        menus.add(new AccountMenu());
    }

    public void start() {
        while (true) {
            menus.peekLast().getInput();
        }
    }

    public static void login(String username, String password) {
        if (Account.getLoginedAccount() != null && Account.getLoginedAccount().getUsername().equals(username)) {
            throw new AlreadyLoginedException();
        }
        if (!Account.accountExist(username)) {
            throw new NoUserExistException();
        }
        Account account = Account.findUser(username);
        if (!account.getPassword().equals(password)) {
            throw new InvalidPasswordException();
        }
        Account.setLoginedAccount(account);
        menus.add(new MainMenu());
    }

    public static void accountCreatorValueGettings() {
        String username = AccountMenu.usernamePrompt();
        String password = AccountMenu.passwordPrompt();
        accountCreator(username, password);
    }

    public static void accountCreator(String username, String password) {
        if (!Account.accountExist(username)) {
            Account account = new Account(username, password);
            // Account.setLoginedAccount(account);
            AbsMenu.notificationPrinter(ConstantMessages.USER_CREATED.getMessage() + username);
            // menus.add(new MainMenu());
        } else {
            throw new UserExistException();
        }
    }

    public static void createNewGameInit() {
        try {
            int n = MainMenu.sizeOfBoardGetter();
            createGame(n);
        } catch (InvalidBoardSizeException e) {
            AbsMenu.errorPrinter(e);
        }
    }

    public static void createGame(int n) {
        Game game = new Game(Account.getLoginedAccount(), n);
        game.randomNumberPutter(2);
        menus.add(new GameMenu(game));
    }

    public static void mover(Game game, char c) {
        if (c == 'w' || c == 'W') {
            if (game.checkIsUpMovePossibleForBoard()) {
                shiftUp(game);
            }
            else
            {
                return;
            }
        }
        if (c == 'd' || c == 'D') {
            if (game.checkIsRightMovePossibleForBoard()) {
                shiftRight(game);
            }
            else
            {
                return;
            }
        }
        if (c == 's' || c == 'S') {
            if (game.checkIsDownMovePossibleForBoard()) {
                shiftDown(game);
            }
            else {
                return;
            }
        }
        if (c == 'a' || c == 'A') {
            if(game.checkIsLeftMovePossibleForBoard()) {
                shiftLeft(game);
            }
            else
            {
                return;
            }
        }
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        game.setChangeOfBlocksToFalse();
        game.randomNumberPutter(1);
        if(!game.checkIsAnyMovePossible())
        {
            //TODO ADD A GAME ENDING STATEMENT
            backFromGame(game);
            return;
        }

        ((GameMenu) menus.peekLast()).showBoard(game.getBoard(),game.getN());
    }


    public static void shiftDown(Game game) {
        for (int i = 0; i < game.getN(); i++) {
            game.shiftColumnDown(i);
        }

    }

    public static void shiftUp(Game game) {
        for (int i = 0; i < game.getN(); i++) {
            game.shiftColumnUp(i);
        }

    }

    public static void shiftRight(Game game) {
        for (int i = 0; i < game.getN(); i++) {
            game.shiftRowRight(i);
        }

    }

    public static void shiftLeft(Game game) {
        for (int i = 0; i < game.getN(); i++) {
            game.shiftRowLeft(i);
        }
    }

    public static void backFromGame(Game game)
    {
        Account account = game.getAccount();
        if (account.getHighscore()<game.getScore())
        {
            account.setHighscore(game.getScore());
        }
        backToLastMenu();
    }

    public static void showleaderboard()
    {
        Account.sortAccounts();
        AccountMenu.showAllAccounts(Account.getAllOfAccounts());
    }

    public static void logout() {
        Account.setLoginedAccount(null);
    }

    public static void currentAccountPresenter() {
        AccountMenu.showAccount(Account.getLoginedAccount());
    }

    public static void exit() {
        System.exit(0);
    }

    public static void backToLastMenu() {
        if (menus.size() > 1) {
            menus.pollLast();
        }
    }

}
