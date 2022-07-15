package thread;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

class ConsoleClock extends Thread {
    @Override
    public void run() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(df.format(System.currentTimeMillis()));
            } catch (InterruptedException e) {
                System.out.println("The clock was stopped");
                break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ChikkenEgg chikken = new ChikkenEgg("Chikken");
        ChikkenEgg egg = new ChikkenEgg("egg");

        chikken.start();
        egg.start();

        while (chikken.isAlive() && egg.isAlive()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("main catched exception " + e.getMessage());
                break;
            }
        }
        final var isChikken = egg.isAlive();
        if (isChikken){
            System.out.println("egg.interrupt");
            egg.interrupt();
            try {
                egg.join();
            } catch (InterruptedException e) {
                System.out.println("main catched exception " + e.getMessage());
            }
        }
        else {
            System.out.println("chikken.interrupt");
            chikken.interrupt();
            try {
                chikken.join();
            } catch (InterruptedException e) {
                System.out.println("main catched exception " + e.getMessage());
            }
        }
        System.out.println(isChikken ? "chikken win": "egg win");
        System.out.println("The dispute is over");
    }
}
