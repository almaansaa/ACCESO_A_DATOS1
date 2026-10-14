package MULTIHILO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EJ5Main {
    public static void main(String[] args) throws InterruptedException {
        final int NUM_BARBEROS = 2;
        final int NUM_SILLAS = 3;

        EJ5Peluqueria peluqueria = new EJ5Peluqueria(NUM_SILLAS);
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 1; i <= NUM_BARBEROS; i++) {
            executor.execute(new EJ5Barbero("Barbero-" + i, peluqueria));
        }

        for (int i = 1; i <= 10; i++) {
            executor.execute(new EJ5ClientePeluqueria("Cliente-" + i, peluqueria));
            Thread.sleep(400);
        }

        Thread.sleep(5000);
        executor.shutdownNow();
    }
}