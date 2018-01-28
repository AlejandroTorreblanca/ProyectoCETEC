package gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Controlador;

public class PanelOperario extends JPanel implements ActionListener, KeyListener {

	private Controlador controlador;
	private JButton confirmarButton;
	private JTextField textoOperario;
	private JTextField textoTrabajo;
	private JTextField textoHoras;

	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}

	public PanelOperario() {
		controlador = Controlador.getUnicaInstancia();
		JLabel rotuloOperario = new JLabel("Identificador Operario: ", SwingConstants.CENTER);
		JLabel rotuloTrabajo = new JLabel("Identificador Trabajo: ", SwingConstants.CENTER);
		JLabel rotuloHoras = new JLabel("Horas: ", SwingConstants.CENTER);
		textoOperario = new JTextField("");
		fixedSize(textoOperario, 150, 30);
		textoTrabajo = new JTextField("");
		fixedSize(textoTrabajo, 150, 30);
		textoHoras = new JTextField("");
		fixedSize(textoHoras, 150, 30);
		textoTrabajo.addKeyListener(this);
		textoOperario.addKeyListener(this);
		textoHoras.addKeyListener(this);

		confirmarButton = new JButton("Confirmar");
		confirmarButton.setMargin(new Insets(2, 21, 2, 21));

		confirmarButton.addActionListener(this);

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
		panelBusqueda.add(rotuloHoras);
		panelBusqueda.add(Box.createRigidArea(new Dimension(10, 10)));
		panelBusqueda.add(textoHoras);
		panelBusqueda.add(Box.createRigidArea(new Dimension(25, 25)));

		panelBotones1.setLayout(new BoxLayout(panelBotones1, BoxLayout.X_AXIS));
		panelBotones1.add(confirmarButton);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(panelBusqueda);
		add(Box.createRigidArea(new Dimension(50, 15)));
		add(panelBotones1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmarButton) {
			String str1 = textoOperario.getText();
			String str2 = textoTrabajo.getText();
			String str3 = textoHoras.getText();
			String nombre = "";
			String dialogo= "Compruebe que los datos son correctos:\nOperario: "+str1+"\nTrabajo: "+str2+"\nHoras: "+str3;
			int res = JOptionPane.showConfirmDialog(this,dialogo, "Confirmación de datos",
					JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION)
				if (!((str1.compareTo("") == 0) || (str2.compareTo("") == 0) || (str3.compareTo("") == 0))) {
					try {
						ResultSet rs = controlador.setStatementSelect("CTCOPE", "OPERARIO=" + str1);
						rs.first();
						nombre = rs.getString("NOMBRE");
						rs = controlador.setStatementSelectMAX();
						rs.first();
						Integer mov = rs.getInt(1) + 1;
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						Date date = new Date();
						String fecha = dateFormat.format(date);
						String datos = "(" + mov.toString() + ",'" + str2 + "',?,'" + str1 + "'," + str3 + ",'" + nombre
								+ "')";
						System.out.println(datos);
						controlador.setStatementUpdate("CTCMOV",
								"(MOVIMIENTO,TRABAJO,FECHA,OPERARIO,HORAS,DESCRIPCION)", datos);
					} catch (SQLException e1) {
						new PanelMensaje("Error en Los datos introducidos.\nCompruebe que los datos son correctos.",
								"Error", "error");
						e1.printStackTrace();
					}
				} else {
					new PanelMensaje("Error en Los datos introducidos.\nCompruebe que los datos son correctos.",
							"Error", "error");
				}

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			confirmarButton.doClick();
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
