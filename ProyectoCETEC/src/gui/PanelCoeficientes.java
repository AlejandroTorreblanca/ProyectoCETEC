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

import controlador.Controlador;

public class PanelCoeficientes extends JPanel implements ActionListener{

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
		
		this.window=w;
		controlador=Controlador.getUnicaInstancia();
		
		JLabel rotuloA�o = new JLabel("A�o de Proceso: ", SwingConstants.CENTER);
		
		textoA�o= new JTextField();
		fixedSize(textoA�o, 50, 24);
		textoA�o.setEditable(false);
		textoPersonal= new JTextField();
		fixedSize(textoPersonal, 100, 24);
		textoOficina= new JTextField();
		fixedSize(textoOficina, 100, 24);
		
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
		scrollPane.setMaximumSize(new Dimension(300, 350));
		tabla.setCellSelectionEnabled(false);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
		        if(e.getClickCount()==1){
		        	actualizarTexto(0);
		        }
		    }
		});
		tabla.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_DOWN ){
					actualizarTexto(1);
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP){
					actualizarTexto(-1);
				}
					
				
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
		
		add(pCentral,BorderLayout.CENTER);
		
		pEste.add(Box.createRigidArea(new Dimension(100, 100)));
		add(pEste, BorderLayout.EAST);
		
		pSur.add(Box.createRigidArea(new Dimension(50, 50)));
		add(pSur, BorderLayout.SOUTH);
	}
	
	public void inicializarTabla(){
		try {
			maxFilas=0;
			ResultSet rs=controlador.setStatementSelect("CTCCOE", "");
			int a�o=0;
			String personal,oficina;
			while(rs.next()){
				a�o=rs.getInt("A�O");
				personal=rs.getString("PERSONAL");
				oficina=rs.getString("OFICINA");
				modelo.addFila(a�o, personal, oficina);
				maxFilas++;
			}
			modelo.addFila(a�o+1, "", "");
			maxFilas++;
			a�oLibre=Integer.toString(a�o+1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void actualizarTexto(int i){
		int filaSeleccionada=tabla.getSelectedRow()+i;
		if(filaSeleccionada<0)
			filaSeleccionada=0;
		if(filaSeleccionada>=maxFilas)
			filaSeleccionada=maxFilas-1;
		int a�o=modelo.getA�oSeleccionado(filaSeleccionada);
		String personal=modelo.getPersonalSeleccionado(filaSeleccionada);
		String oficina=modelo.getOficinaSeleccionado(filaSeleccionada);
		textoA�o.setText(Integer.toString(a�o));
		textoPersonal.setText(personal);
		textoOficina.setText(oficina);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== confirmarButton){
			String a�o=textoA�o.getText();
			String personal=textoPersonal.getText();
			String oficina=textoOficina.getText();
			
			try {
				if(a�o.compareTo(a�oLibre)!=0){
					String str1="A�O="+a�o+",PERSONAL="+personal+",OFICINA="+oficina;
					String str2="A�O="+a�o;
					controlador.setStatementUpdate("CTCCOE", str1, str2);
				}
				else
				{
					String str="('"+a�o+"','"+personal+"','"+oficina+"')";
					controlador.setStatementInsert("CTCCOE", "(A�O,PERSONAL,OFICINA)", str);
				}
				vaciarTabla();
				inicializarTabla();
					
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		else if (e.getSource()== cancelarButton){
			window.setPanelInicial();
		}
		
	}
	
	public void vaciarTabla() {
		while(modelo.getRowCount()>0) modelo.removeRow(0);
	}

}
