package FICHEROS_ACCESO_ALEATORIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio4 {
    private static final String RUTA = "src/FICHEROS/";
    private static final String FICHERO = "juegos.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la edad para consultar los juegos recomendados: ");
        int edadBuscada = sc.nextInt();

        try (RandomAccessFile raf = new RandomAccessFile(RUTA + FICHERO, "r")) {
            System.out.println("Juegos recomendados para edad <= " + edadBuscada + ":");
            while (raf.getFilePointer() < raf.length()) {
                int orden = raf.readInt();

                char[] nom = new char[10];
                for (int i = 0; i < 10; i++) nom[i] = raf.readChar();

                int edad = raf.readInt();

                char[] tem = new char[10];
                for (int i = 0; i < 10; i++) tem[i] = raf.readChar();

                if (edad <= edadBuscada) {
                    System.out.println("Nombre: " + new String(nom).trim() + ", Temática: " + new String(tem).trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
        sc.close();
    }
}