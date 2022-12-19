package it.maxi.project.utilita.math;

import it.maxi.project.utilita.condition.Check;

import java.util.Iterator;
import java.util.function.IntFunction;

public class Sequence<T> implements Iterable<T> {

    public static <T> Sequence<T> of(IntFunction<T> generator) {
        return Sequence.of(0, generator);
    }

    public static <T> Sequence<T> of(int startIndex, IntFunction<T> generator) {
        Check.notNull(generator, "operator is null");
        return new Sequence<>(startIndex, generator);
    }

    private final IntFunction<T> generator;
    private final int startIndex;

    private Sequence(int startIndex, IntFunction<T> generator) {
        this.generator = generator;
        this.startIndex = startIndex;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int cursor = startIndex;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return Sequence.this.generator.apply(cursor++);
            }
        };
    }
}
