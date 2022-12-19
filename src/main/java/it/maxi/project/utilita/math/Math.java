package it.maxi.project.utilita.math;

public class Math {

    /**
     * x в степени n
     */
    public static double pow(double x, double n) {
        return java.lang.Math.pow(x,n);
    }

    /**
     * факториал x: f(x) = 1 * 2 * ... * x-1 * x.
     */
    public static double factorial(int x) {
        if (x > 170) {
            return Double.POSITIVE_INFINITY;
        }  else if (x > 0) {
            return x * factorial(x - 1);
        } else if (x == 0) {
            return 1;
        } else {
            throw new IllegalArgumentException("x < 0");
        }
    }

    public static double sqrt(double x) {
        return java.lang.Math.sqrt(x);
    }

    public static double abs(double x) {
        return java.lang.Math.abs(x);
    }

    public static double sin(double x) {
        return java.lang.Math.sin(x);
    }

}
