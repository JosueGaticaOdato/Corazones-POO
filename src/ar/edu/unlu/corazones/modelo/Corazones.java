package ar.edu.unlu.corazones.modelo;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlu.corazones.observer.Observable;
import ar.edu.unlu.corazones.observer.Observador;

/**
 * CLASE CORAZONES
 * Encarga de manejar todo el flujo del juego, que abarca el
 * 	pasaje de cartas, control de rondas, puntajes, etc.
 */

public class Corazones implements Observable {

	// *************************************************************
	// 						CONSTANTES
	// *************************************************************

	private static final int cantCartasRepartidas = 13; // TESTING (REAL 13)
	private static final int cantCartasIntercambio = 0; // TESTING (REAL 3)
	private static final int puntajeMaximo = 12; // TESTING (REAL 100)
	private static final int cantJugadores = 4;
	
	// *************************************************************
	// 						ATRIBUTOS
	// *************************************************************
	
	private Mazo mazo;
	
	private Jugador[] jugadores;
	
	private int ronda;
	
	private List<Jugada> jugadas;
	
	private int turno = 0;
	
	private Direccion direccion;
	
	private Carta cartaAJugar;
	
	private int posJugadorGanador;
	
	private boolean corazonesRotos;
	
	private List<Observador> observadores;
	
	// *************************************************************
	// 						CONSTRUCTOR
	// *************************************************************
	
	public Corazones() {
		jugadores = new Jugador[cantJugadores];
		ronda = 1;
		
		// Cargo default
		agregarJugadores("Jugador A");
		agregarJugadores("Jugador B");
		agregarJugadores("Jugador C");
		agregarJugadores("Jugador D");

		this.observadores = new ArrayList<>();
		this.jugadas = new ArrayList<>();
	}
	
	// *************************************************************
	// 						COMPORTAMIENTO
	// ************************************************************
	
	public void iniciarJuego() {
		boolean juegoTerminado = false;
		
		while (!juegoTerminado) {
			mazo = new Mazo();
			repartirCartas();
			notificar(EventosCorazones.CARTAS_REPARTIDAS);
			juegoTerminado = true;
			this.corazonesRotos = false;
			
			for (int j = 0; j < cantCartasRepartidas; j++) {
				
				int i = 0;
				Jugada jugada = new Jugada(this.jugadores);
				jugadas.add(jugada);
				
				/*CASO 2 DE TREBOL*/
				if (j == 0) {
					primerCarta2Trebol(jugada);
					i++;
				}
				
				/*1 JUGADA POR CADA JUGADOR*/
				while (i < cantJugadores) {
					notificar(EventosCorazones.PEDIR_CARTA);
					jugarCarta(jugada);
					i++;
				}
				
				turno = jugada.determinarPerdedor();
				
			}

			// Finalizada la ronda, se comprueba si se llego al puntaje maxixo para finalizar el juego
			if (puntajeMaximoActual() >= puntajeMaximo) {
				juegoTerminado = true;
			}
			
		}
		System.out.println("Fin Juego!");
	}
	
	// *************************************************************
	// 					FUNCIONALIDAD RONDA
	// *************************************************************
	
	private void repartirCartas() {
		for (int i = 0; i < cantCartasRepartidas; i++) {
			for (Jugador jugador : jugadores) {
				jugador.recibirCarta(mazo.sacarCarta());
			}
		}
	}
	
	private int puntajeMaximoActual() {
		int max = 0;
		for (Jugador jugadoresCorazones : jugadores) {
			if (max <= jugadoresCorazones.getPuntaje()) {
				max = jugadoresCorazones.getPuntaje();
			}
		}
		return max;
	}
	
	// *************************************************************
	// 					FUNCIONALIDAD JUGADAS
	// *************************************************************

	//Para comenzar la ronda es necesario que el jugador que tiene el dos de trebol comience
	private void primerCarta2Trebol(Jugada jugada) {
		boolean tengoDosDeTrebol = false;
		int pos = 0;
		while (!tengoDosDeTrebol && pos < cantJugadores) {
			
			//Obtengo al jugador que tiene el 2 de trebol
			tengoDosDeTrebol = jugadores[pos].tengoDosDeTrebol();
			if (tengoDosDeTrebol) {
				turno = pos;
				notificar(EventosCorazones.JUGAR_2_DE_TREBOL);
				boolean dosDeTrebolTirado = false;
				
				//Hasta que no tire el dos de trebol no arranca el juego!
				while ( !dosDeTrebolTirado ) {
					
					if (jugada.tirarDosDeTrebol(cartaAJugar, turno)) {
						
						jugadores[turno].tirarCarta(jugadores[turno].buscarCarta(cartaAJugar));
						turno = (turno + 1) % cantJugadores;
						dosDeTrebolTirado = true;
						
					} else {
						
						notificar(EventosCorazones.CARTA_TIRADA_INVALIDA_2_DE_TREBOL);
					}
					
				}
				
			} else {
				pos++;
			}
		}
	}
	
	
	private void jugarCarta(Jugada jugada) {
		
		boolean cartaTiradaValida = false;
		while ( !cartaTiradaValida ) {
			
			if (jugada.tirarCartaEnMesa(turno, cartaAJugar, false, this.corazonesRotos)) {
				jugadores[turno].tirarCarta(jugadores[turno].buscarCarta(cartaAJugar));
				turno = (turno + 1) % cantJugadores;
				cartaTiradaValida = true;
				
			} else {
				
				notificar(EventosCorazones.CARTA_TIRADA_INVALIDA);
				
			}
		}
		
	}

	
	// *************************************************************
	// 						ALTA Y MODIFICACION
	// *************************************************************
	
