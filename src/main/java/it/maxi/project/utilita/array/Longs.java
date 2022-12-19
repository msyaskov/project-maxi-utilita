package it.maxi.project.utilita.array;

import it.maxi.project.utilita.condition.Check;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Longs является оберткой над массивом типа long.
 * Данный клас предоставляет методы для работы с обертываемым массивом.
 */
public class Longs implements Iterable<Long> {

    public static Longs.Builder builder() {
        return new Longs.Builder();
    }

    public static Collector<Long, Longs.Builder, Longs> collector() {
        return Collector.of(
                Longs::builder,
                Longs.Builder::add,
                (left, right) -> {
                    right.build().forEach(left::add);
                    return left;
                },
                Longs.Builder::build);
    }

    public static Longs of(long... values) {
        return new Longs(values);
    }

    public static Longs ofCopy(long... values) {
        long[] array = new long[values.length];
        System.arraycopy(values,0,array,0,array.length);
        return new Longs(array);
    }

    private final long[] array;

    public Longs(long[] array) {
        Check.notNull(array, "array is null");
        this.array = array;
    }

    public long[] asArray() {
        return array;
    }

    public List<Long> asList() {
        return new ArrayList<>() {{ Longs.this.forEach(i -> add(i)); }};
    }

    public <R> Map<Long, R> asMap(LongFunction<R> func) {
        Map<Long, R> map = new HashMap<>();
        forEach(i -> map.put(i, func.apply(i)));
        return map;
    }

    public Set<Long> asSet() {
        return new HashSet<>() {{ Longs.this.forEach(this::add); }};
    }

    public Longs concat(Longs l1, Longs l2) {
        Longs.Builder builder = Longs.builder();
        l1.forEach(builder::add);
        l2.forEach(builder::add);
        return builder.build();
    }

    public boolean contains(long key) {
        for (long l: this) {
            if (l == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Longs longs && Arrays.equals(this.array, longs.array);
    }

    public Ints findIndices(LongPredicate predicate) {
        Ints.Builder builder = Ints.builder();
        forEach((i,v) -> {
            if (predicate.test(v)) {
                builder.add(i);
            }
        });
        return builder.build();
    }

    public Longs forEach(BiConsumer<Integer, Long> biConsumer) {
        for (int i = 0; i < array.length; i++) {
            biConsumer.accept(i, array[i]);
        }
        return this;
    }

    public long get(int index) {
        return array[index];
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public Iterator<Long> iterator() {
        return new Iterator<>() {

            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor != Longs.this.array.length;
            }

            @Override
            public Long next() {
                return Longs.this.array[cursor++];
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

    public Longs map(LongUnaryOperator operator) {
        for (int i = 0; i < array.length; i++) {
            array[i] = operator.applyAsLong(array[i]);
        }
        return this;
    }

    public long prod() {
        if (this.isEmpty()) {
            return 0;
        } else {
            long prod = array[0];
            for (int i = 1; i < array.length; i++) {
                prod *= array[i];
            }
            return prod;
        }
    }

    public Longs reverse() {
        for (int i = 0; i < length() / 2; i++) {
            swap(i, length() - 1 - i);
        }
        return this;
    }

    public Longs set(int index, long value) {
        array[index] = value;
        return this;
    }

    public Longs slice(int start, int stop, int step) {
        Longs.Builder builder = Longs.builder();
        for (int i = start; i <= stop; i += step) {
            builder.add(array[i]);
        }
        return builder.build();
    }

    public LongStream stream() {
        return LongStream.of(array);
    }

    public Longs swap(int index1, int index2) {
        long temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        return this;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static class Builder {

        private final LongStream.Builder builder;

        private Builder() {
            builder = LongStream.builder();
        }

        public Longs.Builder add(long i) {
            builder.add(i);
            return this;
        }

        public Longs build() {
            return new Longs(builder.build().toArray());
        }
    }

}
