package eu.uvarov.aoc22.day07;

import eu.uvarov.aoc22.util.GenericDay;

import java.util.ArrayList;
import java.util.List;

public class Day07 extends GenericDay {
    public Day07() {
        super("07");
    }

    public static void main(String[] args) {
        Day07 day07 = new Day07();
        day07.solve();
    }

    @Override
    public Object part1Solution() {
        return sumSize(structure());
    }

    public Dir structure() {
        Dir rootDir = new Dir("/", null);
        Dir currentDir = rootDir;

        String[] strings = inputArray();
        for (String out : strings) {
            if (out.startsWith("dir")) {
                String[] split = out.split(" ");
                currentDir.getDir(split[1]);
            } else if (out.startsWith("$ cd")) {
                String[] split = out.split(" ");
                if (split[2].equals("..")) {
                    currentDir = currentDir.parent;
                } else if (split[2].equals("/")) {
                    currentDir = rootDir;
                } else {
                    currentDir = currentDir.getDir(split[2]);
                }
            } else if (out.equals("$ ls")) {
                // System.out.println("ls " + currentDir.name);
            } else {
                // System.out.println("file? " + out);
                String[] split = out.split(" ");
                currentDir.addFile(split[1], Long.parseLong(split[0]));
            }
        }
        return rootDir;
    }

    public long sumSize(Dir n) {
        long total = 0;
        for (Dir node : n.dirs.values()) {
            if (node.totalSize() <= 100000 && !node.dirs.isEmpty()) {
                total += node.totalSize();
            }
            total += sumSize(node);
        }
        return total;
    }

    public List<Long> sumSize(Dir n, long sizeRoot) {
        List<Long> total = new ArrayList<>();
        for (Dir dir : n.dirs.values()) {
            if (dir.totalSize() >= sizeRoot - (70000000 - 30000000) && !dir.dirs.isEmpty()) {
                total.add(dir.totalSize());
            }
            total.addAll(sumSize(dir, sizeRoot));
        }
        return total;
    }

    @Override
    public Object part2Solution() {
        Dir structure = structure();
        return sumSize(structure, structure.totalSize()).stream().mapToLong(e -> e).min().getAsLong();
    }
}
