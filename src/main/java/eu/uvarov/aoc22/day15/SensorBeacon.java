package eu.uvarov.aoc22.day15;

public class SensorBeacon {

    public Position sensor;

    public Position beacon;

    public SensorBeacon(String sx, String sy, String bx, String by) {
        sensor = new Position(Integer.parseInt(sx), Integer.parseInt(sy));
        beacon = new Position(Integer.parseInt(bx), Integer.parseInt(by));
    }
}
