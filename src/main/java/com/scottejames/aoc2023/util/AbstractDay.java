package com.scottejames.aoc2023.util;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDay {
    private static String _input;
    public AbstractDay() {

    }
    public AbstractDay(int day) throws IOException {
        InputFetch i = new InputFetch();
        _input = i.fetchPuzzleInput(day);
    }

    public String getInput(){
        return _input;
    }
    public List<Integer> getListInteger(){
        return getInput()
                .lines()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<String> getListString(){
        return getInput()
                .lines()
                .collect(Collectors.toList());
    }

    /**
     * Returns the solution to part 1 of the puzzle.
     * @return the solution to part 1 of the puzzle
     */
    public abstract String solvePart1();

    /**
     * Returns the solution to part 2 of the puzzle.
     * @return the solution to part 2 of the puzzle
     */
    public abstract String solvePart2();

    public static void Main(String [] args){

    }
}
