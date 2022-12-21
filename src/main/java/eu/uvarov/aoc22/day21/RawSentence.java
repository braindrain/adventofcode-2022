package eu.uvarov.aoc22.day21;

public class RawSentence implements Sentence {
    public String left;
    public String right;
    public String sign;

    public RawSentence(String left, String right, String sign) {
        this.left = left;
        this.right = right;
        this.sign = sign;
    }

    @Override
    public String getName() {
        return left + sign + right;
    }

    @Override
    public long perform() {
        return 0;
    }

    @Override
    public Sentence getParent() {
        return null;
    }

    @Override
    public void setParent(Sentence parent) {

    }
}
