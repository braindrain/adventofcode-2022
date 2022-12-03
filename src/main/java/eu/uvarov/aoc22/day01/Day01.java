package eu.uvarov.aoc22.day01;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.LongStream;

public class Day01 extends GenericDay {

    public Day01() {
        super("01");
    }

    public static void main(String[] args) {
        Day01 day01 = new Day01();
        day01.solve();
    }

    public LongStream sumCaloriesStream() {
        return inputStream(LS + LS).mapToLong(s -> Arrays.stream(s.split(LS)).map(String::trim).mapToLong(Long::parseLong).sum());
    }

    @Override
    public Object part1Solution() {
        return sumCaloriesStream().max().getAsLong();
    }

    @Override
    public Object part2Solution() {
        return sumCaloriesStream().boxed().sorted(Comparator.reverseOrder()).limit(3).mapToLong(Long::longValue).sum();
    }
}

