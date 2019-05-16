package Model;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static final double chanceOfGeneratingTwo = 0.7158;
    private Account account;
    private int n;
    private Block[][] board; //TODO Must Be an Object
    private int score = 0;


    public Game(Account account, int n) {
        this.account = account;
        this.n = n;
        board = new Block[n][n];
    }

    public void randomNumberPutter(int numberOfRandoms) {
        ArrayList<Integer> emptyCells = gettingEmptyCells();
        for (int i = 0; i < (numberOfRandoms < emptyCells.size() ? numberOfRandoms : emptyCells.size()); i++) {
            Random random = new Random();
            int pointIndex = random.nextInt(gettingEmptyCells().size());
            int point = emptyCells.get(pointIndex);
            double chance = random.nextDouble();
            int randNumber2or4;
            if (chance < chanceOfGeneratingTwo) { //Generate 2 more than 4
                randNumber2or4 = 2;
            } else {
                randNumber2or4 = 4;
            }
            int row = point / getN();
            int column = point % getN();
            board[row][column] = new Block(randNumber2or4);
            emptyCells.remove(pointIndex);
        }
    }

    public void setChangeOfBlocksToFalse()
    {
        for (int i =0;i<getN();i++)
        {
            for (int j=0;j<getN();j++)
            {
                if (getBoard()[i][j]!=null)
                {
                    getBoard()[i][j].setChanged(false);
                }
            }
        }
    }

    public void shiftColumnDown(int column) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < getN(); i++) {
                for (int j = getN() - 1 - 1; j >= 0; j--) {
                    if (getBoard()[j][column] != null && getBoard()[j + 1][column] == null) {
                        getBoard()[j + 1][column] = getBoard()[j][column];
                        getBoard()[j][column] = null;
                    } else if (getBoard()[j][column] != null && getBoard()[j + 1][column] != null &&
                            getBoard()[j + 1][column].getNum() == getBoard()[j][column].getNum()
                            && !getBoard()[j + 1][column].isChanged() && !getBoard()[j][column].isChanged()) {
                        getBoard()[j + 1][column] = getBoard()[j][column];
                        getBoard()[j][column] = null;
                        getBoard()[j + 1][column].doubleNum();
                        getBoard()[j + 1][column].setChanged(true);
                    }
                }
            }
        });
        t1.start();
    }

    public void shiftColumnUp(int column) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < getN(); i++) {
                for (int j = 1; j <= getN() - 1; j++) {
                    if (getBoard()[j][column] != null && getBoard()[j - 1][column] == null) {
                        getBoard()[j - 1][column] = getBoard()[j][column];
                        getBoard()[j][column] = null;
                    } else if (getBoard()[j][column] != null && getBoard()[j - 1][column] != null &&
                            getBoard()[j - 1][column].getNum() == getBoard()[j][column].getNum()
                            && !getBoard()[j - 1][column].isChanged() && !getBoard()[j][column].isChanged()) {
                        getBoard()[j - 1][column] = getBoard()[j][column];
                        getBoard()[j][column] = null;
                        getBoard()[j - 1][column].doubleNum();
                        getBoard()[j - 1][column].setChanged(true);
                    }
                }
            }
        });
        t1.start();
    }

    public void shiftRowLeft(int row) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < getN(); i++) {
                for (int j = 1; j <= getN() - 1; j++) {
                    if (getBoard()[row][j] != null && getBoard()[row][j - 1] == null) {
                        getBoard()[row][j - 1] = getBoard()[row][j];
                        getBoard()[row][j] = null;
                    } else if (getBoard()[row][j] != null && getBoard()[row][j-1] != null &&
                            getBoard()[row][j-1].getNum() == getBoard()[row][j].getNum()
                            && !getBoard()[row][j-1].isChanged() && !getBoard()[row][j].isChanged()) {
                        getBoard()[row][j-1] = getBoard()[row][j];
                        getBoard()[row][j] = null;
                        getBoard()[row][j-1].doubleNum();
                        getBoard()[row][j-1].setChanged(true);
                    }
                }
            }
        });
        t1.start();
    }

    public void shiftRowRight(int row) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < getN(); i++) {
                for (int j = getN() - 1 - 1; j >= 0; j--) {
                    if (getBoard()[row][j] != null && getBoard()[row][j + 1] == null) {
                        getBoard()[row][j + 1] = getBoard()[row][j];
                        getBoard()[row][j] = null;
                    } else if (getBoard()[row][j] != null && getBoard()[row][j+1] != null &&
                            getBoard()[row][j+1].getNum() == getBoard()[row][j].getNum()
                            && !getBoard()[row][j+1].isChanged() && !getBoard()[row][j].isChanged()) {
                        getBoard()[row][j+1] = getBoard()[row][j];
                        getBoard()[row][j] = null;
                        getBoard()[row][j+1].doubleNum();
                        getBoard()[row][j+1].setChanged(true);
                    }
                }
            }
        });
        t1.start();
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
                if (board[i][j] == null) {
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

    public Block[][] getBoard() {
        return board;
    }

    public void setBoard(Block[][] board) {
        this.board = board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
