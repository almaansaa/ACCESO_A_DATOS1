package MULTIHILO;

public class EJ2Almacen {
    private int stock;

    public EJ2Almacen(int stockInicial) {
        this.stock = stockInicial;
    }

    public synchronized boolean intentarComprar(String nombreCliente) {
        if (stock > 0) {
            stock--;
            System.out.println(nombreCliente + " consiguió entrar, cogió un producto. Stock restante: " + stock);
            return true;
        } else {
            System.out.println(nombreCliente + " entró, pero ya no quedan productos y se marchó.");
            return false;
        }
    }

    public synchronized int getStock() {
        return stock;
    }
}
