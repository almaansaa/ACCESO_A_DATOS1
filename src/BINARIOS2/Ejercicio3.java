package BINARIOS2;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "datosbeca.bin";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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

        System.out.print("Ingresos anuales de la familia: ");
        double ingresos = sc.nextDouble();
        sc.nextLine();

        System.out.print("Tiene beca (SI/NO): ");
        String tieneBeca = sc.nextLine().toUpperCase();

        try (DataOutputStream escribirFichero = new DataOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            escribirFichero.writeUTF(nombre);
            escribirFichero.writeChar(sexo);
            escribirFichero.writeInt(edad);
            escribirFichero.writeInt(suspensos);
            escribirFichero.writeUTF(residencia);
            escribirFichero.writeDouble(ingresos);
            escribirFichero.writeUTF(tieneBeca);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }
    }
}