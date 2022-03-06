package org.example;

public class MainApp {

    public static int solutionlength;
    public static int visitedStates;
    public static int processedStates;
    public static int recursionDepth;

    public static void main(String[] args){

        long timeStart;
        long timeStop;

        timeStart = System.nanoTime();

        //Execution of the algorithm here

        System.out.print("Hello World!");

        //****

        timeStop = System.nanoTime();

        double delay = ((timeStop - timeStart) / 1000) / 1000.0;


    }
}
