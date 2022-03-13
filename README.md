# 15PuzzleSolver

To run the compiled jar program the following parameters must be provided:
java -jar fifteenPuzzle-1.0-SNAPSHOT.jar <strategy> <additional parameters> <source_file.txt> <solution_file.txt> <statistic_file.txt>
  
| Available strategies    | Strategy_Acronym                         |
| ----------------------- | ---------------------------------------- |
| breadth-first search    | `bfs`                                    |
| depth-first search      | `dfs`                                    |
| A-sta                   | `astr`                                   |

  
| Additional parameters   | Parameters_Acronym                       |
| ----------------------- | ---------------------------------------- |
| search order            | `letter permutation: L, R, U, D`         |
| heuristics - Hamming    | `hamm`                                   |
| heuristics - Manhattan  | `manh`                                   |
  
  <source_file.txt> - path to a txt file with the following layout
  -in the first line, the number of lines and columns separated by a space
  -Then the board fields in successive lines separated by spaces, we mark the empty space with 0
    
  <solution_file.txt> - path to the txt file in which the solution, number of steps and subsequent moves will be saved
    
  <solution_file.txt> - path to the txt file in which the statistics, number of step, visited states, processed states, recursion depth, Duration will be saved
