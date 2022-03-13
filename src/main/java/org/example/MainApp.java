package org.example;

import org.example.algorithms.AstarStrategy;
import org.example.algorithms.BreadthStrategy;
import org.example.algorithms.DeeperStrategy;
import org.example.fileSystem.FileFactory;
import org.example.model.PuzzleBoard;

public class MainApp {

    public static void main(String[] args) {

        try {
            org.example.model.CheckArgs.checkArguments(args);
        } catch (org.example.Exceptions.ArgsException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            FileFactory f = new FileFactory();
            byte[][] board = f.getPuzzle(args[2]);
            PuzzleBoard puzzleBoard = new PuzzleBoard(board, f.getWidth() , f.getHeight());

            //System.out.println(puzzleBoard.toString());
            switch (args[0]) {
                case "bfs" -> {
                    BreadthStrategy acrossStrategy = new BreadthStrategy(puzzleBoard, args[1],args[3],args[4]);
                    System.out.println(acrossStrategy.getUtilityBoard().toString());
                } case "dfs" -> {
                    DeeperStrategy deeperStrategy = new DeeperStrategy(puzzleBoard,args[1],args[3],args[4]);
                    System.out.println(deeperStrategy.getUtilityBoard().toString());
                } case "astr" -> {
                    AstarStrategy astarStrategy = new AstarStrategy(puzzleBoard,args[1],args[3],args[4]);
                    System.out.println(astarStrategy.getUtilityBoard().toString());
                } default -> {
                    System.out.println("Incorrect algorithms.strategy");
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
