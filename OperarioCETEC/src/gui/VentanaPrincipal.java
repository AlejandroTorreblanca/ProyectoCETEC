package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.Controlador;

public class VentanaPrincipal extends JFrame {

	private static VentanaPrincipal window;
	private PanelOperario panelOperario;

	
	public void inicializarPaneles() {

		panelOperario = new PanelOperario();
		setLayout(new BorderLayout(10, 10));
		JPanel p0 = new JPanel();
		p0.add(Box.createRigidArea(new Dimension(50, 50)));
		add(p0, BorderLayout.NORTH);
		JPanel p1 = new JPanel();
		p1.add(Box.createRigidArea(new Dimension(100, 100)));
		add(p1, BorderLayout.WEST);
		add(panelOperario,BorderLayout.CENTER);
		JPanel p2 = new JPanel();
		p2.add(Box.createRigidArea(new Dimension(100, 100)));
		add(p2, BorderLayout.EAST);
		JPanel p3 = new JPanel();
		p3.add(Box.createRigidArea(new Dimension(50, 50)));
		add(p3, BorderLayout.SOUTH);
	}
	

	public VentanaPrincipal() {	
		inicializarPaneles();
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamPantalla = miPantalla.getScreenSize();
		int alturaPantalla = tamPantalla.height;
		int anchoPantalla = tamPantalla.width;
		setSize(anchoPantalla, (int) (alturaPantalla-50 ));
		setLocation(0, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CETEC");
		Image miIcono = miPantalla.getImage("Iconos/spotify.png");
		setIconImage(miIcono);
		setVisible(true);
		validate();
		setResizable(true);
		
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new VentanaPrincipal();
					window.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				//Controlador.getUnicaInstancia().close();
			}
		});
	}

}
