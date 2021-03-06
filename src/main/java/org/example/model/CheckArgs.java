package org.example.model;

import org.example.Exceptions.ArgsException;
import java.util.ArrayList;
import java.util.Objects;

public class CheckArgs {

    public static void checkArguments(String[] args) {

        ArrayList<String> acronims = new ArrayList<>();
        acronims.add("dfs");
        acronims.add("bfs");
        acronims.add("astr");

        if(args.length != 5) {
            throw new ArgsException("Invalid count of Arguments");
        }
        if(!acronims.contains(args[0])) {
            throw new ArgsException("Incorrect type of algorithms.strategy");
        }
        if(args[1].length() != 4) {
            throw new ArgsException("Incorrect length of search order");
        }
        if((Objects.equals(args[0], "bfs") || (Objects.equals(args[0], "dfs"))) && (!args[1].contains("L")
                || !args[1].contains("R")
                || !args[1].contains("U")
                || !args[1].contains("D")
                )) {
            throw new ArgsException("Wrong permutation of the search order");
        }
        if(!args[2].contains(".txt")
                || !args[3].contains(".txt")
                || !args[4].contains(".txt")) {
            throw new ArgsException("Some of the files are not of the correct type");
        }
    }
}
