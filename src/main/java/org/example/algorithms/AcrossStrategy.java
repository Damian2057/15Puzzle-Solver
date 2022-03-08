package org.example.algorithms;

import org.example.model.PuzzleBoard;

import java.util.LinkedList;

public class AcrossStrategy extends MaxDepth {

    private final LinkedList<PuzzleBoard> allBoards = new LinkedList<>();
    private PuzzleBoard boardToSolve;

    public AcrossStrategy(PuzzleBoard puzzleBoard) {
        this.boardToSolve = puzzleBoard;
        try {
            solveTheBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void solveTheBoard() {
        if(boardToSolve.checkValidation()) {
            return;
        }
    }
}
