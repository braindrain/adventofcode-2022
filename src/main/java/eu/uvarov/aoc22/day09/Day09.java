package eu.uvarov.aoc22.day09;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static eu.uvarov.aoc22.day09.Direction.*;

public class Day09 extends GenericDay {
    public Day09() {
        super("09");
    }

    public static void main(String[] args) {
        Day09 day09 = new Day09();
        day09.solve();
    }

    @Override
    public Object part1Solution() {
        String[] strings = inputArray();
        Pattern compile = Pattern.compile("([L,R,D,U])\\s(\\d+)");
        Position head = new Position(0, 0);
        Position tail = new Position(0, 0);
        List<Move> moves = Arrays.stream(strings).map(s -> regexp(s, compile, Move.class)).toList();
        Set<Position> tailVisited = new HashSet<>();
        tailVisited.add(head);

        for (Move m : moves) {
            Direction direction = Direction.resolve(m.direction());
            for (int i = 0; i < Integer.parseInt(m.distance()); i++) {
                head = head.move(direction);
                tail = moveTail(head, tail);
                tailVisited.add(tail);
            }
        }
        return tailVisited.size();
    }

    private static Position moveTail(Position head, Position previousTail) {
        if (previousTail.equals(head) || Arrays.stream(Direction.values()).anyMatch(d -> previousTail.move(d).equals(head))) {
            return previousTail;
        }
        if (head.x > previousTail.x && head.y == previousTail.y) {
            return previousTail.move(R);
        } else if (head.x < previousTail.x && head.y == previousTail.y) {
            return previousTail.move(L);
        } else if (head.x == previousTail.x && head.y > previousTail.y) {
            return previousTail.move(D);
        } else if (head.x == previousTail.x && head.y < previousTail.y) {
            return previousTail.move(U);
        } else if (head.x > previousTail.x && head.y > previousTail.y) {
            return previousTail.move(DR);
        } else if (head.x < previousTail.x && head.y < previousTail.y) {
            return previousTail.move(UL);
        } else if (head.x < previousTail.x) {
            return previousTail.move(DL);
        } else if (head.x > previousTail.x) {
            return previousTail.move(UR);
        } else throw new IllegalStateException(head.x + " " + head.y + " " + previousTail.x + " " + previousTail.y);

    }

    @Override
    public Object part2Solution() {
        String[] strings = inputArray();
        Pattern compile = Pattern.compile("([L,R,D,U])\\s(\\d+)");
        Position head = new Position(0, 0);
        List<Move> moves = Arrays.stream(strings).map(s -> regexp(s, compile, Move.class)).toList();
        Set<Position> tailVisited = new HashSet<>();
        tailVisited.add(head);

        Position[] tails = new Position[9];
        for (int i = 0; i < tails.length; i++) {
            tails[i] = new Position(0, 0);
        }

        for (Move m : moves) {
            Direction direction = Direction.resolve(m.direction());
            for (int i = 0; i < Integer.parseInt(m.distance()); i++) {
                head = head.move(direction);
                tails[0] = moveTail(head, tails[0]);
                for (int j = 1; j < tails.length; j++) {
                    tails[j] = moveTail(tails[j - 1], tails[j]);
                }
                tailVisited.add(tails[tails.length - 1]);
            }
        }
        return tailVisited.size();
    }
}
