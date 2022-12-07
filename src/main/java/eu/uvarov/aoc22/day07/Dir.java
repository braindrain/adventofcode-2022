package eu.uvarov.aoc22.day07;

import java.util.HashMap;
import java.util.Map;

public class Dir {

    String name;

    Dir parent;
    Map<String, Dir> dirs = new HashMap<>();
    long fileSize = 0;

    public Dir(String name, Dir parent) {
        this.name = name;
        this.parent = parent;
    }

    public Dir getDir(String dirName) {
        return dirs.computeIfAbsent(dirName, k -> new Dir(dirName, this));
    }

    public void addFile(String name, long size) {
        fileSize += size;
    }

    public long totalSize() {
        return dirs.values().stream().mapToLong(Dir::totalSize).sum() + fileSize;
    }

    public Dir getParent() {
        if(parent == null){
            return this;
        }
        return parent;
    }
}
