package threads;

import java.util.Scanner;

class TestStart extends Thread {
    private int i = 0;

    public TestStart(int i) {
        this.i = i;
    }
    @Override
    public void run() {
        System.out.println(i);
    }
}

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new TestStart(i).start();
        }
    }
}
