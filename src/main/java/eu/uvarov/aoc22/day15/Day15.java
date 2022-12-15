package eu.uvarov.aoc22.day15;

import eu.uvarov.aoc22.util.GenericDay;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static eu.uvarov.aoc22.day15.SensorBeacon.getManhattan;

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
        return dist2 <= dist;
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
        String[] strings = inputArray();
        Pattern compile = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
        ArrayList<SensorBeacon> sbs = new ArrayList<>();

        for (String string : strings) {
            SensorBeacon sb = regexp(string, compile, SensorBeacon.class);
            sbs.add(sb);
        }

        for (SensorBeacon sb : sbs) {
            //walk through each sensor's perimeter
            for (int i = -(sb.radius + 1); i <= sb.radius; i++) {
                int width = (sb.radius - Math.abs(i));

                if (isOutsideAnySensor(sbs, (sb.sensor.x - width - 1), (sb.sensor.y + i))) {
                    return BigInteger.valueOf(sb.sensor.x - width - 1).multiply(BigInteger.valueOf(4000000)).add(BigInteger.valueOf(((sb.sensor.y + i))));
                }

                if (isOutsideAnySensor(sbs, (sb.sensor.x + width + 1), (sb.sensor.y + i))) {
                    return BigInteger.valueOf(sb.sensor.x + width + 1).multiply(BigInteger.valueOf(4000000)).add(BigInteger.valueOf(((sb.sensor.y + i))));
                }
            }
        }

        return -1;
    }

    private boolean isOutsideAnySensor(List<SensorBeacon> sensors, int i, int j) {
        return i >= 0 && i <= 4000000 && j >= 0 && j <= 4000000
                && sensors.stream().noneMatch(sensor -> sensor.withinRadius(i, j));
    }
}
