package edu.ia_parcial;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Define una bÃºsqueda BFS en un puzle validado.
 */
public class BusquedaBFS
{
    /**
     * FunciÃ³n de inicializaciÃ³n para el problema de los canibales.
     *
     * @param ubicaciones
     *            - El estado inicial representando el arreglo con la cantidad
     *              de misioneros a la izquierda, cantidad de canÃ­bales a la
     *              izquierda, 0 si el bote esta a la izquierda o 1 si esta a
     *              derecha.
     * @param d
     *            true para mostrar nodos examinados
     */
    public static void buscar(char[] ubicaciones, boolean d)
    {
        NodoDeBusqueda raiz = new NodoDeBusqueda(new EstadoMonedas(ubicaciones));
        Queue<NodoDeBusqueda> cola = new LinkedList<NodoDeBusqueda>();

        cola.add(raiz);

        realizarBusqueda(cola, d);
    }

    /*
     * MÃ©todo de ayuda para revisar si un NodoDeBusqueda ya fue evaluado.
     * Devuelve true si es asÃ­, false en caso contrario.
     */
    private static boolean revisarRepetidos(NodoDeBusqueda n)
    {
        boolean resultado = false;
        NodoDeBusqueda nodoARevisar = n;

        // Mientras el padre de n no sea null, revisar si este es igual
        // al nodo que estamos buscando.
        while (n.getPadre() != null && !resultado)
        {
            if (n.getPadre().getEstadoActual().igual(nodoARevisar.getEstadoActual()))
            {
                resultado = true;
            }
            n = n.getPadre();
        }

        return resultado;
    }

    /**
     * Realiza una busqueda BFS usando cola como el espacio a buscar
     *
     * @param cola
     *            - La cola NodoDeBusqueda a ser buscada.
     * @param d
     *            true para mostrar nodos examinados
     */
    public static void realizarBusqueda(Queue<NodoDeBusqueda> cola, boolean d)
    {
        int contBusqueda = 1; // contador para el nÃºmero de iteraciones

        while (!cola.isEmpty()) // mientras la cola no este vacÃ­a
        {
            System.out.print("Sale de Cola: " );
            NodoDeBusqueda nodoTemp = (NodoDeBusqueda) cola.poll();
            nodoTemp.getEstadoActual().mostrarEstado();
            if (!nodoTemp.getEstadoActual().esMeta()) // si nodoTemp no es una meta
            {
                // generar sucesores inmediatos a nodoTemp
                ArrayList<Estado> sucesoresTemp = nodoTemp.getEstadoActual().generarSucesores();

                /*
                 * Recorrer los sucesores, envolverlos en un NodoDeBusqueda,
                 * comprobar si ya han sido evaluados, y si no, agregarlos a
                 * la cola
                 */
                for (int i = 0; i < sucesoresTemp.size(); i++)
                {
                    // el segundo parametro aquÃ­ agrega el costo del nuevo
                    // nodo al costo actual total en el NodoDeBusqueda
                    NodoDeBusqueda nuevoNodo = new NodoDeBusqueda(nodoTemp,
                            sucesoresTemp.get(i), nodoTemp.getCosto()
                            + sucesoresTemp.get(i).determinarCosto(), 0);

                    if (!revisarRepetidos(nuevoNodo))
                    {
                        cola.add(nuevoNodo);
                    }
                }
                contBusqueda++;
            }
            else
            // El estado meta ha sido encontrado. Mostrar el camino para llegar
            // a este
            {
                // Use una pila para rastrear el camino desde el estado inicial
                // hasta el estado meta.
                Stack<NodoDeBusqueda> caminoSolucion = new Stack<NodoDeBusqueda>();
                caminoSolucion.push(nodoTemp);
                nodoTemp = nodoTemp.getPadre();

                while (nodoTemp.getPadre() != null)
                {
                    caminoSolucion.push(nodoTemp);
                    nodoTemp = nodoTemp.getPadre();
                }
                caminoSolucion.push(nodoTemp);

                // El tamaÃ±o de la pila antes de vaciarla.
                int iteraciones = caminoSolucion.size();

                for (int i = 0; i < iteraciones; i++)
                {
                    nodoTemp = caminoSolucion.pop();
                    nodoTemp.getEstadoActual().mostrarEstado();
                    System.out.println();
                    System.out.println();
                }
                System.out.println("El costo fue: " + nodoTemp.getCosto());
                if (d)
                {
                    System.out.println("NÃºmero de nodos examinados: "
                            + contBusqueda);
                }

                System.exit(0);
            }
        }

        // Esto no deberÃ­a ocurrir.
        System.out.println("!Error! No se encontrÃ³ una soluciÃ³n!");
    }
}
