package MULTIHILO;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class EJ6Filosofo implements Runnable {
    private int id;
    private Semaphore tenedorIzquierdo;
    private Semaphore tenedorDerecho;
    private Semaphore limitadorComida;
    private Random random = new Random();

    public EJ6Filosofo(int id, Semaphore tenedorIzquierdo, Semaphore tenedorDerecho, Semaphore limitadorComida) {
        this.id = id;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorDerecho = tenedorDerecho;
        this.limitadorComida = limitadorComida;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("El filósofo número " + id + " está pensando.");
                Thread.sleep(random.nextInt(4000) + 1000);

                limitadorComida.acquire();
                tenedorIzquierdo.acquire();
                tenedorDerecho.acquire();

                System.out.println("==> Filósofo número " + id + " comiendo.");
                Thread.sleep(2000);

                tenedorDerecho.release();
                tenedorIzquierdo.release();
                limitadorComida.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
