package application;

import java.sql.Connection;
import java.util.List;

import db.DbConn;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class Program {

	public static void main(String[] args) {

		Connection conn = DbConn.initConection();
		System.out.println("--- CONEXÃO REALIZADA ---");

		UserDao userDao = DaoFactory.createUserDao();

		System.out.println("\n=== TEST 1: findById ===");
		User user1 = userDao.findById(1); // Cassio
		System.out.println(user1);

		System.out.println("\n=== TEST 2: findAll ===");
		List<User> list = userDao.findAll();
		for (User obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n=== TEST 3: insert ===");
		User newUser = new User(null, "Jonathan", "jonathan@email.com");
		userDao.insert(newUser);
		System.out.println("Inserido! Novo id de usuário = " + newUser.getId());

		System.out.println("\n=== TEST 4: update ===");
		newUser.setName("Jonathan Elias");
		newUser.setEmail("jonathan.elias@email.com");
		userDao.update(newUser);
		System.out.println("Update efetuado com sucesso!");

		System.out.println("\n=== TEST 5: delete ===");
		userDao.deleteById(newUser.getId());
		System.out.println("Deleção de teste realizada!");

		DbConn.closeConnection();
	}
}