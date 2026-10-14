package MULTIHILO;

import java.util.Random;

public class EJ2Cliente implements Runnable {
    private String nombre;
    private EJ2Almacen almacen;
    private Random random = new Random();

    public EJ2Cliente(String nombre, EJ2Almacen almacen) {
        this.nombre = nombre;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        int intentos = 0;
        boolean puertaEncontradaLibre = false;

        while (intentos < 10 && !puertaEncontradaLibre) {
            intentos++;

            try {
                synchronized (almacen) {
                    puertaEncontradaLibre = true;
                    almacen.intentarComprar(nombre);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!puertaEncontradaLibre) {
                try {
                    Thread.sleep(random.nextInt(100) + 50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (!puertaEncontradaLibre && intentos >= 10) {
            System.out.println(nombre + " agotó sus 10 intentos sin encontrar la puerta libre y desistió.");
        }
    }
}