package eu.uvarov.aoc22.day03;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.stream.IntStream;

public class Day03 extends GenericDay {
    public Day03() {
        super("03");
    }

    public static void main(String[] args) {
        Day03 day03 = new Day03();
        day03.solve();
    }

    @Override
    public Object part1Solution() {
        return inputStream()
                .map(this::evenlySplit)
                .mapToInt(parts -> getPrioritiesStream(parts[0])
                        .filter(priority1 -> getPrioritiesStream(parts[1]).anyMatch(priority2 -> priority1 == priority2))
                        .findFirst().getAsInt())
                .sum();
    }

    @Override
    public Object part2Solution() {
        String[] rucksacks = inputArray();
        return IntStream.range(0, rucksacks.length / 3)
                .map(chunk -> chunk * 3)
                .map(chunk -> getPrioritiesStream(rucksacks[chunk])
                        .filter(priority1 -> getPrioritiesStream(rucksacks[chunk + 1])
                                .anyMatch(priority2 -> priority1 == priority2) && getPrioritiesStream(rucksacks[chunk + 2])
                                .anyMatch(priority3 -> priority1 == priority3))
                        .findFirst().getAsInt())
                .sum();
    }

    public String[] evenlySplit(String input) {
        return new String[]{input.substring(0, input.length() / 2), input.substring(input.length() / 2)};
    }

    public IntStream getPrioritiesStream(String input) {
        return input.chars().mapToObj(c -> (char) c).map(this::getPriority).mapToInt(Integer::intValue);
    }


    public int getPriority(Character c) {
        return Character.isUpperCase(c) ? (int) c - 38 : (int) c - 96;
    }
}
