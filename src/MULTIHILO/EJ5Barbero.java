package MULTIHILO;

public class EJ5Barbero implements Runnable {
    private String nombre;
    private EJ5Peluqueria peluqueria;

    public EJ5Barbero(String nombre, EJ5Peluqueria peluqueria) {
        this.nombre = nombre;
        this.peluqueria = peluqueria;
    }

    @Override
    public void run() {
        try {
            while (true) {
                peluqueria.siguienteCliente(nombre);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
