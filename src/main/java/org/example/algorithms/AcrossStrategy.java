package org.example.algorithms;

import org.example.Exceptions.CloneException;
import org.example.Exceptions.SolutionException;
import org.example.model.PuzzleBoard;

import java.util.LinkedList;

public class AcrossStrategy extends MaxDepth {

    private final LinkedList<PuzzleBoard> allBoards = new LinkedList<>();
    private PuzzleBoard boardToSolve;
    private final String sequence;

    public AcrossStrategy(PuzzleBoard puzzleBoard, String sequence) {
        this.boardToSolve = puzzleBoard;
        this.sequence = sequence;
        try {
            recursionSolver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recursionSolver() {
        try {
            if(boardToSolve.checkValidation()) {
                //checking if the board is already solved
                return;
            }
            //Calls for sequence permutations LDRU...
            for (int i = 0; i < 4; i++) {
                doStepBySign(String.valueOf(sequence.charAt(i)));
            }
            this.boardToSolve = allBoards.poll();
            if(!allBoards.isEmpty()) {
                recursionSolver();
            }
        }
//        catch (CloneNotSupportedException  e) {
//            throw new CloneException("Error creating clone");
//        }
        catch (Exception e) {
            throw new SolutionException("Error getting solution");
        }
    }

    private void doStepBySign(String sign) {

    }
}
