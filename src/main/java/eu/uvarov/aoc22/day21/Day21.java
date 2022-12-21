package eu.uvarov.aoc22.day21;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.*;
import java.util.function.Function;

public class Day21 extends GenericDay {
    public Day21() {
        super("21");
    }

    public static void main(String[] args) {
        Day21 day21 = new Day21();
        day21.solve();
    }


    @Override
    public Object part1Solution() {
        String[] strings = inputArray();
        Map<String, Sentence> sentenceMap = new HashMap<>();

        for (String string : strings) {
            String[] split = string.split(":");
            String trim = split[1].trim();
            if (trim.contains("-") || trim.contains("+") || trim.contains("*") || trim.contains("/")) {
                String[] split1 = trim.split(" ");
                String sign = split1[1];
                String left = split1[0];
                String right = split1[2];
                sentenceMap.put(split[0], new RawSentence(left, right, sign));
            } else {
                sentenceMap.put(split[0], new Operand(split[0], Long.parseLong(trim)));
            }
        }

        Map<String, Sentence> realSentences = new HashMap<>();

        for (Map.Entry<String, Sentence> stringSentenceEntry : sentenceMap.entrySet()) {
            realSentences.put(stringSentenceEntry.getKey(), createRealSentence(stringSentenceEntry.getKey(), sentenceMap));
        }

        return realSentences.get("root").perform();
    }


    Sentence createRealSentence(String name, Map<String, Sentence> sentenceMap) {
        Sentence sentence = sentenceMap.get(name);
        if (sentence instanceof RawSentence) {
            RawSentence rawSentence = (RawSentence) sentence;
            Sentence left = createRealSentence(rawSentence.left, sentenceMap);
            Sentence right = createRealSentence(rawSentence.right, sentenceMap);
            switch (rawSentence.sign) {
                case "+":
                    return new Sum(name, left, right);
                case "-":
                    return new Min(name, left, right);
                case "*":
                    return new Mul(name, left, right);
                case "/":
                    return new Div(name, left, right);
            }
        }
        return sentence;
    }

    @Override
    public Object part2Solution() {
        String[] strings = inputArray();
        Map<String, Sentence> sentenceMap = new HashMap<>();

        for (String string : strings) {
            String[] split = string.split(":");
            String trim = split[1].trim();
            if (trim.contains("-") || trim.contains("+") || trim.contains("*") || trim.contains("/")) {
                String[] split1 = trim.split(" ");
                String sign = split1[1];
                String left = split1[0];
                String right = split1[2];
                sentenceMap.put(split[0], new RawSentence(left, right, sign));
            } else {
                sentenceMap.put(split[0], new Operand(split[0], Long.parseLong(trim)));
            }
        }

        Map<String, Sentence> realSentences = new HashMap<>();

        for (Map.Entry<String, Sentence> stringSentenceEntry : sentenceMap.entrySet()) {
            realSentences.put(stringSentenceEntry.getKey(), createRealSentence(stringSentenceEntry.getKey(), sentenceMap));
        }


        Operation root = (Operation) realSentences.get("root");

        long result = 0;

        while (true) {
            Operand humn = (Operand) realSentences.get("humn");
            humn.value = result;
            long left = root.left.perform();
            long right = root.right.perform();
            if (left == right) {
               break;
            }
            long diff = left - right;
            if (diff < 50) {
                result++;
            } else {
                result += diff / 50;
            }
        }

        return result;
    }
}
