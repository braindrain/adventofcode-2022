package eu.uvarov.aoc22.day15;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day15 extends GenericDay {
    public Day15() {
        super("15");
    }

    public static void main(String[] args) {
        Day15 day15 = new Day15();
        day15.solve();
    }

    @Override
    public Object part1Solution() {
        String[] strings = inputArray();
        Pattern compile = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
        ArrayList<SensorBeacon> sbs = new ArrayList<>();

        for (String string : strings) {
            SensorBeacon sb = regexp(string, compile, SensorBeacon.class);
            sbs.add(sb);
        }

        int mapSize = 30000000;

        int count = 0;

        for (int i = 0; i < mapSize; i++) {
            int y = 10 + mapSize / 2;
            for (SensorBeacon sb : sbs) {
                if (covered(mapSize, sb, i, y) && !lookup(sbs, i, y, mapSize)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    boolean covered(int mapSize, SensorBeacon sb, int x, int y) {
        int dist = getManhattan(sb.sensor.x, sb.sensor.y, sb.beacon.x, sb.beacon.y);
        int dist2 = getManhattan(x, y, sb.sensor.x + mapSize / 2, sb.sensor.y + mapSize / 2);
        boolean b = dist2 <= dist;
        return b;
    }

    int getManhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    boolean lookup(List<SensorBeacon> sbs, int x, int y, int mapSize) {
        for (SensorBeacon sensorBeacon : sbs) {
            if (sensorBeacon.sensor.x + mapSize / 2 == x && sensorBeacon.sensor.y + mapSize / 2 == y) {
                return true;
            }
            if (sensorBeacon.beacon.x + mapSize / 2 == x && sensorBeacon.beacon.y + mapSize / 2 == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object part2Solution() {
        return -1;
    }

    boolean covered2(SensorBeacon sb, int x, int y) {
        int dist = getManhattan(sb.sensor.x, sb.sensor.y, sb.beacon.x, sb.beacon.y);
        int dist2 = getManhattan(x, y, sb.sensor.x, sb.sensor.y);
        boolean b = dist2 <= dist;
        return b;
    }

    boolean lookup2(List<SensorBeacon> sbs, int x, int y) {
        for (SensorBeacon sensorBeacon : sbs) {
            if (sensorBeacon.sensor.x == x && sensorBeacon.sensor.y == y) {
                return true;
            }
            if (sensorBeacon.beacon.x == x && sensorBeacon.beacon.y == y) {
                return true;
            }
        }
        return false;
    }
}
