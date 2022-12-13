package eu.uvarov.aoc22.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Pair {
    public Object left;
    public Object right;

    public Pair(Object left, Object right) {
        this.left = left;
        this.right = right;
    }

    enum Result {
        YES, NO, CONTINUE
    }

    Result compareOrder(Object left, Object right) {
        if (left instanceof Integer && right instanceof Integer) {
            if ((Integer) left < (Integer) right) {
                return Result.YES;
            } else if ((Integer) left > (Integer) right) {
                return Result.NO;
            } else {
                return Result.CONTINUE;
            }
        }
        if (left instanceof Integer && right instanceof ArrayList<?>) {
            ArrayList<Object> list = new ArrayList<>();
            list.add(left);
            return compareOrder(list, right);
        }
        if (left instanceof ArrayList<?> && right instanceof Integer) {
            ArrayList<Object> list = new ArrayList<>();
            list.add(right);
            return compareOrder(left, list);
        }
        if (left instanceof ArrayList<?> && right instanceof ArrayList<?>) {
            ArrayList<?> leftList = (ArrayList<?>) left;
            ArrayList<?> rightList = (ArrayList<?>) right;
            for (int i = 0; i < rightList.size(); i++) {
                if (leftList.size() > i) {
                    Result result = compareOrder(leftList.get(i), rightList.get(i));
                    if (result != Result.CONTINUE) {
                        return result;
                    }
                } else {
                    return Result.YES;
                }
            }
            if(leftList.size() > rightList.size()) {
                return Result.NO;
            }
        } else {
            throw new IllegalArgumentException("Unknown types");
        }
        return Result.CONTINUE;
    }

}
