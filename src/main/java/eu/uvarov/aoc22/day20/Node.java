package eu.uvarov.aoc22.day20;

import java.util.Objects;

public class Node {

    public Node previous;
    public Long value;
    public Node next;

    public Node(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(previous, node.previous) && Objects.equals(value, node.value) && Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previous, value, next);
    }
}
