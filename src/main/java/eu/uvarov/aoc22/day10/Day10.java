package eu.uvarov.aoc22.day10;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 extends GenericDay {
    public Day10() {
        super("10");
    }

    public static void main(String[] args) {
        Day10 day10 = new Day10();
        day10.solve();
    }

    @Override
    public Object part1Solution() {
        String[] strings = inputArray();

        List<Integer> results = new ArrayList<>();
        int cycle = 1;
        int x = 1;
        for (String operation : strings) {
            results.add(signalStrength(cycle, x));
            if (operation.startsWith("addx")) {
                int add = Integer.parseInt(operation.substring(5));
                cycle++;
                results.add(signalStrength(cycle, x));
                x += add;
            }
            cycle++;
        }
        return results.stream().mapToInt(Integer::intValue).sum();
    }


    private int signalStrength(int cycle, int x) {
        int cycl = cycle + 20;
        if (cycl % 40 == 0) {
            return cycle * x;
        } else {
            return 0;
        }
    }

    private String getPixel(int cycle, int x) {
        StringBuilder builder = new StringBuilder();
        int i = (cycle) % 40;
        if (i == 0) {
            builder.append(LS);
        }
        if (Arrays.asList(x - 1, x, x + 1).contains(i)) {
            builder.append("#");
        } else {
            builder.append(".");
        }
        return builder.toString();
    }

    @Override
    public Object part2Solution() {
        String[] strings = inputArray();

        List<String> results = new ArrayList<>();
        int cycle = 0;
        int x = 1;
        for (String operation : strings) {
            results.add(getPixel(cycle, x));
            if (operation.startsWith("addx")) {
                int add = Integer.parseInt(operation.substring(5));
                cycle++;
                results.add(getPixel(cycle, x));
                x += add;
            }
            cycle++;
        }
        return String.join("", results);
    }
}
