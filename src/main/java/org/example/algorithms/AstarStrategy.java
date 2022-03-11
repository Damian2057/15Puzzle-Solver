package org.example.algorithms;

import org.example.Exceptions.SolutionException;
import org.example.model.PuzzleBoard;
import org.example.model.StatsCollector;

import java.util.ArrayList;

public class AstarStrategy {

    private PuzzleBoard utilityBoard;

    private ArrayList<PuzzleBoard> allBoards = new ArrayList<>();
    private final StatsCollector statsCollector;
    private final String algoritmType;

    public AstarStrategy(PuzzleBoard utilityBoard, String algoritmType
            , String sol, String stats) {
        this.utilityBoard = utilityBoard;
        this.algoritmType = algoritmType;
        statsCollector = new StatsCollector(sol,stats);
        try {
            switch (algoritmType) {
                case "manh" -> {
                    statsCollector.startTime();
                    recursionSolverM();
                }
                case "hamm" -> {
                    statsCollector.startTime();
                    recursionSolverH();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recursionSolverM() {
        try {

            if(statsCollector.getRecursionDepth() < utilityBoard.getStepToSolve()) {
                statsCollector.setRecursionDepth(utilityBoard.getStepToSolve());
            }

            statsCollector.addVisitedStates();

            if(utilityBoard.checkValidation()) {
                //checking if the board is already solved
                statsCollector.endWithSollution(utilityBoard);
                return;
            }

            doStep();

            utilityBoard = allBoards.remove(0);
            if(allBoards.size() != 0) {
                recursionSolverM();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new SolutionException("Error getting solution");
        }
    }

    public void recursionSolverH() {
        try {

            if(statsCollector.getRecursionDepth() < utilityBoard.getStepToSolve()) {
                statsCollector.setRecursionDepth(utilityBoard.getStepToSolve());
            }

            statsCollector.addVisitedStates();

            if(utilityBoard.checkValidation()) {
                //checking if the board is already solved
                statsCollector.endWithSollution(utilityBoard);
                return;
            }

            doStep();

            utilityBoard = allBoards.remove(0);
            if(allBoards.size() != 0) {
                recursionSolverM();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SolutionException("Error getting solution");
        }
    }

    public void doStep() {
        //right border = height-1
        if(utilityBoard.getemptyYcordniate() != utilityBoard.getHeight()-1
                && !utilityBoard.getRecentMove().equals("L")) {
            PuzzleBoard tempClone = utilityBoard.clone();
            tempClone.moveEmptyFieldRight();
            statsCollector.addProcessedStates();

        }

        //left border = 0
        if(utilityBoard.getemptyYcordniate() != 0
                && !utilityBoard.getRecentMove().equals("R")) {
            PuzzleBoard tempClone = utilityBoard.clone();
            tempClone.moveEmptyFieldLeft();
            statsCollector.addProcessedStates();

        }

        //up border = 0
        if(utilityBoard.getemptyXcordniate() != 0
                && !utilityBoard.getRecentMove().equals("D")) {
            PuzzleBoard tempClone = utilityBoard.clone();
            tempClone.moveEmptyFieldUp();
            statsCollector.addProcessedStates();

        }

        //up border = width-1
        if(utilityBoard.getemptyXcordniate() != utilityBoard.getWidth()-1
                && !utilityBoard.getRecentMove().equals("U")) {
            PuzzleBoard tempClone = utilityBoard.clone();
            tempClone.moveEmptyFieldDown();
            statsCollector.addProcessedStates();

        }

    }
}
