package thread;

import java.util.Random;

public class ChikkenEgg extends Thread{
    private static Random rand = new Random(System.currentTimeMillis());

    public ChikkenEgg(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            final var sleepTime= rand.nextInt(1000);
            try {

                Thread.sleep(rand.nextInt(sleepTime));
            } catch (InterruptedException e) {
                System.out.println("%s: run catched exception %s".formatted(this.getName(), e.getMessage()));
                break;
            }
            System.out.println("%d(%d): %s".formatted(i, sleepTime, this.getName()));
        }
    }
}
