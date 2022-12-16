package eu.uvarov.aoc22.day16;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day16 extends GenericDay {

    public Day16() {
        super("16");
    }

    public static void main(String[] args) {
        Day16 day16 = new Day16();
        day16.solve();
    }

    @Override
    public Object part1Solution() {
        String[] strings = inputArray();
        Pattern compile = Pattern.compile("Valve (\\w+) has flow rate=(\\d+); tunnel(s)? lead(s)? to valve(s)? (.+)");
        Map<String, Valve> valves = new HashMap<>();

        for (String string : strings) {
            Valve valve = regexp(string, compile, Valve.class);
            valves.put(valve.name, valve);
        }

        Set<CurrentTimeValvesState> states = new HashSet<>();
        states.add(new CurrentTimeValvesState(new HashMap<>(), valves.get("AA"), 0));
        for (int minutes = 0; minutes < 30; minutes++) {
            Set<CurrentTimeValvesState> nextStates = new HashSet<>();

            for (CurrentTimeValvesState state : states) {
                int flow = state.openValves.values().stream().mapToInt(e -> e).sum() + state.totalFlow;
                if (state.currentValve.flowRate > 0 && !state.openValves.containsKey(state.currentValve.name)) {
                    Map<String, Integer> clonedOpen = new HashMap<>(state.openValves);

                    clonedOpen.put(state.currentValve.name, state.currentValve.flowRate);
                    nextStates.add(new CurrentTimeValvesState(clonedOpen, state.currentValve, flow));
                }

                for (String connection : state.currentValve.rawConnections) {
                    nextStates.add(new CurrentTimeValvesState(state.openValves, valves.get(connection), flow));
                }
            }
            states = nextStates;
        }
        return states.stream().mapToInt(value -> value.totalFlow).max().getAsInt();
    }

    @Override
    public Object part2Solution() {
        return null;
    }
}
