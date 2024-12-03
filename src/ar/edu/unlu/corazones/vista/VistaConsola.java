package ar.edu.unlu.corazones.vista;

import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.corazones.controlador.Controlador;
import ar.edu.unlu.corazones.modelo.Carta;

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
	//                COMPORTAMIENTO PROPIO DE LA CONSOLA
	// *************************************************************
	
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
	

	private void combinacionRondaJugada() {
		puntaje();
		System.out.println("****************************");
		System.out.println("*         RONDA #" + this.controlador.numeroRonda() + "         *");
		System.out.println("*    	  JUGADA #" + this.controlador.numeroJugada() + "        *");
		System.out.println("****************************");
	}
	
	private void combinacionRondaPasaje() {
		System.out.println("****************************");
	    System.out.println("*         RONDA #" + this.controlador.numeroRonda() + "         *");
		System.out.println("*     PASAJE DE CARTAS     *");
		System.out.println("****************************");
	}
	
	private void turnoJugador() {
		System.out.println("****************************");
		System.out.println("*    JUGADOR #" + this.controlador.nombreJugadorActual() + "    *");
		cartasEnMesa();
	}
	
	private void cartasEnMesa() {
		System.out.println("*      CARTAS EN MESA      *");
		System.out.println();
		Carta[] cartasEnMesa = this.controlador.cartasEnMesa();
		for (int i = 0; i < cartasEnMesa.length ; i++) {
			
			Carta carta = cartasEnMesa[i];
			
			if (carta != null) {
				System.out.println((i+1) + ") " + this.controlador.getJugador(i) +
						": " + carta.mostrarCarta());
			} else {
				System.out.println((i+1) + ") " + this.controlador.getJugador(i) +
						": " + "sin jugar");
			}
			
		}
		System.out.println();
		System.out.println("****************************");
		System.out.println();
	}
	
	private void puntaje() {
		System.out.println("*          PUNTAJE         *");
		System.out.println();
		int[] puntajes = this.controlador.puntajesJugadores();
		for (int i = 0; i < puntajes.length; i++) {
			System.out.println((i+1) + ") " + this.controlador.getJugador(i) + 
					" -> " + puntajes[i]);
		}
		System.out.println();
		System.out.println("****************************");
	}
	
	
	private void manoJugador() {
		System.out.println();
		System.out.println("     	    MANO            ");
		System.out.println("----------------------------");
		String mano = "";
		ArrayList<Carta> manoJugador = this.controlador.manoJugador(this.controlador.posicionJugadorActual());
		for (int i = 0; i < manoJugador.size() ; i++) {
			Carta carta = manoJugador.get(i);
			mano = (i+1) + ") " + carta.mostrarCarta();
			System.out.println(mano);
		}
		System.out.println("----------------------------");
		System.out.println();
		System.out.println();
	}

	
	// *************************************************************
	//                         PRE-JUEGO
	// *************************************************************
	
	@Override
	public void iniciar() {
		boolean salir = false;
		while(!salir) {
			limpiarPantalla();
			mostrarMenu();
			int opcion = this.entrada.nextInt();
			limpiarPantalla();
			switch (opcion) {
				case 1: //Crear jugador
					nuevoJugador();
					break;
				case 2: //Modificar jugador por posicion
					modificarJugador();
					break;
				case 3: //Mostrar lista de jugadores 
					listaJugadores();
					break;
				case 4: //Comenzar juego
					jugar();
					break;
				case 0: //Salir del juego
					salir = true;
					System.out.println("Gracias por jugar!!");
					break;
				default: //Opcion por default
					System.out.println("Opcion no valida.");
			}
			continuar();
		}
	}
	
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
	
	// ************************* ALTA ******************************
	
	private void nuevoJugador() {
		if (!this.controlador.isCantidadJugadoresValida()) {
			System.out.println("\n" + "---------- NUEVO JUGADOR! -------------" + "\n");
			System.out.print("Ingrese el nombre del nuevo jugador: ");
			String nombre = entrada.next();
			
			if (nombre != null && !nombre.trim().isEmpty()) {
				
				this.controlador.agregarJugador(nombre);
				System.out.println("Jugador agregado con éxito.");
				
			} else {
				
				System.out.println("El nombre del jugador no puede estar vacio");
			}
			
		} else {
			System.out.println("\n" + "Ya estan todos los jugadores inscriptos" + "\n");
		}
	}

	// ********************* MODIFICACION **************************
	
	private void modificarJugador() {
		listaJugadores();
		System.out.print("Por favor, ingrese el numero de jugador que quiere modificar: ");
		int pos = entrada.nextInt();
		System.out.print("Ingrese el nuevo nombre para el jugador: ");
		String nombre = entrada.next();
		
		boolean creado = controlador.modificarJugador(nombre, pos);
		if (creado) {
			System.out.println("Jugador modificado con exito!");
		} else {
			System.out.println("No se pudo modificar el jugador.");
		}
	}
	
	// ******************* LISTA DE JUGADORES  *********************
	
	private void listaJugadores() {
		System.out.println("\n" + "Lista de jugadores:");
		String[] jugadores = controlador.listaJugadores();
		String s = "\n";
		for (int i = 0; i < controlador.cantidadJugadores(); i++) {
			s += (i+1) + ") Jugador: "; 
			if (jugadores[i] == null) {
				s += "(Sin agregar)";
			} else {
				s += jugadores[i];
			}
			s += "\n";
		}
		System.out.println(s);
	}
	
	// *************************************************************
	//                         JUGAR
	// *************************************************************
	
	public void jugar() {
		if ( this.controlador.isCantidadJugadoresValida() ) {
			System.out.println("Juego comenzado!");
			continuar();
			controlador.iniciarJuego();
		} else {
			System.out.println("Faltan jugadores para comenzar el juego");
		}
	}
	
	// ************ PEDIR CARTAS (para tirar en mesa) **************
	
	@Override
	public void pedirCarta() {
		// TODO Auto-generated method stub
		combinacionRondaJugada();
		System.out.println("Es el turno del jugador: "
				+ this.controlador.nombreJugadorActual()); //Digo quien tiene que jugar
		continuar();
		combinacionRondaJugada();
		turnoJugador();
		if (this.controlador.isCorazonesRotos()) {
			System.out.println("*     ¡CORAZONES ROTOS!    *");
		}
		manoJugador();
		System.out.print("Elija una carta: ");
		int posCarta = entrada.nextInt();
		controlador.cartaJugada(posCarta - 1); //Paso la carta
		continuar();
	}

	// ******************** JUGAR DOS DE TREBOL ********************
	
	@Override
	public void jugarDosDeTrebol() {
		// TODO Auto-generated method stub
		System.out.println("Como es la primer jugada, el jugador " + this.controlador.nombreJugadorActual() +
				" debe iniciar el juego tirando el 2 de Trebol");
		continuar();
		pedirCarta();
	}
	
	// ****************** CARTA TIRADA INVALIDA ********************
	
	@Override
	public void cartaTiradaInvalida() {
		// TODO Auto-generated method stub
		System.out.println("La carta que seleccioanste es invalida."
				+ "Tienes que tirar una carta del mismo palo que la que esta en la mesa."
				+ "Por favor, intentalo denuevo.");
		continuar();
		pedirCarta();
	}
	
	@Override
	public void cartaTiradaInvalida2deTrebol() {
		// TODO Auto-generated method stub
		System.out.println("La carta que seleccioanste es invalida. Para comenzar el juego si o si tienes que tirar el 2 de Trebol. Por favor, intentalo denuevo.");
		continuar();
		pedirCarta();
	}
	
	// ******************** PERDEDOR JUGADA ************************
	
	@Override
	public void perdedorJugada() {
		combinacionRondaJugada();
		cartasEnMesa();
		
		System.out.println("El perdedor de esta jugada es " + this.controlador.jugadorPerdedorJugada() + "\n");
		
		continuar();
	}
	
	// ******************** CORAZONES ROTOS ************************
	
	@Override
	public void corazonesRotos() {
		System.out.println("\n" + "CORAZONES ROTOS");
		System.out.println("A partir de ahora se pueden tirar corazones al inicio" + "\n");
		continuar();
	}

	
	
	// *************************************************************
	//                		 OBSERVER
	// *************************************************************

	@Override
	public void setControlador(Controlador controlador) {
		// TODO Auto-generated method stub
		this.controlador = controlador;
	}

	@Override
	public void cartasRepartidas() {
		// TODO Auto-generated method stub
	}


	
}
