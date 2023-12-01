package com.scottejames.aoc2023.util;

import java.util.HashMap;
import java.util.Map;

public class WordToDigitMap {

    public static String[] get() {

        String[] wordToDigitMap = new String[10];
        wordToDigitMap[0] = "zero";
        wordToDigitMap[1] = "one";
        wordToDigitMap[2] = "two";
        wordToDigitMap[3] = "three";
        wordToDigitMap[4] = "four";
        wordToDigitMap[5] = "five";
        wordToDigitMap[6] = "six";
        wordToDigitMap[7] = "seven";
        wordToDigitMap[8] = "eight";
        wordToDigitMap[9] = "nine";
        return wordToDigitMap;
    }

    public static String convertAllWordsInStringToDigits(String input) {
        String [] wordToDigitMap = get();
        for (int digit = 0; digit < wordToDigitMap.length; digit++) {
            input = input.replaceAll(wordToDigitMap[digit],
                    wordToDigitMap[digit] + digit + wordToDigitMap[digit]);
        }
        return input;
    }
}
