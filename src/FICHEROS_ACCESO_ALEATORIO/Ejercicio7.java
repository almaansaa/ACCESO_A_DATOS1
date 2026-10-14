package FICHEROS_ACCESO_ALEATORIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio7 {
    private static final String RUTA = "src/FICHEROS/";
    private static final String FICHERO_ORIGEN = "precio.dat";
    private static final String FICHERO_DESTINO = "descuento.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el % de descuento a aplicar: ");
        double descuento = sc.nextDouble();

        try (RandomAccessFile rafOrigen = new RandomAccessFile(RUTA + FICHERO_ORIGEN, "r");
             RandomAccessFile rafDestino = new RandomAccessFile(RUTA + FICHERO_DESTINO, "rw")) {

            rafDestino.setLength(0);

            while (rafOrigen.getFilePointer() < rafOrigen.length()) {
                char[] nom = new char[10];
                for (int i = 0; i < 10; i++) nom[i] = rafOrigen.readChar();

                char[] dni = new char[9];
                for (int i = 0; i < 9; i++) dni[i] = rafOrigen.readChar();

                int clases = rafOrigen.readInt();
                double cantidadAnterior = rafOrigen.readDouble();

                double cantidadConDescuento = cantidadAnterior * (1 - descuento / 100.0);

                rafDestino.writeChars(new String(dni));
                rafDestino.writeDouble(cantidadAnterior);
                rafDestino.writeDouble(cantidadConDescuento);
            }
            System.out.println("Fichero 'descuento.dat' creado correctamente.");


            System.out.println("--- CONTENIDO DE DESCUENTO.DAT ---");
            rafDestino.seek(0);
            while (rafDestino.getFilePointer() < rafDestino.length()) {
                char[] dni = new char[9];
                for (int i = 0; i < 9; i++) dni[i] = rafDestino.readChar();

                double ant = rafDestino.readDouble();
                double desc = rafDestino.readDouble();

                System.out.println("DNI: " + new String(dni).trim() + " | Anterior: " + ant + " € | Con descuento: " + desc + " €");
            }
        } catch (IOException e) {
            System.out.println("Error al procesar los ficheros.");
        }
        sc.close();
    }
}