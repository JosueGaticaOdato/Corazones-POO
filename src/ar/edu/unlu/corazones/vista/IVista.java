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
	
	void jugarDosDeTrebol();

	void cartaTiradaInvalida();
	
	void cartaTiradaInvalida2deTrebol();
	
	void perdedorJugada();
	
	// *************************************************************
	//                		 OBSERVER
	// *************************************************************
	
	void setControlador(Controlador controlador);








	

	
}
