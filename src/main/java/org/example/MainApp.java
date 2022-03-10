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

//        try {
//            CheckArgs.checkArguments(args);
//        } catch (ArgsException e) {
//            System.out.println(e.getMessage());
//            return;
//        }

        long timeStart = 0;
        long timeStop = 0;

        byte[][] start = { //to Test
                {0,1,2,7},
                {8,9,12,10},
                {13,3,6,4},
                {15,14,11,5}
        };

        byte[][] start3 = { //to Test
                {1,2,4,8},
                {5,6,3,0},
                {9,10,7,11},
                {13,14,15,12}
        };

        byte[][] start4 = { //to Test
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0}
        };


        byte[][] start2 = { //to Test
                {0,1,3},
                {5,2,6},
                {4,7,9},
                {10,8,12},
                {13,11,14},
                {16,17,15}
        };

        Integer h,w;
        h = 6;
        w = 3;

        PuzzleBoard board = new PuzzleBoard(start2, w.byteValue() , h.byteValue());
        System.out.println(board.toString());

        //Execution of the algorithm here
        //
        //
        String xd = "dfs";
        //args[0]
        switch (xd) {
            case "bfs" -> {
                timeStart = System.nanoTime();
                AcrossStrategy acrossStrategy = new AcrossStrategy(board, "RUDL");
                timeStop = System.nanoTime();
            } case "dfs" -> {
                timeStart = System.nanoTime();
                DeeperStrategy deeperStrategy = new DeeperStrategy(board,"RUDL");
                System.out.println(deeperStrategy.getUtilityBoard().toString());
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
