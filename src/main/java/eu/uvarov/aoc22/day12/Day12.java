package eu.uvarov.aoc22.day12;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day12 extends GenericDay {
    public Day12() {
        super("12");
    }

    public static void main(String[] args) {
        Day12 day12 = new Day12();
        day12.solve();
    }

    @Override
    public Object part1Solution() {
        String[] split = inputString().split(LS);
        char[][] input = new char[split.length][split[0].length()];
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            for (int j = 0; j < s.length(); j++) {
                input[i][j] = s.charAt(j);
            }
        }

        int minDistance = -1;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == 'S') {
                    minDistance = minDistance(i, j, input);
                    break;
                }
            }
        }

        return minDistance;
    }

    public int minDistance(int srcX, int srcY, char[][] input) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[input.length][input[0].length];
        queue.add(new Node(srcX, srcY, 0));
        visited[srcX][srcY] = true;

        while (!queue.isEmpty()) {
            Node p = queue.remove();

            // Destination found;
            if (input[p.row][p.col] == 'E')
                return p.distance;

            // moving up
            if (isValid(p, p.row - 1, p.col, input, visited)) {
                queue.add(new Node(p.row - 1, p.col, p.distance + 1));
                //System.out.println("from " + input[p.row][p.col] + " up visited " + input[p.row - 1][p.col] + " " + p.row + " " + p.col);
                visited[p.row - 1][p.col] = true;
            }

            // moving down
            if (isValid(p, p.row + 1, p.col, input, visited)) {
                queue.add(new Node(p.row + 1, p.col, p.distance + 1));
                //System.out.println("from " + input[p.row][p.col] + " down visited " + input[p.row + 1][p.col] + " " + p.row + " " + p.col);
                visited[p.row + 1][p.col] = true;
            }

            // moving left
            if (isValid(p, p.row, p.col - 1, input, visited)) {
                queue.add(new Node(p.row, p.col - 1, p.distance + 1));
               // System.out.println("from " + input[p.row][p.col] + " left visited " + input[p.row][p.col - 1] + " " + p.row + " " + p.col);
                visited[p.row][p.col - 1] = true;
            }

            // moving right
            if (isValid(p, p.row, p.col + 1, input, visited)) {
                queue.add(new Node(p.row, p.col + 1, p.distance + 1));
                //System.out.println("from " + input[p.row][p.col] + " right visited " + input[p.row][p.col + 1] + " " + p.row + " " + p.col);
                visited[p.row][p.col + 1] = true;
            }
        }
        return Integer.MAX_VALUE;

    }

    // checking where it's valid or not
    private static boolean isValid(Node src, int x, int y,
                                   char[][] grid,
                                   boolean[][] visited) {
        if (x >= 0 && y >= 0 && x < grid.length
                && y < grid[0].length) {
            char srcChar = grid[src.row][src.col];
            char dest = grid[x][y];
            if (dest == 'E') {
                dest = 'z';
            }
            if (srcChar == 'S') {
                srcChar = 'a';
            }
            if (dest <= srcChar + 1 && !visited[x][y]) {
                return true;
            }
        }
        return false;
    }


    public Object part2Solution() {
        String[] split = inputString().split(LS);
        char[][] input = new char[split.length][split[0].length()];
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            for (int j = 0; j < s.length(); j++) {
                input[i][j] = s.charAt(j);
            }
        }

        List<Integer> distances = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == 'S' || input[i][j] == 'a') {
                    distances.add(minDistance(i, j, input));
                }
            }
        }

        return distances.stream().mapToInt(Integer::intValue).min();
    }
}
