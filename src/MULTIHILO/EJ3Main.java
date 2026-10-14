package MULTIHILO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EJ3Main {
    public static void main(String[] args) throws InterruptedException {
        EJ3CuentaBancaria cuenta = new EJ3CuentaBancaria(100);
        ExecutorService executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 40; i++) executor.execute(new EJ3OperacionCuenta(cuenta, 100, true));
        for (int i = 0; i < 20; i++) executor.execute(new EJ3OperacionCuenta(cuenta, 50, true));
        for (int i = 0; i < 60; i++) executor.execute(new EJ3OperacionCuenta(cuenta, 20, true));

        for (int i = 0; i < 40; i++) executor.execute(new EJ3OperacionCuenta(cuenta, 100, false));
        for (int i = 0; i < 20; i++) executor.execute(new EJ3OperacionCuenta(cuenta, 50, false));
        for (int i = 0; i < 60; i++) executor.execute(new EJ3OperacionCuenta(cuenta, 20, false));

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Saldo final en la cuenta bancaria: " + cuenta.getSaldo() + " euros (Esperado: 100 euros)");
    }
}
