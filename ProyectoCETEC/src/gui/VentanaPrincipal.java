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
	private JPanel panelCentral;
	private PanelInicial panelInicial;
	private PanelOperario panelOperario;

	private void initialize() {

		panelInicial = new PanelInicial();
		panelCentral = new JPanel();
		panelOperario = new PanelOperario();

		panelCentral.setLayout(new CardLayout());
		panelCentral.add(panelInicial, "Inicial");
		panelCentral.add(panelOperario, "Operario");

	}
	
	public void inicializarPaneles() {

		initialize();
		setLayout(new BorderLayout(10, 10));
		PanelSupBotones panelS = new PanelSupBotones(this);
		panelS.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 15));
		add(panelS, BorderLayout.NORTH);

		PanelIzqBotones panelIzq = new PanelIzqBotones(this);
		add(panelIzq, BorderLayout.WEST);
		add(panelCentral, BorderLayout.CENTER);
		JPanel p1 = new JPanel();
		p1.add(Box.createRigidArea(new Dimension(100, 100)));
		add(p1, BorderLayout.EAST);
		JPanel p2 = new JPanel();
		p2.add(Box.createRigidArea(new Dimension(50, 50)));
		add(p2, BorderLayout.SOUTH);
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
		add(panelCentral);
		validate();
		setResizable(true);
		
	}

	public void setPanelInicial() {
		CardLayout cl = (CardLayout) panelCentral.getLayout();
		cl.show(panelCentral, "Inicial");
		validate();
	}

	public void setPanelOperario() {
		CardLayout cl = (CardLayout) panelCentral.getLayout();
		cl.show(panelCentral, "Operario");
		validate();
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
