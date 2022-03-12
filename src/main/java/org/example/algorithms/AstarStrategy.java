package org.example.algorithms;

import org.example.Exceptions.SolutionException;
import org.example.model.PuzzleBoard;
import org.example.model.StatsCollector;
import java.util.ArrayList;
import java.util.Objects;

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
                } default -> {
                    throw new SolutionException("Wrong alg Type");
                }
            }
        } catch (Exception e) {
            throw new SolutionException("Error occurred during getting a solution");
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

            utilityBoard = allBoards.get(0);
            allBoards.remove(0);

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

            //get current best element to analyze
            utilityBoard = allBoards.get(0);
            allBoards.remove(0);

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
            if(Objects.equals(algoritmType, "manh")) {
                manhOrder(tempClone);
            } else {
                hammOrder(tempClone);
            }

        }

        //left border = 0
        if(utilityBoard.getemptyYcordniate() != 0
                && !utilityBoard.getRecentMove().equals("R")) {
            PuzzleBoard tempClone = utilityBoard.clone();
            tempClone.moveEmptyFieldLeft();
            statsCollector.addProcessedStates();
            if(Objects.equals(algoritmType, "manh")) {
                manhOrder(tempClone);
            } else {
                hammOrder(tempClone);
            }
        }

        //up border = 0
        if(utilityBoard.getemptyXcordniate() != 0
                && !utilityBoard.getRecentMove().equals("D")) {
            PuzzleBoard tempClone = utilityBoard.clone();
            tempClone.moveEmptyFieldUp();
            statsCollector.addProcessedStates();
            if(Objects.equals(algoritmType, "manh")) {
                manhOrder(tempClone);
            } else {
                hammOrder(tempClone);
            }
        }

        //up border = width-1
        if(utilityBoard.getemptyXcordniate() != utilityBoard.getWidth()-1
                && !utilityBoard.getRecentMove().equals("U")) {
            PuzzleBoard tempClone = utilityBoard.clone();
            tempClone.moveEmptyFieldDown();
            statsCollector.addProcessedStates();
            if(Objects.equals(algoritmType, "manh")) {
                manhOrder(tempClone);
            } else {
                hammOrder(tempClone);
            }
        }
    }

    private void manhOrder(PuzzleBoard board) {
        //a special type of add-on
    }

    private void hammOrder(PuzzleBoard board) {
        boolean flag = true;
        int iterator = allBoards.size(); //it increases the speed the size function is not called every time
        for (int i = 0; i < iterator; i++) {
            //check hammingScore(how many points do not fit into place)
            //depending on the result set the object in the right place
            //the best solutions are listed first and will be analyzed first
            if (board.hammingScore() < allBoards.get(i).hammingScore()) {
                allBoards.add(i, board);
                flag = false;
            }
        }
        if (flag) {
            allBoards.add(board);
        }
    }

    public PuzzleBoard getUtilityBoard() {
        return utilityBoard;
    }
}
