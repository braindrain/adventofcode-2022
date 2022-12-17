package eu.uvarov.aoc22.day17.pieces;

import eu.uvarov.aoc22.day17.Board;
import eu.uvarov.aoc22.day17.types.RockType;

public class CrossRock extends RockBase {

    public CrossRock(RockType type, int x, int y, Board board) {
        super(type, board);
        points.add(new Point(x + 1, y));
        points.add(new Point(x, 1));
        points.add(new Point(x + 1, y + 1));
        points.add(new Point(x + 2, y + 1));
        points.add(new Point(x + 1, y + 2));
    }
}
