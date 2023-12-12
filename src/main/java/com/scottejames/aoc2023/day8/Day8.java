package com.scottejames.aoc2023.day8;

import com.scottejames.aoc2023.util.AbstractDay;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 extends AbstractDay {
    private List<String> sample;
    private World world = null;

    public Day8() throws IOException {
        super(8);
        sample = List.of(
                "LR",
                "",
                "11A = (11B, XXX)",
                "11B = (XXX, 11Z)",
                "11Z = (11B, XXX)",
                "22A = (22B, XXX)",
                "22B = (22C, 22C)",
                "22C = (22Z, 22Z)",
                "22Z = (22B, 22B)",
                "XXX = (XXX, XXX)");
    }
    private void loadData(){
        List<String> data = getListString();
        Map<String, String[]> map = new HashMap<>();
        String path = null;
        for (String line: data){
            if (line.isBlank()) continue;
            if (line.contains("=")){
                String[] split = line.split(" = ");
                String key = split[0];
                String[] splitValues = split[1].split(", ");
                String[] values = new String[2];
                values[0] = splitValues[0].substring(1);
                values[1] = splitValues[1].substring(0, splitValues[1].length() - 1);
                map.put(key, values);

            }else if (line.startsWith("L") || line.startsWith("R")) {
                path = line;
            }
        }

        world = new World(path,map);


    }
    @Override
    public String solvePart1() {
        loadData();
        int steps = 0;//world.walkMap();
        return steps + "";
    }

    @Override
    public String solvePart2() {
        long steps = world.countAllTheSteps();
        return steps + "";
    }
}
