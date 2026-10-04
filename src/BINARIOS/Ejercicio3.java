package BINARIOS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Ejercicio3 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "producto.dat";

    public static void main(String[] args) {
        Producto producto = new Producto("Teclado Mecánico", 49.99, 15);

        try (ObjectOutputStream escribirFichero = new ObjectOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            escribirFichero.writeObject(producto);
            System.out.println("Producto guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        try (ObjectInputStream leerFichero = new ObjectInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            Producto productoLeido = (Producto) leerFichero.readObject();
            System.out.println("Producto recuperado:");
            System.out.println(productoLeido);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}
