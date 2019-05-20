package Model;

public class Block {
    private int num;
    private boolean isChanged = false;

    void doubleNum() {
        setNum(getNum() * 2);
    }

    Block(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    private void setNum(int num) {
        this.num = num;
    }

    boolean isChanged() {
        return isChanged;
    }

    void setChanged(boolean changed) {
        isChanged = changed;
    }
}
