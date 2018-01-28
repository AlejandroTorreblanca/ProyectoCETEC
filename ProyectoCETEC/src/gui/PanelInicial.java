package gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import baseDatos.ControllerBD;
import controlador.Controlador;

@SuppressWarnings("serial")
class PanelInicial extends JPanel implements ActionListener, KeyListener {

	private Controlador controlador;
	private JButton buscarButton;
	private JButton limpiarButton;
	private JTextField textoOperario;
	private JTextField textoTrabajo;
	private JTable tabla;
	private ModeloTabla modelo;
	private JScrollPane scrollPane;

	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}

	public PanelInicial() {

		controlador = Controlador.getUnicaInstancia();
		JLabel rotuloOperario = new JLabel("Identificador Operario: ", SwingConstants.CENTER);
		JLabel rotuloTrabajo = new JLabel("Identificador Trabajo: ", SwingConstants.CENTER);
		textoOperario = new JTextField("");
		fixedSize(textoOperario, 150, 30);
		textoTrabajo = new JTextField("");
		fixedSize(textoTrabajo, 150, 30);
		textoTrabajo.addKeyListener(this);
		textoOperario.addKeyListener(this);

		buscarButton = new JButton("Buscar");
		buscarButton.setMargin(new Insets(2, 21, 2, 21));
		limpiarButton = new JButton("Limpiar");
		limpiarButton.setMargin(new Insets(2, 21, 2, 21));

		buscarButton.addActionListener(this);
		limpiarButton.addActionListener(this);

		modelo = new ModeloTabla();
		tabla = new JTable(modelo);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tabla.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tabla);
		tabla.setCellSelectionEnabled(false);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel panelBusqueda = new JPanel();
		JPanel panelBotones1 = new JPanel();

		panelBusqueda.setLayout(new BoxLayout(panelBusqueda, BoxLayout.X_AXIS));
		panelBusqueda.add(rotuloOperario);
		panelBusqueda.add(Box.createRigidArea(new Dimension(10, 10)));
		panelBusqueda.add(textoOperario);
		panelBusqueda.add(Box.createRigidArea(new Dimension(25, 25)));
		panelBusqueda.add(rotuloTrabajo);
		panelBusqueda.add(Box.createRigidArea(new Dimension(10, 10)));
		panelBusqueda.add(textoTrabajo);
		panelBusqueda.add(Box.createRigidArea(new Dimension(25, 25)));

		panelBotones1.setLayout(new BoxLayout(panelBotones1, BoxLayout.X_AXIS));
		panelBotones1.add(buscarButton);
		panelBotones1.add(Box.createRigidArea(new Dimension(25, 25)));
		panelBotones1.add(limpiarButton);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(panelBusqueda);
		add(Box.createRigidArea(new Dimension(50, 15)));
		add(panelBotones1);
		add(Box.createRigidArea(new Dimension(50, 15)));
		add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buscarButton) {
			String nOperario = textoOperario.getText();
			String nTrabajo = textoTrabajo.getText();
			String str = "OPERARIO=" + nOperario + " AND TRABAJO=" + nTrabajo;

			ResultSet rs = null;
			try {
				if (!((nOperario.compareTo("") == 0) || (nTrabajo.compareTo("") == 0))) {
					rs = controlador.setStatementSelect("CTCMOV", str);
					int totalHoras = 0;
					String nombre = "";
					while (rs.next()) {
						totalHoras += rs.getInt("HORAS");
						nombre = rs.getString("DESCRIPCION");
					}
					modelo.addFila(nOperario, nTrabajo, nombre, totalHoras);
				}
				else
					new PanelMensaje("Error en Los datos introducidos.\nCompruebe que los datos son correctos.", "Error",
							"error");
			} catch (SQLException e2) {
				new PanelMensaje("Error en Los datos introducidos.\nCompruebe que los datos son correctos.", "Error",
						"error");
				e2.printStackTrace();
			}
		}

		if (e.getSource() == limpiarButton) {
			while (modelo.getRowCount() > 0)
				modelo.removeRow(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			buscarButton.doClick();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}