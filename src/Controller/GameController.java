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

    public static void  login (String username , String password)
    {
        if (Account.getLoginedAccount()!=null && Account.getLoginedAccount().getUsername().equals(username))
        {
            throw new AlreadyLoginedException();
        }
        if(!Account.accountExist(username))
        {
            throw new NoUserExistException();
        }
        Account account = Account.findUser(username);
        if (!account.getPassword().equals(password))
        {
            throw new InvalidPasswordException();
        }
        Account.setLoginedAccount(account);
        menus.add(new MainMenu());
    }

    public static void accountCreatorValueGettings()
    {
        String username = AccountMenu.usernamePrompt();
        String password = AccountMenu.passwordPrompt();
        accountCreator(username,password);
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
    public static void createNewGameInit()
    {
        try {
            int n = MainMenu.sizeOfBoardGetter();
            createGame(n);
        }
        catch (InvalidBoardSizeException e)
        {
            AbsMenu.errorPrinter(e);
        }
    }
    public static void createGame(int n)
    {
        Game game = new Game(Account.getLoginedAccount(),n);
        menus.add(new GameMenu(game));
    }
    public static void logout()
    {
        Account.setLoginedAccount(null);
    }
    public static void currentAccountPresenter()
    {
        AccountMenu.showAccount(Account.getLoginedAccount());
    }
    public static void exit()
    {
        System.exit(0);
    }

    public static void backToLastMenu() {
        if (menus.size() > 1) {
            menus.pollLast();
        }
    }

}
