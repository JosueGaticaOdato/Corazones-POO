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
		if (!(jugadores[posicion - 1] == null)){
			seReemplazo = true;
			jugadores[posicion - 1].setNombre(nombre);
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

	public int getTurno() {
		return turno;
	}

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
		return Corazones.cantJugadores;
	}
	
	public int getCantCartasIntercambio() {
		return Corazones.cantCartasIntercambio;
	}
	
	// *************************************************************
	//                  GETTERS ESPECIALES
	// *************************************************************
	
	public Jugador getJugadorActual() {
		return jugadores[turno];
	}
	
	/*public Carta[] getCartasEnMesa(){
		return this.jugadas.get(jugadas.size() - 1).getCartasJugadas();
	}
	
	public Jugador getJugadorPerdedorJugada() {
		return this.jugadas.get(jugadas.size() - 1).getJugadorPerdedor();
	}
	
	public int getNumeroJugada() {
		return this.jugadas.get(jugadas.size()-1).getNumeroJugada();
	}*/
	
	public boolean isCantidadJugadoresValida() {
		return getCantidadJugadores() == getJugadores().length;
	}
	
	
	//Me muestro una array con los nombre de todos los jugadores
	public String[] getListaJugadores(){
		String[] jugadores =  new String[this.jugadores.length];
		for (int i = 0; i < this.jugadores.length; i++)
		{
			jugadores[i] = this.jugadores[i].getNombre();
				//System.out.println(this.jugadores[i].getNombre());
		}
		return jugadores;
	}

	
	// *************************************************************
	//                      SETTERS
	// *************************************************************
	
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
