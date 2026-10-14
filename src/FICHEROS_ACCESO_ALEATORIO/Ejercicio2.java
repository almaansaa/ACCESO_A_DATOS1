package FICHEROS_ACCESO_ALEATORIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio2 {
    private static final String RUTA = "src/FICHEROS/";
    private static final String FICHERO = "enteros.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un número entre 0 y 9: ");
        int numero = sc.nextInt();
        System.out.print("Introduce cuántas veces deseas añadirlo: ");
        int veces = sc.nextInt();

        try (RandomAccessFile raf = new RandomAccessFile(RUTA + FICHERO, "rw")) {
            // Situar el puntero al final del fichero para añadir registros
            raf.seek(raf.length());
            for (int i = 0; i < veces; i++) {
                raf.writeInt(numero);
            }
            System.out.println("Número añadido correctamente al final del fichero.");
        } catch (IOException e) {
            System.out.println("Error al acceder al fichero.");
        }
        sc.close();
    }
}