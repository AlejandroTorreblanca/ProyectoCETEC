package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerBD {

	private final ConnectBD con;

	public ControllerBD() {

		this.con = new ConnectBD();
	}

	public void crearConexion() throws SQLException {

		this.con.EstablecerConexion();
	}

	public ResultSet mandarSql(String sql) throws SQLException {

		ResultSet aux = this.con.ejecutarQuery(sql);
		return aux;
	}

	public ResultSet setStatementSelect(String str1, String str2) {
		String str = "SELECT * FROM " + str1 + " WHERE " + str2;
		try {
			ResultSet rs;
			rs = mandarSql(str);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet setStatementInsert(String str1, String str2) {
		String str = "SELECT * FROM " + str1 + " WHERE " + str2;
		try {
			ResultSet rs;
			rs = mandarSql(str);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
