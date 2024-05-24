package truco.clases;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Integer.parseInt;

public class GameFlow{
    private Juego juego;
    private Scanner scanner;

    public GameFlow(){
        this.juego = new Juego();
        this.scanner = new Scanner(System.in);
    }

    public void cargarJugadores(){
        String jugadores = "";

        while (!jugadores.equals("2")){
            System.out.println("Ingrese cantidad de jugadores: ");
            jugadores = scanner.nextLine();
            if (!jugadores.equals("2")){
                System.out.println("Actualmente solo esta disponible la version 1vs1 (2 jugadores)");
            }
        }

        for (int i = 0; i < parseInt(jugadores); i++) {
            System.out.println("Ingrese nombre jugador " + (i+1) + ": ");
            String jugador = scanner.nextLine();
            juego.agregarJugador(new Jugador(jugador, (i+1)));
        }

    }


    public void flujoJuego(){
        while (!juego.verificarSiGanoAlgunJugador()){
            juego.resetTurno();
            AtomicBoolean jugadorGano = new AtomicBoolean(false);
            AtomicBoolean primerTurno = new AtomicBoolean(true);
            System.out.println("Repartiendo cartas a jugadores...");
            juego.repartirJugadores();
            flujoInterno();
        }



    }

    public Object flujoInterno(){
        int ganadorRonda;
        int opcion = 0;
        AtomicBoolean jugadorGano = new AtomicBoolean(false);
        AtomicBoolean primerTurno = new AtomicBoolean(true);
        juego.definirPrimerTurno(primerTurno);
        boolean jugadorGanoAux = jugadorGano.get();
        while (!jugadorGanoAux){
            for (int i = 0; i < 2; i++) {
                Jugador jugador = juego.getJugadorConTurno();
                System.out.println("Elije una carta el jugador: " + jugador.getNumero());
                jugador.getMano().mostrarMano();
                System.out.println("Seleccione una opcion: ");
                opcion = scanner.nextInt();
                juego.jugarCarta(opcion - 1, jugador);
            }

            juego.procesarTurno(juego.getTurno());

            System.out.println("Mesa: ");
            juego.mostrarMatriz();

            if (juego.getTurno() > 0){
                ganadorRonda = juego.parda();
                if (ganadorRonda != 0){
                    jugadorGano.set(true);
                    Jugador jugadorGanador = juego.getJugadorPorNumero(ganadorRonda);
                    jugadorGanador.setPuntajeTotal(1);
                    juego.aumentarTurno();
                    System.out.println("Gana la ronda el jugador: " + jugadorGanador.getNumero());
                    return null; // corta ejecucion porque ya se determino un ganador de la ronda
                }
            }

            juego.aumentarTurno();

            Jugador jugadorGanador = juego.definirGanadorRonda();
            if (jugadorGanador != null){
                jugadorGano.set(true);
                System.out.println("Gana la ronda el jugador: " + jugadorGanador.getNumero());
            }

            jugadorGanoAux = jugadorGano.get();
        }
        // ver que pasa si empatan las tres manos
        return null;

    }

    public void iniciarPartida() {
        String opcion = "";


        while (!opcion.equals("2")){
            System.out.println("------------ TRUCO --------------");
            System.out.println("       Escoja una opcion         ");
            System.out.println("          [1] JUGAR"               );
            System.out.println("          [2] SALIR               ");
            opcion = scanner.nextLine();
            switch (opcion) {
                case "2":
                    System.out.println("Adios!");
                    break;

                case "1":
                    cargarJugadores();
                    flujoJuego();
                    break;
                default:
                    System.out.println("Escoja una opcion valida.");
                    break;
            }
        }

    }




}
