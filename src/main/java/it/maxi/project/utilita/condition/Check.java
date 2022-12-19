package it.maxi.project.utilita.condition;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AtomicDouble;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Comparator;

public class Check {

    public static TelegramLongPollingBot getTelegram() {
        return new TelegramLongPollingBot() {
            @Override
            public String getBotToken() {
                return null;
            }

            @Override
            public void onUpdateReceived(Update update) {

            }

            @Override
            public String getBotUsername() {
                return null;
            }
        };
    }

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

    public static Object notNullOrElse(Object obj, Object defaultObj) {
        return obj == null ? defaultObj : obj;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static <T> boolean equals(T x, T y) {
        return java.util.Objects.equals(x, y);
    }

    public static <T> boolean isMoreOrEqualThan(T x, T y, Comparator<T> comparator) {
        return comparator.compare(x, y) >= 0;
    }

    public static <T> boolean isMoreThan(T x, T y, Comparator<T> comparator) {
        return comparator.compare(x, y) > 0;
    }

    public static <T> boolean isLessThan(T x, T y, Comparator<T> comparator) {
        return comparator.compare(x, y) < 0;
    }

    public static <T> boolean isLessOrEqualThan(T x, T y, Comparator<T> comparator) {
        return comparator.compare(x, y) <= 0;
    }

    public static <T extends Comparable<T>> boolean isMoreOrEqualThan(T x, T y) {
        return x.compareTo(y) >= 0;
    }

    public static <T extends Comparable<T>> boolean isMoreThan(T x, T y) {
        return x.compareTo(y) > 0;
    }

    public static <T extends Comparable<T>> boolean isLessThan(T x, T y) {
        return x.compareTo(y) < 0;
    }

    public static <T extends Comparable<T>> boolean isLessOrEqualThan(T x, T y) {
        return x.compareTo(y) <= 0;
    }

}
