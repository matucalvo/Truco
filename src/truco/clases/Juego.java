package truco.clases;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Juego {
    private Mazo mazo = new Mazo();
    private Carta matriz_turnos [][] = new Carta[3][2];
    private int turno = 0;
    private LinkedList<Jugador> jugadores = new LinkedList<>();

    public Juego() {
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void agregarJugador(Jugador jugador) {
        if (jugadores.size() > 2) {
            System.out.println("No se pueden agregar mas de 2 jugadores");
            return;
        }
        jugadores.add(jugador);
    }



    public LinkedList<Jugador> getJugadores() {
        return jugadores;
    }

    public int getLenLista() {
        return jugadores.size();
    }

    public void aumentarTurno() {
        if (turno == 2) {
            this.turno = 0;
        }

        this.turno++;
    }

    public void mostrarJugadores() {
        System.out.println("Lista jugadores: \n");
        for (Jugador jugador : jugadores) {
            System.out.println("jugador " + jugador.getNumero() + ": " + jugador.getNombre());
        }
    }

    public void verificarTurnoJugadores() {
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 2; columna++) {
                if (matriz_turnos[fila][columna] == null) {
                    return;
                }
            }
        }
        aumentarTurno();
    }

    public Jugador getJugadorPorNumero(int numero) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNumero() == numero) {
                return jugador;
            }
        }

        return null;
    }

    public Jugador getJugadorConTurno() {
        for (Jugador jugador : jugadores) {
            if (jugador.isTurno()) {
                return jugador;
            }
        }
        return null;
    }

    public Jugador getJugadorConMano() {
        for (Jugador jugador : jugadores) {
            if (jugador.isEsMano()) {
                return jugador;
            }
        }
        return null;
    }

    public void resetTurno(){
        this.turno = 0;
        resetManos();
        resetearMatriz();
        resetMazo();
        generarMazo();
    }

    public Carta definirCartaGanadora(Carta carta1, Carta carta2) {
        if (carta1.getJerarquia() > carta2.getJerarquia()) {
            return carta1;
        } else if (carta2.getJerarquia() > carta1.getJerarquia()) {
            return carta2;
        }
        else return null;

    }

    public Jugador jugadorGanaTurno(Carta cartaGanadora) {
        Carta carta = null;
        int numeroJugador = 0;
        int numeroJugadorPerdedor = 0;
        if (cartaGanadora != null) {
            for (int fila = 0; fila < 3; fila++) {
                for (int columna = 0; columna < 2; columna++) {
                    if (matriz_turnos[fila][columna] != null) {
                        carta = matriz_turnos[fila][columna];
                    }
                    if (compararCartasIguales(carta, cartaGanadora)) {
                        numeroJugador = columna + 1;
                        break;
                    }
                }
            }
        } else numeroJugador = getJugadorConPrimera().getNumero();

        if (numeroJugador == 1) {
            numeroJugadorPerdedor = numeroJugador + 1;
        } else numeroJugadorPerdedor = numeroJugador - 1;

        Jugador jugador = getJugadorPorNumero(numeroJugador);
        getJugadorPorNumero(numeroJugadorPerdedor).setTurno(false);
        jugador.setTurno(true);

        System.out.println(" Gano el jugador " + numeroJugador);
        return jugador;
    }

    public boolean compararCartasIguales(Carta carta, Carta cartaGanadora) {
        return (carta.getNumero() == cartaGanadora.getNumero()) && (carta.getPalo().equals(cartaGanadora.getPalo()));
    }


    public Jugador getJugadorConPrimera() {
        Carta carta1 = matriz_turnos[0][0];
        Carta carta2 = matriz_turnos[0][1];
        if (carta1.getJerarquia() > carta2.getJerarquia()){
            return getJugadorPorNumero(1);
        } else if (carta2.getJerarquia() > carta1.getJerarquia()){
            return getJugadorPorNumero(2);
        } else return null;
    }

    public void aumentarContadorJugadorGanador(Jugador jugador) {
        Mano manoJugador = jugador.getMano();
        manoJugador.incrementarContador();
        jugador.setMano(manoJugador);
    }

    public Object procesarTurno(int turnoActual){
        Carta carta1 = null;
        Carta carta2 = null;
        if (matriz_turnos[turnoActual][0] != null){  // guardo carta que lanzo jugador 1
            carta1 = matriz_turnos[turnoActual][0];

        }
        if (matriz_turnos[turnoActual][1] != null){  // guardo carta que lanzo jugador 2
            carta2 = matriz_turnos[turnoActual][1];
        }

        Carta cartaGanadora = definirCartaGanadora(carta1,carta2); // defino cual gana


        if (cartaGanadora == null){  // si empatan, gana el jugador con primera
            if (turno != 0) {
                aumentarContadorJugadorGanador(getJugadorConPrimera());
            }
            return null;
        }

        aumentarContadorJugadorGanador(jugadorGanaTurno(cartaGanadora));
        return null;

    }



    public void agregarCartaMatriz(Jugador jugador, Carta carta){
        verificarTurnoJugadores();
        matriz_turnos[turno][jugador.getNumero()-1] = carta;
    }

    public void repartirJugadores(){
        int contador = 1;
        while (contador <= 3){
            for (Jugador jugador: jugadores){
                Mano manoJugador = jugador.getMano();
                if (manoJugador == null){
                    manoJugador = new Mano();
                }
                manoJugador.agregarCarta(mazo.repartir());
                jugador.setMano(manoJugador);
            }
            contador++;
        }
    }

    public void resetearMatriz(){
        this.matriz_turnos = new Carta[3][2];
    }

    public void mostrarMatriz(){
        for (int fila = 0; fila < 3; fila++) {
            System.out.println();
            for (int columna = 0; columna < 2; columna++) {
                if (matriz_turnos[fila][columna] != null){
                    matriz_turnos[fila][columna].mostrarCarta();
                }
            }
        }
    }

    public Jugador definirGanadorRonda(){
        for (Jugador jugador: jugadores){
            Mano manoJugador = jugador.getMano();
            if (manoJugador.getContadorManos() > 1){
                jugador.setPuntajeTotal(1);
                return jugador;
            }
        }
        return null;
    }

    public void resetManos(){
        for (Jugador jugador: jugadores){
            jugador.resetMano();
        }
    }

    public void quitarTurnoYDarseloAlSiguiente(Jugador jugador){
        jugador.setTurno(false);  // le quito el turno al jugador
        if (jugador.getNumero() == 1){
            jugadores.get(1).setTurno(true);  // si es el jugador 1 le doy el turno al 2
        } else jugadores.get(0).setTurno(true); // si es el jugador 2 le doy el turno al 1
    }

    public boolean verificarSiSeEmpatoPrimerMano(){
        Carta carta1 = matriz_turnos[0][0];
        Carta carta2 = matriz_turnos[0][1];
        return carta1.getJerarquia() == carta2.getJerarquia();
    }

    public void generarMazo(){
        mazo.inicializarMazo();
        mazo.mezclarMazo();
    }

    public void resetMazo(){
        mazo = new Mazo();
    }

    public boolean verificarSiGanoAlgunJugador(){
        for (Jugador jugador: jugadores){
            if (jugador.getPuntajeTotal() > 14){ // si algun jugador tiene 15 puntos, devuelve true
                System.out.println("Ganador: " + jugador.getNombre());
                return true;
            }
        }
        return false; // si todavia no se alcanzaron los 15 puntos, devuelve false
    }

    public Object definirPrimerTurno(AtomicBoolean primerTurno){
        boolean valorPrimerTurno = primerTurno.get();
        if (!valorPrimerTurno){  // si no es el primer turno, finaliza la ejecucion
            return null;
        }

        primerTurno.set(false);
        if (jugadores.get((0)).isEsMano()){
            jugadores.get(0).setTurno(false);
            jugadores.get(1).setTurno(true);

        }
        else {
            jugadores.get(1).setTurno(false);
            jugadores.get(0).setTurno(true);
        }

        return null;
    }

    public void definirManoJugador(Jugador j1, Jugador j2){
        if (j1.isEsMano()){
            j1.setEsMano(false);
            j2.setEsMano(true);
        } else {
            j2.setEsMano(false);
            j1.setEsMano(true);
        }
    }

    public void jugarCarta(int opcion, Jugador jugador){
        Mano manoJugador = jugador.getMano();
        Carta carta = manoJugador.devolverCartaPorInidice(opcion);
        agregarCartaMatriz(jugador, carta);
        manoJugador.lanzarCarta(carta);
        quitarTurnoYDarseloAlSiguiente(jugador);
    }

    public boolean cartasEmpataron(Carta carta1, Carta carta2){
        return (carta1.getJerarquia() == carta2.getJerarquia());
    }

    public int numeroJugadorQueGano(Carta cartaJugador1, Carta cartaJugador2){
        if (cartaJugador1.getJerarquia() > cartaJugador2.getJerarquia()){
            return 1;
        } else if (cartaJugador1.getJerarquia() < cartaJugador2.getJerarquia()){
            return 2;
        } else return 0;

    }

    public int parda() {
        if (verificarSiSeEmpatoPrimerMano()) {
            Carta carta1 = matriz_turnos[turno - 1][0];
            Carta carta2 = matriz_turnos[turno - 1][1];
            Carta carta1turnoActual = matriz_turnos[turno][0];
            Carta carta2turnoActual = matriz_turnos[turno][1];
            if (cartasEmpataron(carta1, carta2)) {  // si empataron la mano anterior
                return numeroJugadorQueGano(carta1turnoActual, carta2turnoActual);
            }
        }

        return 0;
    }


    }