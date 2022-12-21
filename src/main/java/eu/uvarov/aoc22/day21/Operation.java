package eu.uvarov.aoc22.day21;

public abstract class Operation implements Sentence {
    public String name;

    public Sentence parent;

    public Sentence left;

    public Sentence right;

    public Operation(String name, Sentence left, Sentence right) {
        this.name = name;
        this.left = left;
        this.right = right;
        this.left.setParent(this);
        this.right.setParent(this);
    }

    @Override
    public Sentence getParent() {
        return parent;
    }

    @Override
    public void setParent(Sentence parent) {
        this.parent = parent;
    }

    @Override
    public String getName() {
        return name;
    }
}
