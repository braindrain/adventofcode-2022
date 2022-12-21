package eu.uvarov.aoc22.day21;

public class Operand implements Sentence {
    public String name;

    public Sentence parent;

    public long value;

    public Operand(String name, long value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long perform() {
        return value;
    }

    @Override
    public Sentence getParent() {
        return parent;
    }

    @Override
    public void setParent(Sentence parent) {
        this.parent = parent;
    }
}
