package BINARIOS2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio10 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "nominas.bin";
    private static final String FICHERO_TEMP = "nominas_temp.dat";

    public static void main(String[] args) {
        File origen = new File(RUTA_FICHERO + NOMBRE_FICHERO);
        File temporal = new File(RUTA_FICHERO + FICHERO_TEMP);

        int dadosDeBaja = 0;

        try (DataInputStream leerFichero = new DataInputStream(new FileInputStream(origen));
             DataOutputStream escribirFichero = new DataOutputStream(new FileOutputStream(temporal))) {

            while (leerFichero.available() > 0) {
                String nombre = leerFichero.readUTF();
                int diasBaja = leerFichero.readInt();
                double nomina = leerFichero.readDouble();

                if (diasBaja == 0) {
                    nomina *= 1.05;
                    escribirFichero.writeUTF(nombre);
                    escribirFichero.writeInt(diasBaja);
                    escribirFichero.writeDouble(nomina);
                } else if (diasBaja >= 1 && diasBaja <= 3) {
                    escribirFichero.writeUTF(nombre);
                    escribirFichero.writeInt(diasBaja);
                    escribirFichero.writeDouble(nomina);
                } else if (diasBaja >= 4 && diasBaja <= 10) {
                    nomina *= 0.90;
                    escribirFichero.writeUTF(nombre);
                    escribirFichero.writeInt(diasBaja);
                    escribirFichero.writeDouble(nomina);
                } else {
                    dadosDeBaja++;
                }
            }

        } catch (IOException e) {
            System.out.println("Error durante la actualización.");
        }

        if (origen.delete()) {
            temporal.renameTo(origen);
        }

        try (DataInputStream leerFichero = new DataInputStream(new FileInputStream(origen))) {
            System.out.println("--- FICHERO NOMINAS.DAT ACTUALIZADO ---");
            while (leerFichero.available() > 0) {
                String nombre = leerFichero.readUTF();
                int diasBaja = leerFichero.readInt();
                double nomina = leerFichero.readDouble();
                System.out.println("Empleado: " + nombre + " | Días de baja: " + diasBaja + " | Nómina: " + nomina);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero actualizado.");
        }

        System.out.println("Total de empleados dados de baja (eliminados): " + dadosDeBaja);
    }
}
