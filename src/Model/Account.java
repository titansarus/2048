package Model;

import java.util.ArrayList;

public class Account {
    private static ArrayList<Account> allOfAccounts = new ArrayList<>();
    private static Account loginedAccount = null;
    private String username;
    private String password;


    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        addAccount(this);
    }

    public static boolean accountExist(String username) {
        Account account = findUser(username);
        if (account != null) {
            return true;
        }
        return false;
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
}
