package eu.uvarov.aoc22.day02;

public enum Outcome {
    DRAW(3), WIN(6), LOSS(0);

    public final int score;

    Outcome(int score) {
        this.score = score;
    }

    public static Outcome resolve(String s) {
        return switch (s) {
            case "X" -> LOSS;
            case "Y" -> DRAW;
            case "Z" -> WIN;
            default -> throw new RuntimeException("Outcome not found: " + s);
        };
    }
}
