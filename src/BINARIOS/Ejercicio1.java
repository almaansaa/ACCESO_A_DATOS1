package BINARIOS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio1 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "numeros.dat";

    public static void main(String[] args) {
        try (DataOutputStream escribirFichero = new DataOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            for (int i = 1; i <= 50; i++) {
                escribirFichero.writeInt(i);
            }
            System.out.println("Números del 1 al 50 guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        try (DataInputStream leerFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            System.out.println("Lectura de los números del fichero:");
            for (int i = 1; i <= 50; i++) {
                int numero = leerFichero.readInt();
                System.out.println(numero);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}
