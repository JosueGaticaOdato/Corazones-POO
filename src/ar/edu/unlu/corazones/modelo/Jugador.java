package ar.edu.unlu.corazones.modelo;

import java.util.ArrayList;

public class Jugador {
	
	// *************************************************************
	//                        ATRIBUTOS
	// *************************************************************
	
	private String nombre;
	
	private int puntaje;
	
	private ArrayList<Carta> mano;

	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	public Jugador(String nombre, int posicion) {
		this.nombre = nombre;
		this.puntaje = 0;
		mano = new ArrayList<Carta>();
	}
	
	// *************************************************************
	//                       COMPORTAMIENTO
	// *************************************************************

	public void recibirCarta(Carta carta) {
		mano.add(carta);
	}
	
	public Carta tirarCarta(int posCarta) {
		Carta cartaTirada = obtenerCartaJugador(posCarta);
		mano.remove(posCarta);
		return cartaTirada;
	}
	
	public int buscarCarta(Carta carta) {
    	boolean cartaEncontrada = false;
    	int pos = 0;
    	while (!cartaEncontrada && pos < mano.size()) {
    		if (mano.get(pos).getPalo() == carta.getPalo() && mano.get(pos).getValor() == carta.getValor())  {
    			cartaEncontrada = true;
    		} else {
    			pos++;
    		}
    	}
    	if (!cartaEncontrada) {
    		pos = -1;
    	}
        return pos;
	}
	
	
	// *************************************************************
	//                 FUNCION EN CORAZONES (sin implementar aun)
	// *************************************************************
	
//	public String[] cartasJugablesConola(Carta primeraCartaJugada, boolean corazonesRotos) {
//		// TODO Auto-generated method stub
//		String manoJugable = new ArrayList<Carta>();
//		
//		//Si la primer carta es nula o no tiene otra carta del mismo palo por jugar, muestro mano completa
//		if (primeraCartaJugada == null || !tieneCartasDelMismoPalo(primeraCartaJugada) || corazonesRotos)) {
//			manoJugable = getMano();
//		} else {
//			for (int i = 0; i < mano.size() ; i ++) {
//				Carta carta = mano.get(i);
//				s += (i+1) + ") " + carta.mostrarCarta();
//				//Si son de distinto palo, no la puede tirar
//				if (carta.getPalo() != primeraCartaJugada.getPalo()){
//					s += " X";
//				}
//				s += "\n";
//			}
//		}
//		return manoJugable ;
//	}
//	
//	//Consulta al jugador si, de las cartas que tiene en su mano, no tiene en mismo palo de la que esta en mesa
//	public Boolean tieneCartasDelMismoPalo(Carta carta) {
//    	boolean tieneMismoPalo = false;
//    	int pos = 0;
//    	while (!tieneMismoPalo && pos < mano.size() && carta != null) {
//    		if (mano.get(pos).getPalo() == carta.getPalo())  {
//    			tieneMismoPalo = true;
//    		} else {
//    			pos++;
//    		}
//    	}
//        return tieneMismoPalo;
//	}
	
	//Comprueba si el jugador tiene el 2 de trebol en su poder
    public boolean tengoDosDeTrebol() {
    	boolean loTiene = false;
    	int pos = 0;
    	while (!loTiene && pos < mano.size()) {
    		if (mano.get(pos).getPalo() == Palo.TREBOL && mano.get(pos).getValor() == 2)  {
            	loTiene = true;
    		} else {
    			pos++;
    		}
    	}
        return loTiene;
    }
	
	// *************************************************************
	//                      GETTERS
	// *************************************************************
	
	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<Carta> getMano() {
		return mano;
	}

	public int getPuntaje() {
		return puntaje;
	}
	
	public Carta obtenerCartaJugador(int posCarta) {
		return mano.get(posCarta);
	}
	
	public int cantCartasMano() {
		return mano.size();
	}
	
	// *************************************************************
	//                      SETTERS
	// *************************************************************
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}



	
}
