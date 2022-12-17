package eu.uvarov.aoc22.day17.pieces;


import eu.uvarov.aoc22.day17.Board;
import eu.uvarov.aoc22.day17.types.RockType;

public class SquareRock extends RockBase {

    public SquareRock(RockType type, int x, int y, Board board) {
        super(type, board);
        points.add(new Point(x, y));
        points.add(new Point(x + 1, y));
        points.add(new Point(x, y + 1));
        points.add(new Point(x + 1, y + 1));
    }
}
