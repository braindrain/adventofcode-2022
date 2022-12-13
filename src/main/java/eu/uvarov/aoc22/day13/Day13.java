package eu.uvarov.aoc22.day13;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.uvarov.aoc22.util.GenericDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Day13 extends GenericDay {
    public Day13() {
        super("13");
    }

    public static void main(String[] args) {
        Day13 day13 = new Day13();
        day13.solve();
    }

    @Override
    public Object part1Solution() {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] split = inputString().split(LS + LS);
        List<Pair> pairs = new ArrayList<>();

        for (String s : split) {
            String[] pairStrings = s.split(LS);
            pairs.add(new Pair(parsePair(pairStrings[0], objectMapper), parsePair(pairStrings[1], objectMapper)));
        }


        int sum = 0;
        for (int i = 0; i < pairs.size(); i++) {
            Pair pair = pairs.get(i);
            System.out.println(split[i] + LS);
            if (pair.compareOrder(pair.left, pair.right) == Pair.Result.YES) {
                sum = sum + i + 1;
            }
        }

        return sum;
    }

    public List<Object> parsePair(String pair, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(pair, List.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object part2Solution() {
        Pair pair = new Pair(0, 0);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> objects = Arrays.stream(inputString().replace(LS + LS, LS).split(LS)).map((Function<String, Object>) s -> parsePair(s, objectMapper)).sorted((o1, o2) -> {
            Pair.Result result = pair.compareOrder(o1, o2);
            if (result == Pair.Result.YES) {
                return -1;
            } else if (result == Pair.Result.NO) {
                return 1;
            } else {
                return 0;
            }
        }).toList();

        List<Object> two = new ArrayList<>();
        List<Object> nested2 = new ArrayList<>();
        nested2.add(2);
        two.add(nested2);
        int i = objects.indexOf(two) + 1;

        List<Object> six = new ArrayList<>();
        List<Object> nested6 = new ArrayList<>();
        nested6.add(6);
        six.add(nested6);
        int j = objects.indexOf(six) + 1;

        return i * j;
    }
}
