package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelSupBotones extends JPanel implements ActionListener{
	
	private VentanaPrincipal window;
	private JButton logoutButton;
	private JButton iconoButton;
	
	public PanelSupBotones(VentanaPrincipal window) {
		this.window=window;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		logoutButton = new JButton("Logout");
		logoutButton.setFont(new Font("Arial", Font.BOLD, 11));
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBackground(Color.LIGHT_GRAY);
		logoutButton.addActionListener(this);
		iconoButton = new JButton("");
		
		add(Box.createRigidArea(new Dimension(20,20)));
		add(iconoButton);
		add(Box.createRigidArea(new Dimension(20,20)));
		add(logoutButton);
	}

	
	@Override
	public void actionPerformed(ActionEvent e){
		
		
	}

}
