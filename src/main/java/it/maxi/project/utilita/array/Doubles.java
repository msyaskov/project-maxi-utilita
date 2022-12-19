package it.maxi.project.utilita.array;

import it.maxi.project.utilita.condition.Check;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Doubles является оберткой над массивом типа double.
 * Данный клас предоставляет методы для работы с обертываемым массивом.
 */
public final class Doubles implements Iterable<Double> {

    public static Doubles.Builder builder() {
        return new Doubles.Builder();
    }

    public static Collector<Double, Doubles.Builder, Doubles> collector() {
        return Collector.of(
                Doubles::builder,
                Doubles.Builder::add,
                (left, right) -> {
                    right.build().forEach(left::add);
                    return left;
                },
                Doubles.Builder::build);
    }

    public static Doubles of(double... values) {
        return new Doubles(values);
    }

    public static Doubles ofCopy(double... values) {
        double[] array = new double[values.length];
        System.arraycopy(values,0,array,0,values.length);
        return new Doubles(array);
    }

    private final double[] array;

    public Doubles(double[] array) {
        Check.notNull(array, "array is null");
        this.array = array;
    }

    public double[] asArray() {
        return array;
    }

    public List<Double> asList() {
        return new ArrayList<>() {{ Doubles.this.forEach(d -> add(d)); }};
    }

    public <R> Map<Double, R> asMap(Function<Double, R> func) {
        return new HashMap<>() {{ Doubles.this.forEach(d -> put(d, func.apply(d))); }};
    }

    public Set<Double> asSet() {
        return new HashSet<>() {{ Doubles.this.forEach(this::add); }};
    }

    public boolean contains(double key) {
        for (double d: this) {
            if (d == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Doubles doubles && Arrays.equals(this.array, doubles.array);
    }

    public Ints findIndices(DoublePredicate predicate) {
        Ints.Builder builder = Ints.builder();
        forEach((i,v) -> {
            if (predicate.test(v)) {
                builder.add(i);
            }
        });
        return builder.build();
    }

    public Doubles forEach(BiConsumer<Integer, Double> biConsumer) {
        for (int i = 0; i < array.length; i++) {
            biConsumer.accept(i, array[i]);
        }
        return this;
    }

    public double get(int index) {
        return array[index];
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public Iterator<Double> iterator() {
        return new Iterator<>() {

            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor != Doubles.this.array.length;
            }

            @Override
            public Double next() {
                return Doubles.this.array[cursor++];
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

    public Doubles map(DoubleUnaryOperator operator) {
        for (int i = 0; i < array.length; i++) {
            array[i] = operator.applyAsDouble(array[i]);
        }
        return this;
    }

    public double prod() {
        if (isNotEmpty()) {
            double prod = array[0];
            for (int i = 1; i < array.length; i++) {
                prod *= array[i];
            }
            return prod;
        } else {
            return 0.0;
        }
    }

    public Doubles reverse() {
        for (int i = 0; i < length() / 2; i++) {
            swap(i, length() - 1 - i);
        }
        return this;
    }

    public Doubles set(int index, double value) {
        array[index] = value;
        return this;
    }

    public Doubles slice(int start, int stop, int step) {
        Doubles.Builder builder = Doubles.builder();
        for (int i = start; i <= stop; i += step) {
            builder.add(array[i]);
        }
        return builder.build();
    }

    public DoubleStream stream() {
        return DoubleStream.of(array);
    }

    public Doubles swap(int index1, int index2) {
        double temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        return this;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static class Builder {

        private final Stream.Builder<Double> builder;

        private Builder() {
            builder = Stream.builder();
        }

        public Doubles.Builder add(double o) {
            builder.add(o);
            return this;
        }

        public Doubles build() {
            Object[] objArray = builder.build().toArray();
            double[] array = new double[objArray.length];
            for (int i = 0; i < array.length; i++) {
                array[i] = (Double) objArray[i];
            }
            return new Doubles(array);
        }

    }

}
