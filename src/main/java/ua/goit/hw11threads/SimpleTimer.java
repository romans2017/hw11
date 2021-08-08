package ua.goit.hw11threads;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleTimer {
    private static final int NOTIFY_INTERVAL = 5;

    private final int notifyInterval;
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
        notifyAll();
    }

    synchronized public void printIntervalNotify() throws InterruptedException {
        while (incrementalNotifyInterval > 0) {
            wait();
        }
        System.out.println("Прошло " + notifyInterval + " сек");
        incrementalNotifyInterval = notifyInterval;
        notifyAll();
    }

    public Thread timer() {
        Runnable timer = () -> {
            while (true) {
                try {
                    printCurrentDate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return new Thread(timer);
    }

    public Thread timerNotify() {
        Runnable notifyTimeInterval = () -> {
            while (true) {
                try {
                    printIntervalNotify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return new Thread(notifyTimeInterval);
    }
}
