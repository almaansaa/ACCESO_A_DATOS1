package MULTIHILO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EJ2Main {
    public static void main(String[] args) {
        // Creamos el almacén con 100 unidades de producto
        EJ2Almacen almacen = new EJ2Almacen(100);

        // Creamos un pool de hilos para gestionar de forma eficiente a los 300 clientes
        ExecutorService executor = Executors.newFixedThreadPool(50);

        // Lanzamos los 300 clientes agolpados en la puerta
        for (int i = 1; i <= 300; i++) {
            executor.execute(new EJ2Cliente("Cliente " + i, almacen));
        }

        // Finalizamos el servicio correctamente mediante shutdown() (Apartado 8.3 y 9)
        executor.shutdown();

        try {
            // Esperamos a que terminen todas las tareas para ver el stock final
            executor.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println("Proceso finalizado. Stock final en el almacén: " + almacen.getStock());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
