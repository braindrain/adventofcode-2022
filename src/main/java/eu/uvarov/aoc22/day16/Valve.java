package eu.uvarov.aoc22.day16;

import java.util.*;

public class Valve {



    public String name;
    public int flowRate;

    public String[] rawConnections;


    public Valve(String name, String flowRate, String tunnel, String lead, String valve, String rawConnections) {
        this.name = name;
        this.flowRate = Integer.parseInt(flowRate);
        this.rawConnections = rawConnections.split(", ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valve valve = (Valve) o;
        return flowRate == valve.flowRate && Objects.equals(name, valve.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, flowRate);
    }
}
