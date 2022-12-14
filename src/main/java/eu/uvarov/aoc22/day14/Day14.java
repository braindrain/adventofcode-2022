package eu.uvarov.aoc22.day14;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class Day14 extends GenericDay {
    public Day14() {
        super("14");
    }

    public static void main(String[] args) {
        Day14 day14 = new Day14();
        day14.solve();
    }

    record Rock(int x, int y) {
    }

    @Override
    public Object part1Solution() {
        List<List<Rock>> rocks = new ArrayList<>();
        String[] strings = inputArray();
        for (String string : strings) {
            List<Rock> rocksLine = new ArrayList<>();
            String[] split = string.split(" -> ");
            for (String s : split) {
                rocksLine.add(new Rock(Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1])));
            }
            rocks.add(rocksLine);
        }

        int maxX = rocks.stream().flatMap(Collection::stream).mapToInt(Rock::x).max().orElse(0);
        int maxY = rocks.stream().flatMap(Collection::stream).mapToInt(Rock::y).max().orElse(0);

        char[][] map = new char[maxY + 1][maxX + 1];
        for (int i = 0; i < maxY + 1; i++) {
            for (int j = 0; j < maxX + 1; j++) {
                map[i][j] = '.';
            }
        }

        for (List<Rock> rock : rocks) {
            fillRock(rock, map);
        }

        sandStep(map);

        return countSand(map);
    }

    void fillRock(List<Rock> rocks, char[][] map) {
        for (int i = 0; i < rocks.size() - 1; i++) {
            Rock rock = rocks.get(i);
            Rock nextRock = rocks.get(i + 1);
            if (rock.x == nextRock.x) {
                int from = Math.min(rock.y, nextRock.y);
                int to = Math.max(rock.y, nextRock.y);
                for (int j = from; j <= to; j++) {
                    map[j][rock.x] = '#';
                }
            } else {
                int from = Math.min(rock.x, nextRock.x);
                int to = Math.max(rock.x, nextRock.x);
                for (int j = from; j <= to; j++) {
                    map[rock.y][j] = '#';
                }
            }
        }
    }

    void showMap(char[][] map) {
        for (char[] chars : map) {
            System.out.println(chars);
        }
    }

    void sandStep(char[][] map) {
        int x = 500;
        int y = 0;
        map[y][x] = '+';
        while (y < map.length-1) {
            if (map[y + 1][x] == '.') {
                y = y + 1;
                continue;
            }

            if (map[y + 1][x - 1] == '.') {
                y = y + 1;
                x = x - 1;
                continue;
            }

            if (map[y + 1][x + 1] == '.') {
                y = y + 1;
                x = x + 1;
                continue;
            }
            map[y][x] = '*';
            x = 500;
            y = 0;
        }
    }

    void sandStep2(char[][] map) {
        int x = 500;
        int y = 0;
        map[y][x] = '+';
        while (map[y][x]!='*') {
            if (map[y + 1][x] == '.') {
                y = y + 1;
                continue;
            }

            if (map[y + 1][x - 1] == '.') {
                y = y + 1;
                x = x - 1;
                continue;
            }

            if (map[y + 1][x + 1] == '.') {
                y = y + 1;
                x = x + 1;
                continue;
            }
            map[y][x] = '*';
            //showMap(map);
            //System.out.println();
            x = 500;
            y = 0;
        }
    }

    int countSand(char[][] map) {
        int count = 0;
        for (char[] chars : map) {
            for (char aChar : chars) {
                if (aChar == '*') {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public Object part2Solution() {
        List<List<Rock>> rocks = new ArrayList<>();
        String[] strings = inputArray();
        for (String string : strings) {
            List<Rock> rocksLine = new ArrayList<>();
            String[] split = string.split(" -> ");
            for (String s : split) {
                rocksLine.add(new Rock(Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1])));
            }
            rocks.add(rocksLine);
        }

        int maxX = rocks.stream().flatMap(Collection::stream).mapToInt(Rock::x).max().orElse(0) + 500;
        int maxY = rocks.stream().flatMap(Collection::stream).mapToInt(Rock::y).max().orElse(0) + 2;

        rocks.add(List.of(new Rock(0, maxY), new Rock(maxX, maxY)));

        char[][] map = new char[maxY + 1][maxX + 1];
        for (int i = 0; i < maxY + 1; i++) {
            for (int j = 0; j < maxX + 1; j++) {
                map[i][j] = '.';
            }
        }

        for (List<Rock> rock : rocks) {
            fillRock(rock, map);
        }

        sandStep2(map);

        return countSand(map);
    }
}
