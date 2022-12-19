package eu.uvarov.aoc22.day19;

import java.util.Objects;

public class CollectionProcess {

    public int ore;

    public int clay;

    public int obsidian;

    public int geode;

    public int oreMiners = 1;

    public int clayMiners = 0;

    public int obsidianMiners = 0;

    public int geodeMiners = 0;

    public CollectionProcess(int ore, int clay, int obsidian, int geode, int oreMiners, int clayMiners, int obsidianMiners, int geodeMiners) {
        this.ore = ore;
        this.clay = clay;
        this.obsidian = obsidian;
        this.geode = geode;
        this.oreMiners = oreMiners;
        this.clayMiners = clayMiners;
        this.obsidianMiners = obsidianMiners;
        this.geodeMiners = geodeMiners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionProcess that = (CollectionProcess) o;
        return ore == that.ore && clay == that.clay && obsidian == that.obsidian && geode == that.geode && oreMiners == that.oreMiners && clayMiners == that.clayMiners && obsidianMiners == that.obsidianMiners && geodeMiners == that.geodeMiners;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ore, clay, obsidian, geode, oreMiners, clayMiners, obsidianMiners, geodeMiners);
    }

    @Override
    public String toString() {
        return "CollectionProcess{" +
                "ore=" + ore +
                ", clay=" + clay +
                ", obsidian=" + obsidian +
                ", geode=" + geode +
                ", oreMiners=" + oreMiners +
                ", clayMiners=" + clayMiners +
                ", obsidianMiners=" + obsidianMiners +
                ", geodeMiners=" + geodeMiners +
                '}';
    }

    /*  public void iterate() {
        ore += orePerMinute;
        clay += clayPerMinute;
        obsidian += obsidianPerMinute;
        geode += geodePerMinute;
    }

    public void checkBuy() {
        if (ore >= blueprint.geodeRobotCost.ore && obsidian >= blueprint.geodeRobotCost.obsidian) {
            ore -= blueprint.geodeRobotCost.ore;
            obsidian -= blueprint.geodeRobotCost.obsidian;
            geodePerMinute++;
            geode--;
        }
        if (ore >= blueprint.obsidianRobotCost.ore && clay >= blueprint.obsidianRobotCost.clay) {
            ore -= blueprint.obsidianRobotCost.ore;
            clay -= blueprint.obsidianRobotCost.clay;
            obsidianPerMinute++;
            obsidian--;
        }
        if (ore >= blueprint.clayRobotCost.ore) {
            ore -= blueprint.clayRobotCost.ore;
            clayPerMinute++;
            clay--;
        }
        if (ore >= blueprint.oreRobotCost.ore) {
            ore -= blueprint.oreRobotCost.ore;
            orePerMinute++;
            ore--;
        }
    }*/
}
