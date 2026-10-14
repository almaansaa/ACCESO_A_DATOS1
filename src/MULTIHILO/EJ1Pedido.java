package MULTIHILO;

public class EJ1Pedido implements Runnable {
    private int numero;

    public EJ1Pedido(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        System.out.println("Preparando pedido " + numero + " en " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Pedido " + numero + " finalizado.");
    }
}
