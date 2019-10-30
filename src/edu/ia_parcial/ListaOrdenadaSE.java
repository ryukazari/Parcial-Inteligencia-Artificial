package edu.ia_parcial;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaOrdenadaSE<Tipo extends Comparable<Tipo>> implements Iterable<Tipo> {
    private int N;          // tamaÃ±o de la lista
    private Nodo inicio;     // inicio de la lista
    private Nodo fin;     // final de la lista

    private class Nodo {
        private Tipo item;
        private Nodo prox;

    }

    public ListaOrdenadaSE() {
        inicio = null;
        fin = null;
        N = 0;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public int tamanno() {
        return N;
    }

    public void insertarAlInicio(Tipo item) {
        Nodo inicioAnterior = inicio;
        //Nodo finAnterior = fin;
        if(N==0){
            inicio = fin = new Nodo();
        }
        else{
            inicio = new Nodo();
        }

        inicio.item = item;
        inicio.prox = inicioAnterior;
        N++;
    }

    public Tipo eliminarAlInicio() {
        if (estaVacia()) throw new NoSuchElementException("Lista VacÃ­a");
        Tipo item = inicio.item;        // guarda item a devolver
        if(N==1){
            fin = null;
        }
        inicio = inicio.prox;           // borra el primer nodo
        N--;
        return item;                    // devuelve el item guardado
    }
    public void insertarAlFinal(Tipo item){
        Nodo finAnterior = fin;
        if(N==0){
            fin=inicio= new Nodo();
        }
        else{
            fin= new Nodo();
            finAnterior.prox = fin;
        }
        fin.item = item;
        fin.prox = null;

        N++;
    }
    public Tipo eliminarAlFinal() {
        if (estaVacia()) throw new NoSuchElementException("Lista VacÃ­a");
        Tipo borrado;
        if(N==1){
            borrado=inicio.item;
            inicio=fin=null;
        }
        else{
            Nodo penultimo=null;
            for(Nodo x=inicio;x!=fin;x=x.prox){
                penultimo=x;
            }
            borrado = fin.item;
            penultimo.prox=null;
            fin=penultimo;
        }
        N--;
        return borrado;
    }
    //Devuelve el primer item pero no lo elimina
    public Tipo primero() {
        if (estaVacia()) throw new NoSuchElementException("Lista vacÃ­a");
        return inicio.item;
    }

    public Tipo ultimo() {
        if (estaVacia()) throw new NoSuchElementException("Lista vacÃ­a");
        return fin.item;
    }

    //Devuelve una representaciÃ³n de cadena de esta lista
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Tipo item : this)
            s.append(item + " ");
        return s.toString();
    }

    public Iterator<Tipo> iterator()  {
        return new IteradorInicioAlFinal();
    }

    private class IteradorInicioAlFinal implements Iterator<Tipo> {
        private Nodo actual = inicio;

        public boolean hasNext() {
            return actual != null;
        }

        public void remove(){ throw new UnsupportedOperationException();  }

        public Tipo next() {
            if (!hasNext()) throw new NoSuchElementException();
            Tipo item = actual.item;
            actual = actual.prox;
            return item;
        }
    }


    //Inserta los elementos ascendentemente
    public void insertar(Tipo item){
        //Nodo finAnterior = fin;
        if(N==0){
            fin=inicio= new Nodo();
            fin.item = item;
            fin.prox = null;
        }
        else if(N==1){
            if(item.compareTo(inicio.item)<0){
                inicio = new Nodo();
                inicio.item = item;
                inicio.prox = fin;
            }
            else{
                fin = new Nodo();
                fin.item = item;
                fin.prox = null;
                inicio.prox = fin;
            }
        }
        else{
            if(item.compareTo(inicio.item)<0){
                Nodo inicioAnterior = inicio;
                inicio = new Nodo();
                inicio.item = item;
                inicio.prox = inicioAnterior;
            }
            else if(item.compareTo(fin.item)>=0){
                Nodo finAnterior = fin;
                fin = new Nodo();
                fin.item = item;
                fin.prox = null;
                finAnterior.prox = fin;
            }
            else
            {
                Nodo previo = null;
//                Nodo siguiente = null;
                Nodo actual = inicio;
                while(item.compareTo(actual.item)>=0){
                    previo = actual;
                    actual = actual.prox;
//                    siguiente = actual.prox;
                }
                Nodo nuevo = new Nodo();
                nuevo.item = item;
                previo.prox = nuevo;
                nuevo.prox = actual;
            }
        }

        N++;
    }

    // comprobar invariantes internas
    public boolean revisar() {
        if (N == 0) {
            if (inicio != null) return false;
            if (fin != null) return false;
        }
        else if (N == 1) {
            if (inicio == null)      return false;
            if (fin == null)      return false;
            if (inicio.prox != null) return false;
            if (fin.prox != null) return false;
        }
        else {
            if (inicio.prox == null) return false;
            if (fin == null) return false;
            if (fin.prox != null) return false;
        }

        // revisar consistencia interna de variable N
        int numeroDeNodos = 0;
        for (Nodo x = inicio; x != null; x = x.prox) {
            numeroDeNodos++;
        }
        if (numeroDeNodos != N) return false;

        return true;
    }
}



