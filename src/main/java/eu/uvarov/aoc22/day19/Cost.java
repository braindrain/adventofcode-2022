package eu.uvarov.aoc22.day19;

public class Cost {
    public int ore = 0;
    public int clay = 0;
    public int obsidian = 0;

    public Cost(int ore, int clay, int obsidian) {
        this.ore = ore;
        this.clay = clay;
        this.obsidian = obsidian;
    }

    public Cost(String ore, String clay, String obsidian) {
        this.ore = Integer.parseInt(ore);
        this.clay = Integer.parseInt(clay);
        this.obsidian = Integer.parseInt(obsidian);
    }

    public static Cost oreCost(String ore) {
        return new Cost(ore, "0", "0");
    }

    public static Cost oreClayCost(String ore, String clay) {
        return new Cost(ore, clay, "0");
    }

    public static Cost oreObsidianCost(String ore, String obsidian) {
        return new Cost(ore, "0", obsidian);
    }
}
