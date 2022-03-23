package org.example.algorithms;

import org.example.Exceptions.SolutionException;
import org.example.model.PuzzleBoard;
import org.example.model.StatsCollector;
import java.util.Stack;

public class DeeperStrategy extends MaxDepth implements strategy {

    private final Stack<PuzzleBoard> allBoards = new Stack<>();
    private PuzzleBoard utilityBoard;
    private final String sequence;
    private final StatsCollector statsCollector;

    public DeeperStrategy( PuzzleBoard puzzleBoard, String sequence, String sol, String stats) {
        statsCollector = new StatsCollector(sol,stats);
        this.utilityBoard = puzzleBoard;
        this.sequence = sequence;

        statsCollector.endWithOutSollution();

        try {
            statsCollector.startTime();
            recursionSolver();
        } catch (Exception e) {
            throw new SolutionException("Exp error");
        }
    }

    @Override
    public void recursionSolver() {
        do {

            if(statsCollector.getRecursionDepth() < utilityBoard.getCountOfOperations()) {
                statsCollector.setRecursionDepth(utilityBoard.getCountOfOperations());
            }

            statsCollector.addVisitedStates();

            if(utilityBoard.checkValidation()) {
                //checking if the board is already solved
                statsCollector.endWithSollution(utilityBoard);
                return;
            }

            //Calls for sequence permutations LDRU...
            if(utilityBoard.getCountOfOperations() < maxDepth) {
                for (int i = 0; i < 4; i++) {
                    //create only if current board if less than x iterations??
                    //check if board is already in stack?
                    doStepBySign(String.valueOf(sequence.charAt(i)));
                }
            }

            this.utilityBoard = allBoards.pop();

        } while (!allBoards.isEmpty());
        statsCollector.endWithOutSollution();
        return;
    }

    @Override
    public void doStepBySign(String sign) {
        //We shift the field accordingly in one of the 4 trajectories.
        //The shift must satisfy the condition:
        // it is not an extreme shift, beyond the arrays
        //With an additional condition limiting duplicate shifts, ie.
        // In L, the last move cannot be R
        switch (sign) {
            case "R" -> {
                //right border = height-1
                if(utilityBoard.getemptyYcordniate() != utilityBoard.getHeight()-1
                        && !utilityBoard.getRecentMove().equals("L")) {
                    PuzzleBoard tempClone = utilityBoard.clone();
                    tempClone.moveEmptyFieldRight();
                    allBoards.push(tempClone);
                    statsCollector.addProcessedStates();
                }
            }
            case "L" -> {
                //left border = 0
                if(utilityBoard.getemptyYcordniate() != 0
                        && !utilityBoard.getRecentMove().equals("R")) {
                    PuzzleBoard tempClone = utilityBoard.clone();
                    tempClone.moveEmptyFieldLeft();
                    allBoards.push(tempClone);
                    statsCollector.addProcessedStates();
                }
            }
            case "U" -> {
                //up border = 0
                if(utilityBoard.getemptyXcordniate() != 0
                        && !utilityBoard.getRecentMove().equals("D")) {
                    PuzzleBoard tempClone = utilityBoard.clone();
                    tempClone.moveEmptyFieldUp();
                    allBoards.push(tempClone);
                    statsCollector.addProcessedStates();
                }
            }
            case "D" -> {
                //up border = width-1
                if(utilityBoard.getemptyXcordniate() != utilityBoard.getWidth()-1
                        && !utilityBoard.getRecentMove().equals("U")) {
                    PuzzleBoard tempClone = utilityBoard.clone();
                    tempClone.moveEmptyFieldDown();
                    allBoards.push(tempClone);
                    statsCollector.addProcessedStates();
                }
            }
        }
    }
}
