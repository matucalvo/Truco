package truco.clases;

import java.util.List;
import java.util.Map;

public class Carta implements JerarquiasCartas {
    private int numero = 0;
    private String palo = "";

    private int jerarquia;

    public Carta(int numero, String palo){
        this.numero = numero;
        this.palo = palo;
        this.jerarquia = calcularJerarquia();

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public String getPalo() {
        return palo;
    }

    private int calcularJerarquia(){
        for (Map.Entry<Integer, Map<String, List<String>>> entry : JERARQUIA.entrySet()) {
            Integer claveExterna = entry.getKey();
            Map<String, List<String>> valorExterno = entry.getValue();

            for (Map.Entry<String, List<String>> subEntry : valorExterno.entrySet()) {
                String numero = subEntry.getKey();
                List<String> palos = subEntry.getValue();

                if (palos.contains(getPalo()) && Integer.toString(getNumero()).equals(numero)) {
                    return claveExterna;
                }
            }
        }
        return 0;
    }

    public int getJerarquia(){
        return jerarquia;
    }
    public Carta getCarta(){
        return (new Carta(numero,palo));
    }

    public void mostrarCarta(){
        System.out.println(getNumero() + " de " + getPalo());
    }


}


