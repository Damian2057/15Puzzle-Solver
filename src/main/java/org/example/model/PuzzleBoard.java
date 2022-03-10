package org.example.model;

import org.example.Exceptions.CloneException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PuzzleBoard implements Cloneable {

    private byte[][] board;
    private String recentMove = "NONE";
    private int stepToSolve = 0;

    private int emptyXcordniate;
    private int emptyYcordniate;

    private final byte width;
    private final byte height;

    private ArrayList<String> stepsToSolved = new ArrayList<>();

    public PuzzleBoard(byte[][] board, byte width, byte height) {
        this.board = board;
        this.width = width;
        this.height = height;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 0) {
                    emptyXcordniate = i;
                    emptyYcordniate = j;
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

    public int getWidth() {
        //count of rows
        return height;
    }

    public int getHeight() {
        //count of columns
        return width;
    }

    public ArrayList<String> getStepsToSolved() {
        return stepsToSolved;
    }

    public void setStepsToSolved(ArrayList<String> stepsToSolved) {
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

    public int getemptyXcordniate() {
        return emptyXcordniate;
    }

    public int getemptyYcordniate() {
        return emptyYcordniate;
    }

    public String getRecentMove() {
        return recentMove;
    }

    public int getStepToSolve() {
        return stepToSolve;
    }

    public void setBoard(byte[][] board) {
        this.board = board;
    }

    public void moveEmptyFieldRight() {
        this.recentMove = "R";
        stepsToSolved.add("R");
        stepToSolve++;
        this.board[emptyXcordniate][emptyYcordniate]
                = this.board[emptyXcordniate][emptyYcordniate+1];
        this.board[emptyXcordniate][emptyYcordniate+1] = 0;
        emptyYcordniate = emptyYcordniate + 1;
    }

    public void moveEmptyFieldLeft() {
        this.recentMove = "L";
        stepsToSolved.add("L");
        stepToSolve++;
        this.board[emptyXcordniate][emptyYcordniate]
                = this.board[emptyXcordniate][emptyYcordniate-1];
        this.board[emptyXcordniate][emptyYcordniate-1] = 0;
        emptyYcordniate = emptyYcordniate - 1;
    }

    public void moveEmptyFieldUp() {
        this.recentMove = "U";
        stepsToSolved.add("U");
        stepToSolve++;
        this.board[emptyXcordniate][emptyYcordniate]
                = this.board[emptyXcordniate-1][emptyYcordniate];
        this.board[emptyXcordniate-1][emptyYcordniate] = 0;
        emptyXcordniate = emptyXcordniate - 1;
    }

    public void moveEmptyFieldDown() {
        this.recentMove = "D";
        stepsToSolved.add("D");
        stepToSolve++;
        this.board[emptyXcordniate][emptyYcordniate]
                = this.board[emptyXcordniate+1][emptyYcordniate];
        this.board[emptyXcordniate+1][emptyYcordniate] = 0;
        emptyXcordniate = emptyXcordniate + 1;
    }

    @Override
    public PuzzleBoard clone() {
        try {
            PuzzleBoard clone = (PuzzleBoard) super.clone();
            byte[][] board = new byte[height][width];
            for (int i = 0; i < height; i++) {
                System.arraycopy(this.board[i], 0, board[i], 0, width);
            }
            ArrayList<String> copySteps = new ArrayList<>();
            copySteps.addAll(stepsToSolved);
            clone.setStepsToSolved(copySteps);
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


}
