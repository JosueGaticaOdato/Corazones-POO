package ar.edu.unlu.corazones.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ar.edu.unlu.corazones.controlador.Controlador;
import ar.edu.unlu.corazones.modelo.Carta;
import ar.edu.unlu.corazones.vista.gui.FondoTapete;
import ar.edu.unlu.corazones.vista.gui.VistaCarta;

public class VistaGrafica extends JFrame implements IVista{
	
	private static final long serialVersionUID = 1L;
	
	// *************************************************************
	//                       ATRIBUTOS
	// *************************************************************

	private FondoTapete panelPrincipal;
	
	private Controlador controlador;
	
	private CardLayout cardLayout;
	
	private JPanel panelMenu;
	
	private JPanel panelJuego;
	private JPanel panelCentro;
	private JLabel labelCentro;
	
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelEste;
	private JPanel panelOeste; 

	
	// *************************************************************
	//                        CONSTRUCTOR
	// *************************************************************
	
	public VistaGrafica() {
		
		/* CONFIGURACIONES DE VENTANA */
		setTitle("Corazones"); 
		setSize(1000, 800); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		/* INICIALIZAR LAYOUT Y PANEL PRINCIPAL */
		cardLayout = new CardLayout();
        panelPrincipal = new FondoTapete("/ar/edu/unlu/corazones/img/tapete.jpg");
        panelPrincipal.setLayout(cardLayout);
		setContentPane(panelPrincipal);		
	
	}

    // *************************************************************
    //                    CONTROL DE VISTAS
    // *************************************************************

    public void mostrarVista(String vista) {
        cardLayout.show(panelPrincipal, vista);
    }
    

	// *************************************************************
	//                         PRE-JUEGO
	// *************************************************************
    
	@Override
	public void iniciar() {
		crearMenu();
		setVisible(true);
		mostrarVista("menu");
	}
	
	private void crearMenu() {
		panelMenu = new JPanel();
		panelMenu.setLayout(new BorderLayout());
		panelMenu.setOpaque(false);
		
		/* Falta agregar logo
		 * JLabel lblImagen = new JLabel(new
		 * ImageIcon("/ar/edu/unlu/corazones/img/logo.png"));
		 * lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		 * panelMenu.add(lblImagen, BorderLayout.NORTH);
		 */
	    
	    //Panel de botones
	    JPanel panelBotones = new JPanel();
	    panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
	    panelBotones.setOpaque(false); 
        
	    int botonAncho = 200;
        int botonAlto = 40;
        
        JButton btnCrearJugador = crearBoton("Crear jugador", botonAncho, botonAlto);
        JButton btnModificarJugador = crearBoton("Modificar jugador", botonAncho, botonAlto);
        JButton btnListaJugadores = crearBoton("Ver lista de jugadores", botonAncho, botonAlto);
        JButton btnComenzarJuego = crearBoton("Comenzar juego", botonAncho, botonAlto);
        JButton btnSalir = crearBoton("Salir", botonAncho, botonAlto);
        
        
        // Agregar los botones y darles un espaciado
        int espaciado = 20;
        panelBotones.add(Box.createVerticalStrut(espaciado));
        panelBotones.add(btnCrearJugador);
        panelBotones.add(Box.createVerticalStrut(espaciado));
        panelBotones.add(btnModificarJugador);
        panelBotones.add(Box.createVerticalStrut(espaciado));
        panelBotones.add(btnListaJugadores);
        panelBotones.add(Box.createVerticalStrut(espaciado));
        panelBotones.add(btnComenzarJuego);
        panelBotones.add(Box.createVerticalStrut(espaciado));
        panelBotones.add(btnSalir);
        
        // Centrar botones
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        /* LISTENERS DE BOTONES */
        btnCrearJugador.addActionListener(e -> nuevoJugador());
        
        btnModificarJugador.addActionListener(e -> modificarJugador());
        
        btnListaJugadores.addActionListener(e -> listarJugadores());
        
        btnComenzarJuego.addActionListener(e -> iniciarJuego());	
        
        btnSalir.addActionListener(e -> System.exit(0));
        
        // Panel de botones en el centro de la pantalla
        JPanel contenedorBotones = new JPanel();
        contenedorBotones.setLayout(new GridBagLayout());
        contenedorBotones.setOpaque(false); 
        contenedorBotones.add(panelBotones);
        panelMenu.add(contenedorBotones, BorderLayout.CENTER);
        
        panelPrincipal.add(panelMenu, "menu");
	}

