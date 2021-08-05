package ua.goit.hw11threads;

import java.time.LocalDateTime;

public class SimpleTimer {
    private static final int NOTIFY_INTERVAL = 5;

    private int notifyInterval;
    private LocalDateTime currentDate = LocalDateTime.now();
    boolean switcher;

    public SimpleTimer(int notifyInterval) {
        this.notifyInterval = notifyInterval;
    }

    public SimpleTimer() {
        this(NOTIFY_INTERVAL);
    }

    synchronized public void printCurrentDate() throws InterruptedException {
        while (switcher) {
            wait();
        }
        for (int i = 0; i < notifyInterval; i++) {
            System.out.println(currentDate);
            currentDate = LocalDateTime.now();
            Thread.sleep(1000);
        }
        switcher = true;
        notify();
    }

    synchronized public void printIntervalNotify() throws InterruptedException {
        while (!switcher) {
            wait();
        }
        System.out.println(notifyInterval + " sec have passed");
        switcher = false;
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
