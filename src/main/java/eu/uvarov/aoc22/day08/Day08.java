package eu.uvarov.aoc22.day08;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.Arrays;
import java.util.Map;

public class Day08 extends GenericDay {
    public Day08() {
        super("08");
    }

    public static void main(String[] args) {
        Day08 day08 = new Day08();
        day08.solve();
    }

    @Override
    public Object part1Solution() {
        int[][] trees = parseTrees();
        return traverse(trees).get("totalVisible");
    }

    @Override
    public Object part2Solution() {
        int[][] trees = parseTrees();
        return traverse(trees).get("maxScenicScore");
    }

    public int[][] parseTrees() {
        String[] strings = inputArray();
        int[][] trees = new int[strings[0].length()][strings.length];

        for (int i = 0; i < strings.length; i++) {
            String s = strings[i];
            for (int j = 0; j < s.length(); j++) {
                trees[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }

        return trees;
    }

    Map<String, Object> traverse(int[][] trees) {
        int count = 0;
        int maxScenicScore = 1;
        for (int i = 0; i < trees.length; i++) {
            for (int j = 0; j < trees[i].length; j++) {
                Map<String, Object> tree = countVisibilityAndScore(trees, i, j);
                if ((Boolean) tree.get("visible")) {
                    count++;
                }
                maxScenicScore = Math.max(maxScenicScore, (Integer) tree.get("score"));
            }
        }
        return Map.of("totalVisible", count, "maxScenicScore", maxScenicScore);
    }

    private Map<String, Object> countVisibilityAndScore(int[][] trees, int x, int y) {
        if (x == 0 || x == trees.length - 1 || y == 0 || y == trees[0].length - 1)
            return Map.of("visible", true, "score", 0);
        else {
            int[] vertical = new int[trees.length];
            for (int i = 0; i < vertical.length; i++) {
                vertical[i] = trees[i][y];
            }
            int[] left = Arrays.copyOfRange(trees[x], 0, y + 1);
            int[] right = Arrays.copyOfRange(trees[x], y, trees[x].length);
            int[] top = Arrays.copyOfRange(vertical, 0, x + 1);
            int[] bottom = Arrays.copyOfRange(vertical, x, trees.length);
            boolean visible = isVisible(trees[x][y], top, bottom, left, right);
            int score = scenicScore(trees[x][y], top, bottom, left, right);
            return Map.of("visible", visible, "score", score);
        }
    }

    boolean isVisible(int value, int[] top, int[] bottom, int[] left, int[] right) {
        return isMax(value, top) || isMax(value, bottom) || isMax(value, left) || isMax(value, right);
    }

    int scenicScore(int value, int[] top, int[] bottom, int[] left, int[] right) {
        return countScore(value, top, true) * countScore(value, left, true) * countScore(value, bottom, false) * countScore(value, right, false);
    }

    boolean isMax(int value, int[] array) {
        return Arrays.stream(array).max().getAsInt() == value && Arrays.stream(array).filter(i -> i == value).count() == 1;
    }

    int countScore(int value, int[] array, boolean reverse) {
        int score = 0;

        if (reverse) {
            for (int i = array.length - 2; i >= 0; i--) {
                score++;
                if (array[i] >= value) {
                    break;
                }
            }
        } else {
            for (int i = 1; i < array.length; i++) {
                score++;
                if (array[i] >= value) {
                    break;
                }
            }
        }
        return score;
    }
}
