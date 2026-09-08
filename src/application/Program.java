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
		System.out.println("Teste insert");
		UserDao user = DaoFactory.createUserDao();
		User u1 = new User(null,"Kaio","Kaio@gmail.com");
//		user.insert(u1);
//		System.out.println(u1.getId());
		System.out.println("Teste update");
		u1.setName("KaioSilva");
		u1.setEmail("KaioS@gmail.com");
		u1.setId(12);
		user.update(u1);
	}

}
