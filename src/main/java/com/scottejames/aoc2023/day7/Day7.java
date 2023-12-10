package com.scottejames.aoc2023.day7;

import com.scottejames.aoc2023.util.AbstractDay;


import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Day7 extends AbstractDay {
    List<String> sample;

    public Day7() throws IOException {
        super(7);

        sample = List.of(
                "32T3K 765",
                "T55J5 684",
                "KK677 28",
                "KTJJT 220",
                "QQQJA 483");
    }

    @Override
    public String solvePart1() {
       long result = 0;
       List<String> data = getListString();

       List<Hand> hands = new ArrayList<>();
       for(String line: data){
           String [] parts = line.split("\\s+");
           hands.add(new Hand(parts[0],Integer.parseInt(parts[1])));
       }
       Collections.sort(hands);
       result = IntStream.range(0, hands.size())
                .map(i -> hands.get(i).bid * (i + 1))
                .sum();
        return result + "" ;
    }



    @Override
    public String solvePart2() {
        long result = 0;
        List<String> data = getListString();

        List<HandWild> hands = new ArrayList<>();
        for(String line: data){
            String [] parts = line.split("\\s+");
            hands.add(new HandWild(parts[0],Integer.parseInt(parts[1])));
        }
        Collections.sort(hands);
        result = IntStream.range(0, hands.size())
                .map(i -> hands.get(i).bid * (i + 1))
                .sum();
        return result + "" ;    }




}
