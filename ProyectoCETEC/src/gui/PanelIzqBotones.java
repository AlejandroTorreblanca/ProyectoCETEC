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
	private JPanel panelLista;
	private JButton explorarButton;
	private JButton nuevaListaButton;
	private JButton recienteButton;
	private JButton listasButton;
	private JButton masEscuchadasButton;
	
	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}
	
	public PanelIzqBotones(VentanaPrincipal window) {
		
		this.window=window;
		explorarButton = new JButton("Explorar");
		explorarButton.setMargin(new Insets(2, 37, 2, 37));
		explorarButton.addActionListener(this);
		
		nuevaListaButton = new JButton("Nueva Lista");
		nuevaListaButton.setMargin(new Insets(2, 28, 2, 28));
		nuevaListaButton.addActionListener(this);
		
		recienteButton = new JButton("Reciente");
		recienteButton.setMargin(new Insets(2, 36, 2, 36));
		recienteButton.addActionListener(this);
		
		listasButton = new JButton("Mis Listas");
		listasButton.setMargin(new Insets(2, 32, 2, 32));
		listasButton.addActionListener(this);
		
		masEscuchadasButton = new JButton("Más escuchadas ");
		masEscuchadasButton.setMargin(new Insets(2, 11, 2, 11));
		masEscuchadasButton.addActionListener(this);
		
		panelLista= new JPanel();
		panelLista.setAlignmentX(LEFT_ALIGNMENT);

		panelLista.setVisible(false);
		
		
		setAlignmentY(Component.TOP_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(explorarButton); add(nuevaListaButton); add(recienteButton);  add(masEscuchadasButton); add(listasButton); add(panelLista);

	}
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		
	}
	
}












