package org.example;

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
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,0},
                {13,14,15,12}
        };

        PuzzleBoard board = new PuzzleBoard(start,4,4);

        timeStart = System.nanoTime();

        //Execution of the algorithm here
        //
        //
        //****

        timeStop = System.nanoTime();

        double delay = ((timeStop - timeStart) / 1000) / 1000.0;


    }
}
