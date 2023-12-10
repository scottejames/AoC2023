package com.scottejames.aoc2023;

import com.scottejames.aoc2023.util.AbstractDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6 extends AbstractDay {
    private List<String> sample;

    public Day6() throws IOException {
        super(6);
        sample = List.of(
                "Time:      7  15   30",
                "Distance:  9  40  200");


    }
    private List<Long> strToLong(String ip){
        List<Long> result = new ArrayList<>();
        String [] parts = ip.split("\\s+");
        for (String part: parts){
            result.add(Long.parseLong(part));
        }
        return result;
    }

    @Override
    public String solvePart1() {
        List<Long> time;
        List<Long> distance;
        List<String> data = getListString();
        time = strToLong(data.get(0).split(":")[1].trim());
        distance = strToLong(data.get(1).split(":")[1].trim());

        System.out.println(time);
        System.out.println(distance);
        int result = 1;
        for (int i = 0; i < time.size();i++) {
            int t = time.get(i).intValue();
            int d = distance.get(i).intValue();

            long c = countRecordBeaters(t, d);
            result *= c;
        }

        return result + "";
    }
    @Override
    public String solvePart2() {
        // CBA to parse IP data

        long count = 49979494;
        long record = 263153213781851L;

        long c = countRecordBeaters(count,record);
        return c + "";
    }
    private long countRecordBeaters(long duration, long record){
        long distance = 0;
        long count = 0;
        for (long pressTime = 0;pressTime <=duration;pressTime++){
            long remainingTime = duration - pressTime;
            long speed = pressTime;
            if (speed!=0) {
                distance = remainingTime * pressTime;
            } else {
                distance = 0;
            }
            if (distance > record){
                count++;
            }
        }
        return count;
    }


}
