package edu.ia_parcial;

public class NodoDeBusquedaH implements Comparable<NodoDeBusquedaH> {
    private Estado estadoActual;    /* estado actual */
    private NodoDeBusquedaH padre;   /* nodo padre */
    private double costo;           /* costo para llegar a este estado */
    private double costoHeu;        /* costo heurÃ­stico */
    private double costoF;          /* costo f(n) */

    /**
     * Constructor para la raiz NodoDeBusqueda
     *
     * @param e el estado pasado
     */
    public NodoDeBusquedaH(Estado e) {
        estadoActual = e;
        padre = null;
        costo = 0;
        costoHeu = 0;
        costoF = 0;
    }

    /**
     * Constructor para todos los otros NodoDeBusqueda
     *
     * @param prev el nodo padre
     * @param e el estado
     * @param c el costo g(n) para llegar a este nodo
     * @param h el costo h(n) para obtener este nodo
     */
    public NodoDeBusquedaH(NodoDeBusquedaH prev, Estado e, double c, double h) {
        padre = prev;
        estadoActual = e;
        costo = c;
        costoHeu = h;
        costoF = costo + costoHeu;
    }

    /**
     * @return el estadoActual
     */
    public Estado getEstadoActual() {
        return estadoActual;
    }

    /**
     * @return el padre
     */
    public NodoDeBusquedaH getPadre() {
        return padre;
    }

    /**
     * @return el costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     *
     * @return el costo heurÃ­stico
     */
    public double getCostoHeu() {
        return costoHeu;
    }

    /**
     *
     * @return el costo f(n) para A*
     */
    public double getCostoF() {
        return costoF;
    }

    @Override
    public int compareTo(NodoDeBusquedaH o) {

        if(this.costoHeu > o.costoHeu)        return 1;
        else if( this.costoHeu < o.costoHeu)  return -1;
        else                            return 0;
    }
}
