package org.example.model;

import org.example.fileSystem.FileFactory;

public class StatsCollector {

    private int visitedStates;
    private int processedStates;
    private int recursionDepth = 0;
    private long startTime;
    private int stepsToSolve;

    private double time = 0;

    private final String solutionPath;
    private final String statisticPath;

    public StatsCollector(String solutionPath, String statisticPath) {
        this.solutionPath = solutionPath;
        this.statisticPath = statisticPath;
    }

    public void startTime() {
        startTime = System.nanoTime();
    }

    public void addVisitedStates() {
        visitedStates++;
    }

    public void addProcessedStates() {
        processedStates++;
    }

    public int getRecursionDepth() {
        return recursionDepth;
    }

    public void setRecursionDepth(int recursionDepth) {
        this.recursionDepth = recursionDepth;
    }

    public void endWithSollution(PuzzleBoard board) {
        time = Math.round((System.nanoTime() - startTime)/1000.0)/1000.0;
        stepsToSolve = board.getStepToSolve();
        FileFactory.saveSollution(solutionPath,board.getStepToSolve(),board.getStepsToSolved());
        FileFactory.saveStats(statisticPath,board.getStepToSolve(),visitedStates,processedStates,recursionDepth,time);
    }

    public void endWithOutSollution() {
        FileFactory.endWithError(solutionPath,statisticPath);
    }

    public int getVisitedStates() {
        return visitedStates;
    }
}
