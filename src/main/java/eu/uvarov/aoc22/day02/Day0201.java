package eu.uvarov.aoc22.day02;

import eu.uvarov.aoc22.util.InputUtil;

import java.util.stream.Stream;

import static eu.uvarov.aoc22.util.InputUtil.SPACE_SEPARATED;


public class Day0201 {

    public static void main(String[] args) {
        System.out.println(roundsStream().mapToLong(Round::yourScore).sum());
        System.out.println(roundsStream().mapToLong(Round::yourScoreWithDesiredOutcome).sum());
    }

    public static Stream<Round> roundsStream() {
        return InputUtil.read("day0201.txt", Round.class, SPACE_SEPARATED).stream();
    }
}
