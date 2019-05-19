package Model;

import ProgramExceptions.EmptyFieldException;
import ProgramExceptions.InvalidPasswordException;
import ProgramExceptions.NoUserExistException;
import ProgramExceptions.UserExistException;

import java.util.ArrayList;
import java.util.Collections;

public class Account implements Comparable<Account> {
    private static ArrayList<Account> allOfAccounts = new ArrayList<>();
    private static Account loginedAccount = null;
    private int highscore = 0;
    private String username;
    private String password;


    public Account(String username, String password) {
        if (username.length() <= 0 || password.length() <= 0) {
            throw new EmptyFieldException();
        }
        if (accountExist(username)) {
            throw new UserExistException();
        }
        this.username = username;
        this.password = password;
        addAccount(this);
    }

    public static void login(String username, String password) {
        if (username.length() <= 0 || password.length() <= 0) {
            throw new EmptyFieldException();
        }
        if (!accountExist(username)) {
            throw new NoUserExistException();
        } else if (!findUser(username).getPassword().equals(password)) {
            throw new InvalidPasswordException();
        } else {
            setLoginedAccount(findUser(username));
        }

    }

    public static boolean accountExist(String username) {
        Account account = findUser(username);
        if (account != null) {
            return true;
        }
        return false;
    }

    public static void sortAccounts() {
        Collections.sort(getAllOfAccounts());
    }


    public static Account findUser(String username) {
        for (int i = 0; i < getAllOfAccounts().size(); i++) {
            if (getAllOfAccounts().get(i) != null && getAllOfAccounts().get(i).getUsername().equals(username)) {
                return getAllOfAccounts().get(i);
            }
        }
        return null;
    }

    public static void addAccount(Account account) {
        getAllOfAccounts().add(account);
    }

    public static ArrayList<Account> getAllOfAccounts() {
        return allOfAccounts;
    }

    public static void setAllOfAccounts(ArrayList<Account> allOfAccounts) {
        Account.allOfAccounts = allOfAccounts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public static Account getLoginedAccount() {
        return loginedAccount;
    }

    public static void setLoginedAccount(Account loginedAccount) {
        Account.loginedAccount = loginedAccount;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    @Override
    public String toString() {
        String s = "Account username:" + this.getUsername() + "\t\t\tHighscore:" + getHighscore();
        return s;
    }

    @Override
    public int compareTo(Account o) {
        return o.getHighscore() - this.getHighscore();
    }
}
