package eu.uvarov.aoc22.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Monkey {
    List<Monkey> monkeys = new ArrayList<>();

    List<Long> items = new ArrayList<>();

    LongFunction<Long> operation;

    Predicate<Long> condition;

    int monkeyToThrowIfTrue;

    int monkeyToThrowIfFalse;

    long itemsCount = 0;

    Long divisible;

    public Monkey(List<Monkey> monkeys, long[] items, LongFunction<Long> operation, Long divisible, int monkeyToThrowIfTrue, int monkeyToThrowIfFalse) {
        this.monkeys = monkeys;
        this.items = Arrays.stream(items).boxed().collect(Collectors.toList());
        this.operation = operation;
        this.divisible = divisible;
        this.condition = aLong -> aLong % divisible == 0;
        this.monkeyToThrowIfTrue = monkeyToThrowIfTrue;
        this.monkeyToThrowIfFalse = monkeyToThrowIfFalse;
    }

    public void addToItems(Long item) {
        items.add(item);
    }

    public Long applyOperation(Long item) {
        return operation.apply(item);
    }

    public Boolean applyCondition(Long item) {
        return condition.test(item);
    }

    public void round() {
        for (Iterator<Long> iter = items.listIterator(); iter.hasNext(); ) {
            Long item = iter.next();
            iter.remove();
            itemsCount++;
            Long result = applyOperation(item);
            result = result / 3;
            if (applyCondition(result)) {
                monkeys.get(monkeyToThrowIfTrue).addToItems(result);
            } else {
                monkeys.get(monkeyToThrowIfFalse).addToItems(result);
            }
        }
    }

    public void round2(long gcd) {
        for (Iterator<Long> iter = items.listIterator(); iter.hasNext(); ) {
            Long item = iter.next();
            iter.remove();
            itemsCount++;
            Long result = applyOperation(item);
            if (applyCondition(result)) {
                monkeys.get(monkeyToThrowIfTrue).addToItems(result % gcd);
            } else {
                monkeys.get(monkeyToThrowIfFalse).addToItems(result % gcd);
            }
        }
    }

    @Override
    public String toString() {
        return "Monkey{" +
                ", items=" + items +
                ", operation=" + operation +
                ", condition=" + condition +
                ", monkeyToThrowIfTrue=" + monkeyToThrowIfTrue +
                ", monkeyToThrowIfFalse=" + monkeyToThrowIfFalse +
                ", itemsCount=" + itemsCount +
                '}';
    }
}
