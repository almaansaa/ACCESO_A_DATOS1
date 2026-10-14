package FICHEROS_ACCESO_ALEATORIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio5 {
    private static final String RUTA = "src/FICHEROS/";
    private static final String FICHERO = "practicas.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el número de alumnos a registrar: ");
        int n = sc.nextInt();
        sc.nextLine();

        try (RandomAccessFile raf = new RandomAccessFile(RUTA + FICHERO, "rw")) {
            raf.setLength(0);
            for (int i = 0; i < n; i++) {
                System.out.println("\nAlumno " + (i + 1) + ":");

                System.out.print("Nombre (10 caracteres): ");
                StringBuffer sbNombre = new StringBuffer(sc.nextLine());
                sbNombre.setLength(10);
                raf.writeChars(sbNombre.toString());

                System.out.print("Apellidos (35 caracteres): ");
                StringBuffer sbApellidos = new StringBuffer(sc.nextLine());
                sbApellidos.setLength(35);
                raf.writeChars(sbApellidos.toString());

                System.out.print("DNI (9 caracteres - 8 dígitos y letra): ");
                StringBuffer sbDni = new StringBuffer(sc.nextLine());
                sbDni.setLength(9);
                raf.writeChars(sbDni.toString());

                System.out.print("Vehículo de prácticas (entero de 2 dígitos): ");
                raf.writeInt(sc.nextInt());

                System.out.print("Número de clases de prácticas (entero de 2 dígitos): ");
                raf.writeInt(sc.nextInt());
                sc.nextLine();
            }

            System.out.println("\n--- CONTENIDO DEL FICHERO PRACTICAS.DAT ---");
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                char[] nom = new char[10];
                for (int j = 0; j < 10; j++) nom[j] = raf.readChar();

                char[] apell = new char[35];
                for (int j = 0; j < 35; j++) apell[j] = raf.readChar();

                char[] d = new char[9];
                for (int j = 0; j < 9; j++) d[j] = raf.readChar();

                int vehiculo = raf.readInt();
                int clases = raf.readInt();

                System.out.println("Nombre: " + new String(nom).trim() + " | Apellidos: " + new String(apell).trim() +
                        " | DNI: " + new String(d).trim() + " | Vehículo: " + vehiculo + " | Clases: " + clases);
            }
        } catch (IOException e) {
            System.out.println("Error al acceder al fichero.");
        }
        sc.close();
    }
}