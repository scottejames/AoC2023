package com.scottejames.aoc2023;

import com.scottejames.aoc2023.util.AbstractDay;
import com.scottejames.aoc2023.util.ArrayHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 extends AbstractDay {
    public List<String> sample;

    public Day4() throws IOException {
        super(4);
        sample = List.of(
                "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");

    }

    private String getCardData(String line){
        String [] parts = line.split(":");
        return parts[1].trim();
    }
    private int getCardNumber(String line){
        String [] parts = line.split(":");
        String cardNumber = parts[0].split("\\s+")[1];
        return Integer.parseInt(cardNumber);
    }
    private int [] getWinningNumbers(String line){
        String [] parts = line.split("\\|")[0].trim().split("\\s+");
        return ArrayHelper.convertToIntArray(parts);
    }
    private int [] getYourNumbers(String line){
        String [] parts = line.split("\\|")[1].trim().split("\\s+");

        return ArrayHelper.convertToIntArray(parts);
    }

    public int scoreCard(int [] card){
        int points = 0;
        for (int c: card){
            points = (points == 0) ? 1 : points * 2;
        }
        return points;
    }
    @Override
    public String solvePart1() {
        int answer = 0;
        for (String line: getListString()){
            String cardData = getCardData(line);
            int [] winningNumbers = getWinningNumbers(cardData);
            int [] yourNumbers = getYourNumbers(cardData);
            int [] result = ArrayHelper.intersection(winningNumbers,yourNumbers);
            int score = scoreCard(result);
            answer += score;
        }
        return answer + "";
    }

    @Override
    public String solvePart2() {
        Map<Integer, Integer> cardCount = new HashMap<>();

        for (String line : getListString()) {
            String cardData = getCardData(line);
            int cardNumber = getCardNumber(line);
            if (cardCount.get(cardNumber) == null)
                cardCount.put(cardNumber, 1);
            int[] winningNumbers = getWinningNumbers(cardData);
            int[] yourNumbers = getYourNumbers(cardData);
            int[] result = ArrayHelper.intersection(winningNumbers, yourNumbers);
            int numCards = cardCount.getOrDefault(cardNumber, 1);
            for (int i = cardNumber + 1; i < cardNumber + result.length + 1; i++) {
                int c = cardCount.getOrDefault(i, 1);
                cardCount.put(i, c + numCards);
            }


        }
        int result = 0;
        for (int c : cardCount.keySet()) {
            System.out.println("Card " + c + " has " + cardCount.get(c) + " copies");
            result += cardCount.get(c);
        }
        return "" + result;
    }
}
