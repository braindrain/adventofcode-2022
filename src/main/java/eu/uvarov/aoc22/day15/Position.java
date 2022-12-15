package eu.uvarov.aoc22.day15;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean matches(int x, int y) {
        return this.x == x && this.y == y;
    }
}
