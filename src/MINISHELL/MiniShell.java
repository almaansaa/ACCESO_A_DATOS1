package MINISHELL;

import java.io.IOException;
import java.util.Scanner;

public class MiniShell {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.print("Primer programa: ");
            String programa1 = teclado.nextLine();
            System.out.print("Segundo programa: ");
            String programa2 = teclado.nextLine();

            ProcessBuilder pb1 = new ProcessBuilder(programa1);
            ProcessBuilder pb2 = new ProcessBuilder(programa2);

            Process proceso1 = pb1.start();
            Process proceso2 = pb2.start();

            ProcessHandle handle1 = proceso1.toHandle();
            ProcessHandle handle2 = proceso2.toHandle();

            System.out.println("PID 1: " + handle1.pid());
            System.out.println("Activo 1: " + handle1.isAlive());
            System.out.println("PID 2: " + handle2.pid());
            System.out.println("Activo 2: " + handle2.isAlive());

            int codigo1 = proceso1.waitFor();
            int codigo2 = proceso2.waitFor();

            System.out.println("Código de salida 1: " + codigo1);
            System.out.println("Código de salida 2: " + codigo2);

        } catch (IOException e) {
            System.out.println("No se ha podido iniciar uno de los procesos.");
        } catch (InterruptedException e) {
            System.out.println("La espera ha sido interrumpida.");
        }

    }
}
