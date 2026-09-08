package application;

import java.sql.Connection;

import db.DbConn;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class Program {

	public static void main(String[] args) {

		Connection conn = DbConn.initConection();
		System.out.println("Conectado");
		DbConn.closeConnection();
		System.out.println("Test insert");
		UserDao user = DaoFactory.createUserDao();
		User u1 = new User(null,"Jonathan","Jonh@gmail.com");
//		user.insert(u1);
		System.out.println("Test update");
//		u1.setName("Jonathan Elias");
//		u1.setEmail("jonElias@gmail.com");
//		user.update(u1);
		System.out.println("Test delete");
//		user.deleteById(2)?;
		System.out.println("Test findById");
		User u2=user.findById(14);
		System.out.println(u2);
	}

}
