package com.scottejames.aoc2023.day7;

import java.util.HashMap;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Hand implements Comparable<Hand>{
    String cards;
    int bid;
    Type type;
    Map<Character, Integer> rankings = new HashMap<>();

    public Hand(String cards, int bid) {

        rankings.put('2', 1);
        rankings.put('3', 2);
        rankings.put('4', 3);
        rankings.put('5', 4);
        rankings.put('6', 5);
        rankings.put('7', 6);
        rankings.put('8', 7);
        rankings.put('9', 8);
        rankings.put('T', 9);
        rankings.put('J', 10);
        rankings.put('Q', 11);
        rankings.put('K', 12);
        rankings.put('A', 13);

        this.cards = cards;
        this.bid = bid;
        setType();
    }

    private void setType() {
        Map<Character,Long> cardFrequency = cards.chars()
                .mapToObj(i -> (char) i)
                .collect(groupingBy(identity(), counting()));

        Map<Long,Long> groupFrequency = cardFrequency.values()
                .stream()
                .collect(groupingBy(identity(), counting()));

        long singles = groupFrequency.getOrDefault(1L, -1L);
        long pairs = groupFrequency.getOrDefault(2L, -1L);
        long trios = groupFrequency.getOrDefault(3L, -1L);
        long quartets = groupFrequency.getOrDefault(4L, -1L);

        if (singles == cards.length()) {
            type = Type.HIGH_CARD;
            return;
        }
        // one pair
        if (pairs == 1 && singles == 3) {
            type = Type.ONE_PAIR;
            return;
        }
        // two pair
        if (pairs == 2 && singles == 1) {
            type = Type.TWO_PAIR;
            return;

        }
        // three of a kind
        if (trios == 1 && singles == 2) {
            type = Type.THREE_OF_KIND;
            return;

        }
        // full house
        if (trios == 1 && pairs == 1) {
            type = Type.FULL_HOUSE;
            return;

        }
        // four of a kind
        if (quartets == 1 && singles == 1) {
            type = Type.FOUR_OF_KIND;
            return;

        }
        // five of a kind
        type = Type.FIVE_OF_KIND;
    }
    @Override
    public int compareTo(Hand o) {
        if (type.ordinal() > o.type.ordinal()) {
            return 1;
        } else if (type.ordinal() < o.type.ordinal()) {
            return -1;
        }
        for (int i = 0; i < cards.length(); i++) {
            int thisRank = rankings.get(cards.charAt(i));
            int otherRank = rankings.get(o.cards.charAt(i));

            if (thisRank > otherRank) {
                return 1;
            } else if (thisRank < otherRank) {
                return -1;
            }
        }
        return 0;
    }

}
