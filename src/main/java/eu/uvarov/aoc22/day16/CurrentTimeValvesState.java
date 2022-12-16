package eu.uvarov.aoc22.day16;

import java.util.Map;
import java.util.Objects;

public class CurrentTimeValvesState {
    public Map<String, Integer> openValves;
    public Valve currentValve;

    public int totalFlow;

    public CurrentTimeValvesState(Map<String, Integer> openValves, Valve currentValve, int totalFlow) {
        this.openValves = openValves;
        this.currentValve = currentValve;
        this.totalFlow = totalFlow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentTimeValvesState that = (CurrentTimeValvesState) o;
        return totalFlow == that.totalFlow && Objects.equals(openValves, that.openValves) && Objects.equals(currentValve, that.currentValve);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openValves, currentValve, totalFlow);
    }
}
