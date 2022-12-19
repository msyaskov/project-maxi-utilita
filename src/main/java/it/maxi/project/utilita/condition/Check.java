package it.maxi.project.utilita.condition;

public class Check {

    public static <T> T notNull(T t) {
        return notNull(t, null);
    }

    public static <T> T notNull(T t, String message) {
        if (t == null) {
            throw new NullPointerException(message);
        } else {
            return t;
        }
    }

}
