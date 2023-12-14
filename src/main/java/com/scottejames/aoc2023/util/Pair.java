package com.scottejames.aoc2023.util;

public class Pair<T> {
    public T first;
    public T second;

    public Pair(T first, T second){
        this.first = first;
        this.second = second;
    }
    public Pair(){
        this.first = null;
        this.second = null;
    }

    public boolean contains(T t){
        return first == t || second == t;
    }
    public T other(T other){
        if (this.first == other) return this.second;
        if (this.second == other) return this.first;
        throw new IllegalArgumentException("tried to find other from " + other + " but does not exist in pair");
    }

    public String toString(){
        return "(" + first + "," + second + ")";
    }

}
