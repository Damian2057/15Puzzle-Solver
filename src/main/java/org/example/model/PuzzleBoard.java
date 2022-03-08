package org.example.model;

public class PuzzleBoard implements Cloneable{

    private int[][] board;
    private String recentMove;
    private int index = 0;
    private Field emptyField;

    private final int width;
    private final int height;

    public PuzzleBoard(int[][] board, int width, int height) {
        this.board = board;
        this.width = width;
        this.height = height;
        this.recentMove = "Initialize";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 0) {
                    emptyField = new Field(i,j);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                    stringBuilder.append("[").append(board[i][j]).append("]");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public Field getEmptyField() {
        return emptyField;
    }

    public void setEmptyField(Field emptyField) {
        this.emptyField = emptyField;
    }

    public int getEmptyFieldXCoordinate() {
        return emptyField.getX();
    }

    public int getEmptyFieldYCoordinate() {
        return emptyField.getY();
    }

    public String getRecentMove() {
        return recentMove;
    }

    public int getIndex() {
        return index;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void moveEmptyFieldRight() {
        this.recentMove = "R";
        index ++;
        this.board[emptyField.getX()][emptyField.getY()]
                = this.board[emptyField.getX()][emptyField.getY()+1];
        this.board[emptyField.getX()][emptyField.getY()+1] = 0;
        emptyField.setY(emptyField.getY()+1);
    }

    public void moveEmptyFieldLeft() {
        this.recentMove = "L";
        index ++;
        this.board[emptyField.getX()][emptyField.getY()]
                = this.board[emptyField.getX()][emptyField.getY()-1];
        this.board[emptyField.getX()][emptyField.getY()-1] = 0;
        emptyField.setY(emptyField.getY()-1);
    }

    public void moveEmptyFieldUp() {
        this.recentMove = "U";
        index ++;
        this.board[emptyField.getX()][emptyField.getY()]
                = this.board[emptyField.getX()-1][emptyField.getY()];
        this.board[emptyField.getX()-1][emptyField.getY()] = 0;
        emptyField.setX(emptyField.getX()-1);
    }

    public void moveEmptyFieldDown() {
        this.recentMove = "D";
        index ++;
        this.board[emptyField.getX()][emptyField.getY()]
                = this.board[emptyField.getX()+1][emptyField.getY()];
        this.board[emptyField.getX()+1][emptyField.getY()] = 0;
        emptyField.setX(emptyField.getX()+1);
    }


    @Override
    public PuzzleBoard clone() throws CloneNotSupportedException {
        PuzzleBoard clone = (PuzzleBoard) super.clone();
        int[][] board = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = this.board[i][j];
            }
        }
        clone.setEmptyField(this.getEmptyField().clone());
        clone.setBoard(board);
        return clone;
    }

    public boolean checkValidation() {
        return true;
    }
}
