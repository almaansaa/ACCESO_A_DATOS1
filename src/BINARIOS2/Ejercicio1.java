package BINARIOS2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio1 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "num_aleat.bin";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        File directorio = new File(RUTA_FICHERO);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        System.out.print("¿Cuántos números aleatorios quieres generar?: ");
        int cantidad = sc.nextInt();
        System.out.print("Límite inferior del rango: ");
        int min = sc.nextInt();
        System.out.print("Límite superior del rango: ");
        int max = sc.nextInt();

        try (DataOutputStream escribirFichero = new DataOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO, true))) {
            for (int i = 0; i < cantidad; i++) {
                int numAleatorio = rand.nextInt(max - min + 1) + min;
                escribirFichero.writeInt(numAleatorio);
            }
            System.out.println("Números añadidos correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        try (DataInputStream leerFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            System.out.println("--- CONTENIDO FICHERO ---");
            while (leerFichero.available() > 0) {
                System.out.println(leerFichero.readInt());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}