	public boolean agregarJugadores(String nombre) {
		boolean hayEspacio = false;
		int pos = 0;
		while (!hayEspacio && pos < jugadores.length) {
			if (jugadores[pos] == null) {
				jugadores[pos] = new Jugador(nombre, pos);
				hayEspacio = true; //Solamente se dan altas si hay lugares disponibles
			} else {
				pos++;
			}
		}
		return hayEspacio;
	}
	
	public boolean reemplazarJugadores(String nombre,int posicion) {
		boolean seReemplazo = false;
		if (posicion >= 0 && posicion <= cantJugadores) {
			if (!(jugadores[posicion - 1] == null)){
				seReemplazo = true;
				jugadores[posicion - 1].setNombre(nombre);
			}
		}
		return seReemplazo;
	}
	
	// *************************************************************
	//                      GETTERS
	// *************************************************************
	
	public Mazo getMazo() {
		return mazo;
	}

	public Jugador[] getJugadores() {
		return jugadores;
	}

	public int getRonda() {
		return ronda;
	}

	public List<Jugada> getJugadas() {
		return jugadas;
	}

//	public int getTurno() {
//		return turno;
//	}

	public Direccion getDireccion() {
		return direccion;
	}

	public Carta getCartaAJugar() {
		return cartaAJugar;
	}

	public int getPosJugadorGanador() {
		return posJugadorGanador;
	}

	public boolean isCorazonesRotos() {
		return corazonesRotos;
	}
	
	public int getCantidadJugadores() {
		return cantJugadores;
	}
	
	public int getCantCartasIntercambio() {
		return cantCartasIntercambio;
	}
	
	// *************************************************************
	//                  GETTERS ESPECIALES
	// *************************************************************
	
	public String getNombreJugadorActual() {
		return jugadores[turno].getNombre();
	}
	
	public int getPosicionJugadorActual() {
		return turno;
	}
	
	
	public Carta[] getCartasEnMesa(){
		return this.jugadas.get(jugadas.size() - 1).getCartasJugadas();
	}
	
	/*
	public Jugador getJugadorPerdedorJugada() {
		return this.jugadas.get(jugadas.size() - 1).getJugadorPerdedor();
	}*/
	
	public int getNumeroJugada() {
		return 1;
		//return this.jugadas.get(jugadas.size()-1).getNumeroJugada();
	}
	
	public boolean isCantidadJugadoresValida() {
	    int jugadoresRegistrados = 0;
	    for (Object jugador : getJugadores()) {
	        if (jugador != null) {
	            jugadoresRegistrados++;
	        }
	    }
	    return jugadoresRegistrados == getCantidadJugadores();
	}
	
	
	//Me muestro una array con los nombre de todos los jugadores
	public String[] getListaJugadores(){
		
		String[] jugadores = new String[cantJugadores];
		for (int i = 0; i < cantJugadores; i++) {
			if (this.jugadores[i] != null) {				
				jugadores[i] = this.jugadores[i].getNombre();
			} else {
				jugadores[i] = null;
			}
		}
		return jugadores;
	}
	
	public ArrayList<Carta> getManoJugador(int pos) {
		return this.jugadores[pos].getMano();
	}
	
	// *************************************************************
	//                      SETTERS
	// *************************************************************
	
	public void setCartaAJugar(int pos) {
		cartaAJugar = jugadores[turno].obtenerCartaJugador(pos);
	}
	
	// *************************************************************
	//					 MVC Y OBSERVER
	// *************************************************************

	@Override // Notificar los eventos
	public void notificar(Object evento) {
		for (Observador observador : this.observadores) {
			observador.actualizar(evento, this);
		}
	}

	@Override // Agregar observadores
	public void agregarObservador(Observador observador) {
		this.observadores.add(observador);
	}


	
}
