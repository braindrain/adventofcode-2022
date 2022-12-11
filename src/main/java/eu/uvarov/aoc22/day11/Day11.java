package eu.uvarov.aoc22.day11;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Predicate;

public class Day11 extends GenericDay {
    public Day11() {
        super("11");
    }

    public static void main(String[] args) {
        Day11 day11 = new Day11();
        day11.solve();
    }

    @Override
    public Object part1Solution() {

        List<Monkey> monkeys = input();
        for (int i = 0; i < 20; i++) {
            for (Monkey m : monkeys) {
                m.round();
            }
        }

        OptionalLong reduce = monkeys.stream().sorted((o1, o2) -> Long.compare(o2.itemsCount, o1.itemsCount)).limit(2).mapToLong(m -> m.itemsCount).reduce((a, b) -> a * b);
        return reduce.getAsLong();
    }

    List<Monkey> input() {
        List<Monkey> monkeys = new ArrayList<>();
        Monkey monkey0 = new Monkey(monkeys, new long[]{83, 88, 96, 79, 86, 88, 70}, value -> value * 5, 11L, 2, 3);
        Monkey monkey1 = new Monkey(monkeys, new long[]{59, 63, 98, 85, 68, 72}, value -> value * 11, 5L, 4, 0);
        Monkey monkey2 = new Monkey(monkeys, new long[]{90, 79, 97, 52, 90, 94, 71, 70}, value -> value + 2, 19L, 5, 6);
        Monkey monkey3 = new Monkey(monkeys, new long[]{97, 55, 62}, value -> value + 5, 13L, 2, 6);
        Monkey monkey4 = new Monkey(monkeys, new long[]{74, 54, 94, 76}, value -> value * value, 7L, 0, 3);
        Monkey monkey5 = new Monkey(monkeys, new long[]{58}, value -> value + 4, 17L, 7, 1);
        Monkey monkey6 = new Monkey(monkeys, new long[]{66, 63}, value -> value + 6, 2L, 7, 5);
        Monkey monkey7 = new Monkey(monkeys, new long[]{56, 56, 90, 96, 68}, value -> value + 7, 3L, 4, 1);
        monkeys.add(monkey0);
        monkeys.add(monkey1);
        monkeys.add(monkey2);
        monkeys.add(monkey3);
        monkeys.add(monkey4);
        monkeys.add(monkey5);
        monkeys.add(monkey6);
        monkeys.add(monkey7);
        return monkeys;
    }

    List<Monkey> testInput() {
        List<Monkey> monkeys = new ArrayList<>();
        Monkey monkey0 = new Monkey(monkeys, new long[]{79, 98}, value -> value * 19, 23L, 2, 3);
        Monkey monkey1 = new Monkey(monkeys, new long[]{54, 65, 75, 74}, value -> value + 6, 19L, 2, 0);
        Monkey monkey2 = new Monkey(monkeys, new long[]{79, 60, 97}, value -> value * value, 13L, 1, 3);
        Monkey monkey3 = new Monkey(monkeys, new long[]{74}, value -> value + 3, 17L, 0, 1);
        monkeys.add(monkey0);
        monkeys.add(monkey1);
        monkeys.add(monkey2);
        monkeys.add(monkey3);
        return monkeys;
    }

    @Override
    public Object part2Solution() {
        List<Monkey> monkeys = input();
        long gcd = monkeys.stream().mapToLong(e -> e.divisible).reduce((a, b) -> a * b).getAsLong();

        for (int i = 0; i < 10000; i++) {
            for (Monkey m : monkeys) {
                m.round2(gcd);
            }
        }

        OptionalLong reduce = monkeys.stream().sorted((o1, o2) -> Long.compare(o2.itemsCount, o1.itemsCount)).limit(2).mapToLong(m -> m.itemsCount).reduce((a, b) -> a * b);
        return reduce.getAsLong();
    }
}
