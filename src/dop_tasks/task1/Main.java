package dop_tasks.task1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    static class Adder implements Runnable {
        ArrayBlockingQueue<Integer> list;

        public Adder(ArrayBlockingQueue<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                Random rand = new Random(System.currentTimeMillis());
                if (list.size() < 10) {
                    final var i = rand.nextInt(100);
                    try {
                        list.put(i);
                    } catch (InterruptedException e) {
                        break;
                    }
                    final var sz = list.size();
                    System.out.println("Adder: add %d[%d] to list".formatted(i, sz));
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> stack = new ArrayBlockingQueue<>(10);

        Thread adder = new Thread(new Adder(stack));
        adder.start();
        Thread remover = new Thread(() -> {
            Random rand = new Random(System.currentTimeMillis());
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    break;
                }
                final var r = rand.nextInt(10);
                if (r == 5 && stack.size() > 0) {
                    final Integer remEl;
                    try {
                        remEl = stack.take();
                    } catch (InterruptedException e) {
                        break;
                    }
                    System.out.println("Remover: remove %d[0], list size = %d".formatted(remEl, stack.size()));
                }
            }
        });
        remover.start();
    }
}
