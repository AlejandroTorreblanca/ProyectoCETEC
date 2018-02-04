package gui;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTablaCoef extends DefaultTableModel {
	private String[] columnNames = { "A�o", "Personal","Oficina" };

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public void addFila(int a�o, String personal, String oficina) {
		Object[] nuevafila = new Object[3];
		nuevafila[0] = a�o;
		nuevafila[1] = personal;
		nuevafila[2] = oficina;
		addRow(nuevafila);
	}

	public int getA�oSeleccionado(int row) {
		return (int) getValueAt(row, 0);
	}
	
	public String getPersonalSeleccionado(int row) {
		return (String) getValueAt(row, 1);
	}

	public String getOficinaSeleccionado(int row) {
		return (String) getValueAt(row, 2);
	}
}
