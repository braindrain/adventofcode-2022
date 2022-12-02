package eu.uvarov.aoc22.day02;

import lombok.Data;

import static eu.uvarov.aoc22.day02.Outcome.*;


@Data
public class Round {

    private String opponentInput;
    private String yourInput;

    private Outcome play(Shape opponentShape, Shape yourShape) {
        if (yourShape.ordinal() == opponentShape.ordinal()) {
            return DRAW;
        }
        if (opponentShape.ordinal() == ((yourShape.ordinal() + 1) % Shape.values().length)) {
            return LOSS;
        } else return WIN;
    }

    public long yourScore() {
        Shape opponentShape = Shape.resolve(opponentInput);
        Shape yourShape = Shape.resolve(yourInput);
        return yourShape.score + play(opponentShape, yourShape).score;
    }

    public long yourScoreWithDesiredOutcome(){
        Shape opponentShape = Shape.resolve(opponentInput);
        Shape yourShape = Shape.chooseToMatchOutcome(Shape.resolve(opponentInput), resolve(yourInput));
        return yourShape.score + play(opponentShape, yourShape).score;
    }
}