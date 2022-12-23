package eu.uvarov.aoc22.day23;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.*;

import static eu.uvarov.aoc22.day23.Day23.Direction.*;

public class Day23 extends GenericDay {

    public Day23() {
        super("23");
    }

    public static void main(String[] args) {
        Day23 day23 = new Day23();
        day23.solve();
    }

    @Override
    public Object part1Solution() {
        char[][] map = new char[71][71];
        //char[][] map = new char[12][16];
        String[] strings = inputString().split(LS);
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            for (int j = 0; j < string.length(); j++) {
                map[i][j] = string.charAt(j);
            }
        }
        MazeMap mazeMap = new MazeMap(map);


        round(mazeMap);

        int i = checkSquare(mazeMap);
        return i;
    }

    public AdjacentPositionChecker[] adjacentPositionCheckers = new AdjacentPositionChecker[]{
            new AdjacentPositionChecker(new Direction[]{N, NE, NW}),
            new AdjacentPositionChecker(new Direction[]{S, SE, SW}),
            new AdjacentPositionChecker(new Direction[]{W, NW, SW}),
            new AdjacentPositionChecker(new Direction[]{E, NE, SE}),
    };

    public Direction getNextMoveDirection(MazeMap map, Point elf, int round) {
        for (int i = 0; i < 4; i++) {
            AdjacentPositionChecker adjacentPositionChecker = adjacentPositionCheckers[(i + round) % 4];
            if (adjacentPositionChecker.check(elf, map)) {
                return adjacentPositionChecker.directions[0];
            }
        }
        return null;
    }

    public void round(MazeMap map) {
        for (int round = 0; round < 10; round++) {
            Map<Point, Direction> nextMoves = new HashMap<>();

            Set<Point> elves = map.elves;
            for (Point elf : elves) {
                if (checkMoveNeeded(map, elf)) {
                    Direction direction = getNextMoveDirection(map, elf, round);
                    if (direction != null) {
                        nextMoves.put(elf, direction);
                    }
                }
            }

            filterSameDestinations(nextMoves);

            map.moveElves(nextMoves);
            //System.out.println("End of round " + (round + 1));
            //printMap(map);
        }
    }


    public int round2(MazeMap map) {
        int round = 0;
        while (true) {
            Map<Point, Direction> nextMoves = new HashMap<>();

            Set<Point> elves = map.elves;
            for (Point elf : elves) {
                if (checkMoveNeeded(map, elf)) {
                    Direction direction = getNextMoveDirection(map, elf, round);
                    if (direction != null) {
                        nextMoves.put(elf, direction);
                    }
                }
            }

            filterSameDestinations(nextMoves);

            if (nextMoves.isEmpty()) {
                return round + 1;
            }

            map.moveElves(nextMoves);
            round++;
        }
    }


    public void filterSameDestinations(Map<Point, Direction> nextMoves) {
        Map<Point, List<Point>> nextElvesPoints = new HashMap<>();
        for (Map.Entry<Point, Direction> pointListEntry : nextMoves.entrySet()) {
            Point nextPoint = pointListEntry.getKey().move(pointListEntry.getValue());
            if (!nextElvesPoints.containsKey(nextPoint)) {
                ArrayList<Point> points = new ArrayList<>();
                points.add(pointListEntry.getKey());
                nextElvesPoints.put(nextPoint, points);
            } else {
                nextElvesPoints.get(nextPoint).add(pointListEntry.getKey());
            }
        }

        //remove all elves that move to same point
        for (Map.Entry<Point, List<Point>> pointListEntry : nextElvesPoints.entrySet()) {
            if (pointListEntry.getValue().size() > 1) {
                for (Point point : pointListEntry.getValue()) {
                    nextMoves.remove(point);
                }
            }
        }
    }


    public class AdjacentPositionChecker {
        public Direction[] directions;

        public AdjacentPositionChecker(Direction[] directions) {
            this.directions = directions;
        }

        public boolean check(Point point, MazeMap map) {
            for (Direction direction : directions) {
                Point move = point.move(direction);
                if (!map.isFree(move)) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean checkMoveNeeded(MazeMap mazeMap, Point elf) {
        return Arrays.stream(Direction.values()).map(elf::move).anyMatch(point -> {
            Optional<Character> character = mazeMap.get(point);
            return character.isPresent() && character.get() == '#';
        });
    }

    @Override
    public Object part2Solution() {
        char[][] map = new char[71][71];
        //char[][] map = new char[12][16];
        String[] strings = inputString().split(LS);
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            for (int j = 0; j < string.length(); j++) {
                map[i][j] = string.charAt(j);
            }
        }
        MazeMap mazeMap = new MazeMap(map);

        return round2(mazeMap);
    }

    public class MazeMap {
        public final Map<Point, Character> maze = new HashMap<>();

        public Set<Point> elves = new HashSet<>();

        public MazeMap(char[][] map) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != Character.MIN_VALUE) {
                        maze.put(new Point(j, i), map[i][j]);
                    }
                    if (map[i][j] == '#') {
                        elves.add(new Point(j, i));
                    }
                }
            }
        }

        public Optional<Character> get(Point point) {
            return maze.containsKey(point) ? Optional.of(maze.get(point)) : Optional.empty();
        }

        public boolean isFree(Point point) {
            return !elves.contains(point);
        }

        public void moveElves(Map<Point, Direction> directions) {
            for (Map.Entry<Point, Direction> entry : directions.entrySet()) {
                Point point = entry.getKey();
                Direction direction = entry.getValue();
                Point move = point.move(direction);
                elves.remove(point);
                elves.add(move);
                maze.put(point, '.');
                maze.put(move, '#');
            }
        }
    }

    void printMap(MazeMap mazeMap) {
        int minX = mazeMap.maze.keySet().stream().mapToInt(point -> point.x).min().getAsInt();
        int maxX = mazeMap.maze.keySet().stream().mapToInt(point -> point.x).max().getAsInt();
        int minY = mazeMap.maze.keySet().stream().mapToInt(point -> point.y).min().getAsInt();
        int maxY = mazeMap.maze.keySet().stream().mapToInt(point -> point.y).max().getAsInt();
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                System.out.print(mazeMap.maze.getOrDefault(new Point(x, y), ' '));
            }
            System.out.println();
        }
        System.out.println();
    }

    int checkSquare(MazeMap mazeMap) {
        int count = 0;
        int minXElf = mazeMap.elves.stream().mapToInt(point -> point.x).min().getAsInt();
        int maxXElf = mazeMap.elves.stream().mapToInt(point -> point.x).max().getAsInt();
        int minYElf = mazeMap.elves.stream().mapToInt(point -> point.y).min().getAsInt();
        int maxYElf = mazeMap.elves.stream().mapToInt(point -> point.y).max().getAsInt();
        for (int y = minYElf; y <= maxYElf; y++) {
            for (int x = minXElf; x <= maxXElf; x++) {
                if (mazeMap.isFree(new Point(x, y))) {
                    count++;
                }
            }
        }
        return count;
    }

    public enum Direction {
        N, S, E, W, NE, NW, SE, SW
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
                case NE:
                    pointX++;
                    pointY--;
                    break;
                case NW:
                    pointX--;
                    pointY--;
                    break;
                case SE:
                    pointX++;
                    pointY++;
                    break;
                case SW:
                    pointX--;
                    pointY++;
                    break;
            }
            return new Point(pointX, pointY);
        }
    }
}
