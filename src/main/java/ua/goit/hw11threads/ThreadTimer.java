package ua.goit.hw11threads;

import java.util.Date;

public class ThreadTimer {
    private int notifyInterval;
    private Date currentDate = new Date();

    synchronized private void printCurrentDate() throws InterruptedException {
        wait();
        for (int i = 0; i < notifyInterval; i++) {
            System.out.println(currentDate);
            currentDate = new Date();
            Thread.sleep(1000);
        }
        notify();
    }

    synchronized private void printIntervalNotify() throws InterruptedException {
        wait();
        System.out.println("Прошло 5 секунд");
        notify();
    }

    public ThreadTimer()  {
        Runnable getCurrentDate = () -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(new Date());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread threadCurrentDate = new Thread(getCurrentDate);
        threadCurrentDate.start();
    }

}
