package baseDatos;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import java.sql.SQLException;
 
import java.sql.Statement;
import java.sql.Timestamp;

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
            JOptionPane.showMessageDialog(null,"Error al crear el objeto sentencia "+e);
            return false;
        }
        return true;
 
    }
    public ResultSet ejecutarQuery( String sql) throws SQLException{
        ResultSet rs;
        rs = this.sentencia.executeQuery(sql);
        return rs;
    }
    
    public void ejecutarUpdate( String sql) throws SQLException{
    	java.util.Date utilDate = new java.util.Date();
    	PreparedStatement ps= conexion.prepareStatement(sql);
    	ps.setTimestamp(1, new Timestamp(utilDate.getTime()));
    	ps.executeUpdate();
        //int i = this.sentencia.executeUpdate(sql);
    }
    
    public void getStatement() throws SQLException{
    	java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    System.out.println("utilDate:" + utilDate);
	    System.out.println("sqlDate:" + sqlDate);
    	PreparedStatement ps= conexion.prepareStatement("INSERT INTO CTCMOV(MOVIMIENTO,FECHA) VALUES (?,?)");
		ps.setString(1, "200032");
		ps.setTimestamp(2, new Timestamp(utilDate.getTime()));
		ps.executeUpdate();
    	
    	//this.sentencia.executeUpdate("INSERT INTO CTCMOV(MOVIMIENTO,TRABAJO,OPERARIO,HORAS,DESCRIPCION) VALUES (200001,123,132,5,'jose, miguel')");
    
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
