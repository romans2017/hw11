import ua.goit.hw11threads.SimpleTimer;

public class ThreadTest {
    public static void main(String[] args) {
        SimpleTimer simpleTimer = new SimpleTimer(3);
        simpleTimer.startTimer();
        simpleTimer.startNotify();
    }
}
