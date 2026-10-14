package MULTIHILO;

public class EJ4Main {
    public static void main(String[] args) {
        EJ4ColaLimitada cola = new EJ4ColaLimitada(5);

        Thread p = new Thread(new EJ4Productor(cola), "Productor-1");
        Thread c = new Thread(new EJ4Consumidor(cola), "Consumidor-1");

        p.start();
        c.start();
    }
}
