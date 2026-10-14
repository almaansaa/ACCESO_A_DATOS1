package MULTIHILO;

public class EJ5ClientePeluqueria implements Runnable {
    private String nombre;
    private EJ5Peluqueria peluqueria;

    public EJ5ClientePeluqueria(String nombre, EJ5Peluqueria peluqueria) {
        this.nombre = nombre;
        this.peluqueria = peluqueria;
    }

    @Override
    public void run() {
        try {
            peluqueria.entrarCliente(nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
