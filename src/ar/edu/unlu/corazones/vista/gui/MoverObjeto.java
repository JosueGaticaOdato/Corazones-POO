package ar.edu.unlu.corazones.vista.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MoverObjeto extends JPanel {
    private int x = 50; // Posición inicial en X
    private int y = 50; // Posición inicial en Y
    private final int ANCHO = 50; // Ancho del rectángulo
    private final int ALTO = 50; // Alto del rectángulo

    public MoverObjeto() {
        // Detectar el clic del ratón
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Al hacer clic, mover el rectángulo a una nueva posición
                x = e.getX() - ANCHO / 2; // Centrar el rectángulo en el clic
                y = e.getY() - ALTO / 2;
                repaint(); // Redibujar el panel
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED); // Color del rectángulo
        g.fillRect(x, y, ANCHO, ALTO); // Dibujar el rectángulo en la nueva posición
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mover Objeto");
        MoverObjeto panel = new MoverObjeto();
        
        frame.setSize(500, 500); // Tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }
}