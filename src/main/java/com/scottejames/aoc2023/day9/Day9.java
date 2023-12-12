package com.scottejames.aoc2023.day9;

import com.scottejames.aoc2023.util.AbstractDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9 extends AbstractDay {
    private List<String> sample;
    public Day9() throws IOException {
        super(9);
        sample = List.of(
                "0 3 6 9 12 15",
                "1 3 6 10 15 21",
                "10 13 16 21 30 45");

    }

    private int [] stringToIntArray(String s){
        String [] parts = s.split("\\s+");
        int [] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++){
            result[i] = Integer.parseInt(parts[i]);
        }
        return result;
    }
    private int [] findDifferenceInSequence(int [] sequence){
        int [] result = new int[sequence.length -1];
        for (int i = 0; i < sequence.length -1; i++){
            result[i] = sequence[i+1] - sequence[i];
        }
        return result;
    }
    private boolean areDifferencesConstant(int[] differences){
        int first = differences[0];
        for (int i = 1; i < differences.length; i++){
            if (differences[i] != first) return false;
        }
        return true;
    }
    private List<Integer> findConstantDifference(int[] sequences,boolean end){
        int [] s = sequences;
        List<Integer> result = new ArrayList<Integer> ();

        boolean constantDifferencess = false;
        while(!constantDifferencess){
            int[] d = findDifferenceInSequence(s);
            constantDifferencess = areDifferencesConstant(d);
            s=d;
            if (end)
                result.add(d[d.length-1]);
            else
                result.add(d[0]);
        }
        return result;
    }
    @Override
    public String solvePart1() {
        int result = 0;
        int sum = 0;

        List<String> data = getListString();
        for (String line: data) {
            sum = 0;
            int[] sequence = stringToIntArray(line);

            List<Integer> diffs = findConstantDifference(sequence,true);
            for (Integer i : diffs) {
                sum += i;
            }
            sum += sequence[sequence.length - 1];
           // System.out.println(sum);
            result += sum;

        }
        return result + "";
    }

    @Override
    public String solvePart2() {
        int result = 0;
        int sum = 0;

        List<String> data = getListString();
       // String line = sample.get(1);
       for (String line: data) {
            int[] sequence = stringToIntArray(line);
            sum = 0;

            List<Integer> diffs = findConstantDifference(sequence,false);
            Collections.reverse(diffs);
            for (Integer i : diffs) {
                sum = i - sum;
            }
            // print diffs array
           System.out.println(diffs);
            sum = sequence[0] - sum;
           System.out.println(sum);

           result += sum;

        }
        return result + "";    }
}
