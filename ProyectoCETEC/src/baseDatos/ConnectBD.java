package baseDatos;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import java.sql.SQLException;
 
import java.sql.Statement;
 
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
 
    public ResultSet EjecutarSentencia( String sql) throws SQLException{
 
        ResultSet rs;
        rs = this.sentencia.executeQuery(sql);
        return rs;
    }
    
    public ResultSet EjecutarSentencia( PreparedStatement sql) throws SQLException{
    	 
        ResultSet rs;
        rs = sql.executeQuery();
        return rs;
    }
    
    public Connection getConnection(){
    	return this.conexion;
    }
    
    public PreparedStatement getStatement(String str){
		try {
			return conexion.prepareStatement(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
