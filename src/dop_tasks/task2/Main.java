package dop_tasks.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    interface Perimeter
    {
        double getPerimeter();
    }
    static class PerimetrSquare implements Perimeter
    {
        double a;
        public PerimetrSquare(double a) {
            this.a = a;
        }

        @Override
        public double getPerimeter() {
            return 4*a;
        }
    }
    static class PerimetrCircle implements Perimeter{

        double rad;

        public PerimetrCircle(double rad) {
            this.rad = rad;
        }

        @Override
        public double getPerimeter() {
            return 2*Math.PI*rad;
        }
    }

    static class PerimetrTriangle implements Perimeter
    {
        double a;

        public PerimetrTriangle(double a) {
            this.a = a;
        }

        @Override
        public double getPerimeter() {
            return 3*a;
        }
    }

    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();
        Random random = new Random();
        threads.add(new Thread(() -> {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("PerimetrTriangle(15) = " + new PerimetrTriangle(15).getPerimeter());
        }));
        threads.add(new Thread(() -> {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("PerimetrCircle(10) = " + new PerimetrCircle(10).getPerimeter());
        }));
        threads.add(new Thread(() -> {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("PerimetrSquare(25) = " + new PerimetrSquare(25).getPerimeter());
        }));
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
