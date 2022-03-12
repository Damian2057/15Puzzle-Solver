package org.example.model;

import org.example.Exceptions.CloneException;
import java.util.Arrays;

public class PuzzleBoard implements Cloneable {

    private byte[][] board;
    private String recentMove = "NONE";
    private int stepToSolve = 0;
    private byte emptyXcordniate;
    private byte emptyYcordniate;
    private final byte width;
    private final byte height;
    private String stepsToSolved = "";

    public PuzzleBoard(byte[][] board, byte width, byte height) {
        this.board = board;
        this.width = width;
        this.height = height;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 0) {
                    emptyXcordniate = (byte) i;
                    emptyYcordniate = (byte) j;
                }
            }
        }
    }

    public byte[][] generateSolvedBoard() {
        byte tempIndex = 1;
        byte[][] tempBoard = new byte[height][width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < width; j++) {
                tempBoard[i][j] = tempIndex;
                tempIndex ++;
            }
        }
        tempBoard[this.height -1][width-1] = 0;
        return tempBoard;
    }
    
    //When declaring, we first give the number of rows, then columns
    //So for ours, width is the number of columns and height is the number of lines.

    public byte getWidth() {
        //count of rows
        return height;
    }

    public byte getHeight() {
        //count of columns
        return width;
    }

    public String getStepsToSolved() {
        return stepsToSolved;
    }

    public void setStepsToSolved(String stepsToSolved) {
        this.stepsToSolved = stepsToSolved;
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

    public byte getemptyXcordniate() {
        return emptyXcordniate;
    }

    public byte getemptyYcordniate() {
        return emptyYcordniate;
    }

    public String getRecentMove() {
        return recentMove;
    }

    public int getCountOfSteps() {
        return stepToSolve;
    }

    public void setBoard(byte[][] board) {
        this.board = board;
    }

    public void moveEmptyFieldRight() {
        this.recentMove = "R";
        stepsToSolved +="R ";
        stepToSolve++;
        this.board[emptyXcordniate][emptyYcordniate]
                = this.board[emptyXcordniate][emptyYcordniate+1];
        this.board[emptyXcordniate][emptyYcordniate+1] = 0;
        emptyYcordniate = (byte) (emptyYcordniate + 1);
    }

    public void moveEmptyFieldLeft() {
        this.recentMove = "L";
        stepsToSolved +="L ";
        stepToSolve++;
        this.board[emptyXcordniate][emptyYcordniate]
                = this.board[emptyXcordniate][emptyYcordniate-1];
        this.board[emptyXcordniate][emptyYcordniate-1] = 0;
        emptyYcordniate = (byte) (emptyYcordniate - 1);
    }

    public void moveEmptyFieldUp() {
        this.recentMove = "U";
        stepsToSolved +="U ";
        stepToSolve++;
        this.board[emptyXcordniate][emptyYcordniate]
                = this.board[emptyXcordniate-1][emptyYcordniate];
        this.board[emptyXcordniate-1][emptyYcordniate] = 0;
        emptyXcordniate = (byte) (emptyXcordniate - 1);
    }

    public void moveEmptyFieldDown() {
        this.recentMove = "D";
        stepsToSolved +="D ";
        stepToSolve++;
        this.board[emptyXcordniate][emptyYcordniate]
                = this.board[emptyXcordniate+1][emptyYcordniate];
        this.board[emptyXcordniate+1][emptyYcordniate] = 0;
        emptyXcordniate = (byte) (emptyXcordniate + 1);
    }

    @Override
    public PuzzleBoard clone() {
        try {
            PuzzleBoard clone = (PuzzleBoard) super.clone();
            byte[][] board = new byte[height][width];
            for (int i = 0; i < height; i++) {
                System.arraycopy(this.board[i], 0, board[i], 0, width);
            }
            clone.setBoard(board);
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new CloneException("Error creating clone");
        }
    }

    public boolean checkValidation() {
        byte[][] tempBoard = generateSolvedBoard();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (tempBoard[i][j] != this.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleBoard that = (PuzzleBoard) o;
        return Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    public int manhattanScore() {
        //sum of the distance of the points from their correct positions
        return 0;
    }

    public int hammingScore() {
        //The sum of the number of points not in their positions
        byte[][] tempBoard = generateSolvedBoard();
        int sum = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (tempBoard[i][j] != this.board[i][j]) {
                    sum += 1;
                }
            }
        }
        return sum;
    }
}
