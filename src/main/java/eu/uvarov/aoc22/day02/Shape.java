package eu.uvarov.aoc22.day02;

public enum Shape {
    ROCK(1), PAPER(2), SCISSORS(3);

    public final int score;

    Shape(int score) {
        this.score = score;
    }

    public static Shape resolve(String s) {
        return switch (s) {
            case "A", "X" -> ROCK;
            case "B", "Y" -> PAPER;
            case "C", "Z" -> SCISSORS;
            default -> throw new RuntimeException("Shape not found: " + s);
        };
    }

    public static Shape chooseToMatchOutcome(Shape opponentShape, Outcome outcome) {
        return Shape.values()[(opponentShape.ordinal() + outcome.ordinal()) % Shape.values().length];
    }
}
