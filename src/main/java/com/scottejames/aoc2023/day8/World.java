package com.scottejames.aoc2023.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.scottejames.aoc2023.util.Maths.lcm;

public class World {
    private static final String START = "AAA";
    private static final String END = "ZZZ";

    Path directions;
    Map<String, String[]> graph;

    // Add a constructor
    public World(String directions, Map<String, String[]> graph) {
        this.directions = new Path(directions);
        this.graph = graph;
    }

    public int walkMap(){
        String current = START;
        int steps = 0;
        while (!current.equals(END)){
            String[] next = graph.get(current);
            char direction = directions.nextDirection();
            if (direction == 'L'){
                current = next[0];
            }else if (direction == 'R'){
                current = next[1];
            }
            steps++;
        }
        return steps;
    }
    public long countAllTheSteps() {
        int[] stepForNodes = stepsNeededForEachNode();
        long lcm = 1;
        for (int current : stepForNodes) {
            lcm = lcm(lcm, current);
        }
        return lcm;
    }
    public int[] stepsNeededForEachNode() {
        List<String> startingNodes = getStartingNodes();
        int[] stepsNeeded = new int[startingNodes.size()];

        for (int i = 0; i < startingNodes.size(); i++) {
            int steps = 0;
            String current = startingNodes.get(i);
            while (!current.endsWith("Z")) {
                String[] next = graph.get(current);
                char direction = directions.nextDirection();
                if (direction == 'L'){
                    current = next[0];
                }else if (direction == 'R'){
                    current = next[1];
                }
                steps++;
            }
            stepsNeeded[i] = steps;
        }
        return stepsNeeded;
    }
    private List<String> getStartingNodes() {
        List<String> startingNodes = new ArrayList<>();
        for (String key : graph.keySet()) {
            if (key.endsWith("A")) {
                startingNodes.add(key);
            }
        }
        return startingNodes;
    }

}
