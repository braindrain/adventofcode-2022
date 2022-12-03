package eu.uvarov.aoc22.day03;

import eu.uvarov.aoc22.util.InputUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Day0301 {
    public static void main(String[] args) throws IOException {

        List<String> rucksacks = Files.lines(InputUtil.path("day0301.txt")).toList();

        System.out.println(rucksacks.stream().map(s -> getPriority(findCommonChar(evenlySplit(s)))).mapToInt(value -> value.intValue()).sum());

        List<String> group = new ArrayList<>();
        int priority = 0;
        for (String s : rucksacks) {
            group.add(s);
            if (group.size() == 3) {
                priority += getPriority(findCommonChar(group));
                group.clear();
            }
        }
        System.out.println(priority);
    }

    public static List<String> evenlySplit(String input) {
        int length = input.length() / 2;
        List<String> parts = new ArrayList<>();
        parts.add(input.substring(0, length));
        parts.add(input.substring(length));
        return parts;
    }

    public static Character findCommonChar(List<String> strings) {
        Set<Character> commonCharsSeed = getUniqueChars(strings.get(0));
        strings.stream().skip(1).forEach(s -> commonCharsSeed.retainAll(getUniqueChars(s)));

        assert commonCharsSeed.size() == 1;

        return commonCharsSeed.iterator().next();
    }

    private static Set<Character> getUniqueChars(String string) {
        Set<Character> set = new HashSet<>();
        for (char c : string.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    public static int getPriority(Character c) {
        if (Character.isUpperCase(c)) {
            return (int) c - 38;
        }
        return (int) c - 96;
    }
}
