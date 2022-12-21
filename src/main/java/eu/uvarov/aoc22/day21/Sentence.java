package eu.uvarov.aoc22.day21;

public interface Sentence {

    Sentence getParent();

    void setParent(Sentence parent);

    String getName();

    long perform();

}
