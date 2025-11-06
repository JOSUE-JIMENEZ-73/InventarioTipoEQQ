
/*
 * d (Demanda)
 * k (costoPedido)
 * h (Costo de almaenamiento)
 * y (Cantidad optima de pedido)
 * L (Tiempo de entrega)
 * to (tiempo entre pedido)
 * n (numero de ciclos de pedido)
 * le (Punto de reorden)
 * TCU (Costo total de inventario)
 */
import java.util.Scanner;

public class InventarioTipoEQQ {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int d, k, L, TCU;
        float to, y, h, n, le;

        System.out.print("¿Cual es el costo por pedido?");
        k = sc.nextInt();
        System.out.print("¿Cual es la Demanda diaria del producto? ");
        d = sc.nextInt();
        System.out.print("¿Cual es el costo de almacenamiento? ");
        h = sc.nextFloat();
        System.out.print("¿Cual es el tiempo de entrega en dias? ");
        L = sc.nextInt();

        // Formula para calcular la cantidad optima de pedido
        y = (float) Math.sqrt((2 * k * d) / h);
        System.out.println("La cantidad optima de pedido es de: " + y + " unidades");

        // tiempo de ciclo entre pedidos
        to = y / d;
        System.out.println("El tiempo entre pedidos es de: " + to + " dias");

        // numero de ciclos de pedido
        n = L /  to;
        System.out.println("El numero de ciclos de pedido es de: " + n + " ciclos");

        // punto de reorden
        le= L-(n*to);
        System.out.println("El punto de reorden es de: " + le + " unidades");
        // Costo total de inventario
        TCU = (int) ((h * y / 2) + (k * (d / y)));
        System.out.println("El costo total de inventario es de: $" + TCU);
        sc.close();
        


    }
}