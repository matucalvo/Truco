package truco.clases;

import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Mazo {
    Stack<Carta> mazo = new Stack<>();

    public Mazo() {
    }

    public void inicializarMazo(){
        ArrayList<Integer> numeros = new ArrayList<>();
        ArrayList<String> palos = new ArrayList<>();

        for (int i = 1; i < 13; i++) {
            if ((i != 8) && (i != 9) ) {
                numeros.add(i);
            }
        }

        palos.add("Espada");
        palos.add("Basto");
        palos.add("Oro");
        palos.add("Copa");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                Carta carta = new Carta(numeros.get(j), palos.get(i));
                mazo.add(carta);
            }

        }

    }

    public void mezclarMazo(){
        Stack<Carta> stackAux = new Stack<>();
        Stack<Carta> stackAux1 = new Stack<>();
        Random random = new Random();


        while (mazo.size() > 1){
            int aleatorio = random.nextInt(mazo.size());
            stackAux.add(mazo.remove(aleatorio));
            aleatorio = random.nextInt(mazo.size());
            stackAux1.add(mazo.remove(aleatorio));
            }

        while (!stackAux.isEmpty()){
            mazo.push(stackAux.pop());
            mazo.push(stackAux1.pop());
        }

    }

    public Carta repartir(){
        return mazo.pop();
    }

    public void mostrarMazo(){
        int contador = 0;
        for (Carta carta: mazo){
            contador++;
            System.out.println(contador + " :" + carta.getNumero() + " de " + carta.getPalo());
        }
    }





}
