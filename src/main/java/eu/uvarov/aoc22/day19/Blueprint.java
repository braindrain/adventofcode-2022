package eu.uvarov.aoc22.day19;

public class Blueprint {
    public Cost oreRobotCost;

    public Cost clayRobotCost;

    public Cost obsidianRobotCost;

    public Cost geodeRobotCost;

    public Blueprint(Cost oreRobotCost, Cost clayRobotCost, Cost obsidianRobotCost, Cost geodeRobotCost) {
        this.oreRobotCost = oreRobotCost;
        this.clayRobotCost = clayRobotCost;
        this.obsidianRobotCost = obsidianRobotCost;
        this.geodeRobotCost = geodeRobotCost;
    }

    public static Blueprint fromParseBluePrint(Day19.ParseBluePrint parseBluePrint) {
        return new Blueprint(
                Cost.oreCost(parseBluePrint.oreCostOre()),
                Cost.oreCost(parseBluePrint.clayCostOre()),
                Cost.oreClayCost(parseBluePrint.obsidianCostOre(), parseBluePrint.obsidianCostClay()),
                Cost.oreObsidianCost(parseBluePrint.geodeCostOre(), parseBluePrint.geodeCostObsidian())
        );
    }
}
