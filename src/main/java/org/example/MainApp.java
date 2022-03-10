package org.example;

import org.example.Exceptions.ArgsException;
import org.example.algorithms.AcrossStrategy;
import org.example.algorithms.DeeperStrategy;
import org.example.model.CheckArgs;
import org.example.model.PuzzleBoard;

public class MainApp {

    public static int solutionlength;
    public static int visitedStates;
    public static int processedStates;
    public static int recursionDepth;

    public static void main(String[] args) {

        try {
            CheckArgs.checkArguments(args);
        } catch (ArgsException e) {
            System.out.println(e.getMessage());
            return;
        }

        long timeStart = 0;
        long timeStop = 0;

        int[][] start = { //to Test
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0}
        };

        int[][] start2 = { //to Test
                {1,2,3},
                {4,0,6},
                {7,5,8},
                {10,11,9},
                {13,14,12},
                {16,17,15}
        };

        PuzzleBoard board = new PuzzleBoard(start,4,4);
        System.out.println(board.toString());

        //Execution of the algorithm here
        //
        //
        //String xd = "bfs";
        switch (args[0]) {
            case "bfs" -> {
                timeStart = System.nanoTime();
                AcrossStrategy acrossStrategy = new AcrossStrategy(board, args[1]);
                timeStop = System.nanoTime();
            } case "dfs" -> {
                timeStart = System.nanoTime();
                DeeperStrategy deeperStrategy = new DeeperStrategy(board,args[1]);
                timeStop = System.nanoTime();
            } case "astr" -> {
                //A* here
            } default -> {
                System.out.println("Incorrect strategy");
                return;
            }
        }
        //
        //
        //Execution of the algorithm above

        double delay = ((timeStop - timeStart) / 1000) / 1000.0;

        System.out.println("Visited States: "+ visitedStates);
        System.out.println("Processed States: "+processedStates);
        System.out.println("Max depth: "+ recursionDepth);
//        System.out.println("Solution lenght: "+deeperStrategy.getUtilityBoard().getStepToSolve());
//        System.out.println(deeperStrategy.getUtilityBoard().getStepsToSolved().toString());
//        System.out.println("TIME: "+delay+" ms");


    }
}
