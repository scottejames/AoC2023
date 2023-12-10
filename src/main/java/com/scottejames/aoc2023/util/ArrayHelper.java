package com.scottejames.aoc2023.util;

import java.util.ArrayList;
import java.util.List;

public class ArrayHelper {
    public static int  sumListOfInteger(List<Integer> list){
        int result = 0;
        for (int number: list) result += number;
        return result;
    }

    public static List<Integer> getRange(int x, int y){

        List<Integer> result = new ArrayList<>();


        if (y>x){
            for (int c=x; c<=y;c++){
                result.add(c);
            }
        } else if (x>y) {
            for (int c=x; c>=y; c--){
                result.add(c);
            }
        } else if (x==y){
            result.add(x);
        }
        return result;

    };

    public static int[] convertToIntArray(String [] input){
        int [] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = Integer.parseInt(input[i]);
        }
        return result;
    }
    private static int[] convertToArrayOfInts(final CharSequence input) {
        final int[] intArray = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            intArray[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
        }
        return intArray;
    }

    public static void printArray(int[] array){
        for (int s : array) {
            System.out.println(s);
        }
    }
    public static int[] intersection(int [] a, int []b){
        List<Integer> result = new ArrayList<>();
        for (int i : a) {
            for (int j : b) {
                if (i == j) {
                    result.add(i);
                }
            }
        }
        return result.stream().mapToInt(i->i).toArray();

    }
}
