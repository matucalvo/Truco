package truco.clases;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Integer.parseInt;

public class GameFlow {
    private Juego juego;

    public GameFlow(){
        this.juego = new Juego();
    }

    public void cargarJugadores(){
        Scanner scanner = new Scanner(System.in);
        String jugadores = "a";

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

        scanner.close();

    }


    public void flujoJuego(){
        while (!juego.verificarSiGanoAlgunJugador()){
            juego.resetTurno();
            AtomicBoolean jugadorGano = new AtomicBoolean(false);
            AtomicBoolean primerTurno = new AtomicBoolean(true);
            System.out.println("Repartiendo cartas a jugadores...");
            juego.repartirJugadores();



        }

    }

    public void flujoInterno(){

    }

    public void iniciarPartida() {
        Scanner scanner = new Scanner(System.in);

        String opcion = scanner.nextLine();


        while (!opcion.equals("2")){
            System.out.println("------------ TRUCO --------------");
            System.out.println("       Escoja una opcion         ");
            System.out.println("          [1] JUGAR"               );
            System.out.println("          [2] SALIR               ");
            switch (opcion) {
                case "2":
                    System.out.println("Adios!");
                    break;

                case "1":
                    cargarJugadores();
                    // flujo de juego


                default:
                    System.out.println("Escoja una opcion valida.");
                    break;
            }
        }

    }




}
