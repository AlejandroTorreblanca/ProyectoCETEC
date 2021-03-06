package gui;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTablaOperario extends DefaultTableModel {
	private String[] columnNames = { "A�o","Precio"};

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
	
	public void addFila(int a�o, String precio){
		Object [] nuevafila=new Object[2];
		nuevafila[0]=a�o;
		nuevafila[1]=precio;
		addRow(nuevafila);
	}
	
	public int getA�oSeleccionado(int row){
		return (int)getValueAt(row, 0);	
	}
	
	public String getPrecioSeleccionado(int row){
		return (String)getValueAt(row, 1);	
	}

	
}
