
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
        float d, k, L;
        float to, y, h, n, le, TCU;

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
        y = (int) (y * 1000.0f) / 1000.0f;
        System.out.println("y: "+y);

        // tiempo de ciclo entre pedidos
        to = y / d;
        to = (int) (to * 100.0f) / 100.0f;
        System.out.println("to: "+to);

        // numero de ciclos de pedido
        n = L /  to;
        int n_truncado = (int) n;
        System.out.println("n: " + n_truncado );
        
        // punto de reorden
        le=(float) L - (n_truncado*to);
        le = Math.round(le * 1000.0f) / 1000.0f;
        System.out.println("le: "+ le);

        System.out.println("El punto de reorden: " + ((int) (le*d)));
        // Costo total de inventario
        TCU =  ((h * y / 2) + (k * (d / y)));
        TCU = Math.round(TCU * 1000.0f) / 1000.0f;
        System.out.println("TCU: " + TCU);
        sc.close();
        


    }
}