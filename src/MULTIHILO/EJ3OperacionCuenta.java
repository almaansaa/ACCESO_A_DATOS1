package MULTIHILO;

public class EJ3OperacionCuenta implements Runnable {
    private EJ3CuentaBancaria cuenta;
    private int cantidad;
    private boolean esIngreso;

    public EJ3OperacionCuenta(EJ3CuentaBancaria cuenta, int cantidad, boolean esIngreso) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.esIngreso = esIngreso;
    }

    @Override
    public void run() {
        if (esIngreso) {
            cuenta.ingresar(cantidad);
        } else {
            cuenta.retirar(cantidad);
        }
    }
}