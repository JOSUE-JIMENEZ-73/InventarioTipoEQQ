
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
        int opcion;

        do {

            System.out.println("Seleccione el tipo de inventario: ");
            System.out.println("1. Inventario sin descuento ");
            System.out.println("2. Inventario con descuento ");
            System.out.println("3. Salir ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
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
                    System.out.println("La cantidad optima del pedido es : " + y);

                    // tiempo de ciclo entre pedidos
                    to = y / d;
                    to = (int) (to * 100.0f) / 100.0f;
                    System.out.println("Tiempo entre pedido: " + to);

                    // numero de ciclos de pedido
                    n = L / to;
                    int n_truncado = (int) n;
                    System.out.println("La cantidad de ciclos de pedidos: " + n_truncado);

                    // punto de reorden
                    le = (float) L - (n_truncado * to);
                    le = Math.round(le * 1000.0f) / 1000.0f;
                    System.out.println("Punto de reorden de: " + le);

                    System.out.println("El punto de reorden: " + ((int) (le * d)));
                    // Costo total de inventario
                    TCU = ((h * y / 2) + (k * (d / y)));
                    TCU = Math.round(TCU * 1000.0f) / 1000.0f;
                    System.out.println("Costo total de inventario: " + TCU);
                }
                    break;
                case 2: {
                    System.out.println("Demanda diaria: ");
                    d = sc.nextFloat();
                    System.out.println("Costo por pedido: ");
                    k = sc.nextFloat();
                    System.out.println("porcentaje de descuento (%): ");
                    float p = sc.nextFloat();
                    System.out.println("Cuantos rangos de precio tiene?");
                    int rangos = sc.nextInt();

                    float[] costosUnitarios = new float[rangos];
                    float[] costosAlcacenamiento = new float[rangos];
                    float[] cantidadesOptimas = new float[rangos];
                    float[] CC = new float[rangos];
                    float[] CO = new float[rangos];
                    float[] CM = new float[rangos];
                    float[] CTU = new float[rangos];

                    // Pedir los costos unitarios y calcular los costos de almacenamiento
                    System.out.println("Ingrese los costos unitarios: ");

                    for (int i = 0; i < costosUnitarios.length; i++) {
                        System.out.print("Costo unitario del rango " + (i + 1) + ": ");
                        costosUnitarios[i] = sc.nextFloat();
                        costosAlcacenamiento[i] = (p / 100) * costosUnitarios[i];
                    }

                    // Calcular y*
                    for (int i = 0; i < cantidadesOptimas.length; i++) {
                        cantidadesOptimas[i] = (float) Math.sqrt((2 * k * d) / costosAlcacenamiento[i]);
                    }

                    // Calcular CC, CO, CM, CTU
                    for (int i = 0; i < CTU.length; i++) {
                        CC[i] = d * costosUnitarios[i];
                        CO[i] = k * d / cantidadesOptimas[i];
                        CM[i] = costosAlcacenamiento[i] * cantidadesOptimas[i] / 2;
                        CTU[i] = CC[i] + CO[i] + CM[i];
                    }

                    // Encontrar la mejor opcion
                    float minCTU = CTU[0];
                    int mejorOpcion = 0;

                    for (int i = 1; i < CTU.length; i++) {
                        if (CTU[i] < minCTU) {
                            minCTU = CTU[i];
                            mejorOpcion = i;
                        }
                    }

                    // mostrar resultados
                    System.out.println("La mejor opcion es el rango " + (mejorOpcion + 1));
                    System.out.println("Cantidad optima de pedido: " + cantidadesOptimas[mejorOpcion]);
                    System.out.println("Costo total de inventario: " + minCTU);

                    // Calcular to, n, le, TCU de la mejor opcion
                    y = cantidadesOptimas[mejorOpcion];
                    h = costosAlcacenamiento[mejorOpcion];

                    // tiempo de ciclo entre pedidos
                    to = y / d;
                    to = (int) (to * 100.0f) / 100.0f;
                    System.out.println("Tiempo entre pedido: " + to);

                    // numero de ciclos de pedido
                    System.out.println("Tiempo de entrega en dias: ");
                    L = sc.nextInt();
                    n = L / to;
                    int n_truncado = (int) n;
                    System.out.println("La cantidad de ciclos de pedidos: " + n_truncado);

                    // Punto de reorden
                    le = (float) L - (n_truncado * to);
                    le = Math.round(le * 1000.0f) / 1000.0f;
                    System.out.println("tiempo restante: " + le + " dias");

                    int puntoReorden = (int) (le * d);
                    System.out.println("Punto de reorden: " + puntoReorden + " unidades");

                    // TCU usando la formula tradicional
                    TCU = ((h * y / 2) + (k * (d / y)));
                    TCU = Math.round(TCU * 100.0f) / 100.0f;
                    System.out.println("Costo total de inventario: " + TCU);

                }
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 3);
        sc.close();

    }
}