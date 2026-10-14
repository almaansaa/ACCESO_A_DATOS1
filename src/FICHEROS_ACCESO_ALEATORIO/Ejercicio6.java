package FICHEROS_ACCESO_ALEATORIO;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio6 {
    private static final String RUTA = "src/FICHEROS/";
    private static final String FICHERO_ORIGEN = "practicas.dat";
    private static final String FICHERO_DESTINO = "precio.dat";
    private static final double PRECIO_CLASE = 45.7;

    public static void main(String[] args) {
        try (RandomAccessFile rafOrigen = new RandomAccessFile(RUTA + FICHERO_ORIGEN, "r");
             RandomAccessFile rafDestino = new RandomAccessFile(RUTA + FICHERO_DESTINO, "rw")) {

            rafDestino.setLength(0);

            while (rafOrigen.getFilePointer() < rafOrigen.length()) {
                char[] nom = new char[10];
                for (int i = 0; i < 10; i++) nom[i] = rafOrigen.readChar();

                char[] apell = new char[35];
                for (int i = 0; i < 35; i++) apell[i] = rafOrigen.readChar();

                char[] dni = new char[9];
                for (int i = 0; i < 9; i++) dni[i] = rafOrigen.readChar();

                int vehiculo = rafOrigen.readInt();
                int clases = rafOrigen.readInt();

                double totalPagar = clases * PRECIO_CLASE;

                rafDestino.writeChars(new String(nom));
                rafDestino.writeChars(new String(dni));
                rafDestino.writeInt(clases);
                rafDestino.writeDouble(totalPagar);
            }
            System.out.println("Fichero 'precio.dat' creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al procesar los ficheros.");
        }
    }
}