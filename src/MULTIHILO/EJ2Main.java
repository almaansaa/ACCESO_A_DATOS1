package MULTIHILO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EJ2Main {
    public static void main(String[] args) {
        //100 unidades del producto
        EJ2Almacen almacen = new EJ2Almacen(100);

        //300 clientes
        ExecutorService executor = Executors.newFixedThreadPool(50);

        //300 clientes en la puerta
        for (int i = 1; i <= 300; i++) {
            executor.execute(new EJ2Cliente("Cliente " + i, almacen));
        }
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println("Proceso finalizado. Stock final en el almacén: " + almacen.getStock());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
