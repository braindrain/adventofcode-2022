package eu.uvarov.aoc22.day18;

import java.util.Objects;

public class Cube {
    public int x;
    public int y;
    public int z;

    public Cube(String x, String y, String z) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
        this.z = Integer.parseInt(z);
    }

    public Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return x == cube.x && y == cube.y && z == cube.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public Cube[] adjacentCubes() {
        return new Cube[]{
                new Cube(x + 1, y, z),
                new Cube(x - 1, y, z),
                new Cube(x, y + 1, z),
                new Cube(x, y - 1, z),
                new Cube(x, y, z + 1),
                new Cube(x, y, z - 1)
        };
    }
}
