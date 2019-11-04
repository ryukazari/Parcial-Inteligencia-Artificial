package edu.ia_parcial;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Define una búsqueda BFS en un puzle validado.
 */
public class BusquedaAEstrella
{
    /**
     * Función de inicialización para búsqueda BFS en un puzle 8.
     *
     * @param tablero
     *            - El estado inicial, representado como un arreglo lineal
     *              de longitud 9 formando 3 meta-filas.
     * @param d
     *            true para mostrar nodos examinados
     */
    public static void buscar(char[] tablero, boolean d)
    {
        NodoDeBusquedaF raiz = new NodoDeBusquedaF(new EstadoMonedas(tablero));

        ListaOrdenadaSE<NodoDeBusquedaF> cola = new ListaOrdenadaSE<NodoDeBusquedaF>();

        cola.insertar(raiz);

        realizarBusqueda(cola, d);
    }

    /*
     * Método de ayuda para revisar si un NodoDeBusqueda ya fue evaluado.
     * Devuelve true si es así, false en caso contrario.
     */
    private static boolean revisarRepetidos(NodoDeBusquedaF n)
    {
        boolean resultado = false;
        NodoDeBusquedaF nodoARevisar = n;

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
    public static void realizarBusqueda( ListaOrdenadaSE<NodoDeBusquedaF> cola, boolean d)
    {
        int contBusqueda = 1; // contador para el número de iteraciones

        while (!cola.estaVacia()) // mientras la cola no este vacía
        {
            System.out.print("Sale de Cola:");
            NodoDeBusquedaF nodoTemp = (NodoDeBusquedaF) cola.eliminarAlInicio();
            System.out.print("( f = " + nodoTemp.getCostoF()+")  " );
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
                    // el segundo parametro aquí agrega el costo del nuevo
                    // nodo al costo actual total en el NodoDeBusqueda
                    NodoDeBusquedaF nuevoNodo = new NodoDeBusquedaF(nodoTemp,
                            sucesoresTemp.get(i),
                            nodoTemp.getCosto()+
                                    ((EstadoMonedas)nodoTemp.getEstadoActual()).
                                            costoCambioEstado((EstadoMonedas)sucesoresTemp.get(i)),
                            ((EstadoMonedas)sucesoresTemp.get(i)).getHeuristica());

                    if (!revisarRepetidos(nuevoNodo))
                    {
                        cola.insertar(nuevoNodo);
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
                Stack<NodoDeBusquedaF> caminoSolucion = new Stack<NodoDeBusquedaF>();
                caminoSolucion.push(nodoTemp);
                nodoTemp = nodoTemp.getPadre();

                while (nodoTemp.getPadre() != null)
                {
                    caminoSolucion.push(nodoTemp);
                    nodoTemp = nodoTemp.getPadre();
                }
                caminoSolucion.push(nodoTemp);

                // El tamaño de la pila antes de vaciarla.
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
                    System.out.println("Número de nodos examinados: "
                            + contBusqueda);
                }

                System.exit(0);
            }
        }

        // Esto no debería ocurrir.
        System.out.println("!Error! No se encontró una solución!");
    }


}


