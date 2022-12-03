package eu.uvarov.aoc22.day02;

import eu.uvarov.aoc22.util.GenericDay;

public class Day02 extends GenericDay {

    public Day02() {
        super("02");
    }

    public static void main(String[] args) {
        Day02 day02 = new Day02();
        day02.solve();
    }

    @Override
    public Object part1Solution() {
        return inputClass(Round.class, SPACE_SEPARATED).stream().mapToLong(Round::yourScore).sum();
    }

    @Override
    public Object part2Solution() {
        return inputClass(Round.class, SPACE_SEPARATED).stream().mapToLong(Round::yourScoreWithDesiredOutcome).sum();
    }
}
