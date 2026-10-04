package BINARIOS2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Ejercicio9 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "Septemp.dat";

    public static void main(String[] args) {
        double maxTemp = Double.NEGATIVE_INFINITY;
        double minTemp = Double.POSITIVE_INFINITY;
        String horaMasCalurosa = "";
        String horaMasFria = "";
        double sumaTemp = 0;
        int contador = 0;
        int dia = 0;

        try (DataInputStream leerFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            while (leerFichero.available() > 0) {
                dia = leerFichero.readInt();
                String hora = leerFichero.readUTF();
                double temp = leerFichero.readDouble();

                if (temp > maxTemp) {
                    maxTemp = temp;
                    horaMasCalurosa = hora;
                }
                if (temp < minTemp) {
                    minTemp = temp;
                    horaMasFria = hora;
                }

                sumaTemp += temp;
                contador++;
            }

            if (contador > 0) {
                System.out.println("--- TEMPERATURAS DÍA " + dia + " ---");
                System.out.println("Temperatura Máxima: " + maxTemp + " a las " + horaMasCalurosa);
                System.out.println("Temperatura Mínima: " + minTemp + " a las " + horaMasFria);
                System.out.println("Temperatura Media:  " + (sumaTemp / contador));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }
    }
}
