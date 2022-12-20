package eu.uvarov.aoc22.day20;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day20 extends GenericDay {
    public Day20() {
        super("20");
    }

    public static void main(String[] args) {
        Day20 day20 = new Day20();
        day20.solve();
    }

    @Override
    public Object part1Solution() {
        List<Long> collect = Arrays.stream(inputArray()).map(Long::parseLong).collect(Collectors.toList());
        CircularList circular = new CircularList(collect);
        for (Node n : circular.backedList) {
            circular.moveNode(n);
        }
        return circular.getGroveCoordinates();
    }

    @Override
    public Object part2Solution() {
        List<Long> collect = Arrays.stream(inputArray()).map(s -> Long.parseLong(s) * 811589153L).toList();
        CircularList circular = new CircularList(collect);
        for (int i = 0; i < 10; i++) {
            for (Node n : circular.backedList) {
                circular.moveNode(n);
            }
        }
        return circular.getGroveCoordinates();
    }
}
