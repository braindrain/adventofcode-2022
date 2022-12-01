package eu.uvarov.aoc22.day01;

import eu.uvarov.aoc22.util.InputUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.LongStream;

import static eu.uvarov.aoc22.util.InputUtil.LS;

public class Day0101 {
    public static void main(String[] args) throws IOException {
        System.out.println(sumCaloriesStream().max().getAsLong());
        System.out.println(sumCaloriesStream().boxed().sorted(Comparator.reverseOrder()).limit(3).mapToLong(Long::longValue).sum());
    }

    private static LongStream sumCaloriesStream() throws IOException {
        return Arrays
                .stream(
                        Files.readString(InputUtil.path("day0101.txt")).split(LS + LS))
                .mapToLong(s -> Arrays.stream(s.split(LS))
                        .map(String::trim)
                        .mapToLong(Long::parseLong)
                        .sum());
    }
}

