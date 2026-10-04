package BINARIOS2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Ejercicio5 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "datosbeca.bin";

    public static void main(String[] args) {
        try (DataInputStream leerFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            System.out.println("--- BECAS ASIGNADAS ---");
            while (leerFichero.available() > 0) {
                String nombre = leerFichero.readUTF();
                char sexo = leerFichero.readChar();
                int edad = leerFichero.readInt();
                int suspensos = leerFichero.readInt();
                String residencia = leerFichero.readUTF();
                double ingresos = leerFichero.readDouble();
                String tieneBeca = leerFichero.readUTF();

                if (suspensos >= 2) {
                    continue;
                }

                double cuantiaTotal = 1500;

                if (ingresos <= 12000) {
                    cuantiaTotal += 500;
                }

                if (edad < 23) {
                    cuantiaTotal += 200;
                }

                if (suspensos == 0) {
                    cuantiaTotal += 500;
                } else if (suspensos == 1) {
                    cuantiaTotal += 200;
                }

                if (residencia.equalsIgnoreCase("NO")) {
                    cuantiaTotal += 1000;
                }

                System.out.println("Becario: " + nombre + " | Cuantía total: " + cuantiaTotal);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}
