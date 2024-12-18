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
	
	void corazonesRotos();
	
	void cartaTiradaValida();
	
	// *************************************************************
	//                    FIN JUEGO - RONDA
	// *************************************************************
	
	void finDeRonda();

	void finDeJuego();
	
	// *************************************************************
	//                		 OBSERVER
	// *************************************************************
	
	void setControlador(Controlador controlador);



	










	

	
}
