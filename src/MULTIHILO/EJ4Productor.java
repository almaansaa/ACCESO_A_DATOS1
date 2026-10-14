package MULTIHILO;

import java.util.Random;

public class EJ4Productor implements Runnable {
    private EJ4ColaLimitada cola;
    private Random random = new Random();

    public EJ4Productor(EJ4ColaLimitada cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                cola.producir(i);
                Thread.sleep(random.nextInt(500));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
