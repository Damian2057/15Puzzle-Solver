public class Field implements Cloneable {
    //keep track of where a blank space is in the board
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Field clone() {
        try {
            return (Field) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
