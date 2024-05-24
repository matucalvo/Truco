package truco.clases;

public class Jugador {
    private String nombre = "";
    private Mano mano;
    private int numero = 0;
    private int puntajeTotal = 0;
    private boolean turno = false;
    private boolean esMano = false;

    public Jugador(String nombre, int numero){
        this.nombre = nombre;
        this.numero = numero;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Mano getMano() {
        return mano;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public boolean isEsMano() {
        return esMano;
    }

    public void setEsMano(boolean esMano) {
        this.esMano = esMano;
    }

    public void resetMano(){
        this.mano = null;
    }

    public void jugarCarta(Carta carta){
        mano.lanzarCarta(carta);
    }
}


