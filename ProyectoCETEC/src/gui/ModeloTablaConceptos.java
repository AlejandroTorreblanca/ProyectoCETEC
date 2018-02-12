package gui;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTablaConceptos extends DefaultTableModel {
	private String[] columnNames = { "CD", "Descripcion del Concepto"};

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

	public void addFila(int cd, String descripcion) {
		Object[] nuevafila = new Object[3];
		nuevafila[0] = cd;
		nuevafila[1] = descripcion;
		addRow(nuevafila);
	}

	public int getCDSeleccionado(int row) {
		return (int) getValueAt(row, 0);
	}
	
	public String getDescripcionSeleccionado(int row) {
		return (String) getValueAt(row, 1);
	}

}

