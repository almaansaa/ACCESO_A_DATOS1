package BINARIOS2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio7 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String ORIGEN = "muchosdatos.bin";
    private static final String MENORES = "menores.dat";
    private static final String ADULTOS = "adultos.dat";
    private static final String MAYORES = "mayores.dat";

    public static void main(String[] args) {
        try (DataInputStream leerOrigen = new DataInputStream(new FileInputStream(RUTA_FICHERO + ORIGEN));
             DataOutputStream dosMenores = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + MENORES));
             DataOutputStream dosAdultos = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + ADULTOS));
             DataOutputStream dosMayores = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + MAYORES))) {

            while (leerOrigen.available() > 0) {
                String nombre = leerOrigen.readUTF();
                String apellidos = leerOrigen.readUTF();
                int edad = leerOrigen.readInt();
                String telefono = leerOrigen.readUTF();
                String email = leerOrigen.readUTF();
                String ciudad = leerOrigen.readUTF();
                String nacionalidad = leerOrigen.readUTF();
                String profesion = leerOrigen.readUTF();

                DataOutputStream destino;
                if (edad < 18) {
                    destino = dosMenores;
                } else if (edad <= 65) {
                    destino = dosAdultos;
                } else {
                    destino = dosMayores;
                }

                destino.writeUTF(nombre);
                destino.writeUTF(apellidos);
                destino.writeInt(edad);
                destino.writeUTF(telefono);
                destino.writeUTF(email);
                destino.writeUTF(ciudad);
                destino.writeUTF(nacionalidad);
                destino.writeUTF(profesion);
            }
            System.out.println("Clasificación completada con éxito.");

        } catch (IOException e) {
            System.out.println("Error durante la clasificación.");
        }

        mostrarFichero(MENORES);
        mostrarFichero(ADULTOS);
        mostrarFichero(MAYORES);
    }

    private static void mostrarFichero(String nombreFichero) {
        System.out.println("--- CONTENIDO DE "+nombreFichero+" ---");
        try (DataInputStream leerFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + nombreFichero))) {
            while (leerFichero.available() > 0) {
                System.out.println(leerFichero.readUTF() + " " + leerFichero.readUTF() + " - Edad: " + leerFichero.readInt());
                leerFichero.readUTF(); leerFichero.readUTF(); leerFichero.readUTF(); leerFichero.readUTF(); leerFichero.readUTF();
            }
        } catch (IOException e) {
            System.out.println("Error al leer " + nombreFichero);
        }
    }
}