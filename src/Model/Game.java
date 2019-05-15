package Model;

public class Game {
    private Account account;
    private int n;
    private int[][] board;
    private int score = 0;


    public Game(Account account, int n) {
        this.account = account;
        this.n = n;
        board = new int[n][n];
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
