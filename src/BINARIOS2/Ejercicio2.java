package BINARIOS2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "vehiculos.bin";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántos vehículos deseas introducir?: ");
        int numVehiculos = sc.nextInt();
        sc.nextLine();

        try (DataOutputStream escribirFichero = new DataOutputStream(
                new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO, true))) {
            for (int i = 0; i < numVehiculos; i++) {
                System.out.println("Vehículo " + (i + 1) + ":");
                System.out.print("Matrícula: ");
                String matricula = sc.nextLine();
                System.out.print("Marca: ");
                String marca = sc.nextLine();
                System.out.print("Tamaño del depósito: ");
                double deposito = sc.nextDouble();
                sc.nextLine();
                System.out.print("Modelo: ");
                String modelo = sc.nextLine();

                escribirFichero.writeUTF(matricula);
                escribirFichero.writeUTF(marca);
                escribirFichero.writeDouble(deposito);
                escribirFichero.writeUTF(modelo);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero");
        }

        try (DataInputStream leerFichero = new DataInputStream(
                new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            System.out.println("--- LISTA VEHÍCULOS ---");
            while (leerFichero.available() > 0) {
                String matricula = leerFichero.readUTF();
                String marca = leerFichero.readUTF();
                double deposito = leerFichero.readDouble();
                String modelo = leerFichero.readUTF();
                System.out.println("Matrícula: " + matricula + " | Marca: " + marca + " | Depósito: " + deposito + " | Modelo: " + modelo);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }
    }
}
