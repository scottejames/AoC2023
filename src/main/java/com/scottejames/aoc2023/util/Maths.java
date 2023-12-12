package com.scottejames.aoc2023.util;

public class Maths {
    public static long gcd(long a, long b) {
        if (a == 0)
            return b;

        return gcd(b % a, a);
    }

    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
