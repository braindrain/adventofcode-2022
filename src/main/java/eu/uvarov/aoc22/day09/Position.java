package eu.uvarov.aoc22.day09;

import java.util.Objects;

public class Position {

    public int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position move(Direction direction) {
        return switch (direction) {
            case D -> new Position(x, y + 1);
            case U -> new Position(x, y - 1);
            case R -> new Position(x + 1, y);
            case L -> new Position(x - 1, y);
            case DL -> new Position(x - 1, y + 1);
            case UR -> new Position(x + 1, y - 1);
            case DR -> new Position(x + 1, y + 1);
            case UL -> new Position(x - 1, y - 1);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
