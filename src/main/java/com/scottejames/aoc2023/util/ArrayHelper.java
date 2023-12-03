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

    public static int[][] convertToArrayOfIntArrays(final List<String> input) {
        final int outerLength = input.size();
        final int innerLength = input.get(0).length();

        final int[][] arrayOfIntArrays = new int[outerLength][innerLength];

        for (int i = 0; i < input.size(); i++) {
            final String line = input.get(i);
            arrayOfIntArrays[i] = convertToArrayOfInts(line);
        }

        return arrayOfIntArrays;
    }
    private static int[] convertToArrayOfInts(final CharSequence input) {
        final int[] intArray = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            intArray[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
        }
        return intArray;
    }
}
