package threads;

import java.util.*;

public class MainOneThread {

    public static void main(String[] args) throws InterruptedException {

        final var startTime = System.currentTimeMillis();

        final var id = Thread.currentThread().getId();
        System.out.println("thread(%d): start new Thread".formatted(id));

        /*task1*/
        final Thread thread1 = new Thread(() -> {
            final var id2 = Thread.currentThread().getId();
            System.out.println("thread(%d): start".formatted(id2));
            List<Integer> list = new ArrayList<>();
            Random rand = new Random(System.currentTimeMillis());
            for (int i = 0; i < 10_000_000; i += 1) {
                list.add(rand.nextInt(1000000));
            }
            final var res = Collections.max(list);
            System.out.println("thread(%d): end, res = %d".formatted(id2, res));
        }
        );

        /*task2*/
        final Thread thread2 = new Thread(() -> {
            final var id2 = Thread.currentThread().getId();
            System.out.println("thread(%d): start".formatted(id2));
            List<Integer> vec = new Vector<>();
            for (int i = 10_000_000; i > 0; i--) {
                vec.add(i);
            }
            Collections.sort(vec);
            System.out.println("thread(%d): end".formatted(id2));
        });

        /*task3*/
        final Thread thread3 = new Thread(() -> {
            final var id2 = Thread.currentThread().getId();
            System.out.println("thread(%d): start".formatted(id2));
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 10_000_000; i++) {
                list.add(i);
            }
            for (int i = 0; i < 10_000_000; i++) {
                list.remove(list.size() - 1);
            }
            System.out.println("thread(%d): end".formatted(id2));
        });

        System.out.println("thread(%d): waiting".formatted(id));
        thread1.run();
        thread2.run();
        thread3.run();

        final var endTime = System.currentTimeMillis();
        System.out.println("thread(%d): end, dTime = %d".formatted(id, endTime-startTime));
    }
}
