package Model;

import java.util.ArrayList;
import java.util.Random;

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

    public void randomNumberPutter(int numberOfRandoms) {
        ArrayList<Integer> emptyCells = gettingEmptyCells();
        for (int i = 0; i < (numberOfRandoms < emptyCells.size() ? numberOfRandoms : emptyCells.size()); i++) {
            Random random = new Random();
            int pointIndex = random.nextInt(gettingEmptyCells().size());
            int point = emptyCells.get(pointIndex);
            int[] nums = new int[]{2,4};
            int numIndex = random.nextInt(2);
            int row = point/getN();
            int column = point%getN();
            board[row][column] =nums[numIndex];
            emptyCells.remove(pointIndex);
        }
    }

    public boolean isAnyEmptyCells() {
        ArrayList<Integer> emptyCells = gettingEmptyCells();
        if (emptyCells.size() > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<Integer> gettingEmptyCells() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    result.add(i * n + j);
                }
            }
        }
        return result;
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
