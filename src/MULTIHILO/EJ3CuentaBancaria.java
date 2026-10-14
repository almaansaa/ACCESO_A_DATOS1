package MULTIHILO;

public class EJ3CuentaBancaria {
    private int saldo;

    public EJ3CuentaBancaria(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void ingresar(int cantidad) {
        saldo += cantidad;
    }

    public synchronized void retirar(int cantidad) {
        saldo -= cantidad;
    }

    public synchronized int getSaldo() {
        return saldo;
    }
}
