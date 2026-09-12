package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbConn {

	public static Connection conn = null;

//Metodo que conecta ao banco de dados
	public static Connection initConection() {
		try {
			if (conn == null) {
				Properties props = getProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}
		} catch (SQLException e) {
			throw new DbException("Erro de conexão com o banco causa:" + e.getMessage());
		}
		return conn;

	}

//Metodo que encerra a conexão com o banco de dados
	public static void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			throw new DbException("Erro para encerrar conexão causa:" + e.getMessage());
		}
	}

//Metodo que captura as propriedades do Banco de dados
	private static Properties getProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties");) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException("Erro ao abrir arquivo causa:" + e.getMessage());
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException("Error in close results set" + e.getMessage());
			}
		}

	}

	public static void closerStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException("Error in close Statement" + e.getMessage());
			}
		}
	}
}
