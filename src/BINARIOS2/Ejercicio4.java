package BINARIOS2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio4 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "datosbeca.bin";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántos becarios deseas guardar?: ");
        int n = sc.nextInt();
        sc.nextLine();

        try (DataOutputStream escribirFichero = new DataOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO, true))) {
            for (int i = 0; i < n; i++) {
                System.out.println("Becario " + (i + 1) + ":");
                System.out.print("Nombre y apellidos: ");
                String nombre = sc.nextLine();
                System.out.print("Sexo (H/M): ");
                char sexo = sc.next().toUpperCase().charAt(0);
                System.out.print("Edad (20-60): ");
                int edad = sc.nextInt();
                System.out.print("Número de suspensos (0-4): ");
                int suspensos = sc.nextInt();
                sc.nextLine();
                System.out.print("Residencia familiar (SI/NO): ");
                String residencia = sc.nextLine().toUpperCase();
                System.out.print("Ingresos anuales: ");
                double ingresos = sc.nextDouble();
                sc.nextLine();
                System.out.print("Tiene beca (SI/NO): ");
                String tieneBeca = sc.nextLine().toUpperCase();

                escribirFichero.writeUTF(nombre);
                escribirFichero.writeChar(sexo);
                escribirFichero.writeInt(edad);
                escribirFichero.writeInt(suspensos);
                escribirFichero.writeUTF(residencia);
                escribirFichero.writeDouble(ingresos);
                escribirFichero.writeUTF(tieneBeca);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        try (DataInputStream leerFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            System.out.println("--- LISTA BECARIOS ---");
            while (leerFichero.available() > 0) {
                String nombre = leerFichero.readUTF();
                char sexo = leerFichero.readChar();
                int edad = leerFichero.readInt();
                int suspensos = leerFichero.readInt();
                String residencia = leerFichero.readUTF();
                double ingresos = leerFichero.readDouble();
                String tieneBeca = leerFichero.readUTF();

                System.out.println("Nombre: " + nombre + " | Sexo: " + sexo + " | Edad: " + edad +
                        " | Suspensos: " + suspensos + " | Residencia familiar: " + residencia +
                        " | Ingresos: " + ingresos + " | Beca: " + tieneBeca);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}
