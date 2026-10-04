package BINARIOS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio2 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "alumnos.dat";

    public static void main(String[] args) {
        String[] nombres = {"Ana", "Carlos", "Elena", "David", "Beatriz"};
        double[] notas = {8.5, 6.75, 9.2, 5.5, 7.8};

        try (DataOutputStream escribirFichero = new DataOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            for (int i = 0; i < 5; i++) {
                escribirFichero.writeUTF(nombres[i]);
                escribirFichero.writeDouble(notas[i]);
            }
            System.out.println("Información de los alumnos guardada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        try (DataInputStream leerFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            System.out.println("--- LISTA ALUMNOS ---");
            for (int i = 0; i < 5; i++) {
                String nombre = leerFichero.readUTF();
                double nota = leerFichero.readDouble();
                System.out.println("Nombre: " + nombre + ", Nota media: " + nota);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}
