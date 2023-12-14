package com.scottejames.aoc2023.day10;

import com.scottejames.aoc2023.util.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day10 extends AbstractDay {
    List<String> sample;
    TubeGrid grid = null;

    public Day10() throws IOException {
        super(10);
        grid = new TubeGrid();
        sample = List.of(
                    ".....",
                    ".S-7.",
                    ".|.|.",
                    ".L-J.",
                    "....."
        );
        int row = 0;
        int col = 0;
        Point start = null;
        for (String s: sample){
            for (char c: s.toCharArray()){
                grid.add(new Point(col,row),c);
                if(c == 'S')
                    start = new Point(col,row);
                col++;
            }
            row++;
            col=0;
        }
        grid.showGrid();
        System.out.println("Start at " + start);


    }


    @Override
    public String solvePart1() {
        return null;
    }

    @Override
    public String solvePart2() {
        return null;
    }
}
