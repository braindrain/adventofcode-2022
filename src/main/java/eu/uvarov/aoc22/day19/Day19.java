package eu.uvarov.aoc22.day19;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.*;
import java.util.regex.Pattern;

public class Day19 extends GenericDay {
    public Day19() {
        super("19");
    }

    public static void main(String[] args) {
        Day19 day19 = new Day19();
        day19.solve();
    }

    public record ParseBluePrint(String number, String oreCostOre, String clayCostOre, String obsidianCostOre,
                                 String obsidianCostClay, String geodeCostOre, String geodeCostObsidian) {
    }

    @Override
    public Object part1Solution() {
        String[] strings = inputArray();
        Pattern compile = Pattern.compile("Blueprint (\\d+): Each ore robot costs (\\d+) ore. Each clay robot costs (\\d+) ore. Each obsidian robot costs (\\d+) ore and (\\d+) clay. Each geode robot costs (\\d+) ore and (\\d+) obsidian.");
        List<Blueprint> blueprints = new ArrayList<>();
        for (String string : strings) {
            ParseBluePrint regexp = regexp(string, compile, ParseBluePrint.class);
            Blueprint blueprint = Blueprint.fromParseBluePrint(regexp);
            blueprints.add(blueprint);
        }

        int quality = 0;
        for (int i = 0; i < blueprints.size(); i++) {
            quality += bluePrint(24, new CollectionProcess(0, 0, 0, 0, 1, 0, 0, 0), new HashMap<>(), blueprints.get(i)) * (i + 1);
        }

        return quality;
    }


    int bluePrint(int minute, CollectionProcess collectionProcess, Map<CollectionProcess, Integer> seen, Blueprint blueprint) {
        int nextOre = collectionProcess.ore + collectionProcess.oreMiners;
        int nextClay = collectionProcess.clay + collectionProcess.clayMiners;
        int nextObsidian = collectionProcess.obsidian + collectionProcess.obsidianMiners;
        int nextGeode = collectionProcess.geode + collectionProcess.geodeMiners;

        if (minute == 1) {
            return nextGeode;
        }

        if (collectionProcess.ore >= blueprint.geodeRobotCost.ore && collectionProcess.obsidian >= blueprint.geodeRobotCost.obsidian) {
            return bluePrint(minute - 1, new CollectionProcess(nextOre - blueprint.geodeRobotCost.ore, nextClay, nextObsidian - blueprint.geodeRobotCost.obsidian, nextGeode, collectionProcess.oreMiners, collectionProcess.clayMiners, collectionProcess.obsidianMiners, collectionProcess.geodeMiners + 1), seen, blueprint);
        }
        if (collectionProcess.ore >= blueprint.obsidianRobotCost.ore && collectionProcess.clay >= blueprint.obsidianRobotCost.clay) {
            return bluePrint(minute - 1, new CollectionProcess(nextOre - blueprint.obsidianRobotCost.ore, nextClay - blueprint.obsidianRobotCost.clay, nextObsidian, nextGeode, collectionProcess.oreMiners, collectionProcess.clayMiners, collectionProcess.obsidianMiners + 1, collectionProcess.geodeMiners), seen, blueprint);
        }

        List<Integer> results = new ArrayList<>();
        if (collectionProcess.ore < 4) {
            results.add(bluePrint(minute - 1, new CollectionProcess(nextOre, nextClay, nextObsidian, nextGeode, collectionProcess.oreMiners, collectionProcess.clayMiners, collectionProcess.obsidianMiners, collectionProcess.geodeMiners), seen, blueprint));
        }

        if (collectionProcess.ore >= blueprint.oreRobotCost.ore) {
            results.add(bluePrint(minute - 1, new CollectionProcess(nextOre - blueprint.oreRobotCost.ore, nextClay, nextObsidian, nextGeode, collectionProcess.oreMiners + 1, collectionProcess.clayMiners, collectionProcess.obsidianMiners, collectionProcess.geodeMiners), seen, blueprint));
        }
        if (collectionProcess.ore >= blueprint.clayRobotCost.ore) {
            results.add(bluePrint(minute - 1, new CollectionProcess(nextOre - blueprint.clayRobotCost.ore, nextClay, nextObsidian, nextGeode, collectionProcess.oreMiners, collectionProcess.clayMiners + 1, collectionProcess.obsidianMiners, collectionProcess.geodeMiners), seen, blueprint));
        }
        int result = Collections.max(results);
        //seen.put(collectionProcess, result);
        return result;
    }


    @Override
    public Object part2Solution() {
        String[] strings = inputArray();
        Pattern compile = Pattern.compile("Blueprint (\\d+): Each ore robot costs (\\d+) ore. Each clay robot costs (\\d+) ore. Each obsidian robot costs (\\d+) ore and (\\d+) clay. Each geode robot costs (\\d+) ore and (\\d+) obsidian.");
        List<Blueprint> blueprints = new ArrayList<>();
        for (String string : strings) {
            ParseBluePrint regexp = regexp(string, compile, ParseBluePrint.class);
            Blueprint blueprint = Blueprint.fromParseBluePrint(regexp);
            blueprints.add(blueprint);
        }

        long result = 1;
        for (int i = 0; i < 3; i++) {
            result *= bluePrint(32, new CollectionProcess(0, 0, 0, 0, 1, 0, 0, 0), new HashMap<>(), blueprints.get(i));
        }

        return result;
    }
}
