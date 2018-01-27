package gui;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTabla extends DefaultTableModel {
	private String[] columnNames = { "Operario","Nombre","Trabajo","Horas"};

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
	
	public void addFila(String nOperario, String nTrabajo, String nombre, int h){
		Object [] nuevafila=new Object[4];
		nuevafila[0]=nOperario;
		nuevafila[1]=nombre;
		nuevafila[2]=nTrabajo;
		nuevafila[3]=h;
		addRow(nuevafila);
	}
	
	public String getTrabajadorSeleccionado(int row){
		return (String)getValueAt(row, 0);	
	}

	
}
