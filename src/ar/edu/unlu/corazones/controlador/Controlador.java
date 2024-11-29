package ar.edu.unlu.corazones.controlador;

import ar.edu.unlu.corazones.modelo.Corazones;
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
	
	public void comenzarJuego() {
		this.modelo.iniciarJuego();
	}
	
	// *************************************************************
	//                       OBSERVER
	// *************************************************************

	@Override
	public void actualizar(Object evento, Observable observado) {
		// TODO Auto-generated method stub
		
	}
	

}
