package ar.edu.unlu.corazones.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelEste;
	private JPanel panelOeste;
	private JPanel panelCentro;

	
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
        
        btnComenzarJuego.addActionListener(e -> comenzarJuego());	
        
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

	// ******************* COMENZAR JUEGO **** *********************
	
	private void comenzarJuego() {
    	if ( this.controlador.isCantidadJugadoresValida() ) {
    		
    		JOptionPane.showMessageDialog(this, "Juego comenzado!", 
                    "Juego iniciado", JOptionPane.INFORMATION_MESSAGE);
    		controlador.comenzarJuego();
    		crearVistaJuego();
    		mostrarVista("juego"); // Cambiar a la vista de juego
    		
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
        panelNorte = crearPanelJugador(this.controlador.listaJugadores()[1]);
        panelEste = crearPanelJugador(this.controlador.listaJugadores()[2]);
        panelOeste = crearPanelJugador(this.controlador.listaJugadores()[3]);
        
        // Panel central para cartas jugadas
        panelCentro = new JPanel();
        panelCentro.setOpaque(false);
        panelCentro.setLayout(new GridLayout(2, 2, 10, 10)); // Espacio para las cartas  
        
        panelJuego.add(panelNorte, BorderLayout.NORTH);
        panelJuego.add(panelSur, BorderLayout.SOUTH);
        panelJuego.add(panelEste, BorderLayout.EAST);
        panelJuego.add(panelOeste, BorderLayout.WEST);
        panelJuego.add(panelCentro, BorderLayout.CENTER);
	
        panelPrincipal.add(panelJuego, "juego");
	}
	
	private JPanel crearPanelJugador(String nombreJugador) {
	    JPanel panel = new JPanel(new BorderLayout());
	    panel.setOpaque(false);
	    panel.setPreferredSize(new Dimension(150, 200));
	    
	    //Etiqueta que tiene el nombre del jugador 
	    JLabel etiqueta = new JLabel(nombreJugador, SwingConstants.CENTER);
	    etiqueta.setForeground(Color.WHITE);
	    panel.add(etiqueta, BorderLayout.NORTH);
	    
	    // Contenedor para las cartas
	    JPanel contenedorCartas = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    contenedorCartas.setBackground(new Color(50, 100, 50));
	    contenedorCartas.setOpaque(true);
	    panel.add(contenedorCartas, BorderLayout.CENTER);

	    // Asocio el nombre del jugador con el contenedor
	    contenedorCartas.setName(nombreJugador);
	    
	    return panel;
	}
	

	
	@Override
	public void cartasRepartidas() {
		System.out.println("Cartas repartidas");
	}
	
	// *************************************************************
	//                		 OBSERVER
	// *************************************************************

	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

}
