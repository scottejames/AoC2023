package com.scottejames.aoc2023.day7;


public enum Type {

    HIGH_CARD("High Card"),
    ONE_PAIR("One Pair"),
    TWO_PAIR("Two Pair"),
    THREE_OF_KIND("Three of a Kind"),
    FULL_HOUSE("Full House"),
    FOUR_OF_KIND("Four of a Kind"),
    FIVE_OF_KIND("Five of a Kind");


    private final String name;

    Type(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                '}';
    }
}
