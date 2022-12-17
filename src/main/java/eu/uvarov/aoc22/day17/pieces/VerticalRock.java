package eu.uvarov.aoc22.day17.pieces;

import eu.uvarov.aoc22.day17.Board;
import eu.uvarov.aoc22.day17.types.RockType;

public class VerticalRock extends RockBase {

    public VerticalRock(RockType type, int x, int y, Board board) {
        super(type, board);
        for (int i = 0; i < 4; i++) {
            this.points.add(new Point(x, y + i));
        }
    }
}
