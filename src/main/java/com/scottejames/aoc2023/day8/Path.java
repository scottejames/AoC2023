package com.scottejames.aoc2023.day8;

import java.util.Deque;
import java.util.LinkedList;

public class Path {
    private Deque<Character> path;

    public char nextDirection() {

        char direction = path.pollFirst();
        path.offerLast(direction); // Loopy McLoopface

        return direction;
    }

    public Path(String path) {
        this.path = new LinkedList<>();
        for (char direction : path.toCharArray()) {
            this.path.offerLast(direction);
        }
    }
}
