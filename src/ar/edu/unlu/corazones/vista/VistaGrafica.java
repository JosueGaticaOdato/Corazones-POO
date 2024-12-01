package ar.edu.unlu.corazones.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ar.edu.unlu.corazones.controlador.Controlador;
import ar.edu.unlu.corazones.vista.gui.FondoTapete;

public class VistaGrafica extends JFrame implements IVista {
	
	private static final long serialVersionUID = 1L;
	
	// *************************************************************
	//                       ATRIBUTOS
	// *************************************************************

	private FondoTapete panelPrincipal;
	
	private Controlador controlador;
	
	private CardLayout cardLayout;
	
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
    
    private void crearVistas() {
    	crearMenu();
        //crearVistaJuego(); // Crea la vista del juego
    }
    
	// *************************************************************
	//                         PRE-JUEGO
	// *************************************************************
    
	@Override
	public void iniciar() {
		crearVistas();
		setVisible(true);
		mostrarVista("menu");
	}
	
	private void crearMenu() {
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new BorderLayout());
		panelMenu.setOpaque(false);
        //panelMenu.setLayout(new GridLayout(5, 1));
		
		//Agrego el logo
		JLabel lblImagen = new JLabel(new ImageIcon("/ar/edu/unlu/corazones/img/logo.png"));
	    lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
	    panelMenu.add(lblImagen, BorderLayout.NORTH);
	    
	    //Panel de botones
	    JPanel panelBotones = new JPanel();
	    panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        
        JButton btnCrearJugador = new JButton("Crear jugador");
        JButton btnModificarJugador = new JButton("Modificar jugador");
        JButton btnListaJugadores = new JButton("Ver lista de jugadores");
        JButton btnComenzarJuego = new JButton("Comenzar juego");
        JButton btnSalir = new JButton("Salir");
        
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
        
        btnComenzarJuego.addActionListener(e -> {
        	System.out.println("Click Comenzar juego");
        	//controlador.comenzarJuego();
        	//mostrarVista("juego"); // Cambiar a la vista de juego
        });	
        
        btnSalir.addActionListener(e -> System.exit(0));
        
        // Panel de botones en el centro de la pantalla
        JPanel contenedorBotones = new JPanel();
        contenedorBotones.setLayout(new GridBagLayout());
        contenedorBotones.add(panelBotones);
        panelMenu.add(contenedorBotones, BorderLayout.CENTER);
        
        panelPrincipal.add(panelMenu, "menu");
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

	
	// *************************************************************
	//                		 OBSERVER
	// *************************************************************

	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

}
