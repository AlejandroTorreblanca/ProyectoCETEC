package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import controlador.Controlador;

@SuppressWarnings("serial")
public class PanelOperario extends JPanel {

	private Controlador controlador;
	private JButton confirmarButton;
	private JTextField textoOperario;
	private JDateChooser fechaChooser1 ;
	private JDateChooser fechaChooser2 ;
	private JTextField textoNombre;
	private JTextField xMov;
	private JTextField xOpe;
	private JTextField xFecha;
	private JTextField xNombre;
	private JTextField xHoras;
	private JTextField xTrabajo;
	private JTable tabla;
	private ModeloTabla modelo;
	private JScrollPane scrollPane;

	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}

	public PanelOperario() {
		controlador = Controlador.getUnicaInstancia();
		JLabel rotuloOperario = new JLabel("Operario: ", SwingConstants.CENTER);
		JLabel rotuloFecha1 = new JLabel("Desde Fecha: ", SwingConstants.CENTER);
		JLabel rotuloFecha2 = new JLabel("Hasta Fecha: ", SwingConstants.CENTER);
		textoOperario = new JTextField("");
		fixedSize(textoOperario, 150, 30);
		textoNombre = new JTextField("");
		fixedSize(textoNombre, 500, 30);
		fechaChooser1 = new JDateChooser();
		fixedSize(fechaChooser1, 150, 24);
		fechaChooser2 = new JDateChooser();
		fixedSize(fechaChooser2, 150, 24);
		
		xMov = new JTextField("");
		fixedSize(xMov, 150, 30);
		xOpe = new JTextField("");
		fixedSize(xOpe, 150, 30);
		xFecha = new JTextField("");
		fixedSize(xFecha, 150, 30);
		xNombre = new JTextField("");
		fixedSize(xNombre, 300, 30);
		xHoras = new JTextField("");
		fixedSize(xHoras, 50, 30);
		xTrabajo = new JTextField("");
		fixedSize(xTrabajo, 500, 30);
		xTrabajo.setEditable(false);
		xTrabajo.setText("hola");
		
//		textoOperario.addKeyListener(this);

		modelo = new ModeloTabla();
		tabla = new JTable(modelo);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tabla.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tabla);
		tabla.setCellSelectionEnabled(false);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panel1 =new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.setAlignmentX(RIGHT_ALIGNMENT);
		panel1.add(rotuloOperario);
		panel1.add(textoOperario);
		panel1.add(Box.createRigidArea(new Dimension(30, 15)));
		panel1.add(textoNombre);
		
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.setAlignmentX(RIGHT_ALIGNMENT);
		panel2.add(rotuloFecha1);
		panel2.add(fechaChooser1);
		
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.setAlignmentX(RIGHT_ALIGNMENT);
		panel3.add(rotuloFecha2);
		panel3.add(fechaChooser2);
		
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel4.setAlignmentX(RIGHT_ALIGNMENT);
		panel4.add(xMov);
		panel4.add(xOpe);
		panel4.add(xFecha);
		panel4.add(xNombre);
		panel4.add(xHoras);
		
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.setAlignmentX(RIGHT_ALIGNMENT);
		panel5.add(xTrabajo);
		

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(panel1);
		add(panel2);
		add(panel3);
		add(Box.createRigidArea(new Dimension(50, 15)));
		add(scrollPane);
		add(Box.createRigidArea(new Dimension(50, 15)));
		add(panel4);
		add(panel5);
		add(Box.createRigidArea(new Dimension(50, 15)));
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == confirmarButton) {
//			String str1 = textoOperario.getText();
//			String str2 = textoTrabajo.getText();
//			String str3 = textoHoras.getText();
//			String nombre = "";
//			String dialogo= "Compruebe que los datos son correctos:\nOperario: "+str1+"\nTrabajo: "+str2+"\nHoras: "+str3;
//			int res = JOptionPane.showConfirmDialog(this,dialogo, "Confirmación de datos",
//					JOptionPane.YES_NO_OPTION);
//			if (res == JOptionPane.YES_OPTION)
//				if (!((str1.compareTo("") == 0) || (str2.compareTo("") == 0) || (str3.compareTo("") == 0))) {
//					try {
//						ResultSet rs = controlador.setStatementSelect("CTCOPE", "OPERARIO=" + str1);
//						rs.first();
//						nombre = rs.getString("NOMBRE");
//						rs = controlador.setStatementSelectMAX();
//						rs.first();
//						Integer mov = rs.getInt(1) + 1;
//						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//						Date date = new Date();
//						String fecha = dateFormat.format(date);
//						String datos = "(" + mov.toString() + ",'" + str2 + "',?,'" + str1 + "'," + str3 + ",'" + nombre
//								+ "')";
//						System.out.println(datos);
//						controlador.setStatementUpdate("CTCMOV",
//								"(MOVIMIENTO,TRABAJO,FECHA,OPERARIO,HORAS,DESCRIPCION)", datos);
//					} catch (SQLException e1) {
//						new PanelMensaje("Error en Los datos introducidos.\nCompruebe que los datos son correctos.",
//								"Error", "error");
//						e1.printStackTrace();
//					}
//				} else {
//					new PanelMensaje("Error en Los datos introducidos.\nCompruebe que los datos son correctos.",
//							"Error", "error");
//				}
//
//		}
//
//	}

//	@Override
//	public void keyPressed(KeyEvent e) {
//		if (e.getKeyCode() == KeyEvent.VK_ENTER)
//			confirmarButton.doClick();
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void keyTyped(KeyEvent e) {
//		// TODO Auto-generated method stub
//
//	}
}
