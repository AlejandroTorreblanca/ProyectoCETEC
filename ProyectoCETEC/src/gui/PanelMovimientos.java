package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import controlador.Controlador;

@SuppressWarnings("serial")
public class PanelMovimientos extends JPanel implements ActionListener {

	private VentanaPrincipal window;
	private Controlador controlador;
	private JButton confirmarButton;
	private JButton cancelarButton;
	private JTextField textoOperario;
	private JDateChooser fechaChooser1 ;
	private JDateChooser fechaChooser2 ;
	private JTextField textoNombre;
	private JTextField xMov;
	private JTextField xTraba;
	private JDateChooser xFecha;
	private JTextField xNombre;
	private JTextField xHoras;
	private JTextField xTrabajo;
	private JTable tabla;
	private ModeloTablaMovimientos modelo;
	private JScrollPane scrollPane;
	private int movMax;

	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}

	public PanelMovimientos(VentanaPrincipal w) {
		
		this.window=w;
		controlador = Controlador.getUnicaInstancia();
		JLabel rotuloOperario = new JLabel("Operario: ", SwingConstants.CENTER);
		JLabel rotuloFecha1 = new JLabel("Desde Fecha: ", SwingConstants.CENTER);
		JLabel rotuloFecha2 = new JLabel("Hasta Fecha: ", SwingConstants.CENTER);
		textoOperario = new JTextField("");
		fixedSize(textoOperario, 50, 30);
		textoNombre = new JTextField("");
		textoNombre.setEditable(false);
		fixedSize(textoNombre, 400, 30);
		fechaChooser1 = new JDateChooser();
		fechaChooser1.setDateFormatString("dd/MM/yyyy");
		fixedSize(fechaChooser1, 100, 24);
		fechaChooser2 = new JDateChooser();
		fechaChooser2.setDateFormatString("dd/MM/yyyy");
		fixedSize(fechaChooser2, 100, 24);
		xMov = new JTextField("");
		xMov.setEditable(false);
		fixedSize(xMov, 150, 30);
		xTraba = new JTextField("");
		fixedSize(xTraba, 150, 30);
		xFecha = new JDateChooser();
		xFecha.setDateFormatString("dd/MM/yyyy");
		fixedSize(xFecha, 100, 30);
		xNombre = new JTextField("");
		xNombre.setEditable(false);
		fixedSize(xNombre, 300, 30);
		xHoras = new JTextField("");
		fixedSize(xHoras, 50, 30);
		xTrabajo = new JTextField("");
		fixedSize(xTrabajo, 300, 30);
		
		textoOperario.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					inicializarDatos();
				}
			}
		});
		
		fechaChooser2.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
               
            }
            @Override
            public void keyPressed(KeyEvent e) {
            	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					vaciarTabla();
					actualizarTabla();
				}
            }
        });

		modelo = new ModeloTablaMovimientos();
		tabla = new JTable(modelo);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tabla.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tabla);
		tabla.setCellSelectionEnabled(false);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ListSelectionModel cellSelectionModel = tabla.getSelectionModel();
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				actualizarTexto();
			}
		});
		
		confirmarButton = new JButton("Confirmar");
		confirmarButton.setMargin(new Insets(2, 28, 2, 28));
		confirmarButton.addActionListener(this);
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setMargin(new Insets(2, 28, 2, 28));
		cancelarButton.addActionListener(this);
		
		JPanel panelCentral = new JPanel();
		JPanel panel1 =new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.setAlignmentX(RIGHT_ALIGNMENT);
		panel1.add(rotuloOperario);
		panel1.add(textoOperario);
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
		panel4.add(xTraba);
		panel4.add(xFecha);
		panel4.add(xNombre);
		panel4.add(xHoras);
		
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.setAlignmentX(RIGHT_ALIGNMENT);
		panel5.add(xTrabajo);
		
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
		panel6.setAlignmentX(RIGHT_ALIGNMENT);
		panel6.add(Box.createRigidArea(new Dimension(160, 15)));
		panel6.add(confirmarButton);
		panel6.add(Box.createRigidArea(new Dimension(50, 15)));
		panel6.add(cancelarButton);
		
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panelCentral.add(Box.createRigidArea(new Dimension(50, 50)));
		panelCentral.add(panel1);
		panelCentral.add(panel2);
		panelCentral.add(panel3);
		panelCentral.add(Box.createRigidArea(new Dimension(50, 15)));
		panelCentral.add(scrollPane);
		panelCentral.add(Box.createRigidArea(new Dimension(50, 15)));
		panelCentral.add(panel4);
		panelCentral.add(panel5);
		panelCentral.add(Box.createRigidArea(new Dimension(50, 15)));
		panelCentral.add(panel6);
		
		
		JPanel pNorte = new JPanel();
		
		JLabel rotuloSuperior = new JLabel("MANTENIMIENTO DE MOVIMIENTOS", SwingConstants.CENTER);
		Font font = new Font("Arial", Font.BOLD, 30);
		rotuloSuperior.setFont(font);
		pNorte.setAlignmentX(Component.CENTER_ALIGNMENT);
		pNorte.add(rotuloSuperior);
		pNorte.setBorder(BorderFactory.createLineBorder(Color.black));
		
		setLayout(new BorderLayout(10, 10));
		add(pNorte, BorderLayout.NORTH);
		add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.WEST);
		add(panelCentral, BorderLayout.CENTER);
	}
	
	public String buscarTrabajador(String codigo) {
		String str = "OPERARIO='" + codigo + "'";
		try {
			ResultSet rs = controlador.setStatementSelect("CTCOPE", str);
			if (rs.first())
				return rs.getString("NOMBRE");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public void actualizarTexto() {
		int filaSeleccionada = tabla.getSelectedRow();
		if (filaSeleccionada != -1) {
			String numero = modelo.getNumeroSeleccionado(filaSeleccionada);
			String operario = modelo.getOperarioSeleccionado(filaSeleccionada);
			String fecha = modelo.getFechaSeleccionado(filaSeleccionada);
			String nombre = modelo.getNombreSeleccionado(filaSeleccionada);
			int horas = modelo.getHorasSeleccionado(filaSeleccionada);
			SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
			xMov.setText(numero);
			xTraba.setText(operario);
			try {
				xFecha.setDate(d.parse(fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			xNombre.setText(nombre);
			xHoras.setText(Integer.toString(horas));
		}
	}
	
	public void inicializarDatos(){
		String nombre=buscarTrabajador(textoOperario.getText());
		textoNombre.setText(nombre);
		try {
			SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
			movMax=controlador.getIdentificadorMOV();
			xMov.setText(Integer.toString(movMax));
			xNombre.setText(nombre);
			Date fecha=new Date();
			xFecha.setDate(fecha);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarTabla(){
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		String str1 = "OPERARIO='" + textoOperario.getText() + "' AND FECHA>=? AND FECHA<=?";
		try {
			ResultSet rs = controlador.setStatementSelect("CTCMOV", str1,fechaChooser1.getDate(),fechaChooser2.getDate());
			String codigo,operario,nombre;
			int horas;
			Date fecha;
			while(rs.next()){
				codigo=rs.getString("MOVIMIENTO");
				operario=rs.getString("TRABAJO");
				fecha=rs.getDate("FECHA");
				nombre=rs.getString("DESCRIPCION");
				horas=rs.getInt("HORAS");
				modelo.addFila(codigo,operario,d.format(fecha),nombre,horas);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarCambios(){
//		//Guardamos en operarios
//		String str="OPERARIO='"+xMov.getText()+"'";
//		try {
//			ResultSet rs=controlador.setStatementSelect("CTCOPE",str );
//			if(rs.first()){	//Update
//				String str1 = "NOMBRE='" + textoNombre.getText() + "',DIRECCION='" + textoDirec.getText()
//						+ "',COD_POSTAL='" + textoPobla1.getText() + "',POBLACION='" + textoPobla2.getText()
//						+ "',PROVINCIA='" + textoProvi.getText() + "',`DNI/CIF`='" + textoDni.getText() + "',TELEFONO_1='"
//						+ textoTele1.getText() + "',TELEFONO_2='" + textoTele2.getText()+ "'";
//				controlador.setStatementUpdate("CTCOPE", str1, str);
//			}
//			else { // Insert
//				String str1 = "(OPERARIO,NOMBRE,DIRECCION,COD_POSTAL,POBLACION,PROVINCIA,"
//						+ "`DNI/CIF`,TELEFONO_1,TELEFONO_2)";
//				String str2 = "('" + textoCodigo.getText() + "','" + textoNombre.getText() + "','"
//						+ textoDirec.getText() + "','" + textoPobla1.getText() + "','" + textoPobla2.getText() + "','"
//						+ textoProvi.getText() + "','" + textoDni.getText() + "','" + textoTele1.getText() + "','"
//						+ textoTele2.getText() + "')";
//				controlador.setStatementInsert("CTCOPE", str1, str2);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== confirmarButton){
//			window.setPanelOperarios();
		}
		else if (e.getSource()== cancelarButton){
			window.setPanelInicial();
		}
		
	}
	
	public void vaciarTabla() {
		while (modelo.getRowCount() > 0)
			modelo.removeRow(0);
	}

}
