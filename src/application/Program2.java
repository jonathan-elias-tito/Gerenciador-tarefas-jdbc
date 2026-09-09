package application;

import java.sql.Connection;
import java.util.Date;

import db.DbConn;
import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.dao.UserDao;
import model.entities.Task;
import model.entities.User;

public class Program2 {

	public static void main(String[] args) {

		Connection conn = DbConn.initConection();
		System.out.println("Connected");
		System.out.println("Test insert");
		TaskDao task = DaoFactory.createTaskDao();
		User u1 = new User();
		UserDao user = DaoFactory.createUserDao();
		u1 = user.findById(14);
		Task task1 = new Task(null, "Cabeleleiro", "Cortar cabelo", new Date(), "Pendente", u1);
		task.insert(task1);
		System.out.println("User:" + task1.getUser().getName());
		System.out.println("Test update");
		task1.setStatus("Concluido");
		task.update(task1);
	}
}
