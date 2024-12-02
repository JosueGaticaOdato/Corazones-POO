package ar.edu.unlu.corazones.modelo;

public class Jugada {

	// *************************************************************
	//                        CONSTANTES
	// *************************************************************
	
	private final int DAMADEPICAS = 13;
	private final int CORAZONES = 1;
	private final int OTRAS = 0;
	
	public final int cartasEnMesa = 4;
	
	// *************************************************************
	//                        ATRIBUTOS
	// *************************************************************
	
	private static int contadorJugadas = 0; 

	private int numeroJugada;

	//Vector aparedeado para determinar el jugador con la carta que tiro
	private Jugador[] jugadores; 
	private Carta[] cartasJugadas;
	
	private Carta primeraCartaJugada;
	private Jugador jugadorPerdedor;
	
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	public Jugada(Jugador[] jugadores) {
		setJugadores(jugadores);
		cartasJugadas = new Carta[cartasEnMesa];
		contadorJugadas++;
		setNumeroJugada(contadorJugadas);
	}

	
	// *************************************************************
	//                       COMPORTAMIENTO
	// *************************************************************
	
	public boolean tirarCartaEnMesa(int turnoProx, Carta cartaEnJuego, boolean puedeTirarOtraCarta, boolean corazonesRotos) {
		boolean isCartaValida = true;
		
		/*Si es la primera carta, existes dos casos:
		- El primero, que sea cualquier carta que no sea de corazones: Eso es valido
		- El segundo, que sea una carta de corazones y quiera iniciar jugada sin que esten los corazones rotos: Eso es invalido*/
		
		if(primeraCarta()) {
			
			if (cartaEnJuego.getPalo() == Palo.CORAZONES && !corazonesRotos) {
				isCartaValida = false;
			} else {
				primeraCartaJugada = cartaEnJuego;
			}
		}
		
		/*El en caso de que no se la primera carta, analizo si la carta que tiro es
		del mismo palo de la que esta en la mesa, o si puede tirar otra carta de su mano*/
		
		else if ((puedeTirarOtraCarta) && (cartaEnJuego.getPalo() != primeraCartaJugada.getPalo())){
			isCartaValida = false;
		}
		
		//Si es una carta valida, la mando a la mesa
		if (isCartaValida){
			this.cartasJugadas[turnoProx] = cartaEnJuego;
		}
		
		return isCartaValida;
	}
	
	public boolean tirarDosDeTrebol(Carta carta, int turno) {
		boolean dosDeTrebol = false;
		if (carta.getPalo() == Palo.TREBOL && carta.getValor() == 2) {
			dosDeTrebol = true;
			this.cartasJugadas[turno] = carta;
		}
		return dosDeTrebol;
	}
	
	//Metodo que determina si es la primera carta jugada: Es fundamental ya que el palo de esta determinada cual es la carta mas alta
	private boolean primeraCarta() {
		boolean vacio = true;
		int pos = 0;
		while (vacio && pos < cartasJugadas.length) { //Busco en la mesa si hay alguna carta jugada
			if (cartasJugadas[pos] != null){
				vacio = false;
			}
			pos++;
		}
		return vacio;
	}
	
	public int determinarPerdedor() {
		Palo paloMejorCarta = primeraCartaJugada.getPalo();
		int valorMejorCarta = primeraCartaJugada.getValor();
		
		int posicionPerdedor = 0;
		
		//Recorro las 4 cartas en mesa y comparto
		for (int cartaJugada = 0; cartaJugada < cartasEnMesa; cartaJugada++) {
			
			//Si hay una carta del mismo palo que sobrepase a la mejor que esta en mesa, entonces esa es la mejor
			if (cartasJugadas[cartaJugada].getPalo() == paloMejorCarta
					&& cartasJugadas[cartaJugada].getValor() >= valorMejorCarta) {
				paloMejorCarta = cartasJugadas[cartaJugada].getPalo();
				valorMejorCarta = cartasJugadas[cartaJugada].getValor();
				posicionPerdedor = cartaJugada;
			}
		}
		contarPuntos(posicionPerdedor);
		jugadorPerdedor = jugadores[posicionPerdedor]; //Retorno posicion del ganador
		return posicionPerdedor;
	}
	
	private void contarPuntos(int posPerdedorJugadas) {
		for (Carta cartaConseguida : cartasJugadas) {
			jugadores[posPerdedorJugadas].setPuntaje(puntajeCarta(cartaConseguida));
		}
	}
	
	private int puntajeCarta(Carta carta) {
		int puntaje;
		if (carta.getPalo() == Palo.CORAZONES){
			puntaje = CORAZONES;
		} else if (carta.getPalo() == Palo.PICAS && carta.getValor() == 12) {
			puntaje = DAMADEPICAS;
		} else {
			puntaje = OTRAS;
		}
		return puntaje;
	}
	
	public static void reiniciarContadorJugadas() {
        contadorJugadas = 0;
    }
	
	// *************************************************************
	//                       GETTERS
	// *************************************************************
	

	public int getNumeroJugada() {
		return numeroJugada;
	}

	public Jugador[] getJugadores() {
		return jugadores;
	}

	public Carta[] getCartasJugadas() {
		return cartasJugadas;
	}

	public Carta getPrimeraCartaJugada() {
		return primeraCartaJugada;
	}

	public Jugador getJugadorPerdedor() {
		return jugadorPerdedor;
	}
	
	// *************************************************************
	//                       SETTERS
	// *************************************************************


	public void setNumeroJugada(int numeroJugada) {
		this.numeroJugada = numeroJugada;
	}


	public void setJugadores(Jugador[] jugadores) {
		this.jugadores = jugadores;
	}


	public void setCartasJugadas(Carta[] cartasJugadas) {
		this.cartasJugadas = cartasJugadas;
	}


	public void setPrimeraCartaJugada(Carta primeraCartaJugada) {
		this.primeraCartaJugada = primeraCartaJugada;
	}


	public void setJugadorPerdedor(Jugador jugadorPerdedor) {
		this.jugadorPerdedor = jugadorPerdedor;
	}
	
	
}
