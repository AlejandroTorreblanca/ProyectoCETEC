package baseDatos;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import java.sql.SQLException;
 
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
 

 
public class ConnectBD {
 
    private Connection conexion;
    private Statement sentencia;
    private final String controlador;
    private final String nombre_bd;
    private final String usuarioBD;
    private final String passwordBD;
 
    public ConnectBD(){
 
        this.controlador="sun.jdbc.odbc.JdbcOdbcDriver";
       // this.nombre_bd="C:\\Users\\Sandra\\Documents\\NetBeansProjects\\BD_Access\\Prueba_Java.accdb";
        String nombreBD="bd.accdb";
        this.nombre_bd=System.getProperty("user.dir").replace("\\", "\\\\")+"\\\\"+nombreBD;
        this.usuarioBD="";
        this.passwordBD="";
    }
 
    public boolean EstablecerConexion() throws SQLException{
        try{
            conexion=DriverManager.getConnection("jdbc:ucanaccess://"+this.nombre_bd,this.usuarioBD,this.passwordBD);
        }catch (SQLException e){
             JOptionPane.showMessageDialog(null,"Error al realizar la conexion "+e);
             return false;
        }
        try {
            this.sentencia=this.conexion.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
 
                    ResultSet.CONCUR_READ_ONLY);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al conectar con la base de datos.\n "+e);
            return false;
        }
        return true;
 
    }
    public ResultSet ejecutarQuery( String sql) throws SQLException{
        ResultSet rs;
        rs = this.sentencia.executeQuery(sql);
        return rs;
    }
    
    public void ejecutarUpdate(String sql) throws SQLException{
    	this.sentencia.executeUpdate(sql);
    }
    
	public void ejecutarUpdate(String sql, Date fecha) throws SQLException {
		if (fecha == null) {
			java.util.Date utilDate = new java.util.Date();
			PreparedStatement ps = conexion.prepareStatement(sql);
			System.out.println(sql);
			ps.setTimestamp(1, new Timestamp(utilDate.getTime()));
			ps.executeUpdate();
		}
		else{
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setTimestamp(1, new Timestamp(fecha.getTime()));
			ps.executeUpdate();
		}
	}
    
    public void getStatement() throws SQLException{
    	String str= "SELECT * FROM CTCMOV WHERE FECHA >= ? AND FECHA <= ?";
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	PreparedStatement ps= conexion.prepareStatement(str);
    	String stringFechaConHora1 = "2015-09-15";
    	String stringFechaConHora2 = "2017-01-15";
    	try {
			Date fechaConHora1 = sdf.parse(stringFechaConHora1);
			Date fechaConHora2 = sdf.parse(stringFechaConHora2);
			ps.setTimestamp(1, new Timestamp(fechaConHora1.getTime()));
			ps.setTimestamp(2, new Timestamp(fechaConHora2.getTime()));
			ResultSet rs;
	        rs = ps.executeQuery();
	        while(rs.next())
	        {
	        	System.out.println(rs.getString("FECHA"));
	        }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
       
    
    }
    
    public void ejecutarUpdate(PreparedStatement ps) throws SQLException
    {
    	ps.executeUpdate();
    }
    
    public Connection getConnection(){
    	return this.conexion;
    }
    
    public void close(){
    	try {
			this.sentencia.close();
			this.conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
