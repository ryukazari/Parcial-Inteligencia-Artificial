package edu.ia_parcial;

import java.util.ArrayList;
import java.util.Arrays;

public class EstadoMonedas implements Estado {
    private final char[] META = new char[]{ '_', 'R', 'A', 'R', 'A'}; /* META para el problema de las monedas */
    private int heuristica = 0;
    private int costo;
    private char[] posicionActual;
    private final int CANTIDAD = 5;

    public EstadoMonedas(char[] posiciones){
        posicionActual = posiciones;
        setHeuristica();
    }

    public int determinarCosto() {
        return 1;
    }

    private void setHeuristica(){
        int contadorHeuristica=0;
        int blanco=0;
        int blanco2=0;
        for (int i=0; i<5; i++){
            if(posicionActual[i]!=META[i]) contadorHeuristica++;
            if(posicionActual[i]=='_') blanco = i;
            if(META[i]=='_') blanco2=i;
        }
        heuristica=contadorHeuristica+Math.abs(blanco-blanco2);
    }

    public int getHeuristica(){
        return heuristica;
    }

    private char[] copiarPosiciones(char[] estado){
        char[] resultado = new char[CANTIDAD];
        for (int i = 0; i < CANTIDAD; i++){
            resultado[i] = estado[i];
        }
        return resultado;
    }

    @Override
    public ArrayList<Estado> generarSucesores() {
        ArrayList<Estado> sucesores = new ArrayList<Estado>();
        girar(sucesores);
        desplazar(sucesores);
        salto(sucesores);
        return sucesores;
    }


    private void girar(ArrayList<Estado> s){
        char[] temp;
        for (int i=0; i<CANTIDAD; i++){
            temp = copiarPosiciones(posicionActual);
            if(temp[i]=='R'){
                temp[i]='A';
                s.add(new EstadoMonedas(temp));
            }else if(temp[i]=='A'){
                temp[i]='R';
                s.add(new EstadoMonedas(temp));
            }
        }
    }

    private void desplazar(ArrayList<Estado> s){
        char[] tempIzq = copiarPosiciones(posicionActual);
        char[] tempDer = copiarPosiciones(posicionActual);
        int indice = -1;
        for (int i=0; i<CANTIDAD; i++){
            if(tempIzq[i] == '_') indice = i;
        }
        if(indice == 0){
            tempDer[indice] = tempDer[indice+1];
            tempDer[indice+1] = '_';
            s.add(new EstadoMonedas(tempDer));
        }else if (indice == 4){
            tempIzq[indice] = tempIzq[indice-1];
            tempIzq[indice-1] = '_';
            s.add(new EstadoMonedas(tempIzq));
        }else{
            tempIzq[indice] = tempIzq[indice-1];
            tempIzq[indice-1] = '_';
            s.add(new EstadoMonedas(tempIzq));
            tempDer[indice] = tempDer[indice+1];
            tempDer[indice+1] = '_';
            s.add(new EstadoMonedas(tempDer));
        }

    }

    private void salto(ArrayList<Estado> s){
        char[] tempIzq = copiarPosiciones(posicionActual);
        char[] tempDer = copiarPosiciones(posicionActual);
        int indice = -1;
        for (int i=0; i<5; i++){
            if(tempIzq[i] == '_') indice = i;
        }
        if(indice == 0 || indice == 1){
            if(tempDer[indice+2]=='R') tempDer[indice+2]='A';
            else tempDer[indice+2]='R';
            tempDer[indice] = tempDer[indice+2];
            tempDer[indice+2] = '_';
            s.add(new EstadoMonedas(tempDer));
        }
        if(indice == 4 || indice == 3){
            if(tempDer[indice-2]=='R') tempDer[indice-2]='A';
            else tempDer[indice-2]='R';
            tempDer[indice] = tempDer[indice-2];
            tempDer[indice-2] = '_';
            s.add(new EstadoMonedas(tempDer));
        }
        if(indice == 2){
            if(tempIzq[indice-2]=='R') tempIzq[indice-2]='A';
            else tempIzq[indice-2]='R';
            tempIzq[indice] = tempIzq[indice-2];
            tempIzq[indice-2] = '_';
            s.add(new EstadoMonedas(tempIzq));

            if(tempDer[indice+2]=='R') tempDer[indice+2]='A';
            else tempDer[indice+2]='R';
            tempDer[indice] = tempDer[indice+2];
            tempDer[indice+2] = '_';
            s.add(new EstadoMonedas(tempDer));
        }
    }



    @Override
    public boolean esMeta() {
        if (Arrays.equals(posicionActual, META)) return true;
        return false;
    }

    @Override
    public void mostrarEstado(){
        System.out.println(posicionActual[0] + " | " + posicionActual[1]
                + " | " + posicionActual[2] + " | " + posicionActual[3] + " | " + posicionActual[4]);
    }

    @Override
    public boolean igual(Estado s){
        if (Arrays.equals(posicionActual, ((EstadoMonedas) s).getPosicionActual()))
        {
            return true;
        }
        else
            return false;

    }

    public char[] getPosicionActual(){
        return posicionActual;
    }


    public double costoCambioEstado(EstadoMonedas otro){
        int tam = this.getPosicionActual().length;
        int blanco=0;
        int blanco2=0;
        boolean iguales = true;

        for (int i=0; i<tam && iguales; i++){
            if(posicionActual[i]=='_') blanco = i;
            if(otro.posicionActual[i]=='_') blanco2=i;
            if(this.getPosicionActual()[i] != otro.getPosicionActual()[i]){
                iguales = false;
            }
        }
        if(Math.abs(blanco-blanco2) == 2) return 2.0;
        else if (!iguales) return 1.0;
        else return 0;
    }
}
