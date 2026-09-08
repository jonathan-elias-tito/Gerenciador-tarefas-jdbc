package application;

import java.sql.Connection;

import db.DbConn;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.impl.UserJDBC;
import model.entities.User;

public class Program {

	public static void main(String[] args) {

		Connection conn = DbConn.initConection();
		System.out.println("Conectado");
		DbConn.closeConnection();
//	UserDao user = DaoFactory.createUserDao();
	User u1 = new User(5,"Sarah gau","Euamoela@gmail.com");
//	user.insert(u1);
//	
		System.out.println(u1.getId());
	;
	}

}
