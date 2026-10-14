package MULTIHILO;

import java.util.concurrent.Semaphore;

public class EJ5Peluqueria {
    private int numSillasLibres;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore clientesEsperando = new Semaphore(0);
    private Semaphore barberosDisponibles = new Semaphore(0);

    public EJ5Peluqueria(int sillas) {
        this.numSillasLibres = sillas;
    }

    public boolean entrarCliente(String nombreCliente) throws InterruptedException {
        mutex.acquire();
        if (numSillasLibres > 0) {
            numSillasLibres--;
            System.out.println(nombreCliente + " encontró silla y se sienta a esperar. Sillas libres: " + numSillasLibres);
            mutex.release();

            clientesEsperando.release();
            barberosDisponibles.acquire();
            return true;
        } else {
            System.out.println(nombreCliente + " llegó y NO hay sillas libres. Se marcha.");
            mutex.release();
            return false;
        }
    }

    public void siguienteCliente(String nombreBarbero) throws InterruptedException {
        clientesEsperando.acquire();
        mutex.acquire();
        numSillasLibres++;
        mutex.release();
        barberosDisponibles.release();
        System.out.println("--> " + nombreBarbero + " está afeitando a un cliente.");
    }
}
