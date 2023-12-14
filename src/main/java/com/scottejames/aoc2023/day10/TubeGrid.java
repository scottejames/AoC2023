package com.scottejames.aoc2023.day10;

import com.scottejames.aoc2023.util.Direction;
import com.scottejames.aoc2023.util.Grid;
import com.scottejames.aoc2023.util.Pair;
import com.scottejames.aoc2023.util.Point;

import java.util.HashMap;
import java.util.Set;

public class TubeGrid extends Grid<Character> {
    HashMap<Character, Pair<Direction>> pipeMap = new HashMap<>();

    public TubeGrid(){
        pipeMap.put('|',new Pair(Direction.UP, Direction.DOWN));
        pipeMap.put('-',new Pair(Direction.LEFT, Direction.RIGHT));
        pipeMap.put('7',new Pair(Direction.LEFT, Direction.DOWN));
        pipeMap.put('L',new Pair(Direction.UP, Direction.RIGHT));
        pipeMap.put('F',new Pair(Direction.DOWN, Direction.RIGHT));
        pipeMap.put('J',new Pair(Direction.LEFT, Direction.UP));

    }
    public Set<Point> getNeighbours(Point p){
        Set<Point> result = p.getNeighbours();

        for (Point n : result){
            Character c = get(n);
            if (c == null) result.remove(n);
        }
        return result;
    }

    public boolean canPass(Point from, Point to){
        Direction d = Direction.direction(from,to);

        boolean result = false;
        Character fromChar = this.get(from);
        Character toChar = this.get(to);

        if (pipeMap.get(fromChar).contains(d) && pipeMap.get(toChar).contains(d.reverse())){
            result = true;
        }
        return result;

    }

    public Pair<Direction> getExits(Point p){
        char c = get(p);
        return pipeMap.get(c);
    }
    public Point navigate(Point p, Direction d){
        return p.move(d);
    }
}
