package FICHEROS_ACCESO_ALEATORIO;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio3 {
    private static final String RUTA = "src/FICHEROS/";
    private static final String FICHERO = "juegos.dat";

    public static void main(String[] args) {
        int[] ordenes = {1, 2, 3};
        String[] nombres = {"Zelda", "Tetris", "FIFA"};
        int[] edades = {12, 3, 3};
        String[] tematicas = {"Aventura", "Puzzle", "Deportes"};

        try (RandomAccessFile raf = new RandomAccessFile(RUTA + FICHERO, "rw")) {
            raf.setLength(0);
            for (int i = 0; i < ordenes.length; i++) {
                raf.writeInt(ordenes[i]);

                // Nombre de tamaño fijo (10 caracteres -> 20 bytes con writeChars)
                StringBuffer nombre = new StringBuffer(nombres[i]);
                nombre.setLength(10);
                raf.writeChars(nombre.toString());

                raf.writeInt(edades[i]);

                // Temática de tamaño fijo (10 caracteres -> 20 bytes)
                StringBuffer tematica = new StringBuffer(tematicas[i]);
                tematica.setLength(10);
                raf.writeChars(tematica.toString());
            }
            System.out.println("Registros de juegos guardados correctamente.");

            // Mostrar el fichero línea a línea
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                int orden = raf.readInt();

                char[] nom = new char[10];
                for (int i = 0; i < 10; i++) nom[i] = raf.readChar();

                int edad = raf.readInt();

                char[] tem = new char[10];
                for (int i = 0; i < 10; i++) tem[i] = raf.readChar();

                System.out.println("Orden: " + orden + ", Nombre: " + new String(nom).trim() +
                        ", Edad mínima: " + edad + ", Temática: " + new String(tem).trim());
            }
        } catch (IOException e) {
            System.out.println("Error en el acceso al fichero.");
        }
    }
}