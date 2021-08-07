import ua.goit.hw11threads.NumberThreads;
import ua.goit.hw11threads.SimpleTimer;

public class ThreadTest {

    private static void testSimpleTimer() {
        System.out.println("Test SimpleTimer");
        SimpleTimer simpleTimer = new SimpleTimer();
        simpleTimer.startTimer();
        simpleTimer.startNotify();
    }

    private static void testNumberThread() throws InterruptedException {
        System.out.println("Test NumberThreads");
        NumberThreads numberThreads = new NumberThreads(100);
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

    public static void main(String[] args) throws InterruptedException {
        testNumberThread();
        System.out.println();
        Thread.sleep(2000);
        testSimpleTimer();
    }
}
