package eu.uvarov.aoc22.day04;

import eu.uvarov.aoc22.util.GenericDay;
import java.util.stream.Stream;

import static java.lang.Integer.*;

public class Day04 extends GenericDay {
    public Day04() {
        super("04");
    }

    public static void main(String[] args) {
        Day04 day04 = new Day04();
        day04.solve();
    }

    @Override
    public Object part1Solution() {
        return rangesStream()
                .filter(ranges -> rangeFullyContains(ranges[0][0], ranges[0][1], ranges[1][0], ranges[1][1]))
                .count();
    }

    @Override
    public Object part2Solution() {
        return rangesStream()
                .filter(ranges -> rangePartiallyContains(ranges[0][0], ranges[0][1], ranges[1][0], ranges[1][1]))
                .count();
    }

    public Stream<Integer[][]> rangesStream() {
        return inputStream()
                .map(s -> s.split(","))
                .map(strings -> new String[][]{strings[0].split("-"), strings[1].split("-")})
                .map(strings -> new Integer[][]{new Integer[]{parseInt(strings[0][0]), parseInt(strings[0][1])}, new Integer[]{parseInt(strings[1][0]), parseInt(strings[1][1])}});
    }

    public boolean rangeFullyContains(int start1, int end1, int start2, int end2) {
        return start1 <= start2 && end1 >= end2 || start2 <= start1 && end2 >= end1;
    }

    public boolean rangePartiallyContains(int start1, int end1, int start2, int end2) {
        return start1 <= start2 && end1 >= start2 || start2 <= start1 && end2 >= start1;
    }
}
