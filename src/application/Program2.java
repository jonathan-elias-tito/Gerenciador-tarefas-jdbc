package application;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import db.DbConn;
import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.dao.UserDao;
import model.entities.Task;
import model.entities.User;

public class Program2 {

	public static void main(String[] args) {
		Connection conn = DbConn.initConection();
		TaskDao taskDao = DaoFactory.createTaskDao();
		UserDao userDao = DaoFactory.createUserDao();

		System.out.println("=== TEST 1: findById ===");
		Task task = taskDao.findById(50);
		System.out.println(task);

		System.out.println("\n=== TEST 2: findByUser ===");
		User user = userDao.findById(14);
		List<Task> list = taskDao.findByUser(user);
		for (Task obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n=== TEST 3: findAll ===");
		list = taskDao.findAll();
		for (Task obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n=== TEST 4: insert ===");
		Task newTask = new Task(null, "Treino", "Ir para a academia", new Date(), "Pendente", user);
		taskDao.insert(newTask);
		System.out.println("Inserted! New id = " + newTask.getId());

		System.out.println("\n=== TEST 5: update ===");
		task = taskDao.findById(newTask.getId());
		task.setStatus("Concluido");
		taskDao.update(task);
		System.out.println("Update completed!");
		
//		System.out.println("\n=== TEST 6: delete ===");
//		taskDao.deleteById(newTask.getId());
//		System.out.println("Delete completed!");
		
		System.out.println("\n=== TEST 7: findByStatus ===");
		list=taskDao.findByStatus("Concluido"); 
		for(Task obj: list) {
			System.out.println(obj);
		}
	}
}