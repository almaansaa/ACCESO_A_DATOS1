package BINARIOS;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Ejercicio4 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "productos.dat";

    public static void main(String[] args) {
        Producto[] productos = {
                new Producto("Teclado Mecánico", 49.99, 15),
                new Producto("Ratón Óptico", 19.50, 30),
                new Producto("Monitor 24''", 129.90, 8)
        };

        try (ObjectOutputStream escribirFichero = new ObjectOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            for (Producto p : productos) {
                escribirFichero.writeObject(p);
            }
            System.out.println("Productos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        try (ObjectInputStream leerFichero = new ObjectInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            System.out.println("--- LISTA PRODUCTOS ---");
            while (true) {
                Producto p = (Producto) leerFichero.readObject();
                System.out.println(p);
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}