package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



@SuppressWarnings("serial")
public class PanelIzqBotones extends JPanel implements ActionListener {

	private VentanaPrincipal window;
	private JButton operarioButton;
	private JButton proyectoButton;
	private JButton horasButton;
	
	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}
	
	public PanelIzqBotones(VentanaPrincipal window) {
		
		this.window=window;
		operarioButton = new JButton("Operario");
		operarioButton.setMargin(new Insets(2, 28, 2, 28));
		operarioButton.addActionListener(this);
		
		proyectoButton = new JButton("Proyecto");
		proyectoButton.setMargin(new Insets(2, 27, 2, 28));
		proyectoButton.addActionListener(this);
		
		horasButton = new JButton("Horas");
		horasButton.setMargin(new Insets(2, 36, 2, 36));
		horasButton.addActionListener(this);
		
		setAlignmentY(Component.TOP_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(horasButton); add(proyectoButton); add(operarioButton);  

	}
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource()== operarioButton){
			window.setPanelOperario();
		}
		if (e.getSource()== proyectoButton){
			
			}
		if (e.getSource()== horasButton){
			window.setPanelInicial();
		}
	}
	
	
}












