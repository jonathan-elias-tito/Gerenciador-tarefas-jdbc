package application;

import java.sql.Connection;

import db.DbConn;

public class Program {

	public static void main(String[] args) {

		Connection conn = DbConn.initConection();
		System.out.println("Conectado");
		DbConn.closeConnection();
		System.out.println("Desconectado");
	}

}
