package it.maxi.project.utilita.array;

import it.maxi.project.utilita.condition.Check;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * Ints является оберткой над массивом типа int.
 * Данный клас предоставляет методы для работы с обертываемым массивом.
 */
public final class Ints implements Iterable<Integer> {

    public static Ints.Builder builder() {
        return new Ints.Builder();
    }

    public static Collector<Integer, Ints.Builder, Ints> collector() {
        return Collector.of(
                Ints::builder,
                Ints.Builder::add,
                (left, right) -> {
                    right.build().forEach(left::add);
                    return left;
                },
                Ints.Builder::build);
    }

    public static Ints of(int... ints) {
        return new Ints(ints);
    }

    public static Ints ofCopy(int... ints) {
        int[] array = new int[ints.length];
        System.arraycopy(ints, 0, array, 0, array.length);
        return new Ints(array);
    }

    private final int[] array;

    public Ints(int[] array) {
        Check.notNull(array, "array is null");
        this.array = array;
    }

    public int[] asArray() {
        return array;
    }

    public List<Integer> asList() {
        return new ArrayList<>() {{ Ints.this.forEach(i -> add(i)); }};
    }

    public <R> Map<Integer, R> asMap(IntFunction<R> func) {
        Map<Integer, R> map = new HashMap<>();
        forEach(i -> map.put(i, func.apply(i)));
        return map;
    }

    public Set<Integer> asSet() {
        return new HashSet<>() {{ Ints.this.forEach(this::add); }};
    }

    public boolean contains(int key) {
        for (int i : this) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ints ints && Arrays.equals(this.array, ints.array);
    }

    public Ints findIndices(IntPredicate predicate) {
        Ints.Builder builder = new Ints.Builder();
        forEach((i, v) -> {
            if (predicate.test(v)) {
                builder.add(i);
            }
        });
        return builder.build();
    }

    public Ints forEach(BiConsumer<Integer, Integer> biConsumer) {
        for (int i = 0; i < array.length; i++) {
            biConsumer.accept(i, array[i]);
        }
        return this;
    }

    public int get(int index) {
        return array[index];
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor != Ints.this.array.length;
            }

            @Override
            public Integer next() {
                return Ints.this.array[cursor++];
            }
        };
    }

    public boolean isEmpty() {
        return array.length == 0;
    }

    public boolean isNotEmpty() {
        return array.length != 0;
    }

    public int length() {
        return array.length;
    }

    public Ints map(IntUnaryOperator operator) {
        for (int i = 0; i < array.length; i++) {
            array[i] = operator.applyAsInt(array[i]);
        }
        return this;
    }

    public int prod() {
        if (isNotEmpty()) {
            int prod = array[0];
            for (int i = 1; i < array.length; i++) {
                prod *= array[i];
            }
            return prod;
        } else {
            return 0;
        }
    }

    public Ints reverse() {
        for (int i = 0; i < length() / 2; i++) {
            swap(i, length() - 1 - i);
        }
        return this;
    }

    public Ints set(int index, int value) {
        array[index] = value;
        return this;
    }

    public Ints slice(int start, int stop, int step) {
        Ints.Builder builder = Ints.builder();
        for (int i = start; i <= stop; i += step) {
            builder.add(array[i]);
        }
        return builder.build();
    }

    public IntStream stream() {
        return IntStream.of(array);
    }

    public Ints swap(int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        return this;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static class Builder {

        private final IntStream.Builder builder;

        private Builder() {
            builder = IntStream.builder();
        }

        public Ints.Builder add(int i) {
            builder.add(i);
            return this;
        }

        public Ints build() {
            return new Ints(builder.build().toArray());
        }
    }
}
