package eu.uvarov.aoc22.day21;

public class Min extends Operation {

    public Min(String name, Sentence left, Sentence right) {
        super(name, left, right);
    }

    @Override
    public long perform() {
        return left.perform() - right.perform();
    }
}

