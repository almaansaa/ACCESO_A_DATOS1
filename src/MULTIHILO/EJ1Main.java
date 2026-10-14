package MULTIHILO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EJ1Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            executor.execute(new EJ1Pedido(i));
        }

        executor.shutdown();
    }
}