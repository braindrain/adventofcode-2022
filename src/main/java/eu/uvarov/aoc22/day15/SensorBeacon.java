package eu.uvarov.aoc22.day15;

public class SensorBeacon {

    public Position sensor;

    public Position beacon;

    public int radius;

    public SensorBeacon(String sx, String sy, String bx, String by) {
        sensor = new Position(Integer.parseInt(sx), Integer.parseInt(sy));
        beacon = new Position(Integer.parseInt(bx), Integer.parseInt(by));
        radius = getManhattan(sensor.x, sensor.y, beacon.x, beacon.y);
    }

    public static int getManhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public boolean withinRadius(int x, int y) {
        return getManhattan(x, y, sensor.x, sensor.y) <= radius;
    }
}
