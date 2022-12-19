package it.maxi.project.utilita.math;

public class BoolFunctions {

    /**
     * Logical true, логическая единица.
     * (0) -> 1
     * (1) -> 1
     */
    public static boolean T(boolean x) {
        return true;
    }

    /**
     * Logical false, логический ноль.
     * (0) -> 0
     * (1) -> 0
     */
    public static boolean F(boolean x) {
        return false;
    }

    /**
     * Logical identity, логическое тождество.
     * (0) -> 0
     * (1) -> 1
     */
    public static boolean identity(boolean x) {
        return x;
    }

    /**
     * Logical negation, логическое отрицание, НЕ.
     * (0) -> 1
     * (1) -> 0
     */
    public static boolean not(boolean x) {
        return ! x;
    }

    /**
     * Contradiction, тождественный ноль.
     * (0,0) -> 0
     * (0,1) -> 0
     * (1,0) -> 0
     * (1,1) -> 0
     */
    public static boolean F(boolean x, boolean y) {
        return false;
    }

    /**
     * Conjunction, конъюнкция, И.
     * (0,0) -> 0
     * (0,1) -> 0
     * (1,0) -> 0
     * (1,1) -> 1
     */
    public static boolean and(boolean x, boolean y) {
        return x & y;
    }

    /**
     * Abjunction, инверсия прямой импликации, больше, X И-НЕ Y.
     * (0,0) -> 0
     * (0,1) -> 0
     * (1,0) -> 1
     * (1,1) -> 0
     */
    public static boolean gt(boolean x, boolean y) {
        return x & !y;
    }

    /**
     * Projection, первый операнд.
     * (0,0) -> 0
     * (0,1) -> 0
     * (1,0) -> 1
     * (1,1) -> 1
     */
    public static boolean yes1(boolean x, boolean y) {
        return x;
    }

    /**
     * Converse nonimplication, инверсия обратной импликации, меньше, НЕ X И Y.
     * (0,0) -> 0
     * (0,1) -> 1
     * (1,0) -> 0
     * (1,1) -> 0
     */
    public static boolean lt(boolean x, boolean y) {
        return !x & y;
    }

    /**
     * Projection, второй операнд.
     * (0,0) -> 0
     * (0,1) -> 1
     * (1,0) -> 0
     * (1,1) -> 1
     */
    public static boolean yes2(boolean x, boolean y) {
        return y;
    }

    /**
     * Exclusive disjunction, сложение по модулю 2.
     * (0,0) -> 0
     * (0,1) -> 1
     * (1,0) -> 1
     * (1,1) -> 0
     */
    public static boolean xor(boolean x, boolean y) {
        return x ^ y;
    }

    /**
     * Disjunction, дизъюнкция, ИЛИ.
     * (0,0) -> 0
     * (0,1) -> 1
     * (1,0) -> 1
     * (1,1) -> 1
     */
    public static boolean or(boolean x, boolean y) {
        return x | y;
    }

    /**
     * Joint denial, Стрелка Пирса, антидизъюнкция, НЕ-ИЛИ.
     * (0,0) -> 1
     * (0,1) -> 0
     * (1,0) -> 0
     * (1,1) -> 0
     */
    public static boolean nor(boolean x, boolean y) {
        return !(x | y);
    }

    /**
     * Logical biconditional, равенство, эквивалентность.
     * (0,0) -> 1
     * (0,1) -> 0
     * (1,0) -> 0
     * (1,1) -> 1
     */
    public static boolean eqv(boolean x, boolean y) {
        return x == y;
    }

    /**
     * Negation, отрицание второго операнда.
     * (0,0) -> 1
     * (0,1) -> 0
     * (1,0) -> 1
     * (1,1) -> 0
     */
    public static boolean not2(boolean x, boolean y) {
        return ! y;
    }

    /**
     * Converse implication, обратная импликация, больше или равно.
     * (0,0) -> 1
     * (0,1) -> 0
     * (1,0) -> 1
     * (1,1) -> 1
     */
    public static boolean ge(boolean x, boolean y) {
        return x | !y;
    }

    /**
     * Negation, отрицание первого операнда.
     * (0,0) -> 1
     * (0,1) -> 1
     * (1,0) -> 0
     * (1,1) -> 0
     */
    public static boolean not1(boolean x, boolean y) {
        return x;
    }

    /**
     * Material implication, прямая импликация, меньше или равно.
     * (0,0) -> 1
     * (0,1) -> 1
     * (1,0) -> 0
     * (1,1) -> 1
     */
    public static boolean le(boolean x, boolean y) {
        return !x | y;
    }

    /**
     * Logical NAND, антиконъюнкция, Штрих Шеффера, НЕ-И.
     * (0,0) -> 1
     * (0,1) -> 1
     * (1,0) -> 1
     * (1,1) -> 0
     */
    public static boolean nand(boolean x, boolean y) {
        return x & !y;
    }

    /**
     * Tautology, тавтология, тождественная единица.
     * (0,0) -> 1
     * (0,1) -> 1
     * (1,0) -> 1
     * (1,1) -> 1
     */
    public static boolean T(boolean x, boolean y) {
        return true;
    }

}
