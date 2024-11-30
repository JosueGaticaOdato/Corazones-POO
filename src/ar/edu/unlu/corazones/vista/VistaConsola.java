package ar.edu.unlu.corazones.vista;

import java.util.Scanner;

import ar.edu.unlu.corazones.controlador.Controlador;

public class VistaConsola implements IVista {
	
	// *************************************************************
	//                       CONSTANTES
	// *************************************************************
	
	private final int lineas = 50; //para el salto de linea
	
	// *************************************************************
	//                       ATRIBUTOS
	// *************************************************************
	
	private Scanner entrada;
	
	private Controlador controlador;
	
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	//Creo la instancia para que el usuario pueda ingresar los datos
	public VistaConsola() {
		this.entrada = new Scanner(System.in);
	}
	
	// *************************************************************
	//                  COMPORTAMIENTO MENU CONSOLA
	// *************************************************************
	
	//Menu principal del program
	private void mostrarMenu() {
		System.out.println("****************************");
		System.out.println("*    	 CORAZONES         *");
		System.out.println("****************************");
		System.out.println();
		System.out.println("Seleccione una opcion:");
		System.out.println("----------------------");
		System.out.println("1 - Crear jugador");
		System.out.println("2 - Modificar jugador");
		System.out.println("3 - Ver lista de jugadores");
		System.out.println("4 - Comenzar juego");
		System.out.println("----------------------");
		System.out.println();
		System.out.println("0 - Salir");
		System.out.print("Opcion: ");
	}
	
	//Metodo para continuar y no sacar la pantalla de una
	private void continuar() {
		System.out.println("Escriba cualquier tecla para continuar...");
		this.entrada.next();
		limpiarPantalla();
	}
	
	//Limpieza de pantalla (en realidad agrega lineas)
	private void limpiarPantalla()
	{
	 for (int i=0; i < this.lineas; i++)
	 {
	  System.out.println();
	 }
	}

	
	// *************************************************************
	//                       COMPORTAMIENTO
	// *************************************************************
	
	// ********************* PRE-JUEGO *****************************
	
	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		System.out.println("Iniciar desde consola");
	}
	
	
	// *************************************************************
	//                		 OBSERVER
	// *************************************************************

	@Override
	public void setControlador(Controlador controlador) {
		// TODO Auto-generated method stub
		this.controlador = controlador;
	}

	
	
}
