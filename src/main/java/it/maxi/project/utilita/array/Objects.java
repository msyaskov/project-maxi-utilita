package it.maxi.project.utilita.array;

import it.maxi.project.utilita.condition.Check;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Objects является оберткой над массивом типа Object.
 * Данный клас предоставляет методы для работы с обертываемым массивом.
 */
public final class Objects implements Iterable<Object> {

    public static Objects.Builder builder() {
        return new Objects.Builder();
    }

    public static Collector<Object, Objects.Builder, Objects> collector() {
        return Collector.of(
                Objects::builder,
                Objects.Builder::add,
                (left, right) -> {
                    right.build().forEach(left::add);
                    return left;
                },
                Objects.Builder::build);
    }

    public static Objects of(Object... values) {
        return new Objects(values);
    }

    public static Objects ofCopy(Object... values) {
        Object[] array = new Object[values.length];
        System.arraycopy(values, 0, array, 0, array.length);
        return new Objects(array);
    }

    private final Object[] array;

    public Objects(Object[] array) {
        Check.notNull(array, "array is null");
        this.array = array;
    }

    public Object[] asArray() {
        return array;
    }

    public List<Object> asList() {
        return new ArrayList<>() {{ Objects.this.forEach(o -> this.add(o)); }};
    }

    public <R> Map<Object, R> asMap(Function<Object, R> func) {
        return new HashMap<>() {{ Objects.this.forEach(o -> this.put(o, func.apply(o))); }};
    }

    public Set<Object> asSet() {
        return new HashSet<>() {{ Objects.this.forEach(this::add); }};
    }

    public boolean contains(Object key) {
        for (Object o: this) {
            if (o.hashCode() == key.hashCode() && o.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Objects objects && Arrays.equals(this.array, objects.array);
    }

    public Ints findIndices(Predicate<Object> predicate) {
        Ints.Builder builder = Ints.builder();
        forEach((i,v) -> {
            if (predicate.test(v)) {
                builder.add(i);
            }
        });
        return builder.build();
    }

    public Objects forEach(BiConsumer<Integer, Object> biConsumer) {
        for (int i = 0; i < array.length; i++) {
            biConsumer.accept(i, array[i]);
        }
        return this;
    }

    public Object get(int index) {
        return array[index];
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iterator<>() {

            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor != Objects.this.array.length;
            }

            @Override
            public Object next() {
                return Objects.this.array[cursor++];
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

    public Objects map(UnaryOperator<Object> operator) {
        for (int i = 0; i < array.length; i++) {
            array[i] = operator.apply(array[i]);
        }
        return this;
    }

    public Objects reverse() {
        for (int i = 0; i < length() / 2; i++) {
            swap(i, length() - 1 - i);
        }
        return this;
    }

    public Objects set(int index, Object value) {
        Check.notNull(value, "value is null");
        array[index] = value;
        return this;
    }

    public Objects slice(int start, int stop, int step) {
        Builder builder = Objects.builder();
        for (int i = start; i <= stop; i += step) {
            builder.add(array[i]);
        }
        return builder.build();
    }

    public Stream<Object> stream() {
        return Stream.of(array);
    }

    public Objects swap(int index1, int index2) {
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        return this;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static class Builder {

        private final Stream.Builder<Object> builder;

        private Builder() {
            builder = Stream.builder();
        }

        public Objects.Builder add(Object o) {
            builder.add(o);
            return this;
        }

        public Objects build() {
            return new Objects(builder.build().toArray());
        }

    }
}
