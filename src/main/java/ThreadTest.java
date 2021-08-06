import ua.goit.hw11threads.NumberThreads;
import ua.goit.hw11threads.SimpleTimer;

import java.util.Arrays;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        /*SimpleTimer simpleTimer = new SimpleTimer();
        simpleTimer.startTimer();
        simpleTimer.startNotify();*/
        NumberThreads numberThreads = new NumberThreads(15);
        Thread a = numberThreads.startA();
        Thread b = numberThreads.startB();
        Thread c = numberThreads.startC();
        Thread d = numberThreads.startD();
        a.start();
        b.start();
        c.start();
        d.start();

        a.join();
        b.join();
        c.join();
        d.join();

        System.out.println(numberThreads);
    }
}
