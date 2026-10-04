package BINARIOS2;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio8 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String TXT_ORIGEN = "temperaturas.txt";
    private static final String BIN_DESTINO = "Septemp.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el día de septiembre a filtrar (1-30): ");
        int diaBuscado = sc.nextInt();

        File txtOrigen = new File(RUTA_FICHERO + TXT_ORIGEN);
        if (!txtOrigen.exists()) {
            System.out.println("El fichero no existe");
            return;
        }

        try (Scanner lectorTxt = new Scanner(txtOrigen);
             DataOutputStream escribirFichero = new DataOutputStream(
                     new FileOutputStream(RUTA_FICHERO + BIN_DESTINO))) {

            while (lectorTxt.hasNext()) {
                int dia = lectorTxt.nextInt();
                String hora = lectorTxt.next();
                double temp = lectorTxt.nextDouble();

                if (dia == diaBuscado) {
                    escribirFichero.writeInt(dia);
                    escribirFichero.writeUTF(hora);
                    escribirFichero.writeDouble(temp);
                }
            }
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al procesar los ficheros.");
        }
    }
}
