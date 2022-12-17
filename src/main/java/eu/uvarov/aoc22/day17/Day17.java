package eu.uvarov.aoc22.day17;

import eu.uvarov.aoc22.util.GenericDay;

public class Day17 extends GenericDay {
    public Day17() {
        super("17");
    }

    public static void main(String[] args) {
        Day17 day17 = new Day17();
        day17.solve();
    }

    @Override
    public Object part1Solution() {
        Board tetris = new Board();
        return tetris.play(inputString(), 2022, state -> false).highest;
    }

    @Override
    public Object part2Solution() {
        Board tetris = new Board();
        long numberOfRocks = 1000000000000L;
        State firstPeriod = tetris.play(inputString(), numberOfRocks, s -> s.resetPeriod && s.rocksCount != 0);
        State secondPeriod = tetris.play(inputString(), numberOfRocks, s -> s.resetPeriod && s.rocksCount > firstPeriod.rocksCount);


        long rocksPerPeriod = secondPeriod.rocksCount - firstPeriod.rocksCount;
        long numberOfPeriods = numberOfRocks / rocksPerPeriod;

        long totalRocks = rocksPerPeriod * numberOfPeriods + firstPeriod.rocksCount;
        long heightPerPeriod = secondPeriod.highest - firstPeriod.highest;
        long totalHeight = heightPerPeriod * numberOfPeriods + firstPeriod.highest;
        long delta = totalRocks - numberOfRocks;

        return totalHeight - (firstPeriod.highest - tetris.play(inputString(), numberOfRocks, s -> s.rocksCount == firstPeriod.rocksCount - delta).highest);
    }
}
