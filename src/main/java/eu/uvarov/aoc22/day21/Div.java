package eu.uvarov.aoc22.day21;

public class Div extends Operation {

    public Div(String name, Sentence left, Sentence right) {
        super(name, left, right);
    }

    @Override
    public long perform() {
        return left.perform() / right.perform();
    }
}

