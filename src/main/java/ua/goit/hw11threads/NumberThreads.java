package ua.goit.hw11threads;

import java.util.Arrays;
import java.util.function.IntConsumer;

public class NumberThreads {

    private final int n;
    private final String[] result;

    public NumberThreads(int n) {
        this.n = n;
        this.result = new String[n];
    }

    private void checkMod(int number, int mod, String replacement) {
        if ((result[number - 1] == null
                || result[number - 1].equals(String.valueOf(number)))
                && number % mod == 0) {
            result[number - 1] = replacement;
        }
        System.out.println(Thread.currentThread().getName() + " " + number + " " + result[number - 1]);
    }

    synchronized private void fizz(int number) throws InterruptedException {
        checkMod(number, 3, "fizz");
        Thread.sleep(3);
        //notify();
    }

    synchronized private void buzz(int number) throws InterruptedException {
        checkMod(number, 5, "buzz");
        Thread.sleep(4);
        //notify();
    }

    synchronized private void fizzbuzz(int number) throws InterruptedException {
        if ((result[number - 1] == null
                || result[number - 1].equals(String.valueOf(number))
                || result[number - 1].equals("fizz")
                || result[number - 1].equals("buzz"))
                && number % 15 == 0) {
            result[number - 1] = "fizzbuzz";
        }
        System.out.println(Thread.currentThread().getName() + " " + number + " " + result[number - 1]);
        Thread.sleep(3);
        //notify();
    }

    synchronized private void numbers(int number) throws InterruptedException {
        if (result[number - 1] == null) {
            result[number - 1] = String.valueOf(number);
        }
        System.out.println(Thread.currentThread().getName() + " " + number + " " + result[number - 1]);
        Thread.sleep(4);
        //notify();
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
        Thread thread = startThread(number -> {
            try {
                fizz(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setName("A");
        return thread;
    }

    public Thread startB() {
        Thread thread = startThread(number -> {
            try {
                buzz(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setName("B");
        return thread;
    }

    public Thread startC() {
        Thread thread = startThread(number -> {
            try {
                fizzbuzz(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setName("C");
        return thread;
    }

    public Thread startD() {
        Thread thread = startThread(number -> {
            try {
                numbers(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setName("D");
        return thread;
    }

    @Override
    public String toString() {
        return Arrays.toString(result);
    }
}
