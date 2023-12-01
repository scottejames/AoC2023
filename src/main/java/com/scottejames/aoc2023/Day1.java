package com.scottejames.aoc2023;

import com.scottejames.aoc2023.util.AbstractDay;
import com.scottejames.aoc2023.util.WordToDigitMap;

import java.io.IOException;
import java.util.List;

public class Day1 extends AbstractDay {
    List<String> partTwoSample;
    List<String> sample;

    public Day1() throws IOException {
        super(1);
        sample = List.of(
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet");
        partTwoSample = List.of(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen");
    }

    @Override
    public String solvePart1() {
        List<String> input = getListString();
        int value = input.stream()
                .mapToInt( line -> {
                    char first = firstInt(line);
                    char last = lastInt(line);
                    String sum = "" + first + last;
                   // System.out.println(first + " + " + last + " = " + sum);
                    return Integer.parseInt(sum);
                }).sum();
        return ""+ value;
    }
    @Override
    public String solvePart2() {
        List<String> input = getListString();
        List<String> converted = input.stream()
                .map(WordToDigitMap::convertAllWordsInStringToDigits)
                .collect(java.util.stream.Collectors.toList());
        int value = converted.stream()
                .mapToInt( line -> {
                    char first = firstInt(line);
                    char last = lastInt(line);
                    String sum = "" + first + last;
                     System.out.println(first + " + " + last + " = " + sum);
                    return Integer.parseInt(sum);
                }).sum();
        return value +"";
    }
    private char firstInt(String s){
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c))
                return c;
        }
        return 0;
    }
    private char lastInt(String s){
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return firstInt(sb.toString());
    }



}
