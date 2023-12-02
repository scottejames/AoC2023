package com.scottejames.aoc2023;

import com.scottejames.aoc2023.util.AbstractDay;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 extends AbstractDay {
    List<String> sample;
    public Day2() throws IOException {
        super(2);
        sample = List.of(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        );
    }

    public int parseGameNumber(String line){
        String [] parts = line.split(":");
        return Integer.parseInt(parts[0].split(" ")[1]);
    }
    public Map<String,Integer> parseCounters(String line){
        Map<String,Integer> map = new HashMap<>();

        String [] parts = line.split(":");
        String [] gameParts = parts[1].split(";");

        for (String gamePart : gameParts) {
            String [] game = gamePart.split(",");
            for (String s : game) {
                String [] colour = s.split(" ");
                int curr = map.getOrDefault(colour[2].trim(),0);
                int next = Integer.parseInt(colour[1].trim());
                if (next > curr)
                    map.put(colour[2].trim(),next);
            }
        }
        return map;
    }
    public boolean possible(Map gameData){
        int red = (int) gameData.getOrDefault("red",0);
        int blue = (int) gameData.getOrDefault("blue",0);
        int green = (int) gameData.getOrDefault("green",0);

        if ((green <= 13) && (blue <= 14) && (red <= 12))
            return true;
        else return false;
    }
    public int power(Map gameData){
        int red = (int) gameData.getOrDefault("red",0);
        int blue = (int) gameData.getOrDefault("blue",0);
        int green = (int) gameData.getOrDefault("green",0);

        return (red * blue * green);
    }
    @Override
    public String solvePart1() {
        int total = 0;
        for (String line : getListString()) {
//            System.out.println(parseGameNumber(line));
//            System.out.println(parseCounters(line));
            if (possible(parseCounters(line)))
                total+= parseGameNumber(line);
        }
        return "Total: " + total;
    }

    @Override
    public String solvePart2() {
        int total = 0;
        for (String line : getListString()) {
//            System.out.println(parseGameNumber(line));
//            System.out.println(parseCounters(line));
            total += power(parseCounters(line));
        }
        return "Total: " + total;    }

}

