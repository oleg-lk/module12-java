package dop_tasks.task1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Main {

    static class Adder implements Runnable {
        List<? super Integer> list;

        public Adder(List<? super Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                Random rand = new Random(System.currentTimeMillis());
                if (list.size() < 10) {
                    final var i = rand.nextInt(100);
                    list.add(i);
                    final var sz = list.size();
                    System.out.println("Adder: add %d[%d] to list".formatted(i, sz));
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> stack = new LinkedList<>();

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
                    final var remEl = stack.remove(0);
                    System.out.println("Remover: remove %d[0], list size = %d".formatted(remEl, stack.size()));
                }
            }
        });
        remover.start();
    }
}
