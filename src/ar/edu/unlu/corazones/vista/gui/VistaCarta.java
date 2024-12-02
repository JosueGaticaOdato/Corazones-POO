package ar.edu.unlu.corazones.vista.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ar.edu.unlu.corazones.modelo.Carta;

public class VistaCarta extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final int ANCHO = 60;
	private final int ALTO = 90;
	private final int TAMAÑO_FUENTE = 20;
	
	private Carta carta;
	
	// *************************************************************
	//                        CONSTRUCTOR
	// *************************************************************
	
	public VistaCarta(Carta carta) {
		setBackground(Color.WHITE);
        setPreferredSize(new Dimension(ANCHO, ALTO)); // Tamaño fijo de la carta
        setLayout(new BorderLayout());
		setCarta(carta);
		actualizarVista();
	}

	// *************************************************************
	//                       COMPORTAMIENTO
	// *************************************************************
	
	private void actualizarVista() {
		removeAll();

		//Toma el numero de la carta
		JLabel lblValorCartaArriba = new JLabel(getCarta().getValorTexto());
		lblValorCartaArriba.setFont(new Font("Tahoma", Font.BOLD, TAMAÑO_FUENTE));
		lblValorCartaArriba.setHorizontalAlignment(SwingConstants.LEFT);
        add(lblValorCartaArriba, BorderLayout.NORTH);
		
        //Toma el numero de la carta
		JLabel lblValorCartaAbajo = new JLabel(getCarta().getValorTexto());
		lblValorCartaAbajo.setFont(new Font("Tahoma", Font.BOLD, TAMAÑO_FUENTE));
		lblValorCartaAbajo.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblValorCartaAbajo, BorderLayout.SOUTH);;
		
        //Muestra como imagen en palo de la carta, buscando la url relativa a la imagen.
		JLabel lblValorCartaTipo = new JLabel();
        lblValorCartaTipo.setIcon(new ImageIcon(redimensionarImagen("/ar/edu/unlu/corazones/img/" + getCarta().getPalo().toString() + ".png", 20, 40)));
		lblValorCartaTipo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblValorCartaTipo, BorderLayout.CENTER);
        
        revalidate();
        repaint();
        
        //setPreferredSize(new Dimension(60, 90)); // Tamaño de la carta

	}
	
	
    private Image redimensionarImagen(String ruta, int ancho, int alto) {
        try {
            BufferedImage imagenOriginal = ImageIO.read(VistaCarta.class.getResource(ruta));
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return imagenRedimensionada;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

	// *************************************************************
	//                       GETTERS y SETTERS
	// *************************************************************
	
	public Carta getCarta() {
		return carta;
	}


	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	
}
