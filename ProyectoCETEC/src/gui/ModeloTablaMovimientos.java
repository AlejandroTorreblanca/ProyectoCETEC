package gui;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTablaMovimientos extends DefaultTableModel {
	private String[] columnNames = { "N�mero","Trabajo","Fecha","Nombre","Horas"};

	@Override
	public boolean isCellEditable(int row, int column){
		return false;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int column) {
	    return columnNames[column];
	}
	
	public void addFila(String numero, String nOperario, String fecha, String nombre, int h){
		Object [] nuevafila=new Object[5];
		nuevafila[0]=numero;
		nuevafila[1]=nOperario;
		nuevafila[2]=fecha;
		nuevafila[3]=nombre;
		nuevafila[4]=h;
		addRow(nuevafila);
	}
	
	public String getNumeroSeleccionado(int row){
		return (String)getValueAt(row, 0);	
	}
	
	public String getOperarioSeleccionado(int row){
		return (String)getValueAt(row, 1);	
	}
	
	public String getFechaSeleccionado(int row){
		return (String)getValueAt(row, 2);	
	}
	
	public String getNombreSeleccionado(int row){
		return (String)getValueAt(row, 3);	
	}
	
	public int getHorasSeleccionado(int row){
		return (int)getValueAt(row, 4);	
	}

	
}
