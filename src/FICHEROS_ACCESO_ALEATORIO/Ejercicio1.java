package FICHEROS_ACCESO_ALEATORIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio1 {
    private static final String RUTA = "src/FICHEROS/";
    private static final String FICHERO = "enteros.dat";

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile(RUTA + FICHERO, "rw")) {
            raf.setLength(0); // Limpiar si ya existe

            // Escribir 6 veces el 1 y 10 veces el 2
            for (int i = 0; i < 6; i++) {
                raf.writeInt(1);
            }
            for (int i = 0; i < 10; i++) {
                raf.writeInt(2);
            }

            // Mostrar contenido inicial
            raf.seek(0);
            System.out.println("Contenido inicial del fichero:");
            while (raf.getFilePointer() < raf.length()) {
                System.out.print(raf.readInt() + " ");
            }
            System.out.println();

            // Solicitar número entre 0 y 9 por teclado
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce un número entre 0 y 9 para sustituir los 2: ");
            int nuevoValor = sc.nextInt();

            // Sustituir todos los 2 por el nuevo valor
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                long pos = raf.getFilePointer();
                int valor = raf.readInt();
                if (valor == 2) {
                    raf.seek(pos);
                    raf.writeInt(nuevoValor);
                }
            }

            // Mostrar resultado final
            raf.seek(0);
            System.out.println("Contenido tras la sustitución:");
            while (raf.getFilePointer() < raf.length()) {
                System.out.print(raf.readInt() + " ");
            }
            System.out.println();
            sc.close();
        } catch (IOException e) {
            System.out.println("Error al acceder al fichero.");
        }
    }
}
