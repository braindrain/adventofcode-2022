package eu.uvarov.aoc22.day18;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day18 extends GenericDay {
    public Day18() {
        super("18");
    }

    public static void main(String[] args) {
        Day18 day18 = new Day18();
        day18.solve();
    }

    @Override
    public Object part1Solution() {
        String[] strings = inputArray();
        List<Cube> cubes = new ArrayList<>();
        for (String string : strings) {
            String[] coordinates = string.split(",");
            cubes.add(new Cube(coordinates[0], coordinates[1], coordinates[2]));
        }

        long adjacent = cubes.stream().flatMap(cube -> Arrays.stream(cube.adjacentCubes())).filter(cubes::contains).count();

        return (6L * cubes.size()) - adjacent;
    }

    @Override
    public Object part2Solution() {
        String[] strings = inputArray();
        Set<Cube> cubes = new HashSet<>();
        for (String string : strings) {
            String[] coordinates = string.split(",");
            cubes.add(new Cube(coordinates[0], coordinates[1], coordinates[2]));
        }

        IntSummaryStatistics collectX = cubes.stream().map(cube -> cube.x).collect(Collectors.summarizingInt(Integer::intValue));
        IntSummaryStatistics collectY = cubes.stream().map(cube -> cube.y).collect(Collectors.summarizingInt(Integer::intValue));
        IntSummaryStatistics collectZ = cubes.stream().map(cube -> cube.z).collect(Collectors.summarizingInt(Integer::intValue));


        Set<Cube> visited = new HashSet<>();
        LinkedList<Cube> toVisit = new LinkedList<>();
        toVisit.add(new Cube(collectX.getMin() - 1, collectY.getMin() - 1, collectZ.getMin() - 1));

        AtomicInteger sides = new AtomicInteger(0);

        while (!toVisit.isEmpty()) {
            Cube current = toVisit.removeFirst();
            if (!visited.contains(current)) {
                Arrays.stream(current.adjacentCubes()).filter(cube -> isWithinRange(cube, collectX, collectY, collectZ)).forEach(cube -> {
                    if (cubes.contains(cube)) {
                        sides.addAndGet(1);
                    } else {
                        toVisit.add(cube);
                    }
                });
                visited.add(current);
            }
        }
        return sides;
    }

    boolean isWithinRange(Cube cube, IntSummaryStatistics statsX, IntSummaryStatistics statsY, IntSummaryStatistics statsZ) {
        return cube.x >= statsX.getMin() - 1 && cube.x <= statsX.getMax() + 1 && cube.y >= statsY.getMin() - 1 && cube.y <= statsY.getMax() + 1 && cube.z >= statsZ.getMin() - 1 && cube.z <= statsZ.getMax() + 1;
    }
}
