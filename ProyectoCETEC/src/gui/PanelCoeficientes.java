package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import controlador.Controlador;

public class PanelCoeficientes extends JPanel implements ActionListener {

	private VentanaPrincipal window;
	private Controlador controlador;
	private JTextField textoA�o;
	private JTextField textoPersonal;
	private JTextField textoOficina;
	private JTable tabla;
	private ModeloTablaCoef modelo;
	private JScrollPane scrollPane;
	private JButton confirmarButton;
	private JButton cancelarButton;
	private String a�oLibre;
	private int maxFilas;

	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}

	public PanelCoeficientes(VentanaPrincipal w) {

		this.window = w;
		controlador = Controlador.getUnicaInstancia();

		JLabel rotuloA�o = new JLabel("A�o de Proceso: ", SwingConstants.CENTER);

		textoA�o = new JTextField();
		fixedSize(textoA�o, 50, 24);
		textoA�o.setEditable(false);
		textoPersonal = new JTextField();
		fixedSize(textoPersonal, 75, 24);
		textoOficina = new JTextField();
		fixedSize(textoOficina, 75, 24);

		confirmarButton = new JButton("Confirmar");
		confirmarButton.setMargin(new Insets(2, 28, 2, 28));
		confirmarButton.addActionListener(this);
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setMargin(new Insets(2, 28, 2, 28));
		cancelarButton.addActionListener(this);

		JPanel panelCentral = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		modelo = new ModeloTablaCoef();
		tabla = new JTable(modelo);
		tabla.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tabla);
		scrollPane.setPreferredSize(new Dimension(100, 50));
		scrollPane.setMaximumSize(new Dimension(200, 350));
		tabla.setCellSelectionEnabled(false);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn columna = tabla.getColumn("A�o");
		columna.setMaxWidth(50);
		columna = tabla.getColumn("Personal");
		columna.setMaxWidth(75);
		columna = tabla.getColumn("Oficina");
		columna.setMaxWidth(75);

		ListSelectionModel cellSelectionModel = tabla.getSelectionModel();
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				actualizarTexto();
			}
		});

		inicializarTabla();

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(Box.createRigidArea(new Dimension(113, 15)));
		panel1.add(scrollPane);

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(rotuloA�o);
		panel2.add(Box.createRigidArea(new Dimension(17, 15)));
		panel2.add(textoA�o);
		panel2.add(textoPersonal);
		panel2.add(textoOficina);

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(confirmarButton);
		panel3.add(Box.createRigidArea(new Dimension(50, 15)));
		panel3.add(cancelarButton);

		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panel1.setAlignmentX(LEFT_ALIGNMENT);
		panel2.setAlignmentX(LEFT_ALIGNMENT);

		panelCentral.add(Box.createRigidArea(new Dimension(50, 50)));
		panelCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(panel1);
		panelCentral.add(panel2);

		JPanel pCentral = new JPanel();
		JPanel pNorte = new JPanel();
		JPanel pEste = new JPanel();
		JPanel pSur = new JPanel();
		JPanel pOeste = new JPanel();

		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		pCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
		pCentral.add(panelCentral);
		pCentral.add(Box.createRigidArea(new Dimension(50, 50)));
		pCentral.add(panel3);

		JLabel rotuloSuperior = new JLabel("MANTENIMIENTO DE COEFICIENTES", SwingConstants.CENTER);
		Font font = new Font("Arial", Font.BOLD, 30);
		rotuloSuperior.setFont(font);
		pNorte.setAlignmentX(Component.CENTER_ALIGNMENT);
		pNorte.add(rotuloSuperior);
		pNorte.setBorder(BorderFactory.createLineBorder(Color.black));

		setLayout(new BorderLayout(10, 10));
		add(pNorte, BorderLayout.NORTH);
		pOeste.add(Box.createRigidArea(new Dimension(100, 100)));
		add(pOeste, BorderLayout.WEST);

		add(pCentral, BorderLayout.CENTER);

		pEste.add(Box.createRigidArea(new Dimension(100, 100)));
		add(pEste, BorderLayout.EAST);

		pSur.add(Box.createRigidArea(new Dimension(50, 50)));
		add(pSur, BorderLayout.SOUTH);
	}

	public void inicializarTabla() {
		try {
			maxFilas = 0;
			ResultSet rs = controlador.setStatementSelect("CTCCOE", "");
			int a�o = 0;
			while (rs.next()) {
				a�o = rs.getInt("A�O");
				double value1 = Double.parseDouble(rs.getString("PERSONAL"));
				double value2 = Double.parseDouble(rs.getString("OFICINA"));
				modelo.addFila(a�o, String.format("%.2f", value1), String.format("%.2f", value2));
				maxFilas++;
			}
			modelo.addFila(a�o + 1, "", "");
			maxFilas++;
			a�oLibre = Integer.toString(a�o + 1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void actualizarTexto() {
		int filaSeleccionada = tabla.getSelectedRow();
		if (filaSeleccionada != -1) {
			int a�o = modelo.getA�oSeleccionado(filaSeleccionada);
			String personal = modelo.getPersonalSeleccionado(filaSeleccionada);
			String oficina = modelo.getOficinaSeleccionado(filaSeleccionada);
			textoA�o.setText(Integer.toString(a�o));
			textoPersonal.setText(personal);
			textoOficina.setText(oficina);
		}

	}

	public void guardarCambios(){
		String a�o = textoA�o.getText();
		String personal = textoPersonal.getText();
		String oficina = textoOficina.getText();
		int control = 0;
		textoA�o.setBackground(new Color(255,255,255));
		textoPersonal.setBackground(new Color(255,255,255));
		textoOficina.setBackground(new Color(255,255,255));
		if (a�o.compareTo("") == 0) {
			textoA�o.setBackground(new Color(255,153,153));
			control = 1;
		}
		if (personal.compareTo("") == 0) {
			textoPersonal.setBackground(new Color(255,153,153));
			control = 1;
		}

		if (oficina.compareTo("") == 0) {
			textoOficina.setBackground(new Color(255,153,153));
			control = 1;
		}
		if (control == 0)
			try {
				if (a�o.compareTo(a�oLibre) != 0) {
					String str1 = "PERSONAL=" + personal + ",OFICINA=" + oficina;
					String str2 = "A�O=" + a�o;
					controlador.setStatementUpdate("CTCCOE", str1, str2);
				} else {
					String str = "('" + a�o + "','" + personal + "','" + oficina + "')";
					controlador.setStatementInsert("CTCCOE", "(A�O,PERSONAL,OFICINA)", str);
				}
				vaciarTabla();
				inicializarTabla();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmarButton) {
			guardarCambios();
		} else if (e.getSource() == cancelarButton) {
			window.setPanelInicial();
		}

	}

	public void vaciarTabla() {
		while (modelo.getRowCount() > 0)
			modelo.removeRow(0);
	}

}
