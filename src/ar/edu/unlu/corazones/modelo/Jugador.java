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
