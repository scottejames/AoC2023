package com.scottejames.aoc2023;

import com.scottejames.aoc2023.util.AbstractDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5 extends AbstractDay {
    List<String> sample;
    public Day5() throws IOException {
        super(5);

        sample = List.of(
        "seeds: 79 14 55 13",
        "",
        "seed-to-soil map:",
        "50 98 2",
        "52 50 48",
        "",
        "soil-to-fertilizer map:",
        "0 15 37",
        "37 52 2",
        "39 0 15",
        "",
        "fertilizer-to-water map:",
        "49 53 8",
        "0 11 42",
        "42 0 7",
        "57 7 4",
        "",
        "water-to-light map:",
        "88 18 7",
        "18 25 70",
        "",
        "light-to-temperature map:",
        "45 77 23",
        "81 45 19",
        "68 64 13",
        "",
        "temperature-to-humidity map:",
        "0 69 1",
        "1 0 69",
        "",
        "humidity-to-location map:",
        "60 56 37",
        "56 93 4"
        );
    }
    @Override
    public String solvePart1() {
        List<String>  sourceData = getListString();
        long[] seeds = null;
        List<Range> tmpRangeList = new ArrayList<>();
        List<RangeMap> rangeMaps = new ArrayList<>();

        for (String line: sourceData){
            if (line.isEmpty()){
                continue;
            }
            if (line.contains("seeds:")){
                String[] stringSeeds = sourceData.get(0).split(" ");
                seeds = new long[stringSeeds.length - 1];
                for (int i = 0; i < seeds.length; i++) {
                    seeds[i] = Long.parseLong(stringSeeds[i + 1]);
                }
                continue;
            }
            if (line.contains("map:")){
                // we have hit a new set of ranges so add old to list
                if (tmpRangeList.size() > 0){
                    rangeMaps.add(new RangeMap(tmpRangeList));
                    tmpRangeList.clear();
                }
                tmpRangeList = new ArrayList<>();
                continue;
            }
            tmpRangeList.add(new Range(line));
        }
        long result = 10000000000L;
        rangeMaps.add(new RangeMap(tmpRangeList));
            for (Long seed : seeds) {
                long val = seed;
                for (RangeMap rangeMap : rangeMaps) {
                    val = rangeMap.convert(val);
                }
                if (val < result) {
                    result = val;
                }
            }
        return result + "";
    }

    @Override
    public String solvePart2() {
        List<String>  sourceData = getListString();
        long[] seeds = null;
        List<Range> tmpRangeList = new ArrayList<>();
        List<RangeMap> rangeMaps = new ArrayList<>();

        for (String line: sourceData){
            if (line.isEmpty()){
                continue;
            }
            if (line.contains("seeds:")){
                String[] stringSeeds = sourceData.get(0).split(" ");
                seeds = new long[stringSeeds.length - 1];
                for (int i = 0; i < seeds.length; i++) {
                    seeds[i] = Long.parseLong(stringSeeds[i + 1]);
                }
                continue;
            }
            if (line.contains("map:")){
                // we have hit a new set of ranges so add old to list
                if (tmpRangeList.size() > 0){
                    rangeMaps.add(new RangeMap(tmpRangeList));
                    tmpRangeList.clear();
                }
                tmpRangeList = new ArrayList<>();
                continue;
            }
            tmpRangeList.add(new Range(line));
        }
        long result = 10000000000L;
        rangeMaps.add(new RangeMap(tmpRangeList));
        for (int i = 0; i < seeds.length; i += 2) {
            for (long j = seeds[i]; j < seeds[i] + seeds[i + 1]; j++) {
                long[] ret = returnValAndBound(j, rangeMaps);
                if (ret[0] < result) {
                    result = ret[0];
                }
                j += ret[1];
            }
        }
        return result + "";
    }
    private long[] returnValAndBound(long val, List<RangeMap> rangeMaps) {
        long bound = 10000000000L;
        for (RangeMap rangeMap : rangeMaps) {
            bound = Math.min(bound, rangeMap.convert2(val)[1]);
            val = rangeMap.convert2(val)[0];
        }
        return new long[]{val, bound};
    }
}

class Range {
    long destination;
    long source;
    long range;

    public Range(long destination, long source, long range) {
        this.destination = destination;
        this.source = source;
        this.range = range;
    }

    public Range(String line) {
        String[] pieces = line.split(" ");
        destination = Long.parseLong(pieces[0]);
        source = Long.parseLong(pieces[1]);
        range = Long.parseLong(pieces[2]);
    }
}
    class RangeMap {
        List<Long> starts;
        List<Long> ends;
        List<Long> betweens;

    public RangeMap(List<Range> ranges) {
        starts = new ArrayList<>();
        ends = new ArrayList<>();
        betweens = new ArrayList<>();

        for (Range range : ranges) {
            starts.add(range.source);
            ends.add(range.destination);
            betweens.add(range.range);
        }
    }
        public long convert(long val) {
            for (int i = 0; i < starts.size(); i++) {
                if (starts.get(i) <= val && starts.get(i) + betweens.get(i) > val) {
                    return ends.get(i) + (val - starts.get(i));
                }
            }
            return val;
        }
        public long[] convert2(long val) {
            long nextStart = 10000000000L;
            for (int i = 0; i < starts.size(); i++) {
                if (starts.get(i) > val) {
                    nextStart = Math.min(nextStart, starts.get(i) - val - 1);
                }
                if (starts.get(i) <= val && starts.get(i) + betweens.get(i) > val) {
                    return new long[]{ends.get(i) + (val - starts.get(i)), betweens.get(i) - (val - starts.get(i)) - 1};
                }
            }
            return new long[]{val, nextStart == 10000000000L ? 0 : nextStart};
        }
}