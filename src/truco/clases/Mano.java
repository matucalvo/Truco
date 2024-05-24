package truco.clases;

import java.util.ArrayList;
import java.util.LinkedList;

public class Mano {
    private int contadorManos = 0;
    private int puntajeMano = 0;
    private LinkedList<Carta> mano = new LinkedList<>();

    public Mano() {
    }

    public int getPuntajeMano() {
        return puntajeMano;
    }

    public LinkedList<Carta> getMano() {
        return mano;
    }

    public int getContadorManos() {
        return contadorManos;
    }

    public void incrementarContador(){
        if (contadorManos > 2){
            this.contadorManos = 0;
        }

        this.contadorManos++;
    }

    public void agregarCarta(Carta carta){
        mano.add(carta);
    }

    public void mostrarMano(){
        int contador = 0;
        for (Carta carta: this.mano){
            contador++;
            System.out.println(contador + ":" + carta.getNumero() +  " de " + carta.getPalo());
        }
    }

    public boolean manoEsVacia(){
        return mano.isEmpty();
    }

    public Carta devolverCartaPorInidice(int indice){
        return mano.get(indice); // + 1 ?
    }

    public void aumentarPuntaje(int puntos){
        this.puntajeMano += puntos;
    }


    public void lanzarCarta(Carta carta) {
        int i = 0;
        for (Carta cartaEnLista: mano){
            i++;
            if (cartaEnLista.equals(carta)){
                mano.remove(i);
            }
        }
    }




}