	// Método para crear botones con tamaño fijo
    private JButton crearBoton(String texto, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setMaximumSize(new Dimension(ancho, alto));
        boton.setPreferredSize(new Dimension(ancho, alto));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón en el panel
        return boton;
    }
    
    
	// ************************* ALTA ******************************
	
	private void nuevoJugador() {
		if (!this.controlador.isCantidadJugadoresValida()) {
			
			String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo jugador:", 
                    "Nuevo Jugador", JOptionPane.PLAIN_MESSAGE);
			
			if (nombre != null && !nombre.trim().isEmpty()) {
				
	            this.controlador.agregarJugador(nombre);
	            JOptionPane.showMessageDialog(this, "Jugador agregado con éxito.", 
	                                          "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            
	        } else {
	        	
	            JOptionPane.showMessageDialog(this, "El nombre del jugador no puede estar vacio.", 
	                                          "Error", JOptionPane.ERROR_MESSAGE);
	        }
		} else {
		
	        JOptionPane.showMessageDialog(this, "Ya están todos los jugadores inscritos.", 
                    "Límite Alcanzado", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	// ********************* MODIFICACION **************************

	private void modificarJugador() {
		String[] jugadores = this.controlador.listaJugadores();
		
	    if (jugadores == null || jugadores.length == 0) {
	        JOptionPane.showMessageDialog(this, "No hay jugadores registrados para modificar.", 
	                                      "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    String seleccion = (String) JOptionPane.showInputDialog(this, "Seleccione el jugador a modificar:", 
                "Modificar Jugador", JOptionPane.QUESTION_MESSAGE, 
                null, jugadores, jugadores[0]);
	    
	    if (seleccion != null) {

	        int pos = Arrays.asList(jugadores).indexOf(seleccion) + 1; //Obtengo la posicion en el arreglo
	        
	        String nuevoNombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre para el jugador:", 
	                                                          "Modificar Jugador", JOptionPane.PLAIN_MESSAGE);
	        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
	        	
	            boolean modificado = controlador.modificarJugador(nuevoNombre, pos);
	            
	            if (modificado) {
	                JOptionPane.showMessageDialog(this, "Jugador modificado con éxito.", 
	                                              "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                
	            } else {
	            	
	                JOptionPane.showMessageDialog(this, "No se pudo modificar el jugador.", 
	                                              "Error", JOptionPane.ERROR_MESSAGE);
	                
	            }
	        } else {
	        	
	            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", 
	                                          "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	// ******************* LISTA DE JUGADORES  *********************
	
	private void listarJugadores() {
	    String[] jugadores = this.controlador.listaJugadores();
	    
	    if (jugadores == null || jugadores.length == 0) {
	    	
	        JOptionPane.showMessageDialog(this, "No hay jugadores registrados.", 
	                                      "Información", JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }

	    StringBuilder lista = new StringBuilder("Lista de jugadores:\n");
	    
	    for (int i = 0; i < jugadores.length; i++) {
	        lista.append((i + 1)).append(") ").append(jugadores[i] != null ? jugadores[i] : "(Sin agregar)").append("\n");
	    }

	    JOptionPane.showMessageDialog(this, lista.toString(), 
	                                  "Lista de Jugadores", JOptionPane.INFORMATION_MESSAGE);
	}

	// ******************* COMENZAR JUEGO *************************
	
	private void iniciarJuego() {
    	if ( this.controlador.isCantidadJugadoresValida() ) {
    		
    		JOptionPane.showMessageDialog(this, "Juego comenzado!", 
                    "Juego iniciado", JOptionPane.INFORMATION_MESSAGE);
    		controlador.iniciarJuego();    		
    	} else {
    		
    		JOptionPane.showMessageDialog(this, "Faltan jugadores para comenzar el juego", 
                    "Jugadores insuficientes", JOptionPane.ERROR_MESSAGE);
    	}
    }
	
	// *************************************************************
	//                          JUEGO
	// *************************************************************
	
	private void crearVistaJuego() {
		panelJuego = new JPanel(new BorderLayout());
		panelJuego.setOpaque(false); 
		
		//Paneles para cada jugador
		panelSur = crearPanelJugador(this.controlador.listaJugadores()[0]);
		panelOeste = crearPanelJugador(this.controlador.listaJugadores()[1]);
		panelNorte = crearPanelJugador(this.controlador.listaJugadores()[2]);
        panelEste = crearPanelJugador(this.controlador.listaJugadores()[3]);
        
        // Panel central para cartas jugadas
        panelCentro = crearPanelCentro(); 
        
        panelJuego.add(panelNorte, BorderLayout.NORTH);
        panelJuego.add(panelSur, BorderLayout.SOUTH);
        panelJuego.add(panelEste, BorderLayout.EAST);
        panelJuego.add(panelOeste, BorderLayout.WEST);
        panelJuego.add(panelCentro, BorderLayout.CENTER);
	
        panelPrincipal.add(panelJuego, "juego");
	}
	
	private JPanel crearPanelCentro() {

	    JPanel panel = new JPanel();
	    panel.setOpaque(false);
	    panel.setLayout(new GridBagLayout());

	    // GridBagConstraints es usado para controlar la posición de los componentes en la grilla
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0; // Columna 0 (posición horizontal)
	    gbc.gridy = 0; // Fila 0 (posición vertical)
	    gbc.weightx = 1.0; // Indica que las celdas horizontales deberían expandirse si es necesario
	    gbc.weighty = 1.0; // Indica que las celdas verticales deberían expandirse si es necesario
	    gbc.anchor = GridBagConstraints.CENTER; // Posiciona los componentes en el centro de cada celda

	    // Crea las vistas sin cartas, quedan en blanco
	    VistaCarta cartaNorte = new VistaCarta();
	    VistaCarta cartaSur = new VistaCarta();
	    VistaCarta cartaEste = new VistaCarta();
	    VistaCarta cartaOeste = new VistaCarta();
	    
	    labelCentro = new JLabel("Centro");
	    labelCentro.setFont(new Font("Arial", Font.BOLD, 16)); // Cambiar tamaño y estilo de letra
	    labelCentro.setForeground(Color.BLUE); // Cambiar color del texto

	    /*NORTE*/
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    panel.add(cartaNorte, gbc);

	    /*ESTE*/
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    panel.add(cartaEste, gbc);

	    /*OESTE*/
	    gbc.gridx = 2;
	    gbc.gridy = 1;
	    panel.add(cartaOeste, gbc);

	    /*SUR*/
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    panel.add(cartaSur, gbc);
	    
	    /*CENTRO*/
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    panel.add(labelCentro, gbc);

	    return panel;
	}
	
	private JPanel crearPanelJugador(String nombreJugador) {
	    JPanel panel = new JPanel(new BorderLayout());
	    panel.setOpaque(false);
	    panel.setPreferredSize(new Dimension(150, 200));
	    panel.setBorder(BorderFactory.createTitledBorder(
	    	    BorderFactory.createLineBorder(Color.WHITE, 2),
	    	    nombreJugador, TitledBorder.CENTER, TitledBorder.TOP, 
	    	    new Font("Tahoma", Font.BOLD, 14), Color.WHITE));
	    panel.setName(nombreJugador);
	    
	    // Contenedor para las cartas
	    JPanel contenedorCartas = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    contenedorCartas.setBackground(new Color(50, 100, 50));
	    contenedorCartas.setOpaque(true);
	    panel.add(contenedorCartas, BorderLayout.CENTER);

	    // Asocio el nombre del jugador con el contenedor
	    contenedorCartas.setName(nombreJugador);
	    
	    return panel;
	}
	
	public void mostrarMensajeCentro(String mensaje) {
	    labelCentro.setText(mensaje);
	    labelCentro.revalidate();
	    labelCentro.repaint();
	}
	
	public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, 
                "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	// ****************** CARTAS REPARTIDAS ************************
	
	@Override
	public void cartasRepartidas() {
		crearVistaJuego();
		mostrarVista("juego"); // Cambiar a la vista de juego
		
		String[] jugadores = this.controlador.listaJugadores();
		for (int i = 0; i < this.controlador.cantidadJugadores(); i++) {
			ArrayList<Carta> cartasJugador = this.controlador.manoJugador(i);
			
			mostrarCartasJugador(jugadores[i], cartasJugador);
		}
		
		System.out.println("Cartas repartidas");
	}
	
	private void mostrarCartasJugador(String nombreJugador, ArrayList<Carta> cartas) {
		JPanel contenedorCartas = obtenerContenedorCartas(nombreJugador);
		
		for (Carta carta: cartas) {
			VistaCarta vistaCarta = new VistaCarta(carta);
			contenedorCartas.add(vistaCarta);
		}
		
		// Refrescar la vista
        contenedorCartas.revalidate();
        contenedorCartas.repaint();
	}
	
	private JPanel obtenerContenedorCartas(String nombreJugador) {
	    JPanel[] panelesJugadores = { panelSur, panelNorte, panelEste, panelOeste };
	    
	    for (JPanel panelJugador : panelesJugadores) {
	        for (Component componente : panelJugador.getComponents()) {
	            if (componente instanceof JPanel) {
	                JPanel contenedorCartas = (JPanel) componente;
	                if (nombreJugador.equals(contenedorCartas.getName())) {
	                    return contenedorCartas;
	                }
	            }
	        }
	    }
	    
	    return null; // No se encontró el contenedor
    }
	
	// ********************** PEDIR CARTA ***************************
	
	@Override
	public void pedirCarta() {
		// TODO Auto-generated method stub
		System.out.println("Pedir cartas");
		
		String jugadorActual = this.controlador.nombreJugadorActual();
		
		String mensaje = "Es el turno del jugador " +  jugadorActual;
		
		int indiceCarta = mostrarSeleccionCarta(mensaje);
		
		if (indiceCarta >= 0) {
			System.out.println(indiceCarta);
	        controlador.cartaJugada(indiceCarta);
	    } else {
	        mostrarMensajeCentro("Selección inválida. Intente nuevamente.");
	        pedirCarta(); // Volver a pedir si el índice no es válido
	    }
	}
	
	private int mostrarSeleccionCarta(String text) {
	    String entrada = JOptionPane.showInputDialog(
	            this, 
	            "Ingrese el número de la carta que desea jugar (1 a X):", 
	            text, 
	            JOptionPane.QUESTION_MESSAGE
	        );

	    System.out.println(entrada);
	        try {
	            int seleccion = Integer.parseInt(entrada);
	            return seleccion - 1;
	        } catch (NumberFormatException e) {
	            return -1;
	        }
	}

	// ******************** JUGAR DOS DE TREBOL ********************

	@Override
	public void jugarDosDeTrebol() {
		System.out.println("Jugar dos de trebol");
		
		String jugadorActual = this.controlador.nombreJugadorActual();
		
		 JOptionPane.showMessageDialog(this, "Comienza la ronda el jugador " +  jugadorActual
					+ " ya que tiene el 2 de trebol", 
                 "Comienzo de ronda", JOptionPane.INFORMATION_MESSAGE);
		
		pedirCarta();
	}
	
	// ****************** CARTA TIRADA INVALIDA ********************
	
	@Override
	public void cartaTiradaInvalida() {
		mostrarMensajeError("La carta que seleccioanste es invalida."
				+ "Tienes que tirar una carta del mismo palo que la que esta en la mesa."
				+ "Por favor, intentalo denuevo.");
		pedirCarta();
	}
	
	@Override
	public void cartaTiradaInvalida2deTrebol() {
		mostrarMensajeError("La carta que seleccioanste es invalida. "
				+ "Para comenzar el juego si o si tienes que tirar "
				+ "el 2 de Trebol. Por favor, intentalo denuevo.");
		pedirCarta();
	}
	
	// ******************** PERDEDOR JUGADA ************************
	
	@Override
	public void perdedorJugada() {
		
		mostrarMensajeError("El perdedor de esta jugada es "
				+ this.controlador.jugadorPerdedorJugada() + "\n");
	}
	
	// *************************************************************
	//                		 OBSERVER
	// *************************************************************


	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	@Override
	public void corazonesRotos() {
		// TODO Auto-generated method stub
		System.out.println("Corazones rotos!");
	}


}
