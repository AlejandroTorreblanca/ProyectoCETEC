package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import baseDatos.ConnectBD;
import baseDatos.ControllerBD;

public class Controlador {

	private static Controlador unicaInstancia;
	private ControllerBD controladorBD;
	private final ConnectBD con;

	private Controlador() {
		controladorBD = new ControllerBD();
		this.con = new ConnectBD();
		try {
			this.crearConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}
	
	public void crearConexion() throws SQLException {
		this.con.EstablecerConexion();
	}

	public ResultSet mandarSql(String sql) throws SQLException {
		ResultSet aux = this.con.EjecutarSentencia(sql);
		return aux;
	}

	public ResultSet mandarSql(PreparedStatement sql) throws SQLException {
		ResultSet aux = this.con.EjecutarSentencia(sql);
		return aux;
	}

	public ResultSet setStatementSelect(String str1, String str2) throws SQLException {
		String str = "SELECT * FROM " + str1 + " WHERE " + str2;
			ResultSet rs;
			rs = mandarSql(str);
			return rs;

	}

	
	
	

}
