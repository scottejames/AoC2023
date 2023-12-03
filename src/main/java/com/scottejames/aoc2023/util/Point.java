package com.scottejames.aoc2023.util;

import java.util.*;
import java.util.stream.Collectors;

public class Point {
    public int x;
    public int y;

    public Point(){
        this.x=0;
        this.y=0;
    }
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point(String x, String y) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point(String coords){
        var s = coords.split(",");
        this.x = Integer.parseInt(s[0].trim());
        this.y = Integer.parseInt(s[1].trim());

    }
    public List<Point> trail(Point destination){
        List<Point> result = new ArrayList<>();
        var xRange = ArrayHelper.getRange(x, destination.x);
        var yRange = ArrayHelper.getRange(y, destination.y);

        if ((x==destination.x) ||(y==destination.y)) {
            // Straight line
            for (int x : xRange) {
                for (int y : yRange) {
                    result.add(new Point(x, y));
                }
            }
        } else {
            // diaginal
            for (int i = 0 ; i < xRange.size();i++)
                result.add(new Point(xRange.get(i), yRange.get(i)));
        }
        return result;
    }
    public Point add(int x , int y){
        return add(new Point(x,y));
    }
    public Point add(Point p) {
        return new Point(x + p.x, y + p.y);
    }

    public Point delta(Point b){
        return new Point(x- b.x , y - b.y );
    }
    public Point multiply(int i) {
        return new Point (x*i , y*i);
    }
    public int manHattenDistance(){
        return Math.abs(x) + Math.abs(y);
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Set<Point> getNeighbours() {
        Set<Point> result = new HashSet<>();
        result.add(new Point(x + 1, y));
        result.add(new Point(x - 1, y));
        result.add(new Point(x + 1, y - 1));
        result.add(new Point(x - 1, y - 1));
        result.add(new Point(x + 1, y + 1));
        result.add(new Point(x - 1, y + 1));
        result.add(new Point(x, y + 1));
        result.add(new Point(x, y - 1));
        return result;
    }

    public Set<Point> getNeighboursPositive(){
        return getNeighbours().stream().filter(t->(t.x>=0) && (t.y>=0)).collect(Collectors.toSet());
    }

    public Set<Point> getCartNeighbours(){
        Set<Point> result = new HashSet<>();
        result.add(new Point(x + 1, y));
        result.add(new Point(x - 1, y));
        result.add(new Point(x, y + 1));
        result.add(new Point(x, y - 1));
        return result;
    }
    public Set<Point> getCartNeighboursPositive() {
        return getCartNeighbours().stream().filter(t->(t.x>=0) && (t.y>=0)).collect(Collectors.toSet());

    }


    public boolean adjacent(Point p){
        Set<Point> n = getNeighbours();
        n.add(this); // touching could be overlapping
        return n.contains(p);
    }


}
