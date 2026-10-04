package BINARIOS2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio6 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "datospersonas.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántas personas deseas guardar?: ");
        int n = sc.nextInt();
        sc.nextLine();

        try (DataOutputStream escribirFichero = new DataOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            for (int i = 0; i < n; i++) {
                System.out.println("Persona " + (i + 1) + ":");
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Apellidos: ");
                String apellidos = sc.nextLine();
                System.out.print("Edad: ");
                int edad = sc.nextInt(); sc.nextLine();
                System.out.print("Teléfono: ");
                String telefono = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Ciudad: ");
                String ciudad = sc.nextLine();
                System.out.print("Nacionalidad: ");
                String nacionalidad = sc.nextLine();
                System.out.print("Profesión: ");
                String profesion = sc.nextLine();

                escribirFichero.writeUTF(nombre);
                escribirFichero.writeUTF(apellidos);
                escribirFichero.writeInt(edad);
                escribirFichero.writeUTF(telefono);
                escribirFichero.writeUTF(email);
                escribirFichero.writeUTF(ciudad);
                escribirFichero.writeUTF(nacionalidad);
                escribirFichero.writeUTF(profesion);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        try (DataInputStream leerFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            System.out.println("--- DATOS PERSONAS ---");
            while (leerFichero.available() > 0) {
                System.out.println("Nombre: " + leerFichero.readUTF() + " " + leerFichero.readUTF() +
                        " | Edad: " + leerFichero.readInt() +
                        " | Telefono: " + leerFichero.readUTF() +
                        " | Email: " + leerFichero.readUTF() +
                        " | Ciudad: " + leerFichero.readUTF() +
                        " | Nacionalidad: " + leerFichero.readUTF() +
                        " | Profesion: " + leerFichero.readUTF());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}