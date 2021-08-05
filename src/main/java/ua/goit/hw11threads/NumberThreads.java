package ua.goit.hw11threads;

public class NumberThreads {

    private int n;
    private String[] result;

    public NumberThreads(int n) {
        this.n = n;
        this.result = new String[n];
    }

    private String fizz(int number) {
        return number % 3 == 0 ? "fizz" : String.valueOf(number);
    }

    private String buzz(int number) {
        return number % 5 == 0 ? "buzz" : String.valueOf(number);
    }

    private String fizzbuzz(int number) {
        return number % 15 == 0 ? "fizzbuzz" : String.valueOf(number);
    }
}
