package org.example.algorithms;

import org.example.Exceptions.SolutionException;
import org.example.model.PuzzleBoard;
import org.example.model.StatsCollector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AstarStrategy {

    private PuzzleBoard utilityBoard;

    private ArrayList<PuzzleBoard> allBoards = new ArrayList<>();
    private StatsCollector statsCollector;
    private String algoritmType;

    public AstarStrategy(@NotNull PuzzleBoard utilityBoard, @NotNull String algoritmType
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


    }
}
