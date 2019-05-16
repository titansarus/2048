package Model;

public class Block {
    int num;
    boolean isChanged = false;

    public void doubleNum()
    {
        setNum(getNum()*2);
    }

    public Block(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }
}
