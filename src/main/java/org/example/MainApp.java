package org.example;

import org.example.algorithms.AcrossStrategy;
import org.example.model.PuzzleBoard;

public class MainApp {

    public static int solutionlength;
    public static int visitedStates;
    public static int processedStates;
    public static int recursionDepth;

    public static void main(String[] args) throws CloneNotSupportedException {

        long timeStart;
        long timeStop;

        int[][] start = { //to Test
                {0,1,2,4},
                {6,7,3,8},
                {5,9,10,11},
                {13,14,15,12}
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

        timeStart = System.nanoTime();

        //Execution of the algorithm here
        //
        //
         new AcrossStrategy(board, "UDLR");
        //
        //
        //Execution of the algorithm above

        timeStop = System.nanoTime();

        double delay = ((timeStop - timeStart) / 1000) / 1000.0;
        System.out.println(delay);


    }
}
