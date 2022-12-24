package eu.uvarov.aoc22.day24;

import eu.uvarov.aoc22.day13.Pair;
import eu.uvarov.aoc22.day23.Day23;
import eu.uvarov.aoc22.util.GenericDay;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day24 extends GenericDay {
    public Day24() {
        super("24");
    }

    public static void main(String[] args) {
        Day24 day24 = new Day24();
        day24.solve();
    }

    @Override
    public Object part1Solution() {
        char[][] map = new char[27][122];
        //char[][] map = new char[12][16];
        String[] strings = inputString().split(LS);
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            for (int j = 0; j < string.length(); j++) {
                map[i][j] = string.charAt(j);
            }
        }

        MazeMap mazeMap = new MazeMap(map);

        List<Blizzard> blizzards = initBlizzards(map);
        mazeMap.placeBlizzards(blizzards);
        //mazeMap.print();
        int maxX = mazeMap.maze.keySet().stream().mapToInt(point -> point.x).max().getAsInt();
        int maxY = mazeMap.maze.keySet().stream().mapToInt(point -> point.y).max().getAsInt();

        Point start = new Point(1, 0);
        Point exit = new Point(maxX - 1, maxY);

        return movePath(blizzards, mazeMap, map, start, exit).left;
    }

    Pair movePath(List<Blizzard> blizzards, MazeMap mazeMap, char[][] initialMap, Point start, Point end) {
        Set<Point> path = new HashSet<>();
        path.add(start);
        int minute = 1;
        boolean realEnd = false;
        while (true) {
            blizzards = moveBlizzards(blizzards, mazeMap);
            MazeMap mmap = new MazeMap(initialMap);
            mmap.placeBlizzards(blizzards);
            //mmap.print();
            path = path.stream().flatMap(point -> nextMoves(point, mmap).stream()).collect(Collectors.toSet());
            if (path.contains(end)) {
                return new Pair(minute, blizzards);
            }
            minute++;
        }
    }

    Set<Point> nextMoves(Point current, MazeMap mazeMap) {
        Set<Point> nextMoves = new HashSet<>();
        Direction[] values = Direction.values();
        for (Direction value : values) {
            Point next = current.move(value);
            if (next.x >= 0 && next.y >= 0 && mazeMap.canMove(next)) {
                nextMoves.add(next);
            }
        }
        Point point = new Point(current.x, current.y);
        if (point.x >= 0 && point.y >= 0 && mazeMap.canMove(point)) {
            nextMoves.add(point);
        }
        return nextMoves;
    }

    List<Blizzard> initBlizzards(char[][] map) {
        List<Blizzard> blizzards = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '<' || map[i][j] == '>' || map[i][j] == '^' || map[i][j] == 'v') {
                    Point point = new Point(j, i);
                    blizzards.add(new Blizzard(point, getBlizzardDirection(map[i][j])));
                }
            }
        }
        return blizzards;
    }

    List<Blizzard> moveBlizzards(List<Blizzard> blizzards, MazeMap map) {
        List<Blizzard> newBlizzards = new ArrayList<>();
        for (Blizzard blizzard : blizzards) {
            Blizzard nextBlizzard = new Blizzard(blizzard.point.move(blizzard.direction), blizzard.direction);
            if (map.isWall(nextBlizzard.point)) {
                int maxX = map.maze.keySet().stream().mapToInt(point -> point.x).max().getAsInt();
                int maxY = map.maze.keySet().stream().mapToInt(point -> point.y).max().getAsInt();
                Point newBlizzardLocation = null;
                switch (blizzard.direction) {
                    case N:
                        newBlizzardLocation = new Point(blizzard.point.x, maxY - 1);
                        break;
                    case S:
                        newBlizzardLocation = new Point(blizzard.point.x, 1);
                        break;
                    case E:
                        newBlizzardLocation = new Point(1, blizzard.point.y);
                        break;
                    case W:
                        newBlizzardLocation = new Point(maxX - 1, blizzard.point.y);
                        break;
                }
                nextBlizzard = new Blizzard(newBlizzardLocation, blizzard.direction);
            }
            newBlizzards.add(nextBlizzard);
        }
        return newBlizzards;
    }

    public Direction getBlizzardDirection(char c) {
        switch (c) {
            case '<':
                return Direction.W;
            case '>':
                return Direction.E;
            case '^':
                return Direction.N;
            case 'v':
                return Direction.S;
        }
        throw new IllegalArgumentException("Unknown blizzard direction " + c);
    }

    @Override
    public Object part2Solution() {
        char[][] map = new char[27][122];
        //char[][] map = new char[12][16];
        String[] strings = inputString().split(LS);
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            for (int j = 0; j < string.length(); j++) {
                map[i][j] = string.charAt(j);
            }
        }

        MazeMap mazeMap = new MazeMap(map);

        List<Blizzard> blizzards = initBlizzards(map);
        mazeMap.placeBlizzards(blizzards);
        //mazeMap.print();
        int maxX = mazeMap.maze.keySet().stream().mapToInt(point -> point.x).max().getAsInt();
        int maxY = mazeMap.maze.keySet().stream().mapToInt(point -> point.y).max().getAsInt();

        Point start = new Point(1, 0);
        Point exit = new Point(maxX - 1, maxY);

        Pair pair = movePath(blizzards, mazeMap, map, start, exit);
        Pair pair2 = movePath((List<Blizzard>) pair.right, mazeMap, map, exit, start);
        Pair pair3 = movePath((List<Blizzard>) pair2.right, mazeMap, map, start, exit);

        return (Integer) pair.left + (Integer) pair2.left + (Integer) pair3.left;
    }

    public class MazeMap {
        public final Map<Point, Character> maze = new HashMap<>();

        public MazeMap(char[][] map) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != Character.MIN_VALUE) {
                        if (map[i][j] != '<' && map[i][j] != '>' && map[i][j] != '^' && map[i][j] != 'v') {
                            maze.put(new Point(j, i), map[i][j]);
                        } else {
                            maze.put(new Point(j, i), '.');
                        }
                    }

                }
            }
        }

        public void placeBlizzards(List<Blizzard> blizzards) {
            for (Blizzard blizzard : blizzards) {
                maze.put(blizzard.point, 'B');
            }
        }

        public Optional<Character> get(Point point) {
            return maze.containsKey(point) ? Optional.of(maze.get(point)) : Optional.empty();
        }

        public boolean isWall(Point point) {
            return get(point).map(c -> c == '#').orElse(false);
        }

        public boolean canMove(Point point) {
            return get(point).map(c -> c == '.').orElse(false);
        }

        void print() {
            int minX = maze.keySet().stream().mapToInt(point -> point.x).min().getAsInt();
            int maxX = maze.keySet().stream().mapToInt(point -> point.x).max().getAsInt();
            int minY = maze.keySet().stream().mapToInt(point -> point.y).min().getAsInt();
            int maxY = maze.keySet().stream().mapToInt(point -> point.y).max().getAsInt();
            for (int y = minY; y <= maxY; y++) {
                for (int x = minX; x <= maxX; x++) {
                    System.out.print(maze.getOrDefault(new Point(x, y), '.'));

                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public enum Direction {
        N, S, E, W;

        Direction getReverse() {
            switch (this) {
                case N:
                    return S;
                case S:
                    return N;
                case E:
                    return W;
                case W:
                    return E;
            }
            throw new IllegalArgumentException("Unknown direction " + this);
        }
    }

    public class Blizzard {
        public final Point point;
        public final Direction direction;

        public Blizzard(Point point, Direction direction) {
            this.point = point;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Blizzard blizzard = (Blizzard) o;
            return Objects.equals(point, blizzard.point) && direction == blizzard.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(point, direction);
        }
    }

    public class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public Point move(Direction direction) {
            int pointX = x;
            int pointY = y;
            switch (direction) {
                case N:
                    pointY--;
                    break;
                case S:
                    pointY++;
                    break;
                case E:
                    pointX++;
                    break;
                case W:
                    pointX--;
                    break;
            }
            return new Point(pointX, pointY);
        }
    }
}
