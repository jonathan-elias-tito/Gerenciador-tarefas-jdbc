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

		System.out.println("=== TEST 1: findById (User & Task) ===");
		User user1 = userDao.findById(1); // Cassio
		System.out.println("User encontrado: " + user1);
		
		Task task1 = taskDao.findById(1); // Cabeleireiro
		System.out.println("Task encontrada: " + task1);

		System.out.println("\n=== TEST 2: findByUser ===");
		List<Task> list = taskDao.findByUser(user1);
		for (Task obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n=== TEST 3: findAll ===");
		list = taskDao.findAll();
		for (Task obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n=== TEST 4: insert ===");
		User ryan = userDao.findById(3); // Ryan
		Task newTask = new Task(null, "Treino", "Ir para a academia", new Date(), "Pendente", ryan);
		taskDao.insert(newTask);
		System.out.println("Inserido! Novo id da tarefa = " + newTask.getId());

		System.out.println("\n=== TEST 5: update ===");
		Task taskToUpdate = taskDao.findById(newTask.getId());
		taskToUpdate.setStatus("Concluido");
		taskDao.update(taskToUpdate);
		System.out.println("Update realizado com sucesso!");

		System.out.println("\n=== TEST 6: findByStatus ===");
		list = taskDao.findByStatus("Concluido");
		for (Task obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n=== TEST 7: delete ===");
		taskDao.deleteById(newTask.getId());
		System.out.println("Deleção de teste concluída!");
		
		DbConn.closeConnection();
	}
}