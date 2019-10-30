package edu.ia_parcial;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Define una busqueda DFS.
 */
public class BusquedaDFS
{
    /**
     * Funcion de inicializaciÃ³n
     *
     * @param ubicaciones
     *            - El estado inicial.
     */
    public static void buscar(char[] ubicaciones, boolean d)
    {
        NodoDeBusqueda raiz = new NodoDeBusqueda(new EstadoMonedas(ubicaciones));
        Stack<NodoDeBusqueda> pila = new Stack<NodoDeBusqueda>();

        pila.add(raiz);

        realizarBusqueda(pila, d);
    }

    /*
     * MÃ©todo de ayuda para revisar si un NodoDeBusqueda ya fue evaluado.
     * Returns true si ya fue evaluado, false en caso contrario.
     */
    private static boolean revisarRepetidos(NodoDeBusqueda n)
    {
        boolean resultado = false;
        NodoDeBusqueda nodoRevisado = n;

        // Mientras el padre de n no sea nulo, revisar si es igual al nodo
        // que estamos buscando.
        while (n.getPadre() != null && !resultado)
        {
            if (n.getPadre().getEstadoActual().igual(nodoRevisado.getEstadoActual()))
            {
                resultado = true;
            }
            n = n.getPadre();
        }

        return resultado;
    }

    /**
     * Realiza la Busqueda DFS usando pila como el espacio a buscar
     */
    public static void realizarBusqueda(Stack<NodoDeBusqueda> pila, boolean d)
    {
        int contadorBusqueda = 1; // contador para el nÃºmero de iteraciones

        while (!pila.isEmpty()) // mientras la pila no este vacÃ­a
        {
            System.out.print("Sale de la pila: ");
            NodoDeBusqueda nodoTemp = (NodoDeBusqueda) pila.pop();
            nodoTemp.getEstadoActual().mostrarEstado();
            // si el nodoTemp no es el estado meta
            if (!nodoTemp.getEstadoActual().esMeta())
            {
                // generar los sucesores inmediatos a nodoTemp
                ArrayList<Estado> sucesoresTemp = nodoTemp.getEstadoActual()
                        .generarSucesores();

                /*
                 * Iterar a travÃ©s de los sucesores, envolverlo en un
                 * NodoDeBusqueda, revisar si ya fue evaluado, y si no
                 * agregarlo a la cola.
                 */
                for (int i = sucesoresTemp.size()-1; i >= 0; i--)
                {
                    // segundo parametro que suma el costo del nuevo nodo al
                    // costo total actual en el NodoDeBusqueda
                    NodoDeBusqueda nuevoNodo = new NodoDeBusqueda(nodoTemp,
                            sucesoresTemp.get(i), nodoTemp.getCosto()
                            + sucesoresTemp.get(i).determinarCosto(), 0);

                    if (!revisarRepetidos(nuevoNodo))
                    {
                        pila.add(nuevoNodo);
                    }
                }
                contadorBusqueda++;
            }
            else
            // El estado meta se encontro. Imprimir el camino que lleva a este
            {
                // Use una pila para seguir el camino desde el estado inicial
                // al estado meta
                Stack<NodoDeBusqueda> caminoSolucion = new Stack<NodoDeBusqueda>();
                caminoSolucion.push(nodoTemp);
                nodoTemp = nodoTemp.getPadre();

                while (nodoTemp.getPadre() != null)
                {
                    caminoSolucion.push(nodoTemp);
                    nodoTemp = nodoTemp.getPadre();
                }
                caminoSolucion.push(nodoTemp);

                // El tamaÃ±o de la pila antes de iterarla y vaciarla.
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
                    System.out.println("El nÃºmero de nodos examinados: "
                            + contadorBusqueda);
                }

                System.exit(0);
            }
        }

        // No deberÃ­a ocurrir nunca.
        System.out.println("Error! SoluciÃ³n no encontrada!");
    }
}
