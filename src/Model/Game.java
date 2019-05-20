package Model;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static final double chanceOfGeneratingTwo = 0.7158;
    private Account account;
    private int n;
    private Block[][] board; //TODO Must Be an Object
    private int score = 0;
    private final Object score_lock = new Object();


    public Game(Account account, int n) {
        this.account = account;
        this.n = n;
        board = new Block[n][n];
    }


    public boolean checkIsAnyMovePossible() {
        if (isAnyEmptyCells()) {
            return true;
        } else {
            for (int i = 0; i < getN(); i++) {
                for (int j = 0; j < getN(); j++) {
                    if (checkIfBlockCanMove(getBoard()[i][j], i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean checkIsUpMovePossibleForBoard() {
        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getN(); j++) {
                if (getBoard()[i][j] != null && checkIfUpMovePossibleForBlock(getBoard()[i][j], i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIsDownMovePossibleForBoard() {
        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getN(); j++) {
                if (getBoard()[i][j] != null && checkIfDownMovePossibleForBlock(getBoard()[i][j], i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIsLeftMovePossibleForBoard() {
        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getN(); j++) {
                if (getBoard()[i][j] != null && checkIfLeftMovePossibleForBlock(getBoard()[i][j], i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIsRightMovePossibleForBoard() {
        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getN(); j++) {
                if (getBoard()[i][j] != null && checkIfRightMovePossibleForBlock(getBoard()[i][j], i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIfBlockCanMove(Block block, int row, int column) {
        if (block == null) {
            return false;
        }
        return (checkIfUpMovePossibleForBlock(block, row, column) || checkIfDownMovePossibleForBlock(block, row, column)
                || checkIfRightMovePossibleForBlock(block, row, column) || checkIfLeftMovePossibleForBlock(block, row, column));


    }

    public boolean checkIfLeftMovePossibleForBlock(Block block, int row, int column) {
        return (block != null && column - 1 >= 0 && (board[row][column - 1] == null || board[row][column - 1].getNum() == block.getNum()));
    }

    public boolean checkIfRightMovePossibleForBlock(Block block, int row, int column) {
        return (block != null && column + 1 < getN() && (board[row][column + 1] == null || board[row][column + 1].getNum() == block.getNum()));
    }

    public boolean checkIfDownMovePossibleForBlock(Block block, int row, int column) {
        return (block != null && row + 1 < getN() && (board[row + 1][column] == null || board[row + 1][column].getNum() == block.getNum()));
    }

    public boolean checkIfUpMovePossibleForBlock(Block block, int row, int column) {
        return (block != null && row - 1 >= 0 && (board[row - 1][column] == null || board[row - 1][column].getNum() == block.getNum()));
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

    public void setChangeOfBlocksToFalse() {
        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getN(); j++) {
                if (getBoard()[i][j] != null) {
                    getBoard()[i][j].setChanged(false);
                }
            }
        }
    }

    public void shiftColumnDown(int column) {
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

                        int score_increase = getBoard()[j + 1][column].getNum() * 2;

                        getBoard()[j + 1][column].doubleNum();
                        getBoard()[j + 1][column].setChanged(true);
                        synchronized (score_lock) {
                            increaseScore(score_increase);
                        }
                    }
                }
            }
    }

    public void shiftColumnUp(int column) {
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

                        int score_increase = getBoard()[j - 1][column].getNum() * 2;

                        getBoard()[j - 1][column].doubleNum();
                        getBoard()[j - 1][column].setChanged(true);
                        synchronized (score_lock) {
                            increaseScore(score_increase);
                        }
                    }
                }
            }

    }

    public void shiftRowLeft(int row) {
            for (int i = 0; i < getN(); i++) {
                for (int j = 1; j <= getN() - 1; j++) {
                    if (getBoard()[row][j] != null && getBoard()[row][j - 1] == null) {
                        getBoard()[row][j - 1] = getBoard()[row][j];
                        getBoard()[row][j] = null;
                    } else if (getBoard()[row][j] != null && getBoard()[row][j - 1] != null &&
                            getBoard()[row][j - 1].getNum() == getBoard()[row][j].getNum()
                            && !getBoard()[row][j - 1].isChanged() && !getBoard()[row][j].isChanged()) {
                        getBoard()[row][j - 1] = getBoard()[row][j];
                        getBoard()[row][j] = null;

                        int score_increase = getBoard()[row][j - 1].getNum() * 2;

                        getBoard()[row][j - 1].doubleNum();
                        getBoard()[row][j - 1].setChanged(true);
                        synchronized (score_lock) {
                            increaseScore(score_increase);
                        }
                    }
                }
            }

    }

    public void shiftRowRight(int row) {
        {
            for (int i = 0; i < getN(); i++) {
                for (int j = getN() - 1 - 1; j >= 0; j--) {
                    if (getBoard()[row][j] != null && getBoard()[row][j + 1] == null) {
                        getBoard()[row][j + 1] = getBoard()[row][j];
                        getBoard()[row][j] = null;
                    } else if (getBoard()[row][j] != null && getBoard()[row][j + 1] != null &&
                            getBoard()[row][j + 1].getNum() == getBoard()[row][j].getNum()
                            && !getBoard()[row][j + 1].isChanged() && !getBoard()[row][j].isChanged()) {
                        getBoard()[row][j + 1] = getBoard()[row][j];
                        getBoard()[row][j] = null;

                        int score_increase = getBoard()[row][j + 1].getNum() * 2;

                        getBoard()[row][j + 1].doubleNum();
                        getBoard()[row][j + 1].setChanged(true);
                        synchronized (score_lock) {
                            increaseScore(score_increase);
                        }
                    }
                }
            }
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
                if (board[i][j] == null) {
                    result.add(i * n + j);
                }
            }
        }
        return result;
    }

    public void increaseScore(int i) {
        setScore(getScore() + i);
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

    public void shiftDown() {
        for (int i = 0; i < this.getN(); i++) {
            this.shiftColumnDown(i);
        }

    }

    public void shiftUp() {
        for (int i = 0; i < this.getN(); i++) {
            this.shiftColumnUp(i);
        }


    }

    public  void shiftRight() {
        for (int i = 0; i < this.getN(); i++) {
            this.shiftRowRight(i);
        }

    }

    public void shiftLeft() {
        for (int i = 0; i < this.getN(); i++) {
            this.shiftRowLeft(i);
        }
    }
}
