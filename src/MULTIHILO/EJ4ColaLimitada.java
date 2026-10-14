package MULTIHILO;

import java.util.LinkedList;
import java.util.Queue;

public class EJ4ColaLimitada {
    private Queue<Integer> cola = new LinkedList<>();
    private int capacidad;

    public EJ4ColaLimitada(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void producir(int valor) throws InterruptedException {
        while (cola.size() == capacidad) {
            wait();
        }
        cola.add(valor);
        System.out.println("Productor añadió el valor: " + valor + " | Cola: " + cola);
        notifyAll();
    }

    public synchronized int consumir() throws InterruptedException {
        while (cola.isEmpty()) {
            wait();
        }
        int valor = cola.poll();
        System.out.println("Consumidor leyó el valor: " + valor + " | Cola: " + cola);
        notifyAll();
        return valor;
    }
}
