package ar.edu.unlu.corazones.vista;

import ar.edu.unlu.corazones.controlador.Controlador;

public interface IVista {

	// *************************************************************
	//                         PRE-JUEGO
	// *************************************************************
	
	void iniciar();
	
	// *************************************************************
	//                          JUEGO
	// *************************************************************
	
	void cartasRepartidas();

	void pedirCarta();
	
	// *************************************************************
	//                		 OBSERVER
	// *************************************************************
	
	void setControlador(Controlador controlador);

	

	
}
