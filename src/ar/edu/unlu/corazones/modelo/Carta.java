package ar.edu.unlu.corazones.modelo;

public class Carta {
	
	// *************************************************************
	//                        ATRIBUTOS
	// *************************************************************
	
	private Palo palo;
	
	private int valor;
	
	private String valorTexto; //En el caso de que sea J,Q,K y A
	
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	public Carta(Palo palo, int valor) {
		setPalo(palo);
		setValor(valor);
		valorCarta();
	}
	
	// *************************************************************
	//                       COMPORTAMIENTO
	// *************************************************************

	public String mostrarCarta() {
			return valorTexto + " - " + palo.toString();
	}
	
	//Obtener el valor real de la carta (si son letras o no)
	private void valorCarta() {
		switch (this.valor) {
		case 11:
			setValorTexto("J");
			break;
		case 12:
			setValorTexto("Q");
			break;
		case 13:
			setValorTexto("K");
			break;
		case 1:
			setValorTexto("A");
			setValor(14); //Paso a 14 porque es la mas grande
			break;
		default:
			setValorTexto(String.valueOf(this.valor));
			break;
		}
	}
	
	// *************************************************************
	//                      	GETTERS
	// *************************************************************
	
	public Palo getPalo() {
		return palo;
	}

	public int getValor() {
		return valor;
	}
	
	public String getValorTexto() {
		return valorTexto;
	}
	
	// *************************************************************
	//                      	SETTERS
	// *************************************************************
	
	public void setPalo(Palo palo){
		this.palo = palo;
	}
	
	public void setValor(int valor){
		this.valor = valor;
	}
	
	public void setValorTexto(String valorT) {
		this.valorTexto = valorT;
	}
	
}


