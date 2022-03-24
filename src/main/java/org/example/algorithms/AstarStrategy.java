package org.example.algorithms;

import org.example.Exceptions.SolutionException;
import org.example.model.PuzzleBoard;
import org.example.model.StatsCollector;
import java.util.ArrayList;
import java.util.Objects;

public class AstarStrategy extends MaxDepth {

    private PuzzleBoard utilityBoard;
    private final ArrayList<PuzzleBoard> allBoards = new ArrayList<>();
    private final StatsCollector statsCollector;
    private final String algorithmType;

    public AstarStrategy(PuzzleBoard utilityBoard, String algoritmType
            , String sol, String stats) {
        this.utilityBoard = utilityBoard;
        this.algorithmType = algoritmType;
        statsCollector = new StatsCollector(sol,stats);

        utilityBoard.setManhattanScore(utilityBoard.manhattanScore());
        utilityBoard.setHammingScore(utilityBoard.hammingScore());

        statsCollector.endWithOutSollution();

        try {
            solver();
        } catch (Exception e) {
            throw new SolutionException("Error occurred during getting a solution");
        }
    }

    public void solver() {
        do {

            if(statsCollector.getRecursionDepth() < utilityBoard.getCountOfOperations()) {
                statsCollector.setRecursionDepth(utilityBoard.getCountOfOperations());
            }

            statsCollector.addVisitedStates();

            if(utilityBoard.checkValidation()) {
                //checking if the board is solved
                statsCollector.endWithSollution(utilityBoard);
                return;
            }

            doStep();

            utilityBoard = allBoards.get(0);
            allBoards.remove(0);

        } while (!allBoards.isEmpty());

        statsCollector.endWithOutSollution();
        return;
    }

    public void doStep() {
        //right border = height-1
        if(utilityBoard.getemptyYcordniate() != utilityBoard.getHeight()-1
                && !utilityBoard.getRecentMove().equals("L")) {
            PuzzleBoard tempClone = utilityBoard.clone();
            tempClone.moveEmptyFieldRight();
            statsCollector.addProcessedStates();
            if(Objects.equals(algorithmType, "manh")) {
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
            if(Objects.equals(algorithmType, "manh")) {
                tempClone.setManhattanScore(tempClone.manhattanScore());
                manhOrder(tempClone);
            } else {
                tempClone.setHammingScore(tempClone.hammingScore());
                hammOrder(tempClone);
            }
        }

        //up border = 0
        if(utilityBoard.getemptyXcordniate() != 0
                && !utilityBoard.getRecentMove().equals("D")) {
            PuzzleBoard tempClone = utilityBoard.clone();
            tempClone.moveEmptyFieldUp();
            statsCollector.addProcessedStates();
            if(Objects.equals(algorithmType, "manh")) {
                tempClone.setManhattanScore(tempClone.manhattanScore());
                manhOrder(tempClone);
            } else {
                tempClone.setHammingScore(tempClone.hammingScore());
                hammOrder(tempClone);
            }
        }

        //up border = width-1
        if(utilityBoard.getemptyXcordniate() != utilityBoard.getWidth()-1
                && !utilityBoard.getRecentMove().equals("U")) {
            PuzzleBoard tempClone = utilityBoard.clone();
            tempClone.moveEmptyFieldDown();
            statsCollector.addProcessedStates();
            if(Objects.equals(algorithmType, "manh")) {
                tempClone.setManhattanScore(tempClone.manhattanScore());
                manhOrder(tempClone);
            } else {
                tempClone.setHammingScore(tempClone.hammingScore());
                hammOrder(tempClone);
            }
        }
    }

    private void manhOrder(PuzzleBoard board) {
        boolean flag = true;
        int iterator = allBoards.size(); //it increases the speed the size function is not called every time
        for (int i = 0; i < iterator; i++) {
            //check manhattanScore(how many points do not fit into place)
            //depending on the result set the object in the right place
            //the best solutions are listed first and will be analyzed first
            if (board.getManhattanScore() < allBoards.get(i).getManhattanScore()) {
                flag = false;
                allBoards.add(i, board);
            }
        }
        if (flag) {
            //If the solution is the worst possible, put it at the end of the list
            //or list is empty
            allBoards.add(board);
        }
    }

    private void hammOrder(PuzzleBoard board) {
        boolean flag = true;
        int iterator = allBoards.size(); //it increases the speed the size function is not called every time
        for (int i = 0; i < iterator; i++) {
            //check hammingScore(how many points do not fit into place)
            //depending on the result set the object in the right place
            //the best solutions are listed first and will be analyzed first
            if (board.getHammingScore() < allBoards.get(i).getHammingScore()) {
                flag = false;
                allBoards.add(i, board);
            }
        }
        if (flag) {
            //If the solution is the worst possible, put it at the end of the list
            //or list is empty
            allBoards.add(board);
        }
    }
}
