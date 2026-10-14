package MULTIHILO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class EJ6Main {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Semaphore[] tenedores = new Semaphore[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            tenedores[i] = new Semaphore(1);
        }

        Semaphore limitadorComida = new Semaphore(2);
        ExecutorService executor = Executors.newFixedThreadPool(numFilosofos);

        for (int i = 0; i < numFilosofos; i++) {
            Semaphore izquierdo = tenedores[i];
            Semaphore derecho = tenedores[(i + 1) % numFilosofos];

            if (i == numFilosofos - 1) {
                executor.execute(new EJ6Filosofo(i + 1, derecho, izquierdo, limitadorComida));
            } else {
                executor.execute(new EJ6Filosofo(i + 1, izquierdo, derecho, limitadorComida));
            }
        }
    }
}
