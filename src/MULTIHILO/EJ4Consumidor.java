package MULTIHILO;

import java.util.Random;

public class EJ4Consumidor implements Runnable {
    private EJ4ColaLimitada cola;
    private Random random = new Random();

    public EJ4Consumidor(EJ4ColaLimitada cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                cola.consumir();
                Thread.sleep(random.nextInt(800));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
