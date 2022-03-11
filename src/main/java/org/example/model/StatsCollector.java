package org.example.model;

public class StatsCollector {

    private int visitedStates;
    private int processedStates;
    private int recursionDepth = 0;
    private long startTime;
    private int stepsToSolve;

    private long time = 0;

    private final String solutionPath;
    private final String statisticPath;

    public StatsCollector(String solutionPath, String statisticPath) {
        this.solutionPath = solutionPath;
        this.statisticPath = statisticPath;
    }

    public void startTime() {
        startTime = System.currentTimeMillis();
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
        time = System.currentTimeMillis() - startTime;
        stepsToSolve = board.getStepToSolve();
        //Save file with this whole information
    }

    public void endWithOutSollution() {
        //save file with -1 var
    }

}
