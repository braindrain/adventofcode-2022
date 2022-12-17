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
        return tetris.play(inputString());
    }

    @Override
    public Object part2Solution() {
        return null;
    }
}
