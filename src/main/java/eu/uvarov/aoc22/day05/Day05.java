package eu.uvarov.aoc22.day05;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Collections.*;

public class Day05 extends GenericDay {
    public Day05() {
        super("05");
    }

    public static void main(String[] args) {
        Day05 day05 = new Day05();
        day05.solve();
    }

    @Override
    public Object part1Solution() {
        String[] split = inputString().split(LS + LS);
        Stack<String>[] stacks = inputStacks(split[0]);
        List<Move> moves = Arrays.stream(split[1].split(LS)).map(String::trim).map(s -> regexp(s, Pattern.compile("move (\\d+) from (\\d+) to (\\d+)"), Move.class)).toList();

        for (Move move : moves) {
            Stack<String> from = stacks[Integer.parseInt(move.from) - 1];
            Stack<String> to = stacks[Integer.parseInt(move.to) - 1];
            for (int i = 0; i < Integer.parseInt(move.count); i++) {
                to.push(from.pop());
            }
        }
        return Arrays.stream(stacks).map(Stack::peek).collect(Collectors.joining());
    }

    @Override
    public Object part2Solution() {
        String[] split = inputString().split(LS + LS);
        Stack<String>[] stacks = inputStacks(split[0]);
        List<Move> moves = Arrays.stream(split[1].split(LS)).map(String::trim).map(s -> regexp(s, Pattern.compile("move (\\d+) from (\\d+) to (\\d+)"), Move.class)).toList();

        for (Move move : moves) {
            Stack<String> from = stacks[Integer.parseInt(move.from) - 1];
            Stack<String> to = stacks[Integer.parseInt(move.to) - 1];
            List<String> crates = new ArrayList<>();
            for (int i = 0; i < Integer.parseInt(move.count); i++) {
                crates.add(from.pop());
            }
            Collections.reverse(crates);
            to.addAll(crates);
        }

        return Arrays.stream(stacks).map(Stack::peek).collect(Collectors.joining());
    }

    public Stack<String>[] inputStacks(String input) {
        List<String> strings = Arrays.asList(input.split(LS));
        reverse(strings);

        Stack<String>[] stacks = new Stack[9];
        int count = 0;
        for (String string : strings) {
            if (count != 0) {
                String[] s = string.split(" ");
                int stackNum = 0;
                for (int i = 0; i < s.length; i++) {
                    String s1 = s[i];
                    if (s1.contains("[")) {
                        if (stacks[stackNum] == null) {
                            stacks[stackNum] = new Stack();
                        }
                        stacks[stackNum].push(s1.replace("[", "").replace("]", ""));

                    } else {
                        i += 3;
                    }
                    stackNum++;
                }
            }
            count++;
        }
        return stacks;
    }


    public record Move(String count, String from, String to) {

    }
}
