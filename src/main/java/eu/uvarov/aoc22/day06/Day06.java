package eu.uvarov.aoc22.day06;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.HashMap;
import java.util.Map;

public class Day06 extends GenericDay {
    public Day06() {
        super("06");
    }

    public static void main(String[] args) {
        Day06 day06 = new Day06();
        day06.solve();
    }

    @Override
    public Object part1Solution() {
        return calculateWindowEnd(4);
    }


    @Override
    public Object part2Solution() {
        return calculateWindowEnd(14);
    }

    private int calculateWindowEnd(int size) {
        String s = inputString();
        Map<Character, Integer> windowPositions = new HashMap<>();

        int windowStart = 0;
        int windowEnd = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (windowPositions.containsKey(c)) {
                windowStart = Math.max(windowStart, windowPositions.get(c) + 1);
            }

            windowPositions.put(c, i);
            if (i - windowStart + 1 == size) {
                windowEnd = i + 1;
                break;
            }
        }

        return windowEnd;
    }
}
