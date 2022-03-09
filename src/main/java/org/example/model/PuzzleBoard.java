package org.example.model;

import org.example.Exceptions.CloneException;
import org.jetbrains.annotations.NotNull;

public class PuzzleBoard implements Cloneable {

    private int[][] board;
    private String recentMove = "NONE";
    private int stepToSolve = 0;
    private Field emptyField;

    private final int width;
    private final int height;

    public PuzzleBoard(@NotNull int[][] board, int width, int height) {
        this.board = board;
        this.width = width;
        this.height = height;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 0) {
                    emptyField = new Field(i,j);
                }
            }
        }
    }

    public int[][] generateSolvedBoard() {
        int tempIndex = 1;
        int tempBoard[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tempBoard[i][j] = tempIndex;
                tempIndex ++;
            }
        }
        tempBoard[height-1][width-1] = 0;
        return tempBoard;
    }
    //When declaring, we first give the number of rows, then columns
    //So for ours, width is the number of columns and height is the number of lines.

    public int getWidth() {
        //count of rows
        return height;
    }

    public int getHeight() {
        //count of columns
        return width;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(board[i][j] < 10) {
                    stringBuilder.append(" ").append(board[i][j]).append(" ");
                } else {
                    stringBuilder.append(board[i][j]).append(" ");
                }
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

    public String getRecentMove() {
        return recentMove;
    }

    public int getStepToSolve() {
        return stepToSolve;
    }

    public void setBoard(@NotNull int[][] board) {
        this.board = board;
    }

    public void moveEmptyFieldRight() {
        this.recentMove = "R";
        stepToSolve++;
        this.board[emptyField.getX()][emptyField.getY()]
                = this.board[emptyField.getX()][emptyField.getY()+1];
        this.board[emptyField.getX()][emptyField.getY()+1] = 0;
        emptyField.setY(emptyField.getY()+1);
    }

    public void moveEmptyFieldLeft() {
        this.recentMove = "L";
        stepToSolve++;
        this.board[emptyField.getX()][emptyField.getY()]
                = this.board[emptyField.getX()][emptyField.getY()-1];
        this.board[emptyField.getX()][emptyField.getY()-1] = 0;
        emptyField.setY(emptyField.getY()-1);
    }

    public void moveEmptyFieldUp() {
        this.recentMove = "U";
        stepToSolve++;
        this.board[emptyField.getX()][emptyField.getY()]
                = this.board[emptyField.getX()-1][emptyField.getY()];
        this.board[emptyField.getX()-1][emptyField.getY()] = 0;
        emptyField.setX(emptyField.getX()-1);
    }

    public void moveEmptyFieldDown() {
        this.recentMove = "D";
        stepToSolve++;
        this.board[emptyField.getX()][emptyField.getY()]
                = this.board[emptyField.getX()+1][emptyField.getY()];
        this.board[emptyField.getX()+1][emptyField.getY()] = 0;
        emptyField.setX(emptyField.getX()+1);
    }

    @Override
    public PuzzleBoard clone() {
        try {
            PuzzleBoard clone = (PuzzleBoard) super.clone();
            int[][] board = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    board[i][j] = this.board[i][j];
                }
            }
            clone.setEmptyField(this.getEmptyField().clone());
            clone.setBoard(board);
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new CloneException("Error creating clone");
        }
    }

    public boolean checkValidation() {
        int tempBoard[][] = generateSolvedBoard();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (tempBoard[i][j] != this.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
