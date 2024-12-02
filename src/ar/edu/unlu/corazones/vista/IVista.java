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
	
	
	// *************************************************************
	//                		 OBSERVER
	// *************************************************************
	
	void setControlador(Controlador controlador);

	void cartasRepartidas();

	
}
