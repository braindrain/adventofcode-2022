package eu.uvarov.aoc22.day17;

public class State {
    public long highest;
    public long rocksCount;
    boolean resetPeriod;

    public State(long highest, long rocksCount, boolean resetPeriod) {
        this.highest = highest;
        this.rocksCount = rocksCount;
        this.resetPeriod = resetPeriod;
    }
}
