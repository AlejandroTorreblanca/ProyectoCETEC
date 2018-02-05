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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import controlador.Controlador;

@SuppressWarnings("serial")
public class PanelTrabajos extends JPanel implements ActionListener {

	private Controlador controlador;
	private VentanaPrincipal window;
	private JTextField textoCodigo;
	private JTextField textoClave;
	private JTextField textoDeno;
	private JTextArea textoDescrip;
	private JTextField textoCliente;
	private JTextField textoTrabajador;
	private JTextField textoNombre;
	private JTextField textoEsti;
	private JTextField textoAdju;
	private JTextField textoCob;
	private JTextField textoFra;
	private JTextArea textoObs;
	private JDateChooser fechaChooser1;
	private JButton confirmarButton;
	private JButton cancelarButton;
	
	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}
	
	public PanelTrabajos(VentanaPrincipal w) {
		
		this.window=w;
		this.controlador=Controlador.getUnicaInstancia();
		JLabel rotuloCodigo = new JLabel("C�digo: ", SwingConstants.CENTER);
		JLabel rotuloClave = new JLabel("Clave Trabajo: ", SwingConstants.CENTER);
		JLabel rotuloFecha = new JLabel("Fecha de Contrato: ", SwingConstants.CENTER);
		JLabel rotuloDeno = new JLabel("Denominaci�n: ", SwingConstants.CENTER);
		JLabel rotuloDescrip = new JLabel("Descripci�n: ", SwingConstants.CENTER);
		JLabel rotuloCliente = new JLabel("Cliente: ", SwingConstants.CENTER);
		JLabel rotuloTrabajador = new JLabel("Trabajador: ", SwingConstants.CENTER);
		JLabel rotuloEstimado = new JLabel("Presupuesto Estimado: ", SwingConstants.CENTER);
		JLabel rotuloAdjudicado = new JLabel("Presupuesto Adjudicado: ", SwingConstants.CENTER);
		JLabel rotuloCob = new JLabel("Cob: ", SwingConstants.CENTER);
		JLabel rotuloFra = new JLabel("Fra: ", SwingConstants.CENTER);
		JLabel rotuloObs = new JLabel("Observaciones: ", SwingConstants.CENTER);
		
		textoCodigo= new JTextField();
		fixedSize(textoCodigo, 50, 24);
		textoClave= new JTextField();
		fixedSize(textoClave, 25, 24);
		fechaChooser1 = new JDateChooser();
		fechaChooser1.setDateFormatString("dd/MM/yyyy");
		fixedSize(fechaChooser1, 100, 24);
		textoDeno= new JTextField();
		fixedSize(textoDeno, 500, 24);
		textoDescrip= new JTextArea();
		textoDescrip.setBorder(BorderFactory.createLineBorder(Color.gray));
		textoDescrip.setLineWrap(true);
		textoCliente= new JTextField();
		fixedSize(textoCliente, 500, 24);
		textoTrabajador= new JTextField();
		fixedSize(textoTrabajador, 50, 24);
		textoNombre= new JTextField();
		textoNombre.setEditable(false);
		fixedSize(textoNombre, 450, 24);
		textoEsti= new JTextField();
		fixedSize(textoEsti, 100, 24);
		textoAdju= new JTextField();
		fixedSize(textoAdju, 100, 24);
		textoCob= new JTextField();
		fixedSize(textoCob, 25, 24);
		textoFra= new JTextField();
		fixedSize(textoFra, 25, 24);
		textoObs= new JTextArea();
		textoObs.setBorder(BorderFactory.createLineBorder(Color.gray));
		textoObs.setLineWrap(true);
		
		textoCodigo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					actualizarPlantilla();
				}
			}
		});
		textoTrabajador.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					textoNombre.setText(buscarTrabajador(textoTrabajador.getText()));
				}
			}
		});
		
		confirmarButton = new JButton("Confirmar");
		confirmarButton.setMargin(new Insets(2, 28, 2, 28));
		confirmarButton.addActionListener(this);
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setMargin(new Insets(2, 28, 2, 28));
		cancelarButton.addActionListener(this);
		
		JScrollPane scrollDesc = new JScrollPane(textoDescrip);
		JScrollPane scrollObs = new JScrollPane(textoObs);
		fixedSize(scrollObs, 500, 100);
		fixedSize(scrollDesc, 500, 100);
		
		JPanel panelCentral = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(rotuloCodigo);
		panel1.add(Box.createRigidArea(new Dimension(62, 15)));
		panel1.add(textoCodigo);
		panel1.add(Box.createRigidArea(new Dimension(15, 15)));
		panel1.add(rotuloClave);
		panel1.add(Box.createRigidArea(new Dimension(15, 15)));
		panel1.add(textoClave);
		panel1.add(Box.createRigidArea(new Dimension(87, 15)));
		panel1.add(rotuloFecha);
		panel1.add(Box.createRigidArea(new Dimension(15, 15)));
		panel1.add(fechaChooser1);
		
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(rotuloDeno);
		panel2.add(Box.createRigidArea(new Dimension(20, 15)));
		panel2.add(textoDeno);
		
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(rotuloDescrip);
		panel3.add(Box.createRigidArea(new Dimension(33, 15)));
		panel3.add(scrollDesc);
		
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel4.add(rotuloCliente);
		panel4.add(Box.createRigidArea(new Dimension(62, 15)));
		panel4.add(textoCliente);
		
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.add(rotuloTrabajador);
		panel5.add(Box.createRigidArea(new Dimension(39, 15)));
		panel5.add(textoTrabajador);
		panel5.add(textoNombre);
		
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
		panel6.add(rotuloEstimado);
		panel6.add(Box.createRigidArea(new Dimension(25, 15)));
		panel6.add(textoEsti);
		panel6.add(Box.createRigidArea(new Dimension(50, 15)));
		panel6.add(rotuloCob);
		panel6.add(Box.createRigidArea(new Dimension(11, 15)));
		panel6.add(textoCob);
		
		panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));
		panel7.add(rotuloAdjudicado);
		panel7.add(Box.createRigidArea(new Dimension(15, 15)));
		panel7.add(textoAdju);
		panel7.add(Box.createRigidArea(new Dimension(50, 15)));
		panel7.add(rotuloFra);
		panel7.add(Box.createRigidArea(new Dimension(15, 15)));
		panel7.add(textoFra);
		
		panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));
		panel8.add(rotuloObs);
		panel8.add(Box.createRigidArea(new Dimension(15, 15)));
		panel8.add(scrollObs);
		
		panel9.setLayout(new BoxLayout(panel9, BoxLayout.X_AXIS));
		panel9.add(confirmarButton);
		panel9.add(Box.createRigidArea(new Dimension(50, 15)));
		panel9.add(cancelarButton);
		
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panel1.setAlignmentX(LEFT_ALIGNMENT);
		panel2.setAlignmentX(LEFT_ALIGNMENT);
		panel3.setAlignmentX(LEFT_ALIGNMENT);
		panel4.setAlignmentX(LEFT_ALIGNMENT);
		panel5.setAlignmentX(LEFT_ALIGNMENT);
		panel6.setAlignmentX(LEFT_ALIGNMENT);
		panel7.setAlignmentX(LEFT_ALIGNMENT);
		panel8.setAlignmentX(LEFT_ALIGNMENT);
		
		panelCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(Box.createRigidArea(new Dimension(25, 25)));
		panelCentral.add(panel1);
		panelCentral.add(panel2);
		panelCentral.add(panel3);
		panelCentral.add(panel4);
		panelCentral.add(panel5);
		panelCentral.add(panel6);
		panelCentral.add(panel7);
		panelCentral.add(panel8);

		
		JPanel pCentral = new JPanel();
		JPanel pNorte = new JPanel();
		JPanel pEste = new JPanel();
		JPanel pSur = new JPanel();
		JPanel pOeste = new JPanel();

		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		pCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
		pCentral.add(panelCentral);
		pCentral.add(Box.createRigidArea(new Dimension(25, 25)));
		pCentral.add(panel9);
		
		JLabel rotuloSuperior = new JLabel("MANTENIMIENTO DE TRABAJOS", SwingConstants.CENTER);
		Font font = new Font("Arial", Font.BOLD, 30);
		rotuloSuperior.setFont(font);
		pNorte.setMaximumSize(new Dimension(700, 100));
		pNorte.setAlignmentX(Component.CENTER_ALIGNMENT);
		pNorte.add(rotuloSuperior);
		pNorte.setBorder(BorderFactory.createLineBorder(Color.black));
		
		setLayout(new BorderLayout(10, 10));
		add(pNorte, BorderLayout.NORTH);
		pOeste.add(Box.createRigidArea(new Dimension(100, 100)));
		add(pOeste, BorderLayout.WEST);
		
		add(pCentral,BorderLayout.CENTER);
		
		pEste.add(Box.createRigidArea(new Dimension(100, 100)));
		add(pEste, BorderLayout.EAST);
		
		pSur.add(Box.createRigidArea(new Dimension(10, 10)));
		add(pSur, BorderLayout.SOUTH);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== confirmarButton){
			guardarCambios();
		}
		else if (e.getSource()== cancelarButton){
			window.setPanelInicial();
		}
		
	}
	
	public void guardarCambios(){
		String str="NRO_TRABAJO='"+textoCodigo.getText()+"'";
		try {
			ResultSet rs=controlador.setStatementSelect("CTCTRB",str );
			if(rs.first()){	//Update
				String str1 = "CLAVE_TRABAJO='" + textoClave.getText() + "',FECHA_CONTRATO=?,DENOMINACION='"
						+ textoDeno.getText() + "',DESCRIPCION='" + textoDescrip.getText() + "',CLIENTE='"
						+ textoCliente.getText() + "',TRABAJADOR='" + textoTrabajador.getText() + "',ESTIMADO='"
						+ textoEsti.getText() + "',ADJUDICADO='" + textoAdju.getText() + "',FACTURADO='"
						+ textoFra.getText() + "',COBRADO='" + textoCob.getText() + "',OBSERVACIONES='" + textoObs.getText()
						+ "'";
				String str2="NRO_TRABAJO='"+textoCodigo.getText()+"'";
				controlador.setStatementUpdate("CTCTRB", str1, str2,fechaChooser1.getDate());
			}
			else { // Insert
				String str1 = "(NRO_TRABAJO,CLAVE_TRABAJO,FECHA_CONTRATO,DENOMINACION,DESCRIPCION,CLIENTE,"
						+ "TRABAJADOR,ESTIMADO,ADJUDICADO,FACTURADO,COBRADO,OBSERVACIONES)";
				String str2 = "('" + textoCodigo.getText() + "','" + textoClave.getText() + "', ? ,'"
						+ textoDeno.getText() + "','" + textoDescrip.getText() + "','" + textoCliente.getText() + "','"
						+ textoTrabajador.getText() + "','" + textoEsti.getText() + "','" + textoAdju.getText() + "','"
						+ textoFra.getText() + "','" + textoCob.getText() + "','" + textoObs.getText() + "')";
				controlador.setStatementInsert("CTCTRB", str1, str2,fechaChooser1.getDate());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarPlantilla(){
		String str="NRO_TRABAJO='"+textoCodigo.getText()+"'";
		try {
			ResultSet rs=controlador.setStatementSelect("CTCTRB",str );
			if(rs.first()){
				double value1 = Double.parseDouble(rs.getString("ESTIMADO"));
				double value2 = Double.parseDouble(rs.getString("ADJUDICADO"));
				
				textoClave.setText(rs.getString("CLAVE_TRABAJO"));
				textoDeno.setText(rs.getString("DENOMINACION"));
				textoDescrip.setText(rs.getString("DESCRIPCION"));
				textoCliente.setText(rs.getString("CLIENTE"));
				textoTrabajador.setText(rs.getString("TRABAJADOR"));
				textoNombre.setText(buscarTrabajador(rs.getString("TRABAJADOR")));
				textoEsti.setText(String.format("%.2f", value1));
				textoAdju.setText(String.format("%.2f", value2));
				textoFra.setText(rs.getString("FACTURADO"));
				textoCob.setText(rs.getString("COBRADO"));
				textoObs.setText(rs.getString("OBSERVACIONES"));
				fechaChooser1.setDate(rs.getDate("FECHA_CONTRATO"));
			}
			else{
				textoClave.setText("");
				textoDeno.setText("");
				textoDescrip.setText("");
				textoCliente.setText("");
				textoTrabajador.setText("");
				textoNombre.setText("");
				textoEsti.setText("");
				textoAdju.setText("");
				textoFra.setText("");
				textoCob.setText("");
				textoObs.setText("");
				fechaChooser1.setDate(null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String buscarTrabajador(String codigo){
		String str="OPERARIO='"+codigo+"'";
		try {
			ResultSet rs=controlador.setStatementSelect("CTCOPE",str );
			if(rs.first())
				return rs.getString("NOMBRE");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}














