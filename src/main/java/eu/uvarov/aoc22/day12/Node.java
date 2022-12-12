package eu.uvarov.aoc22.day12;

import java.util.HashMap;
import java.util.Map;

public class Node {
    int row;
    int col;

    int distance;


    public Node(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}
