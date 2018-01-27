package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerBD {

	private final ConnectBD con;
	
	public ControllerBD(){
		
		this.con=new ConnectBD();	
	}
	
	public void crearConexion() throws SQLException{
		
		this.con.EstablecerConexion();
	}
	
	public ResultSet mandarSql(String sql)  throws SQLException{
		
		ResultSet aux=this.con.EjecutarSentencia(sql);
		return aux;
	}
	
public ResultSet mandarSql(PreparedStatement sql)  throws SQLException{
		
		ResultSet aux=this.con.EjecutarSentencia(sql);
		return aux;
	}
	
	public ResultSet setStatementSelect(String str1,String str2){
//		Connection c= con.getConnection();
		String str="SELECT * FROM "+str1+" WHERE "+str2;
		try {
//			PreparedStatement st=c.prepareStatement(str);
			ResultSet rs;
		
//			st.setString(1, str1);
			rs=mandarSql(str);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
