package ar.edu.unlu.corazones.controlador;

import java.util.ArrayList;

import ar.edu.unlu.corazones.modelo.Carta;
import ar.edu.unlu.corazones.modelo.Corazones;
import ar.edu.unlu.corazones.modelo.EventosCorazones;
import ar.edu.unlu.corazones.observer.Observable;
import ar.edu.unlu.corazones.observer.Observador;
import ar.edu.unlu.corazones.vista.IVista;

public class Controlador implements Observador {
	
	private Corazones modelo;
	
	private IVista vista;
	
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	public Controlador(Corazones modelo, IVista vista) {
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
		this.modelo.agregarObservador(this);
	}
	
	// *************************************************************
	//                       COMPORTAMIENTO
	// *************************************************************

	// ********************* PRE-JUEGO *****************************
	
	public boolean isCantidadJugadoresValida() {
		return modelo.isCantidadJugadoresValida();
	}
	
	public void agregarJugador(String nombre) {
		modelo.agregarJugadores(nombre);
	}
	
	public String[] listaJugadores() {
		return modelo.getListaJugadores();
	}
	
	public int cantidadJugadores() {
		return modelo.getCantidadJugadores();
	}
	
	public boolean modificarJugador(String nombre, int pos) {
		return this.modelo.reemplazarJugadores(nombre, pos);
	}
	
	public void iniciarJuego() {
		this.modelo.iniciarJuego();
	}
	
	// ************************ JUEGO ******************************
	
	public int numeroRonda() {
		return this.modelo.getRonda();
	}

	public int numeroJugada() {
		return this.modelo.getNumeroJugada();
	}
	
	public ArrayList<Carta> manoJugador(int pos){
		return this.modelo.getManoJugador(pos);
	} 
	
	public String nombreJugadorActual() {
		return this.modelo.getNombreJugadorActual();
	}
	
	public int posicionJugadorActual() {
		return this.modelo.getPosicionJugadorActual();
	}
	
	public String getJugador(int i) {
		return this.modelo.getJugador(i);
	}

	public void cartaJugada(int i) {
		this.modelo.setCartaAJugar(i);
	}
	
	public Carta[] cartasEnMesa() {
		return this.modelo.getCartasEnMesa();
	}
	
	public String jugadorPerdedorJugada() {
		// TODO Auto-generated method stub
		return this.modelo.getJugadorPerdedorJugada();
	}
	
	public int[] puntajesJugadores() {
		return this.modelo.puntajesJugadores();
	}
	

	public boolean isCorazonesRotos() {
		// TODO Auto-generated method stub
		return this.modelo.isCorazonesRotos();
	}
	
	public Carta getCartaAJugar() {
		return this.modelo.getCartaAJugar();
	}
	
	public String ganadorJuego() {
		return this.modelo.getNombreGanadorJuego();
	}



	
	// *************************************************************
	//                       OBSERVER
	// *************************************************************

	@Override
	public void actualizar(Object evento, Observable observado) {
		// TODO Auto-generated method stub
		if (evento instanceof EventosCorazones) {
			switch ((EventosCorazones) evento) {
			case CARTAS_REPARTIDAS:
				this.vista.cartasRepartidas();
				break;
			case PEDIR_CARTA:
				this.vista.pedirCarta();
				break;
			case JUGAR_2_DE_TREBOL:
				this.vista.jugarDosDeTrebol();
				break;
			case CARTA_TIRADA_INVALIDA:
				this.vista.cartaTiradaInvalida();
				break;
			case CARTA_TIRADA_INVALIDA_2_DE_TREBOL:
				this.vista.cartaTiradaInvalida2deTrebol();
				break;
			case PERDEDOR_JUGADA:
				this.vista.perdedorJugada();
				break;
			case CORAZONES_ROTOS:
				this.vista.corazonesRotos();
				break;
			case CARTA_TIRADA_VALIDA:
				this.vista.cartaTiradaValida();
				break;
			case FIN_DE_RONDA:
				this.vista.finDeRonda();
				break;
			case 	FIN_DE_JUEGO:
				this.vista.finDeJuego();
				break;
			}
		}
	}










	


	

}
