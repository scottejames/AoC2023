package com.scottejames.aoc2023;

import com.scottejames.aoc2023.util.AbstractDay;
import com.scottejames.aoc2023.util.Grid;
import com.scottejames.aoc2023.util.Point;

import java.io.IOException;
import java.util.*;

public class Day3 extends AbstractDay {
    public List<String> sample;
    public Grid<Character> grid;
    public Day3() throws IOException {
        super(3);
        sample = List.of(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."
        );
    }

    @Override
    public String solvePart1() {
        List<String> sourceData = getListString();
        buildGrid(sourceData);
        int sum = 0;
        for (int row = 0;row<=grid.getHeight();row++){
            List<Point> rowPoints = grid.getRowPoints(row);
            String digit = new String();
            boolean isAdjacent = false;

            for (Point p : rowPoints){

                Character c = grid.get(p);
                if (Character.isDigit(c)){

                    if (isAdjacent == false) {
                    Set<Point> neighbours = p.getNeighbours();
                    for (Point n : neighbours) {
                        Character test = grid.get(n);
                        if (test != null) {
                            if ((!Character.isDigit(test)) && (test != '.'))
                                isAdjacent = true;
                        }
                    }
                }

                    digit = digit + c.toString();
                } else {
                    if (!digit.isEmpty()) {
                 //       System.out.println(digit + " " + isAdjacent);
                        if (isAdjacent == true)
                            sum += Integer.parseInt(digit);
                        isAdjacent = false;
                        digit = "";
                    }
                }
            }
        }
        return sum + "";
    }

    @Override
    public String solvePart2() {
        int result = 0;
        List<String> sourceData = getListString();
        buildGrid(sourceData);
        List<Point> stars = findAllStars();
        for (Point s: stars){
            Set<Point> digits = getSurroundingDigitsStart(s);
            if (digits.size() == 2) {
                System.out.println(digits);

                Iterator<Point> i = digits.iterator();
                int x = getDigit(i.next());
                int y = getDigit(i.next());
                result += (x*y);
            }
        }


        return result + "";
    }

    private List<Point> findAllStars(){
        return grid.getAllDataMatching('*');
    }
    private Set<Point> getSurroundingDigitsStart(Point point) {
        Set<Point> results = new HashSet();

        Set<Point> neighbours = point.getNeighbours();
        int count = 0;
        for (Point n : neighbours) {
            if (grid.withinGrid(n)) {
                if (Character.isDigit(grid.get(n))) {
                    Point start = startOfDigit(n);
                    results.add(start);
                }
            }
        }

        return results;
    }
    private Set<Point> getSurroundingDigits(Point p){
        Set<Point> result = new HashSet<>();
        Set<Point> neighbours = p.getNeighbours();
        for (Point n : neighbours){
            if (grid.withinGrid(n)) {
                if (Character.isDigit(grid.get(n)))
                    result.add(startOfDigit(n));
            }
        }
        return result;
    }
    private Point startOfDigit(Point p){
        Point result = new Point(p);
        while (grid.withinGrid(result) && Character.isDigit(grid.get(result))){
            result = result.add(-1,0);
        }
        return result.add(1,0);
    }

    private int getDigit(Point p){
        String digit = "";
        while (Character.isDigit(grid.get(p))){
            digit = digit + grid.get(p);
            p = p.add(1,0);
        }
        return Integer.parseInt(digit);
    }


    public void buildGrid(List<String> sourceData){
        grid = new Grid<>();
        for (int y = 0; y < sourceData.size(); y++) {
            String line = sourceData.get(y);
            for (int x = 0; x < line.length(); x++) {
                grid.add(new Point(x,y),line.charAt(x));
            }
        }
    }
}
