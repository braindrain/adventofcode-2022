package eu.uvarov.aoc22.day09;

public enum Direction {
    U, R, D, L,
    UR, DR, DL, UL;

    public static Direction resolve(String direction) {
        return Direction.valueOf(direction);
    }


}
