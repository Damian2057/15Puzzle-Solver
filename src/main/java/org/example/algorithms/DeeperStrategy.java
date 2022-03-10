package org.example.algorithms;

import org.example.Exceptions.SolutionException;
import org.example.MainApp;
import org.example.model.PuzzleBoard;
import org.jetbrains.annotations.NotNull;
import java.util.Stack;

public class DeeperStrategy extends MaxDepth implements strategy {
    private final Stack<PuzzleBoard> allBoards = new Stack<>();
    private PuzzleBoard utilityBoard;
    private final String sequence;

    public DeeperStrategy(@NotNull PuzzleBoard puzzleBoard,@NotNull String sequence) {
        this.utilityBoard = puzzleBoard;
        this.sequence = sequence;
        try {
            recursionSolver();
        } catch (Exception e) {
            throw new SolutionException("Exp error");
        }
    }

    public PuzzleBoard getUtilityBoard() {
        return utilityBoard;
    }

    @Override
    public void recursionSolver() {
        try {

            if(MainApp.recursionDepth < utilityBoard.getStepToSolve()) {
                MainApp.recursionDepth = utilityBoard.getStepToSolve();
            }

            MainApp.visitedStates++;

            if(utilityBoard.checkValidation()) {
                //checking if the board is already solved
                return;
            }

            //Calls for sequence permutations LDRU...
            for (int i = 0; i < 4; i++) {
                doStepBySign(String.valueOf(sequence.charAt(i)));
            }

            // take the first of the queue as arrays for the work of subsequent recursion levels
            this.utilityBoard = allBoards.pop();
            if(!allBoards.isEmpty() && MainApp.recursionDepth <= 20) {
                recursionSolver();
            }
        } catch (Exception e) {
            throw new SolutionException("Error getting solution");
        }
    }

    @Override
    public void doStepBySign(@NotNull String sign) {
        //We shift the field accordingly in one of the 4 trajectories.
        //The shift must satisfy the condition:
        // it is not an extreme shift, beyond the arrays
        //With an additional condition limiting duplicate shifts, ie.
        // In L, the last move cannot be R
        switch (sign) {
            case "R" -> {
                //right border = height-1
                if(utilityBoard.getEmptyField().getY() != utilityBoard.getHeight()-1
                        && !utilityBoard.getRecentMove().equals("L")) {
                    PuzzleBoard tempClone = utilityBoard.clone();
                    tempClone.moveEmptyFieldRight();
                    allBoards.push(tempClone);
                    MainApp.processedStates++;
                }
            }
            case "L" -> {
                //left border = 0
                if(utilityBoard.getEmptyField().getY() != 0
                        && !utilityBoard.getRecentMove().equals("R")) {
                    PuzzleBoard tempClone = utilityBoard.clone();
                    tempClone.moveEmptyFieldLeft();
                    allBoards.push(tempClone);
                    MainApp.processedStates++;
                }
            }
            case "U" -> {
                //up border = 0
                if(utilityBoard.getEmptyField().getX() != 0
                        && !utilityBoard.getRecentMove().equals("D")) {
                    PuzzleBoard tempClone = utilityBoard.clone();
                    tempClone.moveEmptyFieldUp();
                    allBoards.push(tempClone);
                    MainApp.processedStates++;
                }
            }
            case "D" -> {
                //up border = width-1
                if(utilityBoard.getEmptyField().getX() != utilityBoard.getWidth()-1
                        && !utilityBoard.getRecentMove().equals("U")) {
                    PuzzleBoard tempClone = utilityBoard.clone();
                    tempClone.moveEmptyFieldDown();
                    allBoards.push(tempClone);
                    MainApp.processedStates++;
                }
            }
        }
    }
}