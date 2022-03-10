package org.example.model;

import org.example.Exceptions.ArgsException;
import java.util.ArrayList;

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
            throw new ArgsException("Incorrect type of strategy");
        }
        if(args[1].length() != 4) {
            throw new ArgsException("Incorrect length of search order");
        }
        if(!args[1].contains("L")
                || !args[1].contains("R")
                || !args[1].contains("U")
                || !args[1].contains("D")) {
            throw new ArgsException("Wrong permutation of the search order");
        }
    }
}
