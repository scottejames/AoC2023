package com.scottejames.aoc2023.day10;

import com.scottejames.aoc2023.util.*;

import java.io.IOException;
import java.util.*;

public class Day10 extends AbstractDay {
    List<String> sample;
    TubeGrid grid = null;
    Point start = null;
    List<Point> path = new ArrayList<Point>();

    public Day10() throws IOException {
        super(10);
        grid = new TubeGrid();
        sample = List.of(
                    "FF7FSF7F7F7F7F7F---7",
                    "L|LJ||||||||||||F--J",
                    "FL-7LJLJ||||||LJL-77",
                    "F--JF--7||LJLJ7F7FJ-",
                    "L---JF-JLJ.||-FJLJJ7",
                    "|F|F-JF---7F7-L7L|7|",
                    "|FFJF7L7F-JF7|JL---7",
                    "7-L-JL7||F7|L7F-7F7|",
                    "L.L7LFJ|||||FJL7||LJ",
                    "L7JLJL-JLJLJL--JLJ.L");
//                "...........",
//                ".S-------7.",
//                ".|F-----7|.",
//                ".||.....||.",
//                ".||.....||.",
//                ".|L-7.F-J|.",
//                ".|..|.|..|.",
//                ".L--J.L--J.",
//                "...........");

        int row = 0;
        int col = 0;
        for (String s: getListString()){
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
        // get exits from start location
        Pair<Direction> exits = grid.getExits(start);
        // for each exit walk the grid
        Direction d = Direction.DOWN;
        Point current = start.move(d);
        int count = 0;
        while (!current.equals(start)){
            path.add(current);
            System.out.println("Current " + current + " direction " + d + " count " + count++);
            d = grid.getExits(current).other(d.reverse());
            current = grid.navigate(current,d);
        }
        int result = (count + 1) / 2;
        return result + "";
    }

    @Override
    public String solvePart2() {
        double enclosedArea = calculateEnclosedArea(path);
        int pick = calculateEnclosedAreaUsingPicksTheorem(path);
        return enclosedArea + " " + pick;
    }
    public static double calculateEnclosedArea(List<Point> path) {
        // Ensure the path forms a closed loop
        path.add(path.get(0));

        int n = path.size();
        double shoelaceSum = 0;

        for (int i = 0; i < n - 1; i++) {
            shoelaceSum += (path.get(i).x * path.get(i + 1).y) - (path.get(i + 1).x * path.get(i).y);
        }

        double area = 0.5 * Math.abs(shoelaceSum);
        return area;
    }

    public static int calculateEnclosedAreaUsingPicksTheorem(List<Point> path) {
        // Ensure the path forms a closed loop
        path.add(path.get(0));

        int n = path.size();
        int boundaryPoints = n - 1; // Number of points on the boundary

        // Use Pick's Theorem: A = I + B/2 - 1
        int interiorPoints = (int) (calculateEnclosedArea(path) - boundaryPoints / 2 + 1);
        return interiorPoints;
    }
}
