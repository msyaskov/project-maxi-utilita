package it.maxi.project.utilita.math;

import it.maxi.project.utilita.array.Ints;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

public class MathFunctions {

    /**
     * Пропорциональность: f(x) = k * x.
     */
    public static UnaryOperator<Double> proportionality(double k) {
        return x -> k * x;
    }

    /**
     * Обратная пропорциональность: f(x) = k / x.
     */
    public static UnaryOperator<Double> inverseProportionality(double k) {
        return x -> k / x;
    }

    /**
     * Линейная функция: f(x) = a * x + b.
     */
    public static UnaryOperator<Double> linear(double a, double b) {
        return x -> a * x + b;
    }

    /**
     * Парабола: f(x) = a * x * x + b * x + c
     */
    public static UnaryOperator<Double> parabola(double a, double b, double c) {
        return x -> a * x * x + b * x + c;
    }

    /**
     * Многочлен, полином: f(x,n) = an * pow(x,n) + an-1 * pow(x, n-1) + ... + a1 * x + a0.
     */
    public static UnaryOperator<Double> polynomial(int... as) {
        return x -> {
            AtomicReference<Double> polynomial = new AtomicReference<>((double) 0);
            Ints.of(as).forEach((i, a) -> polynomial.updateAndGet(v -> v + a * Math.pow(x, i)));
            return polynomial.get();
        };
    }

    /**
     * Гипербола.
     * Канонический вид: x * x / a * a - y * y / b * b = 1
     * Вид для вычислений: f(x) = |b| * sqrt((x * x) / (a * a) - 1)
     */
    public static UnaryOperator<Double> hyperbola(double a, double b) {
        return x -> Math.abs(b) * Math.sqrt((x * x) / (a * a) - 1);
    }

    /**
     * Синусоида: f(x) = a + b * sin(c * x + d).
     */
    public static UnaryOperator<Double> sin(double a, double b, double c, double d) {
        return x -> a + b * Math.sin(c * x + d);
    }

}
