package eu.uvarov.aoc22.day20;

import java.util.ArrayList;
import java.util.List;

public class CircularList {
    public final List<Node> backedList = new ArrayList<>();

    public Node zeroNodePointer;

    public CircularList(List<Long> values) {
        Node previous = null;
        Node first = null;
        for (long element : values) {
            Node n = new Node(element);
            if (n.value == 0) {
                zeroNodePointer = n;
            }
            n.value = element;
            n.previous = previous;
            backedList.add(n);
            if (previous != null) previous.next = n;
            else first = n;
            previous = n;
        }
        previous.next = first;
        first.previous = previous;
    }

    public void moveNode(Node n) {
        long position = getGetPositionToInsert(n);
        if (position == 0) {
            return;
        }

        n.previous.next = n.next;
        n.next.previous = n.previous;

        Node newPrevious = n.previous;
        if (n.value < 0) {
            for (long i = 0; i > position; i--) {
                newPrevious = newPrevious.previous;
            }
        } else {
            for (long i = 0; i < position; i++) {
                newPrevious = newPrevious.next;
            }
        }

        n.next = newPrevious.next;
        n.previous = newPrevious;
        n.next.previous = n;
        n.previous.next = n;
    }

    public long getGroveCoordinates() {
        long sum = 0;
        Node node = zeroNodePointer;
        for (int i = 0; i <= 3000; i++) {
            if (i % 1000 == 0) {
                sum = Math.addExact(sum, node.value);
            }
            node = node.next;
        }
        return sum;
    }


    long getGetPositionToInsert(Node n) {
        return n.value % (backedList.size() - 1);
    }
}
