package ua.goit.hw11threads;

import java.util.Arrays;
import java.util.function.IntConsumer;

public class NumberThreads {

    private final static boolean SHOW_NOTIFICATIONS = false;
    private final static int N = 15;

    private final int n;
    private final String[] result;
    private final boolean showNotifications;

    public NumberThreads(int n, boolean showNotifications) {
        this.n = n;
        this.showNotifications = showNotifications;
        this.result = new String[n];
    }

    public NumberThreads(int n) {
        this(n, SHOW_NOTIFICATIONS);
    }

    public NumberThreads() {
        this(N, SHOW_NOTIFICATIONS);
    }

    private void checkMod(int number, int mod, String replacement) {
        if ((result[number - 1] == null
                || result[number - 1].equals(String.valueOf(number)))
                && number % mod == 0) {
            result[number - 1] = replacement;
        }
        if (showNotifications) {
            System.out.println(Thread.currentThread().getName() + " " + number + " " + result[number - 1]);
        }
    }

    synchronized private void fizz(int number) {
        checkMod(number, 3, "fizz");
        notify();
    }

    synchronized private void buzz(int number) {
        checkMod(number, 5, "buzz");
        notify();
    }

    synchronized private void fizzbuzz(int number) {
        if ((result[number - 1] == null
                || result[number - 1].equals(String.valueOf(number))
                || result[number - 1].equals("fizz")
                || result[number - 1].equals("buzz"))
                && number % 15 == 0) {
            result[number - 1] = "fizzbuzz";
        }
        if (showNotifications) {
            System.out.println(Thread.currentThread().getName() + " " + number + " " + result[number - 1]);
        }
        notify();
    }

    synchronized private void numbers(int number) {
        if (result[number - 1] == null) {
            result[number - 1] = String.valueOf(number);
        }
        if (showNotifications) {
            System.out.println(Thread.currentThread().getName() + " " + number + " " + result[number - 1]);
        }
        notify();
    }

    private Thread startThread(IntConsumer func) {
        Runnable runnable = () -> {
            for (int i = 1; i <= n; i++) {
                func.accept(i);
            }
        };
        return new Thread(runnable);
    }

    public Thread startA() {
        return startThread(this::fizz);
    }

    public Thread startB() {
        return startThread(this::buzz);
    }

    public Thread startC() {
        return startThread(this::fizzbuzz);
    }

    public Thread startD() {
        return startThread(this::numbers);
    }

    @Override
    public String toString() {
        return Arrays.toString(result).replaceAll("[\\[\\]]", "");
    }

}
