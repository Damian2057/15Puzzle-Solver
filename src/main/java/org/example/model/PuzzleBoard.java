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

    private int hammingScore = 0;
    private int manhattanScore = 0;

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

    public int getHammingScore() {
        return hammingScore;
    }

    public void setHammingScore(int hammingScore) {
        this.hammingScore = hammingScore;
    }

    public int getManhattanScore() {
        return manhattanScore;
    }

    public void setManhattanScore(int manhattanScore) {
        this.manhattanScore = manhattanScore;
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

    public int getCountOfOperations() {
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
        byte index = 1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (index != this.board[i][j]) {
                    if(index == width*height && this.board[i][j] == 0) {

                        return true;
                    }
                    return false;
                }
                index++;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    public int manhattanScore() {
        //sum of the distance of the points from their correct positions
        int sum = 0;
        int M;
        int N;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 0) {
                    M = height - 1;
                    N = width - 1;
                } else {
                    M = (board[i][j] - 1) / width;
                    N = (board[i][j] - 1) % width;
                }
                sum += Math.abs(i - M) + Math.abs(j - N);
            }
        }
        return sum;
    }

    public int hammingScore() {
        //The sum of the number of points not in their positions
        byte index = 1;
        int sum = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (index != this.board[i][j]) {
                    sum++;
                }
                index++;
            }
        }
        if(board[height-1][width-1] == 0) {
            sum--;
        }
        return sum;
    }
}
