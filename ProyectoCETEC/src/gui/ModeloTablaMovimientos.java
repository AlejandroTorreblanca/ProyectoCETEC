package gui;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTablaMovimientos extends DefaultTableModel {
	private String[] columnNames = { "Fecha","Operario","Concepto","Descripción","Horas","Precio","Importe"};

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
	
	public void addFila(String fecha, String operario,String concepto, String descrip, int h, String precio, String cantidad){
		Object [] nuevafila=new Object[7];
		nuevafila[0]=fecha;
		nuevafila[1]=operario;
		nuevafila[2]=concepto;
		nuevafila[3]=descrip;
		nuevafila[4]=h;
		nuevafila[5]=precio;
		nuevafila[6]=cantidad;
		addRow(nuevafila);
	}
	
	public String getFechaSeleccionada(int row){
		return (String)getValueAt(row, 0);	
	}
	
	public String getOperarioSeleccionado(int row){
		return (String)getValueAt(row, 1);	
	}
	
	public String getDescripSeleccionada(int row){
		return (String)getValueAt(row, 2);	
	}
	
	public int getHoraSeleccionada(int row){
		return (int)getValueAt(row, 3);	
	}
	
	public int getPrecioSeleccionado(int row){
		return (int)getValueAt(row, 4);	
	}
	
	public int getCantidadSeleccionado(int row){
		return (int)getValueAt(row, 5);	
	}

	
}
