package ua.goit.hw11threads;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleTimer {
    private static final int NOTIFY_INTERVAL = 5;

    private int notifyInterval;
    private int incrementalNotifyInterval;

    public SimpleTimer(int notifyInterval) {
        this.notifyInterval = this.incrementalNotifyInterval = notifyInterval;
    }

    public SimpleTimer() {
        this(NOTIFY_INTERVAL);
    }

    synchronized public void printCurrentDate() throws InterruptedException {
        while (incrementalNotifyInterval == 0) {
            wait();
        }
        Thread.sleep(1000);
        System.out.println(LocalDateTime.now().withNano(0).format(DateTimeFormatter.ofPattern("E dd.MM.yyyy HH:mm:ss")));
        incrementalNotifyInterval--;
        notify();
    }

    synchronized public void printIntervalNotify() throws InterruptedException {
        while (incrementalNotifyInterval > 0) {
            wait();
        }
        System.out.println("Прошло " + notifyInterval + " сек");
        incrementalNotifyInterval = notifyInterval;
        notify();
    }

    public void startTimer() {
        Runnable timer = () -> {
            while (true) {
                try {
                    printCurrentDate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(timer).start();
    }

    public void startNotify() {
        Runnable notifyTimeInterval = () -> {
            while (true) {
                try {
                    printIntervalNotify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(notifyTimeInterval).start();
    }

}
